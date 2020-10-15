package br.usjt.ads20.marvelapp;

import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    private ImageView characterPoster;
    private TextView characterName, categoryDetail, popularityDetail, descriptionDetail;

    public ViewHolder(ImageView characterPoster, TextView characterName, TextView categoryDetail, TextView popularityDetail, TextView descriptionDetail) {
        this.characterPoster = characterPoster;
        this.characterName = characterName;
        this.categoryDetail = categoryDetail;
        this.popularityDetail = popularityDetail;
        this.descriptionDetail = descriptionDetail;

    }

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

    public TextView getCategoryDetail() {
        return categoryDetail;
    }

    public void setCategoryDetail(TextView categoryDetail) {
        this.categoryDetail = categoryDetail;
    }

    public TextView getPopularityDetail() {
        return popularityDetail;
    }

    public void setPopularityDetail(TextView popularityDetail) {
        this.popularityDetail = popularityDetail;
    }

    public TextView getDescriptionDetail() {
        return descriptionDetail;
    }

    public void setDescriptionDetail(TextView descriptionDetail) {
        this.descriptionDetail = descriptionDetail;
    }
}