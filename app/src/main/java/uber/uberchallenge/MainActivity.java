package uber.uberchallenge;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private ImageAdapter imageAdapter;
    private List<String > history;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        history = new ArrayList<>();

        RestAdapter restAdapter = new RestAdapter.Builder().setErrorHandler(new MyErrorHandler())
                .setEndpoint("https://ajax.googleapis.com/ajax/services/search/").build();

        final Api api = restAdapter.create(Api.class);

        mRecyclerView = (RecyclerView) findViewById(R.id.imagegrid);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        imageAdapter = new ImageAdapter(api);
        ((RecyclerView) findViewById(R.id.imagegrid)).setAdapter(imageAdapter);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, history);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.search);
        textView.setAdapter(arrayAdapter);

        findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = ((AutoCompleteTextView) findViewById(R.id.search)).getText().toString();
                imageAdapter.loadData(query);
                history.add(query);
            }
        });
    }

    private Map<String, String> getOptionMapFor(String query, int page) {
        Map map = new HashMap();

        map.put("rsz", "8");
        map.put("start", String.valueOf(page));
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
}
