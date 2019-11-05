package com.example.myrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myrecyclerview.Adapter.CardViewAdapter;
import com.example.myrecyclerview.Adapter.GridHeroAdapter;
import com.example.myrecyclerview.Adapter.ListHeroAdapter;
import com.example.myrecyclerview.model.Hero;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvHeroes;
    private ArrayList<Hero> list = new ArrayList<>();

    private String title = "mode list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rvHeroes = findViewById(R.id.rv_heroes);
        rvHeroes.setHasFixedSize(true);


        list.addAll(getListHeroes());
        showRecyclerList();
        showRecyclerGrid();
        showRecyclerCard();

        list.addAll(getListHeroes());

        setTitleBar(title);
    }


    private void setTitleBar(String title) {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }


    public ArrayList<Hero> getListHeroes() {
        String[] dataName = getResources().getStringArray(R.array.data_name);
        String[] dataDescription = getResources().getStringArray(R.array.data_description);
        String[] dataPhoto = getResources().getStringArray(R.array.data_photo);

        ArrayList<Hero> listHero = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            Hero hero = new Hero();
            hero.setName(dataName[i]);
            hero.setDescription(dataDescription[i]);
            hero.setPhoto(dataPhoto[i]);

            listHero.add(hero);
        }
        return listHero;
    }

    private void showRecyclerList(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter listHeroAdapter = new ListHeroAdapter(list);
        rvHeroes.setAdapter(listHeroAdapter);

        listHeroAdapter.setOnItemClickCallback(new ListHeroAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Hero dataHero) {
                showSelectedHero(dataHero);
            }
        });

    }


    private void showRecyclerGrid() {
        rvHeroes.setLayoutManager(new GridLayoutManager(this,3));
        GridHeroAdapter gridHeroAdapter = new GridHeroAdapter(list);
        rvHeroes.setAdapter(gridHeroAdapter);


        gridHeroAdapter.setOnItemClickCallback(new GridHeroAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Hero dataHero) {
                showSelectedHero(dataHero);
            }
        });

    }

    private void showRecyclerCard() {
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        CardViewAdapter cardViewAdapter = new CardViewAdapter(list);
        rvHeroes.setAdapter(cardViewAdapter);


        cardViewAdapter.setOnItemClickCallback(new CardViewAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Hero dataHero) {
                showSelectedHero(dataHero);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void setMode(int itemId) {
        switch (itemId) {
            case R.id.action_list:
                title = "mode list";
                showRecyclerList();
                break;

            case R.id.action_grid:
                title = "mode grid";
                showRecyclerGrid();
                break;

            case R.id.action_cardview:
                title = "mode cardview";
                showRecyclerCard();
                break;
        }
        setTitleBar(title);
    }

    private void showSelectedHero(Hero hero){
        Toast.makeText(this,"kamu memilih "+hero.getName(),Toast.LENGTH_LONG).show();
    }
}
