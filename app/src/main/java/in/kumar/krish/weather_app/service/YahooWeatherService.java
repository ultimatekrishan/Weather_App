package in.kumar.krish.weather_app.service;

import android.app.Service;
import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import in.kumar.krish.weather_app.data.Channel;

/**
 * Created by KRISHAN KUMAR on 01-07-2016.
 */
public class YahooWeatherService {

    private WeatherServiceCallback callback;
    private String location;
    private Exception error;

    public YahooWeatherService(WeatherServiceCallback callback)
    {
        this.callback = callback;
    }

    public String getLocation() {

        return location;
    }

    public void refreshWeather(String l)
    {
        this.location = l;

        new AsyncTask<String,Void,String>()
        {

            @Override
            protected String doInBackground(String... strings) {

                String JQl = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='C'",strings[0]);

                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(JQl));

                try {
                    URL url = new URL(endpoint);

                    URLConnection connection = url.openConnection();

                    InputStream inputstream= connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));

                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line=reader.readLine()) != null){
                        result.append(line);

                    }
                    return result.toString();

                } catch (Exception e) {
                    error=e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {


                if(s==null && error!=null)
                {
                    callback.ServiceFailure(error);
                    return;
                }
                try {
                    JSONObject data = new JSONObject(s);

                    JSONObject querryResult= data.optJSONObject("query");

                    int count = querryResult.optInt("count");

                    if(count==0)
                    {
                        callback.ServiceFailure(new LocationWeatherException("No Weather Information Found For "+ location));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(querryResult.optJSONObject("results").optJSONObject("channel"));
                    callback.ServiceSuccess(channel);


                } catch (JSONException e) {
                    callback.ServiceFailure(e);
                }
            }
        }.execute(location);
    }

    public class LocationWeatherException extends Exception
    {
        public LocationWeatherException(String detailMessage) {
            super(detailMessage);
        }
    }

}
