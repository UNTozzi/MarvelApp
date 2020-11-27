package br.usjt.ads20.marvelapp.model;

import android.provider.BaseColumns;

public final class CharacterContract {
    public static abstract class CharacterEntry implements BaseColumns {
        public static final String TABLE_NAME = "character";
        public static final String COLUMN_NAME_ID_CHARACTER = "idCharacter";
        public static final String COLUMN_NAME_NAME = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_POPULARITY = "popularity";
        public static final String COLUMN_NAME_RELEASE_DATE = "releaseDate";
        public static final String COLUMN_NAME_POSTER_PATH = "posterPath";
        public static final String COLUMN_NAME_BACKDROP_PATH = "backdropPath";
        public static final String COLUMN_NAME_CATEGORY = "idCategory";
        public static final String COLUMN_NAME_CATEGORY_NAME = "categoryName";
        public static final String COLUMN_NAME_IMAGE_POSTER = "imagePoster";
        public static final String COLUMN_NAME_IMAGE_BACKDROP = "imageBackdrop";
    }
}