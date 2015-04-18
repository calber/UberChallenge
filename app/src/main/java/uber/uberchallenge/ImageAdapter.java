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
import models.ResponseData;
import models.Result;
import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by calber on 12/4/15.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private static final int PAGESIZE = 5;
    private Api api;
    private List<Result> results;
    int currentpage = 0, pages = 0;
    String query;

    public ImageAdapter(Api api) {
        this.api = api;
        results = new ArrayList<>();
    }

    private void addData(int page) {
        callApi(query, page);
    }

    public void loadData(String query) {
        this.query = query;
        callApi(query, 0);
    }

    private void callApi(String query, int page) {
        Log.d("TAG","requesting:" + page + " " + query);
        api.getImages(getOptionMapFor(query, page), new Callback<Response>() {
            @Override
            public void success(Response response, retrofit.client.Response r) {
                try {
                    results.addAll(response.getResponseData().getResults());
                    currentpage = response.getResponseData().getCursor().getCurrentPageIndex();
                    pages = response.getResponseData().getCursor().getPages().size();
                    notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

    private Map<String, String> getOptionMapFor(String query, int page) {
        Map map = new HashMap();

        map.put("rsz", String.valueOf(PAGESIZE));
        map.put("start", String.valueOf(page));
        map.put("v", "1.0");
        map.put("q", query);

        return map;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
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
        Log.d("TAG", String.format("page %d, tot %d, pos %d", currentpage, pages, position));
        if (position % PAGESIZE > PAGESIZE - 2 && currentpage < pages - 1) {
            // ask for more
            Log.d("TAG", "adding 1 page");
            addData(++currentpage);
        }
        viewHolder.load(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
