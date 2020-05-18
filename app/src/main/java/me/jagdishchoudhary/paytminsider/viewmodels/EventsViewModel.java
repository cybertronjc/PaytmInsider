package me.jagdishchoudhary.paytminsider.viewmodels;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.gson.JsonObject;
import me.jagdishchoudhary.paytminsider.MainActivity;
import me.jagdishchoudhary.paytminsider.repositories.EventRepository;
import me.jagdishchoudhary.paytminsider.utils.SharedPref;

public class EventsViewModel extends AndroidViewModel {

    private MutableLiveData<JsonObject> mutableLiveData;
    private EventRepository eventRepository;
    private SharedPref sharedPref ;

    public EventsViewModel(@NonNull Application application) {
        super(application);
        sharedPref = new SharedPref(application);

    }


    public void init(){
        if (mutableLiveData != null){
            return;
        }
        eventRepository = EventRepository.getInstance();

    }

    public LiveData<JsonObject> getEventRepository(String city){
        if (!city.isEmpty() && !city.equals("")) {
            mutableLiveData = eventRepository.getEvents("1", "go-out", sharedPref.getCity().toLowerCase());
        }
            return mutableLiveData;
    }

}
