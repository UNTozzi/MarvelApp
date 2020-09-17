package br.usjt.ads20.marvelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CharacterDetailActivity extends AppCompatActivity {
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name = (TextView)findViewById(R.id.selected_character);
        Intent intent = getIntent();
        name.setText(intent.getStringExtra(ListCharactersActivity.DESCRIPTION));
    }
}