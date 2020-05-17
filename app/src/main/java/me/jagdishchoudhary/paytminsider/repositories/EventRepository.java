package me.jagdishchoudhary.paytminsider.repositories;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.JsonObject;
import me.jagdishchoudhary.paytminsider.api.EventsApi;
import me.jagdishchoudhary.paytminsider.api.RetrofitBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventRepository {

    private static EventRepository eventRepository;

    public static EventRepository getInstance(){
        if (eventRepository == null){
            eventRepository = new EventRepository();
        }

        return  eventRepository;
    }

    private EventsApi eventsApi;

    public EventRepository(){
        eventsApi = RetrofitBuilder.createService(EventsApi.class);
    }

    public MutableLiveData<JsonObject> getEvents(String norm, String filerBy, String city){
        final MutableLiveData<JsonObject> eventsData = new MutableLiveData<>();
        eventsApi.getEventList(norm, filerBy, city).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    Log.d("call", call.request().url().toString());
                    eventsData.setValue(response.body().getAsJsonObject());
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                eventsData.setValue(null);
            }
        });

        return  eventsData;
    }

}
