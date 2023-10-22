package com.example.mad_assignment2b;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RemoteAPICalls {
    @GET("/api/")
    Observable<ResponseModel> getSearchResponse(@Query("key") String key,
                                                @Query("q") String searchValue);
    @GET
    Observable<ResponseBody> getImage(@Url String imageUrl);

}
