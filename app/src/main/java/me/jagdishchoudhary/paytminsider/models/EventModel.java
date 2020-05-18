package me.jagdishchoudhary.paytminsider.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class EventModel {

    private String id;
    private String name;
    private String horizontal_cover_image;
    private String vertical_image;
    private String city;
    private String venue_name;
    private String venue_date_string;
    private JsonObject category;
    private JsonObject group;
    private String min_price;
    private String price_display_string;
    private String slug;

    public EventModel(String id, String name, String horizontal_cover_image, String vertical_image, String city, String venue_name, String venue_date_string, JsonObject category, JsonObject group, String min_price, String price_display_string, String slug) {
        this.id = id;
        this.name = name;
        this.horizontal_cover_image = horizontal_cover_image;
        this.vertical_image = vertical_image;
        this.city = city;
        this.venue_name = venue_name;
        this.venue_date_string = venue_date_string;
        this.category = category;
        this.group = group;
        this.min_price = min_price;
        this.price_display_string = price_display_string;
        this.slug = slug;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHorizontal_cover_image() {
        return horizontal_cover_image;
    }

    public void setHorizontal_cover_image(String horizontal_cover_image) {
        this.horizontal_cover_image = horizontal_cover_image;
    }

    public String getVertical_image() {
        return vertical_image;
    }

    public void setVertical_image(String vertical_image) {
        this.vertical_image = vertical_image;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public String getVenue_date_string() {
        return venue_date_string;
    }

    public void setVenue_date_string(String venue_date_string) {
        this.venue_date_string = venue_date_string;
    }

    public JsonObject getCategory() {
        return category;
    }

    public void setCategory(JsonObject category) {
        this.category = category;
    }

    public JsonObject getGroup() {
        return group;
    }

    public void setGroup(JsonObject group) {
        this.group = group;
    }

    public String getMin_price() {
        return min_price;
    }

    public void setMin_price(String min_price) {
        this.min_price = min_price;
    }

    public String getPrice_display_string() {
        return price_display_string;
    }

    public void setPrice_display_string(String price_display_string) {
        this.price_display_string = price_display_string;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
