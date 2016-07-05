package in.kumar.krish.weather_app;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import in.kumar.krish.weather_app.data.Atmosphere;
import in.kumar.krish.weather_app.data.Channel;
import in.kumar.krish.weather_app.data.Forecast;
import in.kumar.krish.weather_app.data.Item;
import in.kumar.krish.weather_app.data.Wind;
import in.kumar.krish.weather_app.service.WeatherServiceCallback;
import in.kumar.krish.weather_app.service.YahooWeatherService;

public class WeatherActivity extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private TextView speedTextView;
    private TextView directionTextView;
    private TextView humidityTextView;
    private TextView visibilityTextView;
    private ImageButton location;

    private YahooWeatherService service;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


        weatherIconImageView=(ImageView)findViewById(R.id.weatherIconImageView);
        temperatureTextView=(TextView)findViewById(R.id.temperatureTextView);
        conditionTextView=(TextView)findViewById(R.id.conditionTextView);
        locationTextView=(TextView)findViewById(R.id.locationTextView);
        speedTextView=(TextView)findViewById(R.id.speedTextView);
        directionTextView=(TextView)findViewById(R.id.directionTextView);
        humidityTextView=(TextView)findViewById(R.id.humidityTextView);
        visibilityTextView=(TextView)findViewById(R.id.visibilityTextView);

        service=new YahooWeatherService(this);
        dialog= new ProgressDialog(this);
        dialog.setMessage("Loading.........");
        dialog.show();
        service.refreshWeather("Dwarka,Delhi");

        ImageView Yahoo_image = (ImageView)findViewById(R.id.yahoo);
        Yahoo_image.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.yahoo.com/?ilc=401 "));
                startActivity(intent);
            }
        });

        location = (ImageButton)findViewById(R.id.locationImageViewButton);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent();
                intent.setClass(v.getContext(),Location.class);
                startActivity(intent);
            }
        });



    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void ServiceSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();
        Wind wind = channel.getWind();
        Atmosphere atmosphere = channel.getAtmosphere();
        Forecast forecast = channel.getItem().getForecast();

        int resourceId = getResources().getIdentifier("@drawable/icon_"+ item.getCondition().getCode(),null,getPackageName());

        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);

        weatherIconImageView.setImageDrawable(weatherIconDrawable);
        locationTextView.setText(service.getLocation());
        temperatureTextView.setText(item.getCondition().getTemperature()+"\u00b0"+channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());
        speedTextView.setText((int)(wind.getSpeed()*1.60934)+" "+"\u006b\u006d\u002f\u0068");
        directionTextView.setText(wind.getDirection()+"\u00B0");
        humidityTextView.setText(atmosphere.getHumidity()+"\u0025");
        visibilityTextView.setText(atmosphere.getVisibility()+"\u006d");





    }

    @Override
    public void ServiceFailure(Exception exception) {

        Toast.makeText(this, exception.getMessage(),Toast.LENGTH_LONG).show();
    }
}
