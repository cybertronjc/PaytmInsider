package me.jagdishchoudhary.paytminsider.api;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventsApi {


    @GET("home")
    Call<JsonObject> getEventList(@Query("norm") String norm,
                                 @Query("filterBy") String filterBy,
                                  @Query("city") String city);

}
