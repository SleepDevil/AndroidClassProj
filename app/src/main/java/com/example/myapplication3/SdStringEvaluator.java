package com.example.myapplication3;

import android.animation.TypeEvaluator;

public class SdStringEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float v, Object o, Object t1) {
        String startStr = (String) o;
        String endStr = (String) t1;
        String returnStr;

        if (v < 0.3) {
            returnStr = startStr;
        } else if(v <0.6) {
            returnStr = startStr+endStr;
        } else {
            returnStr = endStr;
        }
        return returnStr;
    }
}
