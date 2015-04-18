package uber.uberchallenge;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by calber on 12/4/15.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private List<Image> products;

    public ImageAdapter(List<Image> products) {
        this.products = products;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View viewholder;
        public ViewHolder(View v) {
            super(v);
            viewholder = v;
        }

        public void setName(Image product) {
            ((TextView) viewholder.findViewById(R.id.name)).setText(product.name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.imagecard, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.setName(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
