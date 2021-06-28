package com.chootdev.csnackbar;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import com.google.android.material.appbar.AppBarLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Choota on 1/25/17.
 */

/*Updated to AndroidX*/

public class Snackbar {
    // main context items
    private static Context snackContext;
    private static com.google.android.material.snackbar.Snackbar snackbar;
    private static Snackbar singleton;

    // variables
    private static int colorCode = Type.getColorCode(Type.SUCCESS);
    private static String snackMessage = "Hi there !";
    private static int snackDuration = Duration.getDuration(Duration.SHORT);
    private static View view;

    private static boolean isCustomView;
    private static boolean isFillParent;
    private static Align textAlign;

    public static Snackbar with(Context context, View fab) {
        snackContext = context.getApplicationContext();
        if (singleton == null)
            singleton = new Snackbar();

        if (fab == null) {
            View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
            view = rootView;
            snackbar = com.google.android.material.snackbar.Snackbar
                    .make(view, "", snackDuration);
        } else {
            view = fab;
            snackbar = com.google.android.material.snackbar.Snackbar
                    .make(view, "", snackDuration);
        }

        isCustomView = false;
        isFillParent = false;
        textAlign = Align.LEFT;

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

    public static Snackbar message(CharSequence displayingMessage) {
        snackMessage = displayingMessage.toString();
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

        final com.google.android.material.snackbar.Snackbar.SnackbarLayout snackLayout = (com.google.android.material.snackbar.Snackbar.SnackbarLayout) snackbar.getView();
        com.google.android.material.snackbar.Snackbar.SnackbarLayout.LayoutParams params =
                (com.google.android.material.snackbar.Snackbar.SnackbarLayout.LayoutParams) snackLayout.getLayoutParams();

        params.height = (int) pxFromDp(heightInDp);

        TextView textView = (TextView) snackLayout.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        snackLayout.addView(view, 0, params);
        return singleton;
    }

    public static Snackbar fillParent(boolean fillParent) {
        isFillParent = fillParent;
        return singleton;
    }

    public static Snackbar textAlign(Align align) {
        textAlign = align;
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

    private static void setTextAlignment(com.google.android.material.snackbar.Snackbar snackbar) {
        TextView textView = (TextView) snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) textView.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        textView.setLayoutParams(params);

        switch (textAlign) {
            case CENTER:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                else
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                break;
            case RIGHT:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                else
                    textView.setGravity(Gravity.RIGHT);
                break;
            case LEFT:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                else
                    textView.setGravity(Gravity.LEFT);
                break;
            default:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                else
                    textView.setGravity(Gravity.LEFT);
                break;
        }
    }

    public static void show() {
        if (isCustomView) {
            snackbar.setDuration(snackDuration);
            snackbar.show();
        } else {
            snackbar = com.google.android.material.snackbar.Snackbar
                    .make(view, snackMessage, snackDuration)
                    .setDuration(snackDuration);

            if (isFillParent)
                snackbar.getView().getLayoutParams().width = AppBarLayout.LayoutParams.MATCH_PARENT;

            setTextAlignment(snackbar);

            setColor(colorCode);

            View snackbarView = snackbar.getView();
            TextView textView = (TextView) snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
            textView.setMaxLines(10);
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
