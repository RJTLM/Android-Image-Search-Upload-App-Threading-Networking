package com.example.mad_assignment2b;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel {

    @SerializedName("hits")
    private List<Hit> hits;

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public static class Hit {
        @SerializedName("largeImageURL")
        private String largeImageURL;
        public String getLargeImageURL() {
            return largeImageURL;
        }

        public void setLargeImageURL(String largeImageURL)
        {
            this.largeImageURL = largeImageURL;
        }
    }
}