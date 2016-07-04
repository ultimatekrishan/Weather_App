package in.kumar.krish.weather_app.data;

import org.json.JSONObject;

/**
 * Created by KRISHAN KUMAR on 01-07-2016.
 */
public class Units implements JSONPopulator {

    private String temperature;

    public String getTemperature()
    {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {

        temperature= data.optString("temperature");



    }
}
