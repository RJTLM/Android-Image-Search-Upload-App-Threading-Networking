package com.example.mad_assignment2b;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Adapter class for displaying images in a RecyclerView
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    // Interface for handling item click events in the RecyclerView
    RecyclerInterface imageRecyclerInterface;

    // List of images to be displayed
    List<Bitmap> images;

    // Application context
    Context context;

    // Constructor for the ImageAdapter
    public ImageAdapter(List<Bitmap> data, Context context, RecyclerInterface imageRecyclerInterface) {
        this.images = data;
        this.context = context;
        this.imageRecyclerInterface = imageRecyclerInterface;
    }

    // Method to create a new ViewHolder for the RecyclerView
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for individual image items
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.image_layout, parent, false);

        // Create and return a new ViewHolder
        ImageViewHolder imageViewHolder = new ImageViewHolder(view, imageRecyclerInterface);
        return imageViewHolder;
    }

    // Method to bind data to a ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        // Set the image for the current item
        holder.image.setImageBitmap(images.get(position));
    }

    // Method to get the total number of items in the RecyclerView
    @Override
    public int getItemCount() {
        return images.size();
    }

    // ViewHolder class for individual image items
    public class ImageViewHolder extends RecyclerView.ViewHolder {
        // ImageView to display the image
        ImageView image;

        // Constructor for the ImageViewHolder
        public ImageViewHolder(@NonNull View itemView, RecyclerInterface imageRecyclerInterface) {
            super(itemView);

            // Get the ImageView from the layout
            image = itemView.findViewById(R.id.imageDisplay);

            // Set an OnClickListener for the item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Check if the interface is implemented
                    if (imageRecyclerInterface != null) {
                        int pos = getAdapterPosition();

                        // Check if the position is valid
                        if (pos != RecyclerView.NO_POSITION) {
                            // Call the onItemClick method of the interface
                            imageRecyclerInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
