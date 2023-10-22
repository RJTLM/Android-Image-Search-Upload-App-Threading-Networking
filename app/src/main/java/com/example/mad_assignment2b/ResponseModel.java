package com.example.mad_assignment2b;

import com.google.gson.annotations.SerializedName;
import java.util.List;

// Model class to represent the response from the Pixabay API
public class ResponseModel {

    // List of image hits from the API response
    @SerializedName("hits")
    private List<Hit> hits;

    // Getter method to retrieve the list of image hits
    public List<Hit> getHits() {
        return hits;
    }

    // Setter method to set the list of image hits
    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    // Inner class to represent individual image hits in the API response
    public static class Hit {

        // URL of the large version of the image
        @SerializedName("largeImageURL")
        private String largeImageURL;

        // Getter method to retrieve the large image URL
        public String getLargeImageURL() {
            return largeImageURL;
        }

        // Setter method to set the large image URL
        public void setLargeImageURL(String largeImageURL) {
            this.largeImageURL = largeImageURL;
        }
    }
}
