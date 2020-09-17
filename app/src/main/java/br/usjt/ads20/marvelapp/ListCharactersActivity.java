package br.usjt.ads20.marvelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListCharactersActivity extends AppCompatActivity {
    public static final String DESCRIPTION = "br.usjt.ads20.marvelapp.description";
    ArrayList<String> list;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_characters);
        activity = this;
        final Intent intent = getIntent();
        String key = intent.getStringExtra(MainActivity.NAME);
        list = searchCharacters(key);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Intent itemClickedIntent = new Intent(activity, CharacterDetailActivity.class);
                itemClickedIntent.putExtra(DESCRIPTION, list.get(index));
                startActivity(itemClickedIntent);
            }
        });
    }

    private ArrayList<String> searchCharacters(String key) {
        ArrayList<String> list = generateCharactersList();

        if (key == null || key.length() == 0) return list;
        else {
            ArrayList<String> filter = new ArrayList<>();
            for (String name : list) {
                if (name.toUpperCase().contains(key.toUpperCase())) {
                    filter.add(name);
                }
            }
            return filter;
        }
    }

    private ArrayList<String> generateCharactersList() {
        ArrayList<String> list = new ArrayList<>();

        list.add("Captain America");
        list.add("Captain Marvel");
        list.add("Iron Man");
        list.add("Hulk");
        list.add("Thor ");
        list.add("The Avengers");
        list.add("Guardians of the Galaxy");
        list.add("Ant-Man");
        list.add("Spider-Man");
        list.add("Doctor Strange");
        list.add("Black Panther");

        return list;
    }
}