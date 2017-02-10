package solutions.hedron.android_locator.services;

import java.util.ArrayList;

import solutions.hedron.android_locator.models.MyLocation;

/**
 * Created by armin on 10/02/2017.
 */
public class DataService {
    private static DataService ourInstance = new DataService();

    public static DataService getInstance() {
        return ourInstance;
    }

    private DataService() {
    }

    public ArrayList<MyLocation> getLocationsWithin10MilesOfZip(int zipcode){
        ArrayList<MyLocation> locations = new ArrayList<>();

        locations.add(new MyLocation(46.526597f, 6.604484f, "digitec", "Rue du Grand-Pré 2B, 1007 Lausanne", "digitec"));
        locations.add(new MyLocation(46.522405f, 6.619248f, "Les Docks", "Avenue de Sévelin 34, 1004 Lausanne", "docks"));
        locations.add(new MyLocation(46.522065f, 6.626737f, "Cinéma Pathé Flon", "Rue du Port-Franc 16, 1003 Lausanne", "cinema"));

        return locations;
    }
}
