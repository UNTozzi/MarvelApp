package br.usjt.ads20.marvelapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class CharacterDB {
    CharacterDBHelper characterDBHelper;

    public CharacterDB (Context context) {
        characterDBHelper = new CharacterDBHelper(context);
    }

    public void saveCharacters (ArrayList<MarvelCharacter> characters) {
        SQLiteDatabase db = characterDBHelper.getWritableDatabase();
        db.delete(CharacterContract.CharacterEntry.TABLE_NAME, null, null);

        for (MarvelCharacter character: characters) {
            ContentValues values = new ContentValues();
            values.put(CharacterContract.CharacterEntry.COLUMN_NAME_ID_CHARACTER, character.getId());
            values.put(CharacterContract.CharacterEntry.COLUMN_NAME_NAME, character.getName());
            values.put(CharacterContract.CharacterEntry.COLUMN_NAME_DESCRIPTION, character.getDescription());
            values.put(CharacterContract.CharacterEntry.COLUMN_NAME_POPULARITY, character.getPopularity());
            values.put(CharacterContract.CharacterEntry.COLUMN_NAME_POSTER_PATH, character.getPosterPath());
            values.put(CharacterContract.CharacterEntry.COLUMN_NAME_BACKDROP_PATH, character.getBackdropPath());
            db.insert(CharacterContract.CharacterEntry.TABLE_NAME, null, values);
        }
        db.close();
    }

    public ArrayList<MarvelCharacter> searchCharacters () {
        ArrayList<MarvelCharacter> characters = new ArrayList<>();

        SQLiteDatabase db = characterDBHelper.getWritableDatabase();

        String[] columns = {
                CharacterContract.CharacterEntry.COLUMN_NAME_ID_CHARACTER,
                CharacterContract.CharacterEntry.COLUMN_NAME_NAME,
                CharacterContract.CharacterEntry.COLUMN_NAME_DESCRIPTION,
                CharacterContract.CharacterEntry.COLUMN_NAME_POPULARITY,
                CharacterContract.CharacterEntry.COLUMN_NAME_CATEGORY_NAME,
                CharacterContract.CharacterEntry.COLUMN_NAME_CATEGORY,
                CharacterContract.CharacterEntry.COLUMN_NAME_POSTER_PATH,
                CharacterContract.CharacterEntry.COLUMN_NAME_BACKDROP_PATH,
                CharacterContract.CharacterEntry.COLUMN_NAME_RELEASE_DATE
        };

        String where = null;

        String[] conditions = null;

        String order = CharacterContract.CharacterEntry.COLUMN_NAME_NAME;

        Cursor cursor = db.query(CharacterContract.CharacterEntry.TABLE_NAME, columns, where, conditions, order, null, null);

        while (cursor.moveToNext()) {
            MarvelCharacter character = new MarvelCharacter();
            character.setId(cursor.getInt(cursor.getColumnIndex(CharacterContract.CharacterEntry.COLUMN_NAME_ID_CHARACTER)));
            character.setName(cursor.getString(cursor.getColumnIndex(CharacterContract.CharacterEntry.COLUMN_NAME_NAME)));
            character.setDescription(cursor.getString(cursor.getColumnIndex(CharacterContract.CharacterEntry.COLUMN_NAME_DESCRIPTION)));
            character.setPopularity(cursor.getDouble(cursor.getColumnIndex(CharacterContract.CharacterEntry.COLUMN_NAME_POPULARITY)));
            character.setPosterPath(cursor.getString(cursor.getColumnIndex(CharacterContract.CharacterEntry.COLUMN_NAME_POSTER_PATH)));
            character.setBackdropPath(cursor.getString(cursor.getColumnIndex(CharacterContract.CharacterEntry.COLUMN_NAME_BACKDROP_PATH)));
            characters.add(character);
        }
        cursor.close();
        db.close();
        return characters;
    }

    public void updateBackdrop(int idCharacter, Bitmap img){
        SQLiteDatabase db = characterDBHelper.getWritableDatabase();

        String select = CharacterContract.CharacterEntry.COLUMN_NAME_ID_CHARACTER + "=?";
        String[] selectionArgs = new String[1];

        selectionArgs[0] = ""+idCharacter;
        ContentValues values = new ContentValues();
        values.put(CharacterContract.CharacterEntry.COLUMN_NAME_IMAGE_BACKDROP, getPictureOfArray(img));
        db.update(CharacterContract.CharacterEntry.TABLE_NAME, values, select, selectionArgs);
    }

    public void updatePosters (ArrayList<Poster> posters) {
        SQLiteDatabase db = characterDBHelper.getWritableDatabase();

        String select = CharacterContract.CharacterEntry.COLUMN_NAME_ID_CHARACTER + "=?";

        String[] selectionArgs = new String[1];

        for (Poster poster:posters) {
            selectionArgs[0] = ""+poster.getId();

            ContentValues values = new ContentValues();

            values.put(CharacterContract.CharacterEntry.COLUMN_NAME_IMAGE_POSTER, getPictureOfArray(poster.getPoster()));

            db.update(CharacterContract.CharacterEntry.TABLE_NAME, values, select, selectionArgs);
        }
    }

    public Bitmap searchBackdrop(int idCharacter){
        Bitmap img = null;
        SQLiteDatabase db = characterDBHelper.getReadableDatabase();
        String[] columns = {
                CharacterContract.CharacterEntry.COLUMN_NAME_IMAGE_BACKDROP
        };
        String select = CharacterContract.CharacterEntry.COLUMN_NAME_ID_CHARACTER + "=?";
        String[] selectionArgs = new String[1];

        selectionArgs[0] = ""+idCharacter;

        Cursor c = db.query(CharacterContract.CharacterEntry.TABLE_NAME, columns, select, selectionArgs, null, null, null);
        if (c.moveToNext()){
            img = getBitmapFromByte(c.getBlob(c.getColumnIndex(CharacterContract.CharacterEntry.COLUMN_NAME_IMAGE_BACKDROP)));
        }
        c.close();
        db.close();

        return img;
    }

    public ArrayList<Poster> searchPosters () {
        ArrayList<Poster> posters = new ArrayList<>();

        SQLiteDatabase db = characterDBHelper.getWritableDatabase();

        String[] columns = {
                CharacterContract.CharacterEntry.COLUMN_NAME_ID_CHARACTER,
                CharacterContract.CharacterEntry.COLUMN_NAME_NAME,
                CharacterContract.CharacterEntry.COLUMN_NAME_IMAGE_POSTER
        };

        String order = null;
        String where = null;
        String[] conditions = null;

        Cursor cursor = db.query(CharacterContract.CharacterEntry.TABLE_NAME, columns, where, conditions, order, null, null);

        while (cursor.moveToNext()) {
            Poster poster = new Poster();
            poster.setId(cursor.getInt((cursor.getColumnIndex(CharacterContract.CharacterEntry.COLUMN_NAME_ID_CHARACTER))));
            poster.setTitle(cursor.getString(cursor.getColumnIndex(CharacterContract.CharacterEntry.COLUMN_NAME_NAME)));
            poster.setPoster(getBitmapFromByte(cursor.getBlob(cursor.getColumnIndex(CharacterContract.CharacterEntry.COLUMN_NAME_IMAGE_POSTER))));

            posters.add(poster);
        }
        cursor.close();
        db.close();

        return posters;
    }

    private  Bitmap getBitmapFromByte (byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    private byte[] getPictureOfArray (Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}