package in.kumar.krish.weather_app.data;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by KRISHAN KUMAR on 04-07-2016.
 */
public class Forecast implements JSONPopulator
{
    private String date;
    private int high;
    private int low;
    private String description;

    public String getDate() {
        return date;
    }

    public int getHigh() {
        return high;
    }

    public int getLow() {
        return low;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void populate(JSONObject data)
    {
        date = data.optString("date");
        high = data.optInt("high");
        low =  data.optInt("low");
        description =  data.optString("text");
    }
}
