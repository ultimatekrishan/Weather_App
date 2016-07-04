package in.kumar.krish.weather_app.data;

import org.json.JSONObject;

/**
 * Created by KRISHAN KUMAR on 02-07-2016.
 */
public class Wind implements JSONPopulator{

    private int speed;
    private int direction;


    public int getSpeed() {
        return speed;
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public void populate(JSONObject data) {

        speed = data.optInt("speed");
        direction = data.optInt("direction");


    }
}
