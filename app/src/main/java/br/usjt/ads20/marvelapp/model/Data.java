package br.usjt.ads20.marvelapp.model;

import java.util.ArrayList;

public class Data {
    public static Character[] searchCharacters(String key) {
        ArrayList<Character> list = createCharacter();

        if (key == null || key.length() == 0) {
            return list.toArray(new Character[0]);
        }
        else {
            ArrayList<Character> filter = new ArrayList<>();
            for (Character character : list) {
                String name = character.getName();
                if (name.toUpperCase().contains(key.toUpperCase())) {
                    filter.add(character);
                }
            }
            return filter.toArray(new Character[0]);
        }
    }

    public static ArrayList<String> generateCharactersList() {
        ArrayList<String> list = new ArrayList<>();

        list.add("Aventura: Guerra nas Estrelas (1977)");
        list.add("Fantasia: O Senhor dos Anéis: O Retorno do Rei");
        list.add("Ação: Matrix");
        list.add("Aventura: De Volta para o Futuro");
        list.add("Ficção Científica: Jornada nas Estrelas");
        list.add("Aventura: Os Goonies");
        list.add("Ficção Científica: Blade Runner, o Caçador de Androides");
        list.add("Suspense: Allien");
        list.add("Drama: Platoon");
        list.add("Ação: Os Vingadores");
        list.add("Thriller: Pulp Fiction");
        list.add("Aventura: Os Caçadores da Arca Perdida");
        list.add("Terror: It - A coisa");
        list.add("Terror: Psicose");
        list.add("Comédia: Monty Python em Busca do Cálice Sagrado");
        list.add("Terror: Os Garotos Perdidos");
        list.add("Suspense: Seven, os Sete Pecados Capitais");
        list.add("Ação: Kill Bill");
        list.add("Fantasia: Alice no País das Maravilhas");
        list.add("Anime: Akira");
        list.add("Terror: Hereditário");

        return list;
    }
    
    public static ArrayList<Character> createCharacter() {
        ArrayList<Character> list = new ArrayList<>();
        Character character;

        character = new Character();
        character.setName("Steve Rogers - Captain America");
        character.setDescription("America’s World War II Super-Soldier continues his fight in the present as an Avenger and untiring sentinel of liberty."
        );
        character.setPopularity(847.503);
        character.setPosterPath("captainamericaposter.jpg");
        character.setBackdropPath("captainamericabackdrop.jpg");
        character.setCategory(Category.Hero);
        list.add(character);

        character = new Character();
        character.setName("Thanos");
        character.setDescription("Using the power of the Infinity Stones, Thanos believes he can ultimately save the universe by wiping out half of its population."
        );
        character.setPopularity(702.503);
        character.setPosterPath("thanosposter.jpg");
        character.setBackdropPath("thanosbackdrop.jpg");
        character.setCategory(Category.Villain);
        list.add(character);

        character = new Character();
        character.setName("Carol Danvers - Captain Marvel");
        character.setDescription("Near death, Carol Danvers was transformed into a powerful warrior for the Kree. Now, returning to Earth years later, she must remember her past in order to to prevent a full invasion by shapeshifting aliens, the Skrulls."
        );
        character.setPopularity(999.999);
        character.setPosterPath("captainmarvelposter.jpg");
        character.setBackdropPath("captainmarvelbackdrop.jpg");
        character.setCategory(Category.Hero);
        list.add(character);

        character = new Character();
        character.setName("Wade Wilson - Deadpool");
        character.setDescription("Wade Wilson was born in Canada, but grew up to become the least Canadian person ever. When it comes to the Merc with a Mouth, with great power comes no responsibility."
        );
        character.setPopularity(998.100);
        character.setPosterPath("deadpoolposter.jpg");
        character.setBackdropPath("deadpoolbackdrop.jpg");
        character.setCategory(Category.AntiHero);
        list.add(character);

        character = new Character();
        character.setName("T’Challa - Black Panther");
        character.setDescription("As the king of the African nation of Wakanda, T’Challa protects his people as the latest in a legacy line of Black Panther warriors."
        );
        character.setPopularity(999.999);
        character.setPosterPath("blackpantherposter.jpg");
        character.setBackdropPath("blackpantherbackdrop.jpg");
        character.setCategory(Category.Hero);
        list.add(character);
        
        return list;
    }
}