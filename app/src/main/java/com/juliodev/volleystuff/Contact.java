package com.juliodev.volleystuff;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.LruCache;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class Contact {

    private String name;
    private String email;
    private String gender;
    private Bitmap icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Bitmap getImage() {
        return icon;
    }

    public void setImage(Bitmap icon) {
        this.icon = icon;
    }

    public void setImage(final String url, Context context) {
        ImageLoader load = new ImageLoader(Volley.newRequestQueue(context),new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap>
                    cache = new LruCache<String, Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
        load.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if(response.getBitmap()!= null){
                    icon = response.getBitmap();
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //this.icon = Image.fromUrl(icon);
    }

    public Contact() {
        this.name = "";
        this.email = "";
        this.gender = "";
        this.icon = null;
    }

    public Contact(String name, String email, String gender, String iconUrl) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

}
