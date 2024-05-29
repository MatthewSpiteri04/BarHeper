package com.example.barhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import io.paperdb.Paper;

public class DetailsScreen extends AppCompatActivity {


    CardView toolbar;
    ImageView backBtn;

    ImageView mainImage, favImage;
    TextView description, ingredients, title;
    Cocktail cocktail;
    int pos;
    private ArrayList<Cocktail> notesArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);

        initAll();

        pos = getIntent().getIntExtra("pos", 0);
        notesArrayList = Paper.book().read("cocktails_list", new ArrayList<>());

        cocktail = notesArrayList.get(pos);
        if (cocktail != null) {
            title.setText(cocktail.getTitle());
            description.setText(cocktail.getDescription());
            ingredients.setText(cocktail.getIngredients());
            mainImage.setImageResource(cocktail.getPhotoId());

            if (cocktail.isFavourite) {
                favImage.setImageResource(R.drawable.ic_heart_selected);
            } else {
                favImage.setImageResource(R.drawable.ic_heart_not_selected);
            }
        }

        favImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cocktail.isFavourite) {
                    favImage.setImageResource(R.drawable.ic_heart_not_selected);
                    notesArrayList.get(pos).setFavourite(false);
                    Paper.book().write("cocktails_list", notesArrayList);
                } else {
                    favImage.setImageResource(R.drawable.ic_heart_selected);
                    notesArrayList.get(pos).setFavourite(true);
                    Paper.book().write("cocktails_list", notesArrayList);
                }
            }
        });
    }

    private void initAll() {
        toolbar = findViewById(R.id.toolbar);
        backBtn = findViewById(R.id.back_btn);
        toolbar.setBackgroundResource(R.drawable.bottom_corer_round);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        title = findViewById(R.id.heading_text);
        mainImage = findViewById(R.id.image);
        favImage = findViewById(R.id.favoutite_image);
        description = findViewById(R.id.description_tv);
        ingredients = findViewById(R.id.ingredients_tv);
        cocktail = new Cocktail();


    }
}