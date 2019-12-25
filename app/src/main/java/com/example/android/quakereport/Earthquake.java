package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private Long mTimeInMillis;
    private String mUrl;

    public Earthquake(double magnitude, String location, Long timeInMillis, String url) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mTimeInMillis = timeInMillis;
        this.mUrl = url;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public Long getTimeInMillis() {
        return mTimeInMillis;
    }

    public String getUrl() {
        return mUrl;
    }
}
