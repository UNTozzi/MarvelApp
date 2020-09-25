package br.usjt.ads20.marvelapp;

import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    private ImageView characterPoster;
    private TextView characterName, characterDetail;

    public ImageView getCharacterPoster() {
        return characterPoster;
    }

    public void setCharacterPoster(ImageView characterPoster) {
        this.characterPoster = characterPoster;
    }

    public TextView getCharacterName() {
        return characterName;
    }

    public void setCharacterName(TextView characterName) {
        this.characterName = characterName;
    }

    public TextView getCharacterDetail() {
        return characterDetail;
    }

    public void setCharacterDetail(TextView characterDetail) {
        this.characterDetail = characterDetail;
    }

    public ViewHolder(ImageView characterPoster, TextView characterName, TextView characterDetail) {
        this.characterPoster = characterPoster;
        this.characterName = characterName;
        this.characterDetail = characterDetail;
    }
}