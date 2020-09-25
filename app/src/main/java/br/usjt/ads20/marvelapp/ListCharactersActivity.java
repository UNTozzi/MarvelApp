package br.usjt.ads20.marvelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import br.usjt.ads20.marvelapp.model.Character;

import static br.usjt.ads20.marvelapp.model.Data.searchCharacters;

public class ListCharactersActivity extends AppCompatActivity {
    public static final String DESCRIPTION = "br.usjt.ads20.marvelapp.description";
    Character[] characters;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_characters);
        activity = this;
        final Intent intent = getIntent();
        String key = intent.getStringExtra(MainActivity.NAME);
        characters = searchCharacters(key);
        BaseAdapter adapter = new CharacterAdapter(this, characters);
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Intent itemClickedIntent = new Intent(activity, CharacterDetailActivity.class);
                itemClickedIntent.putExtra(DESCRIPTION, characters[index].getName());
                startActivity(itemClickedIntent);
            }
        });
    }
}