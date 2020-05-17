package me.jagdishchoudhary.paytminsider.models;
public class CategoryModel {
    private String _id;
    private String name;
    private String icon_img;
    Display_details Display_detailsObject;


    // Getter Methods

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getIcon_img() {
        return icon_img;
    }

    public Display_details getDisplay_details() {
        return Display_detailsObject;
    }

    // Setter Methods

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon_img(String icon_img) {
        this.icon_img = icon_img;
    }

    public void setDisplay_details(Display_details display_detailsObject) {
        this.Display_detailsObject = display_detailsObject;
    }
}



