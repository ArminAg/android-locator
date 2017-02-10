package solutions.hedron.android_locator.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import solutions.hedron.android_locator.holders.LocationsViewHolder;
import solutions.hedron.android_locator.models.MyLocation;

/**
 * Created by armin on 11/02/2017.
 */

public class LocationsAdapter extends RecyclerView.Adapter<LocationsViewHolder> {
    private ArrayList<MyLocation> locations;

    public LocationsAdapter(ArrayList<MyLocation> locations){
        this.locations = locations;
    }
    @Override
    public void onBindViewHolder(LocationsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public LocationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}
