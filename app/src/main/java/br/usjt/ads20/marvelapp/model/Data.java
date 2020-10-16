package br.usjt.ads20.marvelapp.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {
    private static ArrayList<MarvelCharacter> characters;
    private static ArrayList<Poster> images;

    public static ArrayList<Poster> getImages() {
        return images;
    }

    public static void setImages(ArrayList<Poster> images) {
        Data.images = images;
    }

    public static void setCharacters(ArrayList<MarvelCharacter> pCharacters){
        characters = pCharacters;
    }

    public static MarvelCharacter[] searchCharacters(String key) {
        ArrayList<MarvelCharacter> list = characters;

        if (key == null || key.length() == 0) {
            return list.toArray(new MarvelCharacter[0]);
        }
        else {
            ArrayList<MarvelCharacter> filter = new ArrayList<>();
            for (MarvelCharacter character : list) {
                String name = character.getName();
                if (name.toUpperCase().contains(key.toUpperCase())) {
                    filter.add(character);
                }
            }
            return filter.toArray(new MarvelCharacter[0]);
        }
    }

    public static Poster[] searchPosters(String key){
        ArrayList<Poster> list = images;
        ArrayList<Poster> filter;
        Poster[] posters;
        if(key == null || key.length() == 0){
            filter = list;
        } else {
            filter = new ArrayList<>();
            for(Poster poster: list){
                String name = poster.getTitle();
                if(name.toUpperCase().contains(key.toUpperCase())){
                    filter.add(poster);
                }
            }
        }
        posters = filter.toArray(new Poster[0]);
        Arrays.sort(posters);
        return posters;
    }

}