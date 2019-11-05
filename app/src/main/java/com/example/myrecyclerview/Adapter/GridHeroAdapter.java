package com.example.myrecyclerview.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myrecyclerview.R;
import com.example.myrecyclerview.model.Hero;

import java.util.ArrayList;

public class GridHeroAdapter extends RecyclerView.Adapter<GridHeroAdapter.GridViewHolder> {
    private ArrayList<Hero>listHero;

    public GridHeroAdapter(ArrayList<Hero>list){
        this.listHero = list;
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public GridHeroAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_hero,parent,false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridHeroAdapter.GridViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(listHero.get(position).getPhoto())
                .apply(new RequestOptions().override(350,550))
                .into(holder.imgPhoto);

    }

    @Override
    public int getItemCount() {
        return listHero.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;

        GridViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(Hero dataHero2);
    }
}
