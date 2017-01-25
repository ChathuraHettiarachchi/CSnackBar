package com.chootdev.csnackbar;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Choota on 1/25/17.
 */

public class Snackbar {
    // main context items
    private static Context snackContext;
    private static android.support.design.widget.Snackbar snackbar;
    private static Snackbar singleton;

    // variables
    private static int colorCode = Type.getColorCode(Type.SUCCESS);
    private static String snackMessage = "Hi there !";
    private static int snackDuration = Duration.getDuration(Duration.SHORT);
    private static View view;
    private static boolean isCustomView;

    public static Snackbar with(Context context, View fab) {
        snackContext = context.getApplicationContext();
        if (singleton == null)
            singleton = new Snackbar();

        if (fab == null) {
            View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
            view = rootView;
            snackbar = android.support.design.widget.Snackbar
                    .make(view, "", snackDuration);
        } else {
            view = fab;
            snackbar = android.support.design.widget.Snackbar
                    .make(view, "", snackDuration);
        }

        isCustomView = false;
        return singleton;
    }

    public static Snackbar type(Type type) {
        colorCode = Type.getColorCode(type);
        return singleton;
    }

    public static Snackbar type(Type type, int color) {
        if (type == Type.CUSTOM)
            colorCode = color;
        else
            colorCode = Type.getColorCode(type);
        return singleton;
    }

    public static Snackbar message(String displayingMessage) {
        snackMessage = displayingMessage;
        return singleton;
    }

    public static Snackbar duration(Duration duration) {
        if (duration != Duration.CUSTOM) {
            snackDuration = Duration.getDuration(duration);
        }
        return singleton;
    }

    public static Snackbar duration(Duration durationType, int duration) {
        if (durationType == Duration.CUSTOM) {
            snackDuration = duration;
        }
        return singleton;
    }

    public static Snackbar contentView(final View view, int heightInDp) {
        isCustomView = true;

        final android.support.design.widget.Snackbar.SnackbarLayout snackLayout = (android.support.design.widget.Snackbar.SnackbarLayout) snackbar.getView();
        android.support.design.widget.Snackbar.SnackbarLayout.LayoutParams params =
                (android.support.design.widget.Snackbar.SnackbarLayout.LayoutParams) snackLayout.getLayoutParams();

        params.height = (int) pxFromDp(heightInDp);

        TextView textView = (TextView) snackLayout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        snackLayout.addView(view, 0, params);
        return singleton;
    }

    private static View getSnackBarLayout() {
        if (snackbar != null) {
            return snackbar.getView();
        }
        return null;
    }

    private static Snackbar setColor(int colorId) {
        View snackBarView = getSnackBarLayout();
        if (snackBarView != null) {
            snackBarView.setBackgroundColor(colorId);
        }

        return singleton;
    }

    public static void show() {
        if(isCustomView){
            snackbar.setDuration(snackDuration);
            snackbar.show();
        } else {
            snackbar = android.support.design.widget.Snackbar
                    .make(view, snackMessage, snackDuration)
                    .setDuration(snackDuration);

            setColor(colorCode);
        }
        snackbar.show();
    }

    private static float pxFromDp(int dp) {
        return dp * snackContext.getResources().getDisplayMetrics().density;
    }

    public static void dismiss() {
        if (snackbar != null) {
            if (snackbar.isShown()) {
                snackbar.dismiss();
            }
        }
    }
}
