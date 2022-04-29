package com.example.myapplication3;

import android.animation.TimeInterpolator;

public class SdTimeInterplator implements TimeInterpolator {

    @Override
    public float getInterpolation(float v) {
        return v * v;
    }
}
