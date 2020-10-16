package br.usjt.ads20.marvelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import br.usjt.ads20.marvelapp.model.Data;
import br.usjt.ads20.marvelapp.model.MarvelCharacter;
import br.usjt.ads20.marvelapp.model.CharacterNetwork;
import br.usjt.ads20.marvelapp.model.Poster;

public class MainActivity extends AppCompatActivity {
    private EditText txtName;
    private ProgressBar progressBar;
    public  static final String NAME = "br.usjt.ads20.marvelapp.name";
    public  static final String CHARACTERS = "br.usjt.ads20.marvelapp.characters";
    private String url = "https://gateway.marvel.com:443/v1/public/characters?";
    private String imgUrl = "http://i.annihil.us/u/prod/marvel/i/mg/";
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = (EditText)findViewById(R.id.search_character);
        progressBar = (ProgressBar)findViewById(R.id.progressBarMain);
        context = this;
    }

    public void searchCharacters(View view) {
        if (CharacterNetwork.isConnected(this)) {
            progressBar.setVisibility(View.VISIBLE);
            new DownloadJsonCharacters().execute(String.format(url));
        } else {
            String msg = this.getResources().getString(R.string.networkError);
            Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private class DownloadJsonCharacters extends AsyncTask<String, Void, ArrayList<MarvelCharacter>> {

        @Override
        protected ArrayList<MarvelCharacter> doInBackground(String... strings) {
            ArrayList<MarvelCharacter> characters = new ArrayList<>();
            ArrayList<Poster> images = new ArrayList<>();
            try {
                characters = CharacterNetwork.searchCharacters(strings[0]);
                for(MarvelCharacter character: characters){
                    String poster = character.getPosterPath();
                    Bitmap img = CharacterNetwork.searchImages(poster);
                    Poster p = new Poster();
                    p.setId(character.getId());
                    p.setTitle(character.getName());
                    p.setPoster(img);
                    images.add(p);
                }
                Data.setImages(images);
            } catch (IOException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return characters;
        }

        protected void onPostExecute(ArrayList<MarvelCharacter> characters){
            Intent intent = new Intent(context, ListCharactersActivity.class);
            String nome = txtName.getText().toString();
            intent.putExtra(NAME, nome);
            intent.putExtra(CHARACTERS, characters);
            progressBar.setVisibility(View.INVISIBLE);
            startActivity(intent);
        }
    }
}