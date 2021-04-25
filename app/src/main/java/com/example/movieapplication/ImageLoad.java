package com.example.movieapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


// call: imageView.setImageBitmap(ImageLoad.getBitMapFromUrl(url));
public class ImageLoad{
    //variables
    private String url;
    private ImageView imageView;

    // constractor
    ImageLoad(String url, ImageView imageView) {
        this.url = url;
        this.imageView = imageView;
    }
    public static Bitmap getBitMapFromUrl(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
