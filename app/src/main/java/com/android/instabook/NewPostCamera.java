package com.android.instabook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.File;

public class NewPostCamera extends AppCompatActivity {

    EditText descriptionText;
    ImageView postImage;
    Button uploadButton;
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 42;
    public String photoFileName = "photo.jpg";
    File photoFile;
    public final String TAG = "InstaBook Tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post_camera);

        descriptionText = findViewById(R.id.newPostDescriptionTextCamera);
        postImage = findViewById(R.id.newPostImageViewCamera);
        uploadButton = findViewById(R.id.newPostUploadCamera);

        postImage.setOnClickListener(v -> openCamera());
        uploadButton.setOnClickListener(v -> {
            try {
                createNewPost();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoFile = getPhotoFileUri(photoFileName);

        Uri fileProvider = FileProvider.getUriForFile(NewPostCamera.this, "com.codepath.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }

    }

    private File getPhotoFileUri(String fileName) {
        File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Toast.makeText(this, "failed to create directory", Toast.LENGTH_SHORT).show();
        }
        return new File(mediaStorageDir.getPath() + File.separator + fileName);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                postImage.setImageBitmap(takenImage);
            } else {
                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void createNewPost() throws ParseException {
        String description = descriptionText.getText().toString();
        if(description.equals("")){
            descriptionText.setFocusable(true);
            descriptionText.setError("Enter Description!");
            Snackbar.make(findViewById(R.id.cameraView), "Description Required!", Snackbar.LENGTH_LONG).show();
            return;
        }

        else if(photoFile == null || postImage.getDrawable() == null){
            postImage.setFocusable(true);
            Snackbar.make(findViewById(R.id.cameraView), "Click on camera icon to take a new Picture!", Snackbar.LENGTH_LONG).show();
            return;
        }

        else if (!description.equals("")){
            ParseUser cur_user = ParseUser.getCurrentUser();
            savePost(description,cur_user,photoFile);
        }
    }

    private void savePost(String description, ParseUser cur_user, File PhotoFile) {
        Post post = new Post();
        post.setDescription(description);
        post.setUser(cur_user);
        post.setPostImage(new ParseFile(PhotoFile));

        post.saveInBackground(e -> {
            if(e != null){
                Log.d("TAG", e.getMessage());
                Snackbar.make(findViewById(R.id.cameraView), "Error Uploading Post!", Snackbar.LENGTH_LONG).show();
                return;
            }
            goToMainActivity();
        });
    }

    private void goToMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
        finishActivity(3);
    }
}