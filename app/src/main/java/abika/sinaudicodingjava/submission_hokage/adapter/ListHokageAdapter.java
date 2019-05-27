package abika.sinaudicodingjava.submission_hokage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import abika.sinaudicodingjava.submission_hokage.R;
import abika.sinaudicodingjava.submission_hokage.model.Hokage;

public class ListHokageAdapter extends RecyclerView.Adapter<ListHokageAdapter.CategoryViewHolder> {
    private Context context;
    private ArrayList<Hokage> listHokage;

    public ListHokageAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Hokage> getListHokage() {
        return listHokage;
    }

    public void setListHokage(ArrayList<Hokage> listHokage) {
        this.listHokage = listHokage;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_hokage, viewGroup, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int position) {
        categoryViewHolder.tvName.setText(getListHokage().get(position).getName());
        categoryViewHolder.tvTitle.setText(getListHokage().get(position).getTitle());
        Glide.with(context)
                .load(getListHokage().get(position).getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(categoryViewHolder.imgPhoto);

    }

    @Override
    public int getItemCount() {
        return getListHokage().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvTitle;
        ImageView imgPhoto;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_list_name);
            tvTitle = itemView.findViewById(R.id.tv_list_title);
            imgPhoto = itemView.findViewById(R.id.img_list_photo);
        }
    }
}
