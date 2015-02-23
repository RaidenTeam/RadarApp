package bg.mentormate.academy.radarapp.models;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import bg.mentormate.academy.radarapp.Constants;

/**
 * Created by tl on 14.02.15.
 */

@ParseClassName(Constants.CURRENT_LOCATION_TABLE)
public class CurrentLocation extends ParseObject {
    public ParseGeoPoint getLocation() {
        return getParseGeoPoint(Constants.CURRENT_LOCATION_COL_LOCATION);
    }

    public void setLocation(ParseGeoPoint location) {
        put(Constants.CURRENT_LOCATION_COL_LOCATION, location);
    }

    public String getProvider() {
        return getString(Constants.CURRENT_LOCATION_COL_PROVIDER);
    }

    public void setProvider(String provider) {
        put(Constants.CURRENT_LOCATION_COL_PROVIDER, provider);
    }

    public boolean getActive() {
        return getBoolean(Constants.CURRENT_LOCATION_COL_ACTIVE);
    }

    public void setActive(boolean active) {
        put(Constants.CURRENT_LOCATION_COL_ACTIVE, active);
    }
}
