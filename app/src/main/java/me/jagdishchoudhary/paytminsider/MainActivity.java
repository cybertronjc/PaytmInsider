 package me.jagdishchoudhary.paytminsider;

import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.jagdishchoudhary.paytminsider.adapters.EventsAdapter;
import me.jagdishchoudhary.paytminsider.adapters.FeaturedEventsAdapter;
import me.jagdishchoudhary.paytminsider.adapters.ViewPagerAdapter;
import me.jagdishchoudhary.paytminsider.models.EventModel;
import me.jagdishchoudhary.paytminsider.utils.SharedPref;
import me.jagdishchoudhary.paytminsider.utils.SliderUtil;
import me.jagdishchoudhary.paytminsider.viewmodels.EventsViewModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

 public class MainActivity extends AppCompatActivity {

    EventsAdapter eventsAdapter;
    FeaturedEventsAdapter featuredEventsAdapter;
    ImageView insiderGif;
    ViewPager viewPager;
     LinearLayout sliderDotspanel;
     private int dotscount;
     private TextView[] dots;
     List<SliderUtil> sliderImg;
     ViewPagerAdapter viewPagerAdapter;
     Switch aSwitch;
     SharedPref sharedPref;
    EventsViewModel eventsViewModel;
    RecyclerView featured_recycler, comedy_recycler, music_recycler, workshop_recycler, online_recycler, health_recycler;
    private List<EventModel> featuredEventsList = new ArrayList<>();
    LinearLayoutManager featuredLayout, comedyLayout, musicLayout, workshopLayout, onlineLayout, healthLayout;
    ShimmerFrameLayout featuredShimmer, comedyShimmer, musicShimmer, workshopShimmer, onlineShimmer, healthShimmer, bannerShimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightModeState()) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }
        setContentView(R.layout.activity_main);

        insiderGif = findViewById(R.id.insiderGif);
        aSwitch = findViewById(R.id.darkTheme);

        Glide.with(this).asGif().load(R.raw.insider).into(insiderGif);
        Log.d("night", sharedPref.loadNightModeState().toString());

        if (sharedPref.loadNightModeState()){
            aSwitch.setChecked(true);
        }
        else {
            aSwitch.setChecked(false);
        }

        //dark theme switch
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    aSwitch.setChecked(true);
                    sharedPref.setNightModeState(true);
                    setTheme(R.style.DarkTheme);
                    recreate();
                    if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }
//                    finish();
//                    startActivity(new Intent(MainActivity.this, MainActivity.this.getClass()));
                }
                else {
                    aSwitch.setChecked(false);
                    sharedPref.setNightModeState(false);
                    setTheme(R.style.LightTheme);
                    recreate();
                    if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }

