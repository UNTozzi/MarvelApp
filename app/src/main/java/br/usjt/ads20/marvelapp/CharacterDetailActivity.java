package br.usjt.ads20.marvelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import br.usjt.ads20.marvelapp.model.MarvelCharacter;

public class CharacterDetailActivity extends AppCompatActivity {
    private TextView name, category, popularity, description;
    private ImageView backdrop;
    private MarvelCharacter character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        name = (TextView)findViewById(R.id.txtName);
        description = (TextView)findViewById(R.id.txtDescription);
        category = (TextView)findViewById(R.id.txtCategory);
        popularity = (TextView)findViewById(R.id.txtPopularity);

        Intent intent = getIntent();

        character = (MarvelCharacter)intent.getSerializableExtra(ListCharactersActivity.CHARACTER);
        name.setText(character.getName());
        description.setText(character.getDescription());
        category.setText(character.getCategory().name());
        popularity.setText(String.format("%.1f", character.getPopularity()));

    }
}