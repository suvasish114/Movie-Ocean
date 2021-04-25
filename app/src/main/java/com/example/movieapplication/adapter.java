package com.example.movieapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class adapter extends RecyclerView.Adapter<adapter.viewHolder> {
    private String[] data;
    private String[] overview;
    private String[] image_url;
    private String[] rating;
    adapter(String[] dataset, String[] moverview, String[] rating, String[] image_url){
        data = dataset;
        overview = moverview;
        this.image_url = image_url;
        this.rating = rating;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.title.setText(data[position]);
        holder.about.setText(overview[position]);
        holder.rating.setText("Rating : "+rating[position]);
        Glide.with(holder.image.getContext()).load(image_url[position]).into(holder.image);
        System.out.println(image_url+"\n");
        //holder.image.setImageBitmap(ImageLoad.getBitMapFromUrl(image_url[position]));
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
    public class viewHolder extends RecyclerView.ViewHolder{
        TextView rating;
        TextView title;
        ImageView image;
        TextView about;
        public viewHolder(@NonNull View itemview){
            super(itemview);
            title = itemview.findViewById(R.id.view_title);
            about = itemview.findViewById(R.id.view_about);
            image = itemview.findViewById(R.id.view_image);
            rating = itemview.findViewById(R.id.view_rating);
        }
    }
}