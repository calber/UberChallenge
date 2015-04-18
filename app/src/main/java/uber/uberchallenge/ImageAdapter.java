package uber.uberchallenge;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applidium.shutterbug.FetchableImageView;

import java.util.ArrayList;
import java.util.List;

import models.Result;

/**
 * Created by calber on 12/4/15.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private List<Result> results;

    public ImageAdapter() {
        this.results = new ArrayList<>();
    }

    public void setData(List<Result> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View viewholder;
        public ViewHolder(View v) {
            super(v);
            viewholder = v;
        }

        public void load(Result result) {
            ((TextView) viewholder.findViewById(R.id.name)).setText(result.getTitle());
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
        viewHolder.load(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
