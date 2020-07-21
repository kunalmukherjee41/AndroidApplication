package com.example.androidapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageAdapter extends  RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{

    private Context mContext;
    private List<String> list;

    public ImageAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(mContext).inflate(R.layout.recycle_view, parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        String s = list.get(position);

//        Glide.with(mContext)
//                .asBitmap()
//                .apply(RequestOptions.circleCropTransform())
//                .load(s)
//                .into(holder.imageView);
//
//
////        RequestOptions requestOptions = new RequestOptions();
////        requestOptions.placeholder(R.mipmap.ic_launcher);
////        requestOptions.error(R.mipmap.ic_launcher);
////        Glide
////                .with(mContext)
////                .setDefaultRequestOptions(requestOptions)
////                .load(s)
////                .into(holder.imageView);
//
//
////        Glide.with(mContext).load(s).into(holder.imageView);
////        holder.imageView.setImageResource(R.mipmap.ic_launcher);

        Picasso.get().load(s).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);

        }
    }
}
