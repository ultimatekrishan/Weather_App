package in.kumar.krish.weather_app.data;

import org.json.JSONObject;

/**
 * Created by KRISHAN KUMAR on 02-07-2016.
 */
public class Atmosphere implements JSONPopulator {

    private int humidity;
    private int visibility;


    public int getHumidity() {
        return humidity;
    }

    public int getVisibility() {
        return visibility;
    }

    @Override
    public void populate(JSONObject data) {

        humidity=data.optInt("humidity");
        visibility=data.optInt("visibility");

    }
}
