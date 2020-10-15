package br.usjt.ads20.marvelapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.Hashtable;

import br.usjt.ads20.marvelapp.model.MarvelCharacter;

public class CharacterAdapter extends BaseAdapter implements SectionIndexer {
    private MarvelCharacter[] characters;
    private Context context;
    private Object[] sectionHeaders;
    private Hashtable<Integer, Integer> sectionForPositionMap;
    private Hashtable<Integer, Integer> positionToSectionMap;

    public CharacterAdapter(Context context, MarvelCharacter[] characters) {
        this.characters = characters;
        this.context = context;
        sectionHeaders = SectionIndexBuilder.buildSectionHeaders(characters);
        positionToSectionMap = SectionIndexBuilder.buildPositionForSectionMap(characters);
        sectionForPositionMap = SectionIndexBuilder.buildSectionForPositionMap(characters);

    }

    @Override
    public int getCount() {
        return characters.length;
    }

    @Override
    public Object getItem(int index) {
        if (index >= 0 && index < characters.length) return characters[index];
        return null;
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.character_row, viewGroup, false);

            ImageView characterPoster = (ImageView) view.findViewById(R.id.character_poster);
            TextView characterName = (TextView) view.findViewById(R.id.character_name);
            TextView categoryDetail = (TextView) view.findViewById(R.id.categoryDetail);
            TextView popularityDetail = (TextView) view.findViewById(R.id.popularityDetail);
            TextView descriptionDetail = (TextView) view.findViewById(R.id.descriptionDetail);
            ViewHolder viewHolder = new ViewHolder(characterPoster, characterName, categoryDetail, popularityDetail, descriptionDetail);
            view.setTag(viewHolder);
        }

        Drawable drawable = Util.getDrawable(context, characters[index].getPosterPath().substring(0, characters[index].getPosterPath().length()-4).toLowerCase());

        ViewHolder viewHolder = (ViewHolder)view.getTag();
        viewHolder.getCharacterPoster().setImageDrawable(drawable);
        viewHolder.getCharacterName().setText(characters[index].getName());
        //Locale locale = new Locale("pt", "BR");
        String lblCat = context.getResources().getString(R.string.lblCategory);
        String lblDes = context.getResources().getString(R.string.lblDescription);
        String lblPop = context.getResources().getString(R.string.lblPopularity);
        viewHolder.getCategoryDetail().setText(String.format("%s: %s", lblCat, characters[index].getCategory().name()));
        viewHolder.getDescriptionDetail().setText(String.format("%s: %s", lblDes, characters[index].getDescription()));
        viewHolder.getPopularityDetail().setText(String.format("%s: %.1f", lblPop, characters[index].getPopularity()));

        return view;
    }


    @Override
    public Object[] getSections() {
        return sectionHeaders;
    }

    @Override
    public int getPositionForSection(int i) {
        return positionToSectionMap.get(i).intValue();
    }

    @Override
    public int getSectionForPosition(int i) {
        return sectionForPositionMap.get(i).intValue();
    }
}