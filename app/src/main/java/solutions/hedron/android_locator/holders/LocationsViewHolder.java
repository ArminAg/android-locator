package solutions.hedron.android_locator.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import solutions.hedron.android_locator.R;
import solutions.hedron.android_locator.models.MyLocation;

/**
 * Created by armin on 11/02/2017.
 */

public class LocationsViewHolder extends RecyclerView.ViewHolder {
    private ImageView locationImage;
    private TextView locationTitle;
    private TextView locationAddress;

    public LocationsViewHolder(View itemView) {
        super(itemView);
        this.locationImage = (ImageView)itemView.findViewById(R.id.location_image);
        this.locationTitle = (TextView)itemView.findViewById(R.id.location_title);
        this.locationAddress = (TextView)itemView.findViewById(R.id.location_address);
    }

    public void updateUI(MyLocation location){
        String uri = location.getLocationImgUrl();
        int resource = locationImage.getResources().getIdentifier(uri, null, locationImage.getContext().getPackageName());
        locationImage.setImageResource(resource);
        locationTitle.setText(location.getLocationTitle());
        locationAddress.setText(location.getLocationAddress());
    }
}
