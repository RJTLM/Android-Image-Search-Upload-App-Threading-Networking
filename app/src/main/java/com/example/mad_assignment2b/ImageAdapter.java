package com.example.mad_assignment2b;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    RecyclerInterface imageRecyclerInterface;
    List<Bitmap> images;
    Context context;
    public ImageAdapter(List<Bitmap> data, Context context, RecyclerInterface imageRecyclerInterface)
    {
        this.images = data;
        this.context = context;
        this.imageRecyclerInterface = imageRecyclerInterface;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.image_layout,parent,false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view, imageRecyclerInterface);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.image.setImageBitmap(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public ImageViewHolder(@NonNull View itemView, RecyclerInterface imageRecyclerInterface) {
            super(itemView);
            image = itemView.findViewById(R.id.imageDisplay);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(imageRecyclerInterface != null)
                    {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION)
                        {
                            imageRecyclerInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

}
