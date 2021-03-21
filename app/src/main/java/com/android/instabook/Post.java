package com.android.instabook;

import android.media.Image;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

@ParseClassName("Post")
public class Post extends ParseObject {

    public static final String USER_KEY = "user";
    public static final String DESCRIPTION_KEY = "description";
    public static final String IMAGE_KEY = "image";


    public ParseUser getUser() throws ParseException {
        return fetchIfNeeded().getParseUser("user");
    }

    public void setUser(ParseUser user) {
        put(USER_KEY, user);
    }

    public String getDescription() throws ParseException {
        return fetchIfNeeded().getString(DESCRIPTION_KEY);
    }

    public void setDescription(String description) {
        put(DESCRIPTION_KEY,description);
    }

    public ParseFile getPostImage() throws ParseException {
        return fetchIfNeeded().getParseFile(IMAGE_KEY);
    }

    public void setPostImage(ParseFile postImage) {
        put(IMAGE_KEY, postImage);
    }

    public Post(){
    }
}
