package com.android.instabook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.ParseUser;

public class NewPostUpload extends AppCompatActivity {

    EditText descriptionText;
    ImageView postImage;
    Button uploadButton;
    Post newPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post_upload);

        descriptionText = findViewById(R.id.newPostDescriptionText);
        postImage = findViewById(R.id.newPostImageView);
        uploadButton = findViewById(R.id.newPostUpload);

        postImage.setOnClickListener(v -> createNewPost());
        uploadButton.setOnClickListener(v -> goToMainActivity());
    }

    private void createNewPost() {
        Intent galleryOpener = new Intent();
        galleryOpener.setType("image/*");
        galleryOpener.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(galleryOpener,4);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4 && resultCode == RESULT_OK && data != null) {
            postImage.setImageURI(data.getData());
        }
    }

    private void goToMainActivity() {
        newPost = new Post();
        newPost.setDescription(descriptionText.getText().toString());
        newPost.setUser(ParseUser.getCurrentUser());

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}