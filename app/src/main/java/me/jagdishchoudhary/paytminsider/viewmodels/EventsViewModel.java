package me.jagdishchoudhary.paytminsider.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.gson.JsonObject;
import me.jagdishchoudhary.paytminsider.repositories.EventRepository;

public class EventsViewModel extends ViewModel {

    private MutableLiveData<JsonObject> mutableLiveData;
    private EventRepository eventRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        eventRepository = EventRepository.getInstance();
        mutableLiveData = eventRepository.getEvents("1", "go-out", "mumbai");

    }

    public LiveData<JsonObject> getEventRepository(){
        return  mutableLiveData;
    }

}
