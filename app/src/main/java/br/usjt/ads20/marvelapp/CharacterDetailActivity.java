package br.usjt.ads20.marvelapp;

import androidx.appcompat.app.AppCompatActivity;

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

import br.usjt.ads20.marvelapp.model.CharacterNetwork;
import br.usjt.ads20.marvelapp.model.MarvelCharacter;

public class CharacterDetailActivity extends AppCompatActivity {
    private TextView name, category, popularity, description;
    private ImageView backdrop;
    private ProgressBar progressBar;
    private String imgUrl = "http://i.annihil.us/u/prod/marvel/i/mg/";
    private MarvelCharacter character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        backdrop = (ImageView)findViewById(R.id.backdropView);

        name = (TextView)findViewById(R.id.txtName);
        description = (TextView)findViewById(R.id.txtDescription);

        Intent intent = getIntent();

        character = (MarvelCharacter)intent.getSerializableExtra(ListCharactersActivity.CHARACTER);
        name.setText(character.getName());
        description.setText(character.getDescription());
        progressBar = (ProgressBar)findViewById(R.id.progressBarDetail);
        if (CharacterNetwork.isConnected(this)) {
            progressBar.setVisibility(View.VISIBLE);
            new DownloadBackdrop().execute(character.getBackdropPath());
        } else {
            String msg = this.getResources().getString(R.string.networkError);
            Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private class DownloadBackdrop extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap img = null;
            try {
                img = CharacterNetwork.searchImages(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return img;
        }

        protected void onPostExecute(Bitmap img) {
            backdrop.setImageBitmap(img);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}