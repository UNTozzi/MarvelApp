package br.usjt.ads20.marvelapp.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.security.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CharacterNetwork {

    private static String hashKey() throws NoSuchAlgorithmException {
        long timeStamp = System.currentTimeMillis();
        String stringToHash = timeStamp + "d271543e9bd90520dfba0f4f0007d1e880a7e476" + "1ace8dd841b201fae4e4009486c253a2";
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(stringToHash.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
// Now we need to zero pad it if you actually want the full 32 chars.
        while(hashtext.length() < 32 ){
            hashtext = "0"+hashtext;
        }
        return "ts=" + timeStamp + "&apikey=1ace8dd841b201fae4e4009486c253a2&hash=" + hashtext + "&limit=20";
    }
    public static ArrayList<MarvelCharacter> searchCharacters(String url) throws IOException, NoSuchAlgorithmException {
        ArrayList<MarvelCharacter> characters = new ArrayList<>();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        OkHttpClient client = new OkHttpClient();

        Log.d("CharacterNetwork.searchCharacters:url", url);

        Request request = new Request.Builder().url(url + hashKey()).build();
        Response response = client.newCall(request).execute();


        String json = response.body().string();

        Log.i(json, "searchCharacters: ");

        try {
            JSONObject jsonResponse = new JSONObject(json);
            JSONArray list = jsonResponse.getJSONObject("data").getJSONArray("results");
            for(int i = 0; i < list.length(); i++){
                MarvelCharacter movie = new MarvelCharacter();
                JSONObject item = (JSONObject) list.get(i);

                movie.setId(item.getInt("id"));
                movie.setName(item.getString("name"));
                movie.setBackdropPath(item.getJSONObject("thumbnail").getString("path") + "." + item.getJSONObject("thumbnail").getString("extension"));
                movie.setDescription(item.getString("description"));
                movie.setPosterPath(item.getJSONObject("thumbnail").getString("path") + "." + item.getJSONObject("thumbnail").getString("extension"));
                characters.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        return characters;
    }

    public static Bitmap searchImages(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Bitmap img = null;

        Log.d("CharacterNetwork.searchImages:url", url);
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();

        InputStream is = response.body().byteStream();

        img = BitmapFactory.decodeStream(is);

        is.close();

        return img;
    }

    public static boolean isConnected(Context context){
        ConnectivityManager manager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return manager.getActiveNetworkInfo() != null &&
                manager.getActiveNetworkInfo().isConnected();
    }
} 