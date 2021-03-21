package com.android.instabook;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinkedList<Post> posts;
    RecyclerView recyclerView;
    PostsAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        posts = new LinkedList<>();
        recyclerView = findViewById(R.id.timeline_recyclerview);
        loadPostsData();
    }

    private void updateAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        postsAdapter = new PostsAdapter(this,posts);
        recyclerView.setAdapter(postsAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void loadPostsData() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if(e != null){
                    Toast.makeText(MainActivity.this, "Couldn't Retrieve Posts!", Toast.LENGTH_SHORT).show();
                    return;
                }
                posts.addAll(objects);
                updateAdapter();
                Snackbar.make(findViewById(R.id.relativeLayout), "Post Data Loaded!", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_post_menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.uploadPicturePost){
            startActivityForResult(new Intent(this, NewPostUpload.class), 2);
            return true;
        }
        else if(item.getItemId() == R.id.cameraPicturePost){
            startActivityForResult(new Intent(this, NewPostCamera.class), 3);
            return true;
        }
        else if(item.getItemId() == R.id.logOutButton){
            ParseUser.logOut();
            if(ParseUser.getCurrentUser() != null){
                startActivity(new Intent(this, LauncherActivity.class));
                finish();
                return true;
            }
            Toast.makeText(this, "Failed to Logout!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK){
        }
        if(requestCode == 3 && resultCode == RESULT_OK){
            Snackbar.make(findViewById(R.id.relativeLayout), "Uploaded Successfully!", Snackbar.LENGTH_LONG).show();
        }
    }
}

