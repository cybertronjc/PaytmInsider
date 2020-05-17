package me.jagdishchoudhary.paytminsider.models;

public class Display_details {
    private String colour;
    private String seo_title;
    private String seo_description;


    // Getter Methods

    public String getColour() {
        return colour;
    }

    public String getSeo_title() {
        return seo_title;
    }

    public String getSeo_description() {
        return seo_description;
    }

    // Setter Methods

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setSeo_title(String seo_title) {
        this.seo_title = seo_title;
    }

    public void setSeo_description(String seo_description) {
        this.seo_description = seo_description;
    }
}