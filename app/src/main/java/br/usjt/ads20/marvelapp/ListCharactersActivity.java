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

import br.usjt.ads20.marvelapp.model.Data;
import br.usjt.ads20.marvelapp.model.MarvelCharacter;
import br.usjt.ads20.marvelapp.model.Poster;

import static br.usjt.ads20.marvelapp.model.Data.searchCharacters;

public class ListCharactersActivity extends AppCompatActivity {
    public static final String CHARACTER = "br.usjt.ads20.marvelapp.description";

    MarvelCharacter[] charactersList;
    Poster[] posters;

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_characters);
        activity = this;

        final Intent intent = getIntent();

        String key = intent.getStringExtra(MainActivity.NAME);

        ArrayList<MarvelCharacter> characters = (ArrayList<MarvelCharacter>) intent.getSerializableExtra(MainActivity.CHARACTERS);
        Data.setCharacters(characters);

        charactersList = searchCharacters(key);
        posters = Data.searchPosters(key);

        BaseAdapter adapter = new CharacterAdapter(this, charactersList, posters);

        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Intent itemClickedIntent = new Intent(activity, CharacterDetailActivity.class);
                itemClickedIntent.putExtra(CHARACTER, charactersList[index]);
                startActivity(itemClickedIntent);
            }
        });
    }
}