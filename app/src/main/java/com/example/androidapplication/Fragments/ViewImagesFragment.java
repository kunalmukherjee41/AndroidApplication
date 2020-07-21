package com.example.androidapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapplication.Api.RetrofitClient;
import com.example.androidapplication.R;
import com.example.androidapplication.adapter.ImageAdapter;
import com.example.androidapplication.model.PhotosResponse;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewImagesFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayout layout;

    ArrayList<String> images;

    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_images, container, false);

        layout = view.findViewById(R.id.layout);
        recyclerView = view.findViewById(R.id.images);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        images = new ArrayList<>();

        getData();

        return view;
    }

    private void getData() {

        Call<ArrayList<PhotosResponse>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getPhotos();

        call.enqueue(
                new Callback<ArrayList<PhotosResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<PhotosResponse>> call, Response<ArrayList<PhotosResponse>> response) {
                        if(response.isSuccessful()){

                            ArrayList<PhotosResponse> photos = response.body();
                            assert photos != null;
                            for(PhotosResponse pr : photos){
                                images.add(pr.getUrl());
                            }
                            ImageAdapter adapter = new ImageAdapter(getContext(), images);
                            recyclerView.setAdapter(adapter);
//                            Toast.makeText(getContext(), photos.get(0).getUrl(),Toast.LENGTH_LONG).show();

                        } else {
                            Snackbar.make(layout, "Something Went wrong Try Again",Snackbar.LENGTH_LONG)
                                    .setAction("Close", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {}
                                    }).setActionTextColor(getResources().getColor(R.color.colorAccent)).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<PhotosResponse>> call, Throwable t) {
                        Snackbar.make(layout, t.getMessage(),Snackbar.LENGTH_LONG)
                                .setAction("Close", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {}
                                }).setActionTextColor(getResources().getColor(R.color.colorAccent)).show();
                    }
                }
        );

    }

}