//                    finish();
//                    startActivity(new Intent(MainActivity.this, MainActivity.this.getClass()));
                }
            }
        });

        featuredShimmer = findViewById(R.id.featuredShimmer);
        featured_recycler = findViewById(R.id.featured_recycler);
        featured_recycler.setHasFixedSize(true);
        featuredLayout = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        featured_recycler.setLayoutManager(featuredLayout);

        comedyShimmer = findViewById(R.id.comedyShimmer);
        comedy_recycler = findViewById(R.id.comedy_recycler);
        comedy_recycler.setHasFixedSize(true);
        comedyLayout = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        comedy_recycler.setLayoutManager(comedyLayout);

        musicShimmer = findViewById(R.id.musicShimmer);
        music_recycler = findViewById(R.id.music_recycler);
        music_recycler.setHasFixedSize(true);
        musicLayout = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        music_recycler.setLayoutManager(musicLayout);

        workshopShimmer = findViewById(R.id.workshopShimmer);
        workshop_recycler = findViewById(R.id.workshops_recycler);
        workshop_recycler.setHasFixedSize(true);
        workshopLayout = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        workshop_recycler.setLayoutManager(workshopLayout);

        onlineShimmer = findViewById(R.id.healthShimmer);
        health_recycler = findViewById(R.id.health_recycler);
        health_recycler.setHasFixedSize(true);
        healthLayout = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        health_recycler.setLayoutManager(healthLayout);

        healthShimmer = findViewById(R.id.onlineShimmer);
        online_recycler = findViewById(R.id.online_recycler);
        online_recycler.setHasFixedSize(true);
        onlineLayout = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        online_recycler.setLayoutManager(onlineLayout);

        sliderImg = new ArrayList<>();

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        bannerShimmer = findViewById(R.id.bannerShimmer);

        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        eventsViewModel = ViewModelProviders.of(this).get(EventsViewModel.class);
        eventsViewModel.init();
        eventsViewModel.getEventRepository().observe(this, jsonObject -> {

            Log.d("response", jsonObject.toString());
            Log.d("featured_events", jsonObject.get("featured").getAsJsonArray().toString());

            //featured events
            JsonArray featuredEvents = new JsonArray();
            try {
                featuredEvents = jsonObject.get("featured").getAsJsonArray();
            }
            catch (Exception e){
                featuredEvents = null;
            }
            featuredEventsAdapter = new FeaturedEventsAdapter(getEventsList(featuredEvents), this);
            featured_recycler.setAdapter(featuredEventsAdapter);
            featuredShimmer.setVisibility(View.GONE);

            //master list
            JsonObject masterListObj = jsonObject.get("list").getAsJsonObject().get("masterList").getAsJsonObject();

//            JsonArray masterList = jsonObject.get("list").getAsJsonObject().get("masterList").getAsJsonArray();
//            Log.d("list", masterList.toString());

            //picks for courousel
            JsonObject picksObj = jsonObject.get("picks").getAsJsonObject().get("masterList").getAsJsonObject();
            Log.d("picks", picksObj.toString());
            Map<String, JsonObject> attributes = new HashMap<String, JsonObject>();
            Set<Map.Entry<String, JsonElement>> entrySet = picksObj.entrySet();
            for(Map.Entry<String,JsonElement> entry : entrySet){
                SliderUtil sliderUtils = new SliderUtil();
                attributes.put(entry.getKey(), picksObj.get(entry.getKey()).getAsJsonObject());
                sliderUtils.setSliderImageUrl(picksObj.get(entry.getKey()).getAsJsonObject().get("headerImage").getAsString());
                sliderUtils.setAction_url("https://insider.in/"+picksObj.get(entry.getKey()).getAsJsonObject().get("slug").getAsString());
                sliderImg.add(sliderUtils);
            }
            Log.d("events", sliderImg.get(5).getAction_url());

            viewPagerAdapter = new ViewPagerAdapter(MainActivity.this, sliderImg);
            bannerShimmer.setVisibility(View.GONE);

            viewPager.setAdapter(viewPagerAdapter);

            dotscount = viewPagerAdapter.getCount();

            dots = new TextView[dotscount];

            sliderDotspanel.removeAllViews();
            for (int i = 0; i < dots.length; i++) {
                dots[i] = new TextView(this);
                dots[i].setText(Html.fromHtml("&#8226;"));
                dots[i].setTextSize(20);
                dots[i].setTextColor(R.color.colorAccent);
                sliderDotspanel.addView(dots[i]);
            }

            if (dots.length > 0)
                dots[0].setTextColor(R.color.colorAccent);


            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    for(int i = 0; i< dotscount; i++){
                        dots[i].setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.unselected_indicator));
                    }

                    dots[position].setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selected_indicator));
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            //comedy events
            JsonArray comedyEventsList = new JsonArray();
            try {
                JsonArray comedyEvents = jsonObject.get("list").getAsJsonObject().get("categorywiseList").getAsJsonObject().get("Comedy").getAsJsonArray();
                for (int j=0; j<comedyEvents.size(); j++){
                    comedyEventsList.add(masterListObj.get(comedyEvents.get(j).getAsString()).getAsJsonObject());
                }
            }
            catch (Exception e){
                comedyEventsList = null;
            }
            eventsAdapter = new EventsAdapter(getEventsList(comedyEventsList), this);
            comedy_recycler.setAdapter(eventsAdapter);
            comedyShimmer.setVisibility(View.GONE);

            //music events
            JsonArray musicEventsList = new JsonArray();
            try {
                JsonArray musicEvents = jsonObject.get("list").getAsJsonObject().get("categorywiseList").getAsJsonObject().get("Music").getAsJsonArray();
                for (int j=0; j<musicEvents.size(); j++){
                    musicEventsList.add(masterListObj.get(musicEvents.get(j).getAsString()).getAsJsonObject());
                }
            }
            catch (Exception e){
                musicEventsList = null;
            }
            eventsAdapter = new EventsAdapter(getEventsList(musicEventsList), this);
            music_recycler.setAdapter(eventsAdapter);
            musicShimmer.setVisibility(View.GONE);


            //workshop events
            JsonArray workshopEventsList = new JsonArray();
            try {
                JsonArray workshopEvents = jsonObject.get("list").getAsJsonObject().get("categorywiseList").getAsJsonObject().get("Workshops").getAsJsonArray();
                for (int j=0; j<workshopEvents.size(); j++){
                    workshopEventsList.add(masterListObj.get(workshopEvents.get(j).getAsString()).getAsJsonObject());
                }
            }
            catch (Exception e){
                workshopEventsList = null;
            }
            eventsAdapter = new EventsAdapter(getEventsList(workshopEventsList), this);
            workshop_recycler.setAdapter(eventsAdapter);
            workshopShimmer.setVisibility(View.GONE);

            //online courses events
            JsonArray onlineEventsList = new JsonArray();
            try {
                JsonArray Events = jsonObject.get("list").getAsJsonObject().get("categorywiseList").getAsJsonObject().get("Online Course").getAsJsonArray();
                for (int j=0; j<Events.size(); j++){
                    onlineEventsList.add(masterListObj.get(Events.get(j).getAsString()).getAsJsonObject());
                }
            }
            catch (Exception e){
                onlineEventsList = null;
            }
            eventsAdapter = new EventsAdapter(getEventsList(onlineEventsList), this);
            online_recycler.setAdapter(eventsAdapter);
            onlineShimmer.setVisibility(View.GONE);

            //online courses events
            JsonArray healthEventsList = new JsonArray();
            try {
                JsonArray Events = jsonObject.get("list").getAsJsonObject().get("categorywiseList").getAsJsonObject().get("Health and Fitness").getAsJsonArray();
                for (int j=0; j<Events.size(); j++){
                    healthEventsList.add(masterListObj.get(Events.get(j).getAsString()).getAsJsonObject());
                }
            }
            catch (Exception e){
                healthEventsList = null;
            }
            eventsAdapter = new EventsAdapter(getEventsList(healthEventsList), this);
            health_recycler.setAdapter(eventsAdapter);
            healthShimmer.setVisibility(View.GONE);

            });
    }


    private List<EventModel> getEventsList(JsonArray jsonElements){
        List<EventModel> list = new ArrayList<>();
        if (jsonElements != null) {

            for (int i = 0; i < jsonElements.size(); i++) {
                JsonObject object = jsonElements.get(i).getAsJsonObject();
                String id = "", name = "", horizontal_cover_image = "", vertical_image = "", city = "", venue_name = "", venue_date_string = "", min_price = "", price_display_string = "";
                JsonObject category = new JsonObject(), group = new JsonObject();

                try {
                    id = object.get("_id").getAsString();
                } catch (Exception e) {

                }
                try {
                    name = object.get("name").getAsString();
                } catch (Exception e) {

                }
                try {
                    horizontal_cover_image = object.get("horizontal_cover_image").getAsString();
                } catch (Exception e) {

                }
                try {
                    vertical_image = object.get("vertical_cover_image").getAsString();
                } catch (Exception e) {

                }
                try {
                    city = object.get("city").getAsString();
                } catch (Exception e) {

                }
                try {
                    venue_name = object.get("venue_name").getAsString();
                } catch (Exception e) {

                }
                try {
                    venue_date_string = object.get("venue_date_string").getAsString();
                } catch (Exception e) {

                }
                try {
                    min_price = object.get("min_price").getAsString();
                } catch (Exception e) {

                }
                try {
                    price_display_string = object.get("price_display_string").getAsString();
                } catch (Exception e) {

                }

                try {
                    group = object.get("group_id").getAsJsonObject();
                } catch (Exception e) {

                }
                try {
                    category = object.get("category_id").getAsJsonObject();
                } catch (Exception e) {

                }

                EventModel eventModel = new EventModel(id, name, horizontal_cover_image, vertical_image, city, venue_name, venue_date_string, category, group, min_price, price_display_string);
                list.add(eventModel);
            }
        }

        return  list;
    }
}
