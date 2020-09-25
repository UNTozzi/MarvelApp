package br.usjt.ads20.marvelapp;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.lang.reflect.Field;

public class Util {
    public static Drawable getDrawable (Context context, String name) {
        Class<?> c = R.drawable.class;
        try {
            Field fieldId = c.getDeclaredField(name);
            int id = fieldId.getInt(fieldId);
            return context.getResources().getDrawable(id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


        return null;
    }
}