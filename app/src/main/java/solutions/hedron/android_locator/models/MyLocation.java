package solutions.hedron.android_locator.models;

/**
 * Created by armin on 10/02/2017.
 */

public class MyLocation {
    final String DRAWABLE = "drawable/";

    private float latitude;
    private float longitude;
    private String locationTitle;
    private String locationAddress;
    private String locationImgUrl;

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public String getLocationImgUrl() {
        return DRAWABLE + locationImgUrl;
    }

    public MyLocation(float latitude, float longitude, String locationTitle, String locationAddress, String locationImgUrl) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationTitle = locationTitle;
        this.locationAddress = locationAddress;
        this.locationImgUrl = locationImgUrl;
    }
}
