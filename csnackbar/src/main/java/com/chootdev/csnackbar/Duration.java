package com.chootdev.csnackbar;

import android.support.design.widget.Snackbar;

/**
 * Created by Choota on 1/25/17.
 */

public enum Duration {
    SHORT, LONG, CUSTOM, INFINITE;

    public static int getDuration(Duration duration){
        switch (duration){
            case SHORT:
                return Snackbar.LENGTH_SHORT;
            case LONG:
                return Snackbar.LENGTH_SHORT;
            case INFINITE:
                return Snackbar.LENGTH_INDEFINITE;
            case CUSTOM:
                return Snackbar.LENGTH_SHORT;
        }

        return Snackbar.LENGTH_SHORT;
    }
}
