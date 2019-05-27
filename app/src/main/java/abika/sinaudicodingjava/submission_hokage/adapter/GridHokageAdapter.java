package abika.sinaudicodingjava.submission_hokage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import abika.sinaudicodingjava.submission_hokage.R;
import abika.sinaudicodingjava.submission_hokage.model.Hokage;



public class GridHokageAdapter extends RecyclerView.Adapter<GridHokageAdapter.GridViewHolder> {
    private Context context;
    private ArrayList<Hokage> listHokage;

    public ArrayList<Hokage> getListHokage() {
        return listHokage;
    }

    public void setListHokage(ArrayList<Hokage> listHokage) {
        this.listHokage = listHokage;
    }

    public GridHokageAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_hokage, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        Glide.with(context)
                .load(getListHokage().get(position).getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);

    }

    @Override
    public int getItemCount() {
        return getListHokage().size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;

        public GridViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_grid_photo);
        }
    }
}
