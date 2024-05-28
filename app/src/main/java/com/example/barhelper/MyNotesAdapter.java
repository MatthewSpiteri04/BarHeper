package com.example.barhelper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.paperdb.Paper;

public class MyNotesAdapter extends RecyclerView.Adapter<MyNotesAdapter.MyViewHolder> {

    List<Cocktail> cocktailList;
    Context context;

    public MyNotesAdapter(List<Cocktail> usersList, Context context) {
        this.cocktailList = usersList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cocktail, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Cocktail cocktail = cocktailList.get(position);

        holder.title.setText(cocktail.getTitle());
        holder.image.setImageResource(cocktail.getPhotoId());
        if (cocktail.isFavourite) {
            holder.favouriteImg.setImageResource(R.drawable.ic_heart_selected);
        } else {
            holder.favouriteImg.setImageResource(R.drawable.ic_heart_not_selected);
        }


        holder.favouriteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cocktail.isFavourite) {
                    holder.favouriteImg.setImageResource(R.drawable.ic_heart_not_selected);
                    cocktailList.get(position).setFavourite(false);
                    Paper.book().write("cocktails_list", cocktailList);
                } else {
                    holder.favouriteImg.setImageResource(R.drawable.ic_heart_selected);
                    cocktailList.get(position).setFavourite(true);
                    Paper.book().write("cocktails_list", cocktailList);
                }
            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsScreen.class);
                intent.putExtra("pos", position);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return cocktailList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout layout;
        TextView title;
        ImageView image, favouriteImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.relative_layout);
            title = itemView.findViewById(R.id.title_tv);
            image = itemView.findViewById(R.id.image);
            favouriteImg = itemView.findViewById(R.id.favoutite_image);
        }
    }
}
