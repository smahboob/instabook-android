package com.android.instabook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
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
        if(item.getItemId() == R.id.compose_button_action_bar){
            startActivityForResult(new Intent(this,NewPost.class), 2);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK){
            //get data from the intent
            assert data != null;
            //Post post = Parcels.unwrap(data.getParcelableExtra("post"));
            //update the recycler view
            //posts.add(0,post);
        }
    }
}

