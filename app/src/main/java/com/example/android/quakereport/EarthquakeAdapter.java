package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeText = (TextView) listItemView.findViewById(R.id.magnitude_text);
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeText.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);
        double magnitude = currentEarthquake.getMagnitude();
        magnitudeText.setText(getFormattedMagnitude(magnitude));

        String location = currentEarthquake.getLocation();
        TextView primaryLocationText = (TextView) listItemView.findViewById(R.id.primary_location_text);
        primaryLocationText.setText(getPrimaryLocation(location));
        TextView locationOffsetText = (TextView) listItemView.findViewById(R.id.location_offset_text);
        locationOffsetText.setText(getLocationOffset(location));

        Date date = new Date(currentEarthquake.getTimeInMillis());
        TextView dateText = (TextView) listItemView.findViewById(R.id.date_text);
        dateText.setText(getFormattedDate(date));
        TextView timeText = (TextView) listItemView.findViewById(R.id.time_text);
        timeText.setText(getFormattedTime(date));
        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeFloor = (int) Math.floor(magnitude);
        int magnitudeColorResourceId;
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    private String getFormattedMagnitude(double magnitude) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitude);
    }

    private String getPrimaryLocation(String location) {
        if (location.contains("of")) {
            int i = location.indexOf("of");
            return location.substring(i + 3, location.length());
        }
        else {
            return location;
        }
    }

    private String getLocationOffset(String location) {
        if (location.contains("of")) {
            int i = location.indexOf("of");
            return location.substring(0, i + 2);
        }
        else {
            return "Near the";
        }
    }

    private String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return simpleDateFormat.format(date);
    }

    private String getFormattedTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
        return simpleDateFormat.format(date);
    }
}
