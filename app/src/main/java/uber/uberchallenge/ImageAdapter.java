package uber.uberchallenge;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applidium.shutterbug.FetchableImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Response;
import models.Result;
import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by calber on 12/4/15.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    static final int PAGESIZE = 8;

    Api api;
    List<Result> results;
    int resultcountMax = 0;
    int start = 0;
    String query;

    public ImageAdapter(Api mApi) {
        api = mApi;
        results = new ArrayList<>();
    }

    public void loadDataForQuery(String mQuery) {
        results = new ArrayList<>();
        query = mQuery;
        start = 0;

        callApi(mQuery, start);
    }


    private void addData() {
        start += PAGESIZE;
        callApi(query, start);
    }

    private void callApi(String query, int page) {
        api.getImages(getOptionMapFor(query, page), new Callback<Response>() {
            @Override
            public void success(Response response, retrofit.client.Response r) {
                try {
                    results.addAll(response.getResponseData().getResults());
                    int size = response.getResponseData().getCursor().getPages().size();
                    resultcountMax = Integer.parseInt(response.getResponseData().getCursor().getPages().get(size-1).getStart());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    notifyDataSetChanged();
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

    private Map<String, String> getOptionMapFor(String query, int page) {
        Map<String,String> map = new HashMap<>();

        map.put("rsz", String.valueOf(PAGESIZE));
        map.put("start", String.valueOf(page));
        map.put("imgsz","small");
        map.put("v", "1.0");
        map.put("q", query);

        return map;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View viewholder;

        public ViewHolder(View v) {
            super(v);
            viewholder = v;
        }

        public void load(Result result) {
            ((TextView) viewholder.findViewById(R.id.name)).setText(Html.fromHtml(result.getTitle()));
            ((FetchableImageView) viewholder.findViewById(R.id.image)).setImage(result.getUrl());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.imagecard, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if (resultcountMax > results.size() && position > results.size() - 2) {
            addData();
        }
        viewHolder.load(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
