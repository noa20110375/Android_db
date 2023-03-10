package com.example.retrofitmovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MovieAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    Button btn;
    private MovieInterface apiInterface;
    List<Movie> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_list);


        btn = findViewById(R.id.button);
        recyclerView = findViewById(R.id.recyclerView);

        list = new ArrayList<>();
        adapter = new MovieAdapter(list);

        //화면 뿌리기위해 레이아웃 객체를 만듦
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface = MovieClient.getClient().create(MovieInterface.class);
                Call<List<Movie>> call = apiInterface.doGetMovie();
                call.enqueue(new Callback<List<Movie>>() {
                    @Override
                    public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                        Log.d("TAG",response.code()+"");
                        Log.d("TAG",response.toString()+"");
                        String displayResponse = "";

                        List<Movie> resource = response.body();
                        Log.d("TAG",resource.size()+"");
                        Log.d("adapter.getItemCount()",adapter.getItemCount()+"");


                        for(Movie zip : resource){
                            list.add(zip);
                        }
                        Toast.makeText(getApplicationContext(),adapter.getItemCount()+"",Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<List<Movie>> call, Throwable t) {

                    }
                });

            }
        });


    }
}