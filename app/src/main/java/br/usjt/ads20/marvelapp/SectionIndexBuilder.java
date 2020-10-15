package br.usjt.ads20.marvelapp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeSet;

import br.usjt.ads20.marvelapp.model.MarvelCharacter;

public class SectionIndexBuilder {

    public static Object[] buildSectionHeaders(MarvelCharacter[] characters) {
        ArrayList<String> results = new ArrayList<>();

        TreeSet<String> used = new TreeSet<>();
        if (characters != null) {
            for (MarvelCharacter character : characters) {
                String letter = character.getName().substring(0, 1);
                if (!used.contains(letter)) results.add(character.getName().substring(0, 1));
                used.add(letter);
            }
        }
        return results.toArray(new Object[0]);
    }

    public static Hashtable<Integer, Integer> buildPositionForSectionMap(MarvelCharacter[] characters) {
        Hashtable<Integer, Integer> results = new Hashtable<>();
        TreeSet<String> used = new TreeSet<>();
        int section = -1;

        if (characters != null) {
            for (int i = 0; i < characters.length; i++) {
                String letter = characters[i].getName().substring(0, 1);
                if (!used.contains(letter)) {
                    section++;
                    used.add(letter);
                    results.put(section, i);
                }
            }
        }

        return results;
    }

    public static Hashtable<Integer, Integer> buildSectionForPositionMap(MarvelCharacter[] characters) {
        Hashtable<Integer, Integer> results = new Hashtable<>();
        TreeSet<String> used = new TreeSet<>();
        int section = -1;

        if (characters != null) {
            for (int i = 0; i < characters.length; i++) {
                String letter = characters[i].getName().substring(0, 1);
                if (!used.contains(letter)) {
                    section++;
                    used.add(letter);
                }
                results.put(i, section);
            }
        }

        return results;
    }
}
