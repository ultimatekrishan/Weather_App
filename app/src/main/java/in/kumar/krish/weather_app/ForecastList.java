package in.kumar.krish.weather_app;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import in.kumar.krish.weather_app.data.Channel;
import in.kumar.krish.weather_app.data.Item;

/**
 * Created by KRISHAN KUMAR on 04-07-2016.
 */
public class ForecastList extends ArrayAdapter<String> {

    int[] image={};
    String[] date={};
    String[] description={};
    int[] high={};
    int[] low={};
    Context c;
    LayoutInflater inflater;
    Channel channel;
    public ForecastList(Context context, String[] date,String[] description,int[] image,int[] high,int[] low) {
        super(context, R.layout.list_row,date);

        this.c=context;
        this.date=date;
        this.description=description;
        this.image=image;
        this.high=high;
       this.low=low;

    }

    public class ViewHolder
    {
        ImageView image;
        TextView date;
        TextView description;
        TextView high;
        TextView low;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Item item;
        item=channel.getItem();

        if(convertView==null)
        {
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.list_row,null);

        }

        final ViewHolder holder =  new ViewHolder();
        holder.image=(ImageView) convertView.findViewById(R.id.list_image);
        holder.date=(TextView) convertView.findViewById(R.id.date);
        holder.description=(TextView) convertView.findViewById(R.id.description);
        holder.high = (TextView) convertView.findViewById(R.id.high);
        holder.low= (TextView) convertView.findViewById(R.id.low);

        int resourceId = getResources().getIdentifier("@drawable/icon_"+ item.getCondition().getCode(),null,getPackageName());

        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);



        return super.getView(position, convertView, parent);
    }
}


