package in.kumar.krish.weather_app.data;

import org.json.JSONObject;

/**
 * Created by KRISHAN KUMAR on 01-07-2016.
 */
public class Channel implements JSONPopulator {
    private Units units;
    private Item item;
    private Wind wind;
    private Atmosphere atmosphere;


    public Units getUnits() {
        return units;
    }

    public Item getItem() {
        return item;
    }

    public Wind getWind() { return wind; }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }


    @Override
    public void populate(JSONObject data) {

        units= new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));

        wind =  new Wind();
        wind.populate(data.optJSONObject("wind"));

        atmosphere= new Atmosphere();
        atmosphere.populate(data.optJSONObject("atmosphere"));
    }
}
