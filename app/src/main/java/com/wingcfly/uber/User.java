package com.wingcfly.uber;

public class User {
    public String displayName;
    public String mail;
    public String ggId;
    public String fbID;
    public String photo;
    public String genre;
    public String dob;

    public User(String displayName, String mail, String ggId, String fbID, String photo, String genre, String dob) {
        this.displayName = displayName;
        this.mail = mail;
        this.ggId = ggId;
        this.fbID = fbID;
        this.photo = photo;
        this.genre = genre;
        this.dob = dob;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getMail() {
        return mail;
    }

    public String getGgId() {
        return ggId;
    }

    public String getFbID() {
        return fbID;
    }

    public String getPhoto() {
        return photo;
    }

    public String getGenre() {
        return genre;
    }

    public String getDob() {
        return dob;
    }
}
