package com.example.mad_assignment2b;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

// Interface defining the API calls for the application using Retrofit
public interface RemoteAPICalls {

    // API call to get search results from the Pixabay API
    // @param key - the API key for authentication
    // @param searchValue - the keyword to search for images
    @GET("/api/")
    Observable<ResponseModel> getSearchResponse(@Query("key") String key,
                                                @Query("q") String searchValue);

    // API call to get an individual image using its URL
    // @param imageUrl - the URL of the image to be fetched
    @GET
    Observable<ResponseBody> getImage(@Url String imageUrl);
}
