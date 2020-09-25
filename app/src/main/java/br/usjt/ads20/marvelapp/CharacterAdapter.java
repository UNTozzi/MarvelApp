package br.usjt.ads20.marvelapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import br.usjt.ads20.marvelapp.model.Character;

public class CharacterAdapter extends BaseAdapter {
    private Character[] characters;
    private Context context;

    public CharacterAdapter(Context context, Character[] characters) {
        this.characters = characters;
        this.context = context;
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

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.character_row, viewGroup, false);

            ImageView characterPoster = (ImageView) view.findViewById(R.id.character_poster);
            TextView characterName = (TextView) view.findViewById(R.id.character_name);
            TextView characterDetail = (TextView) view.findViewById(R.id.character_detail);

            view.setTag(new ViewHolder(characterPoster, characterName, characterDetail));
        }

        Drawable drawable = Util.getDrawable(context, characters[index].getPosterPath().substring(0, characters[index].getPosterPath().length()-4).toLowerCase());

        ViewHolder holder = (ViewHolder)view.getTag();

        holder.getCharacterPoster().setImageDrawable(drawable);
        holder.getCharacterName().setText(characters[index].getName());
        holder.getCharacterDetail().setText(String.format("%s - Directed by: %s", characters[index].getCategory(), characters[index].getDescription()));


        return view;
    }
}