package com.example.barhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private MyNotesAdapter mAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Cocktail> notesArrayList;
    private ArrayList<Cocktail> tempArrayList;
    private TextView noNotesAvailable;

    private EditText titleInput;

    CardView toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAll();

        searchFunc();

    }

    private void initAll() {
        Paper.init(MainActivity.this);
        notesArrayList = new ArrayList<Cocktail>();
        tempArrayList = new ArrayList<Cocktail>();
        recyclerView = findViewById(R.id.cocktails_list);
        noNotesAvailable = findViewById(R.id.no_notes_tv);
        titleInput = findViewById(R.id.name_input);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.drawable.bottom_corer_round);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //clear the list before storing new data
        tempArrayList.clear();


        notesArrayList = Paper.book().read("cocktails_list", new ArrayList<>());
        if (notesArrayList.isEmpty()) {
            notesArrayList.add(new Cocktail(1, "Classic Old Fashioned", false, R.drawable.template,"An old fashioned is a classic whiskey cocktail with bitters, simple syrup, and fruit. Experts believe the drink is called the \"old fashioned\" because it's one of the first widely known cocktails ever. The name comes from people ordering the drink the \"old-fashioned way. ","Simple syrup, Water, Bitters, Ice, Bourbon, Garnishes"));
            notesArrayList.add(new Cocktail(2, "Negroni Cocktail", false, R.drawable.nagroni,"This Negroni cocktail will knock your socks off. I believe this drink is Italian and is generally sipped while eating marinated olives. Be sure to use good quality gin!","¾ fluid ounce campari, ¾ fluid ounce gin, ¾ fluid ounce sweet vermouth, 2 fluid ounces carbonated water, 1 wedge lemon, for garnish"));
            notesArrayList.add(new Cocktail(3, "Classic Daiquiri", false, R.drawable.daiquiri,"Enjoy this classic daiquiri frozen or on the rocks.","1 cup ice cubes, 1 ½ fluid ounces light rum, 1 fluid ounce lime juice, 1 fluid ounce triple sec, 1 teaspoon white sugar, 1 lime wedge, 2 tablespoons white sugar, or as needed"));
            notesArrayList.add(new Cocktail(4, "Martini", false, R.drawable.martini,"This is the perfect cosmopolitan drink - very dry, and very smooth.","2 ½ fluid ounces gin, ½ fluid ounce dry vermouth, 1 pitted green olive, 1 cup ice"));
            notesArrayList.add(new Cocktail(5, "The Perfect Margarita", false, R.drawable.the_perfect_margarita,"This is the best margarita recipe I know! It has the perfect ratio of white tequila, high-quality triple sec, and freshly squeezed lime juice for the best-tasting margarita every time. ","1 wedge lime, 1 teaspoon coarse sea salt, or as needed, 1 large ice cube, 1 cup ice cubes, or as needed, 2 fluid ounces white tequila, 1 ½ fluid ounces triple sec, 1 fluid ounce freshly squeezed lime juice, 1 slice lime"));
            notesArrayList.add(new Cocktail(6, "Espresso Martini Cocktail", false, R.drawable.espresso_martini_cocktail,"This espresso martini is a sweet and delicious after-dinner drink made with espresso, coffee liqueur, vodka, and white creme de cacao for a coffee lovers dream cocktail. ","1 ½ fluid ounces vodka, 1 ½ fluid ounces coffee-flavored liqueur (such as Kahlua), 1 fluid ounce white creme de cacao, 1 fluid ounce brewed espresso,1 cup ice"));
            notesArrayList.add(new Cocktail(7, "Classic Whiskey Sour", false, R.drawable.classic_whiskey_sour,"A classic lemon whiskey sour should be made from scratch, or it ain't a whiskey sour. For a cleaner, classier-looking drink, strain the lemon juice to remove seeds and pulp. This cocktail can be served in martini glasses or tumblers full of ice.","5 fluid ounces whiskey, 2 fluid ounces fresh lemon juice, 1 fluid ounce simple syrup, 1 cup ice cubes, or as needed, 3 maraschino cherries for garnish"));
            notesArrayList.add(new Cocktail(8, "Manhattan Cocktail", false, R.drawable.manhattan_cocktail,"An easy recipe for a Manhattan — a classic cocktail that is believed to date back to the late 1800s. It's made with a bracing mixture of bourbon or rye whiskey mixed with sweet vermouth and a dash of aromatic bitters.","2 fluid ounces rye whiskey, ½ fluid ounce sweet vermouth, 1 dash Angostura bitters, 1 cup ice cubes, 1 maraschino cherry"));
            notesArrayList.add(new Cocktail(9, "Aperol Spritz", false, R.drawable.aperol_spritz,"Aperol spritz is a gloriously refreshing cocktail that I discovered in Berlin on a tour of Eastern European cities. Brings back wonderful memories. Serve in a wine glass. Pretty and tasty!","4 ice cubes, or as desired, 2 fluid ounces Prosecco, 1 splash club soda, 1 (1.5 fluid ounce shot Aperol (or other bitter orange aperitif), 1 slice orange"));
            notesArrayList.add(new Cocktail(10, "The Real Mojito", false, R.drawable.mojito,"This is an authentic recipe for mojito. I sized the recipe for one serving, but you can adjust it accordingly and make a pitcher full. It's a very refreshing drink for hot summer days. Be careful when drinking it, however. If you make a pitcher you might be tempted to drink the whole thing yourself, and you just might find yourself talking Spanish in no time!","10 fresh mint leaves, ½ medium lime, cut into 3 wedges, divided, 2 tablespoons white sugar, or to taste, 1 cup ice cubes, or as needed, 1 ½ fluid ounces white rum,½ cup club soda, or as needed"));
            notesArrayList.add(new Cocktail(11, "Classic Bloody Mary", false, R.drawable.bloody_mary,"A Bloody Mary is easy to make from scratch with vodka, tomato juice, and a few other simple ingredients. The spicy, salty, and savory taste of this classic cocktail makes it perfect for brunch or other afternoon gatherings.","1 teaspoon sea salt, or as needed, ice cubes, as needed, ¾ cup spicy tomato-vegetable juice cocktail (such as V8®), 1 (1.5 fluid ounce) jigger vodka, 2 dashes Worcestershire sauce, 1 dash hot pepper sauce (such as Tabasco®), salt and ground black pepper to taste, 1 stalk celery, 2 garlic-stuffed green olives, threaded onto a toothpic"));
            Paper.book().write("cocktails_list", notesArrayList);
            setData();
        } else {

            setData();
        }

    }


    private void setData() {
        Log.d("tEstarraylist", notesArrayList.toString());
        if (notesArrayList.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            noNotesAvailable.setVisibility(View.GONE);

            mAdapter = new MyNotesAdapter(notesArrayList, MainActivity.this);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(mLayoutManager1);
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } else {
            noNotesAvailable.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    private void searchFunc() {
        titleInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() == 0) {

                    if (notesArrayList.size() != 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        noNotesAvailable.setVisibility(View.GONE);
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        noNotesAvailable.setVisibility(View.VISIBLE);
                    }


                    mAdapter = new MyNotesAdapter(notesArrayList, MainActivity.this);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                } else {
                    ArrayList<Cocktail> clone = new ArrayList<>();
                    for (Cocktail element : notesArrayList) {
                        if (element.getTitle().toLowerCase().contains(s.toString().toLowerCase())) {
                            clone.add(element);
                        }
                    }

                    if (clone.size() != 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        noNotesAvailable.setVisibility(View.GONE);
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        noNotesAvailable.setVisibility(View.VISIBLE);
                    }

                    mAdapter = new MyNotesAdapter(clone, MainActivity.this);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}