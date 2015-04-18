package uber.uberchallenge;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.GridLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import models.Response;
import models.Result;
import retrofit.Callback;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;


public class MainActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.imagegrid);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new ImageAdapter();
        ((RecyclerView) findViewById(R.id.imagegrid)).setAdapter(adapter);

        RestAdapter restAdapter = new RestAdapter.Builder().setErrorHandler(new MyErrorHandler()).setEndpoint("https://ajax.googleapis" +
                ".com/ajax/services/search/").build();

        Api api = restAdapter.create(Api.class);

        api.getImages(getOptionMapFor("query"), new Callback<Response>() {
            @Override
            public void success(Response response, retrofit.client.Response response2) {

                adapter.setData(response.getResponseData().getResults());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private Map<String, String> getOptionMapFor(String query) {
        Map map = new HashMap();

        map.put("rsz", "8");
        map.put("v", "1.0");
        map.put("q", query);

        return map;
    }

    private static class MyErrorHandler implements ErrorHandler {
        @Override
        public Throwable handleError(RetrofitError cause) {
            Log.d("TAG", "Error");
            return null;
        }
    }

    private class LoadImage implements Func1<Result, Observable<Result>> {
        @Override
        public Observable<Result> call(final Result result) {
            return Observable.create(new Observable.OnSubscribe<Result>() {
                @Override
                public void call(Subscriber<? super Result> subscriber) {
                    try {
                        HttpURLConnection connection = (HttpURLConnection) new URL(result.getTbUrl()).openConnection();
                        connection.connect();
                        InputStream input = connection.getInputStream();
                        result.setBitmap(BitmapFactory.decodeStream(input));
                        subscriber.onNext(result);
                        subscriber.onCompleted();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
