package com.chootdev.csnackbar;

import com.google.android.material.snackbar.Snackbar;

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
                return Snackbar.LENGTH_LONG;
            case INFINITE:
                return Snackbar.LENGTH_INDEFINITE;
        }

        return Snackbar.LENGTH_SHORT;
    }
}
