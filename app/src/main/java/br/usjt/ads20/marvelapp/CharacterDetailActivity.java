package br.usjt.ads20.marvelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import br.usjt.ads20.marvelapp.model.CharacterDB;
import br.usjt.ads20.marvelapp.model.CharacterNetwork;
import br.usjt.ads20.marvelapp.model.MarvelCharacter;

public class CharacterDetailActivity extends AppCompatActivity {
    private TextView name, category, popularity, description;
    private ImageView backdrop;
    private ProgressBar progressBar;
    private String imgUrl = "http://i.annihil.us/u/prod/marvel/i/mg/";
    private MarvelCharacter character;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        backdrop = findViewById(R.id.backdropView);

        name = findViewById(R.id.txtName);
        description = findViewById(R.id.txtDescription);

        Intent intent = getIntent();

        context = this;

        character = (MarvelCharacter) intent.getSerializableExtra(ListCharactersActivity.CHARACTER);
        name.setText(character.getName());
        description.setText(character.getDescription());
        progressBar = findViewById(R.id.progressBarDetail);
        if (CharacterNetwork.isConnected(this)) {
            progressBar.setVisibility(View.VISIBLE);
            new DownloadBackdrop().execute(character);
        } else {
            String msg = this.getResources().getString(R.string.networkError);
            Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
            toast.show();
            progressBar.setVisibility(View.VISIBLE);
            new LoadBackdropDB().execute(character);
        }
    }

    private class DownloadBackdrop extends AsyncTask<MarvelCharacter, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(MarvelCharacter... characters) {
            Bitmap img = null;
            try {
                img = CharacterNetwork.searchImages(imgUrl + characters[0].getBackdropPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            CharacterDB db = new CharacterDB(context);
            db.updateBackdrop(characters[0].getId(), img);
            return img;
        }

        protected void onPostExecute(Bitmap img) {
            backdrop.setImageBitmap(img);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private class LoadBackdropDB extends AsyncTask<MarvelCharacter, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(MarvelCharacter... characters) {
            Bitmap img = null;
            CharacterDB db = new CharacterDB(context);
            img = db.searchBackdrop(characters[0].getId());

            return img;
        }

        @Override
        protected void onPostExecute(Bitmap img) {
            if (img != null) {
                backdrop.setImageBitmap(img);
            }
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}