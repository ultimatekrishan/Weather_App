package in.kumar.krish.weather_app.service;

import in.kumar.krish.weather_app.data.Channel;

/**
 * Created by KRISHAN KUMAR on 01-07-2016.
 */
public interface WeatherServiceCallback {

    void ServiceSuccess(Channel channel);
    void ServiceFailure(Exception exception);
}
