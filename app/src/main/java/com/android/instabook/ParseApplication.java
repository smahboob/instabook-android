package com.android.instabook;
import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("EhZM8Dk31V50sXCQmb3aoQq5uQ8WGx7cXRubslMN")
                .clientKey("e4w4Qt7UWbQzTTIxWp093Xaj53EbbvZHJlWIwTiW")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
