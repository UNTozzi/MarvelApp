package br.usjt.ads20.marvelapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static br.usjt.ads20.marvelapp.model.CharacterContract.*;
public class CharacterDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public  static final String DATABASE_NAME = "character.db";

    public static final String SQL_CREATE_CHARACTER =
            "CREATE TABLE " + CharacterEntry.TABLE_NAME + " ( " +
                    CharacterEntry._ID + " INTEGER PRIMARY KEY," +
                    CharacterEntry.COLUMN_NAME_ID_CHARACTER + " INTEGER," +
                    CharacterEntry.COLUMN_NAME_NAME + " TEXT," +
                    CharacterEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    CharacterEntry.COLUMN_NAME_RELEASE_DATE + " INTEGER," +
                    CharacterEntry.COLUMN_NAME_POPULARITY + " REAL," +
                    CharacterEntry.COLUMN_NAME_POSTER_PATH + " TEXT," +
                    CharacterEntry.COLUMN_NAME_BACKDROP_PATH + " TEXT," +
                    CharacterEntry.COLUMN_NAME_CATEGORY_NAME + " TEXT," +
                    CharacterEntry.COLUMN_NAME_CATEGORY + " INTEGER," +
                    CharacterEntry.COLUMN_NAME_IMAGE_BACKDROP + " BLOB," +
                    CharacterEntry.COLUMN_NAME_IMAGE_POSTER + " BLOB)";

    public static final String SQL_DROP_CHARACTER =
            "DROP TABLE IF EXISTS " + CharacterEntry.TABLE_NAME;

    public  CharacterDBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CHARACTER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_CHARACTER);
        db.execSQL(SQL_CREATE_CHARACTER);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        onUpgrade(db, newVersion, oldVersion);
    }
}