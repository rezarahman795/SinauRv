package com.example.myrecyclerview.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myrecyclerview.R;
import com.example.myrecyclerview.model.Hero;

import java.util.ArrayList;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> {
    private ArrayList<Hero>cardHero;
    public CardViewAdapter (ArrayList<Hero>list){
        this.cardHero = list;
    }

    @NonNull
    @Override
    public CardViewAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_hero,parent,false);
       return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewAdapter.CardViewHolder holder, int position) {

        Hero hero = cardHero.get(position);

        Glide.with(holder.itemView.getContext())
                .load(cardHero.get(position).getPhoto())
                .apply(new RequestOptions().override(350,550))
                .into(holder.imgPhoto);

        holder.tvName.setText(hero.getName());
        holder.tvDesc.setText(hero.getDescription());

        holder.btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(),"Favourite "+
                        cardHero.get(holder.getAdapterPosition()).getName(),Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast.makeText(holder.itemView.getContext(),"Share "+
                    cardHero.get(holder.getAdapterPosition()).getName(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
       return cardHero.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName,tvDesc;
        Button btnFav,btnShare;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            btnFav = itemView.findViewById(R.id.btn_set_fav);
            btnShare = itemView.findViewById(R.id.btn_set_share);
        }
    }
}
