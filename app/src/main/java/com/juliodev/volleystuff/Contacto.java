package com.juliodev.volleystuff;

import android.graphics.drawable.Drawable;

public class Contacto {

    private String name;
    private String email;
    private String gender;
    private Drawable icon;

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

    public Drawable getImage() {
        return icon;
    }

    public void setImage(Drawable icon) {
        this.icon = icon;
    }

    public Contacto() {
        this.name = "";
        this.email = "";
        this.gender = "";
        this.icon = null;
    }

    public Contacto(String name, String email, String gender, String iconUrl) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.icon = image.fromUrl(iconUrl);
    }

}
