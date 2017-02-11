package solutions.hedron.android_locator.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import solutions.hedron.android_locator.R;
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
        final MyLocation location = locations.get(position);
        holder.updateUI(location);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Load Details View
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    @Override
    public LocationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_location, parent, false);
        return new LocationsViewHolder(card);
    }
}
