package in.kumar.krish.weather_app.data;

import org.json.JSONObject;

/**
 * Created by KRISHAN KUMAR on 01-07-2016.
 */
public class Item implements JSONPopulator {
    private Condition condition;
    private Forecast forecast;

    public Forecast getForecast() {
        return forecast;
    }

    public Condition getCondition() {
        return condition;

    }

    @Override
    public void populate(JSONObject data) {

        condition= new Condition();
        condition.populate(data.optJSONObject("condition"));

        forecast =  new Forecast();
        forecast.populate(data.optJSONObject("forecast"));

    }
}
