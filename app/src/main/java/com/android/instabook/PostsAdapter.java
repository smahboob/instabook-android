package com.android.instabook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    List<Post> posts;
    Context context;

    public PostsAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View timeline_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout, parent, false);
        return new ViewHolder(timeline_view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post item = posts.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView post_image;
        TextView user_name;
        TextView post_data;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            post_image = itemView.findViewById(R.id.postImage);
            user_name = itemView.findViewById(R.id.userName);
            post_data = itemView.findViewById(R.id.postDetail);
        }

        //update the view inside of the view holder with the data
        @SuppressLint("CheckResult")
        public void bind(Post item) {
//            user_name.setText(item.getUser().getUsername());
//            post_data.setText(item.description);
//            Glide.with(context).load(item.postImage.getUrl()).into(post_image);
        }
    }

}