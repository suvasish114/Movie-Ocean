package com.example.movieapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private int page_no = 1;
    private String URL = "https://api.themoviedb.org/3/discover/movie?api_" +
            "key=bd7b9df8d595246317d4138e076c2ad5" +
            "&language=" + "en-US" +
            "&sort_by=" + "popularity.desc" +
            "&include_adult=" + "false" +
            "&include_video=" + "false" +
            "&page=" + page_no +
            "&with_watch_monetization_types="+ "flatrate";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        get_data();
    }

    // get data from server and render to the recycler view
    public void get_data(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view_holder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Request
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        //String URL = "https://api.themoviedb.org/3/discover/movie?api_key=bd7b9df8d595246317d4138e076c2ad5&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    String[] movie_title = new String[jsonArray.length()];
                    String[] movie_overview = new String[jsonArray.length()];
                    String[] movie_rating = new String[jsonArray.length()];
                    String[] movie_poster = new String[jsonArray.length()];
                    String pos;
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject movie = jsonArray.getJSONObject(i);
                        movie_title[i] = movie.getString("title");
                        movie_rating[i] = movie.getString("vote_average");
                        movie_overview[i] = movie.getString("overview");
                        pos = movie.getString("poster_path");
                        movie_poster[i] = "https://image.tmdb.org/t/p/w500" + pos;
                    }
                    recyclerView.setAdapter(new adapter(movie_title, movie_overview, movie_rating, movie_poster));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error to read API"+ error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}