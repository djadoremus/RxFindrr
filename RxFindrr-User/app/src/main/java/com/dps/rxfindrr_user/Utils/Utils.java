package com.dps.rxfindrr_user.Utils;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {


//    public static void snack(View view, String text) {
//        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
//    }

    public static void toast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void anim(Context context, TextView textView,
                            int offsetTimeMillis, int animResource) {
        Animation a = AnimationUtils.loadAnimation(context, animResource);
        a.setStartOffset(offsetTimeMillis);
        textView.startAnimation(a);
    }

    public static void anim(Context context, ImageView imageView,
                            int offsetTimeMillis, int animResource) {
        Animation a = AnimationUtils.loadAnimation(context, animResource);
        a.setStartOffset(offsetTimeMillis);
        imageView.startAnimation(a);
    }

    public static void animMove(Context context, ImageView imageview,
                                float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                long durationMillis, long offSetdurationMillis) {
        TranslateAnimation animation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, fromXDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, toXDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, fromYDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, toYDelta);
        animation.setDuration(durationMillis);
        animation.setStartOffset(offSetdurationMillis);
        animation.setFillAfter(true);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.RESTART);
        imageview.startAnimation(animation);
    }

    public static Animation animMoveStop(Context context, ImageView imageview,
                                         float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                         long durationMillis, long offSetdurationMillis) {
        TranslateAnimation animation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, fromXDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, toXDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, fromYDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, toYDelta);
        animation.setDuration(durationMillis);
        animation.setStartOffset(offSetdurationMillis);
        animation.setFillAfter(true);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.RESTART);
        imageview.startAnimation(animation);

        return animation;
    }

    public static Animation animMoveStop(Context context, TextView textView,
                                         float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                         long durationMillis, long offSetdurationMillis) {
        TranslateAnimation animation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, fromXDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, toXDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, fromYDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, toYDelta);
        animation.setDuration(durationMillis);
        animation.setStartOffset(offSetdurationMillis);
        animation.setFillAfter(true);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.RESTART);
        textView.startAnimation(animation);

        return animation;
    }

    public static Animation animMoveStop(Context context, Button button,
                                         float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                         long durationMillis, long offSetdurationMillis) {
        TranslateAnimation animation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, fromXDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, toXDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, fromYDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, toYDelta);
        animation.setDuration(durationMillis);
        animation.setStartOffset(offSetdurationMillis);
        animation.setFillAfter(true);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.RESTART);
        button.startAnimation(animation);

        return animation;
    }

    public static void animMove(Context context, Button button,
                                float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                long durationMillis, long offSetdurationMillis) {
        TranslateAnimation animation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, fromXDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, toXDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, fromYDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, toYDelta);
        animation.setDuration(durationMillis);
        animation.setStartOffset(offSetdurationMillis);
        animation.setFillAfter(true);
        button.startAnimation(animation);
    }

    public static void animShake(Context context, LinearLayout llt_layout) {
        TranslateAnimation animation = new TranslateAnimation(-15f, 15f, 00, 0);
        animation.setDuration(50);
        animation.setRepeatCount(3);
        animation.setRepeatMode(Animation.REVERSE);
        llt_layout.startAnimation(animation);
    }

    public static void animMove(Context context, TextView textView,
                                float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                long durationMillis, long offSetdurationMillis) {
        TranslateAnimation animation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, fromXDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, toXDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, fromYDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, toYDelta);
        animation.setDuration(durationMillis);
        animation.setStartOffset(offSetdurationMillis);
        animation.setFillAfter(true);
        textView.startAnimation(animation);
    }

    public static void animMove(View v, float fromXDelta, float toXDelta,
                                float fromYDelta, float toYDelta, long durationMillis) {
        TranslateAnimation animation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, fromXDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, toXDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, fromYDelta,
                TranslateAnimation.RELATIVE_TO_PARENT, toYDelta);
        animation.setDuration(durationMillis);
        animation.setFillAfter(true);
        v.startAnimation(animation);
    }

    static void shuffleArray(int[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    static void shuffleArray(String[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }


    public static boolean isNetworkAvailable(final Context context) {

        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static String checkIfNull(String string) {
        if (string == null)
            string = "";
        return string;
    }

    public static void dialogAlert(Context context, String title, String message, final Runnable negative, final Runnable positive, boolean okButtonOnly) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);

        if (okButtonOnly) {
            alertDialogBuilder.setNeutralButton("OK", null);
        } else {

            alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (negative != null)
                        negative.run();
                }
            });
            alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (positive != null)
                        positive.run();
                }
            });
        }
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public static void setImageViewGrayScale(ImageView imageView, boolean setEnabled) {

        if (setEnabled) {
            imageView.setColorFilter(null);
            imageView.setAlpha(255);
        } else {
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);  //0 means grayscale
            ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
            imageView.setColorFilter(cf);
//            imageView.setAlpha(128);   // 128 = 0.5
        }
    }

    public static boolean validateEmailFormat(String emailStr) {
        Pattern pattern =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(emailStr);
        return matcher.find();
    }

    public static void delayRunnable(Runnable runnable, int seconds) {
        Handler mHandler;
        mHandler = new Handler();
        mHandler.postDelayed(runnable, seconds * 1000);

    }
}

// Hide softinput keyboard on touch outside
//@Override
//public boolean dispatchTouchEvent(MotionEvent event) {
//    View view = getCurrentFocus();
//    boolean ret = super.dispatchTouchEvent(event);
//
//    if (view instanceof EditText) {
//        View w = getCurrentFocus();
//        int scrcoords[] = new int[2];
//        w.getLocationOnScreen(scrcoords);
//        float x = event.getRawX() + w.getLeft() - scrcoords[0];
//        float y = event.getRawY() + w.getTop() - scrcoords[1];
//
//        if (event.getAction() == MotionEvent.ACTION_UP
//                && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w
//                .getBottom())) {
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(getWindow().getCurrentFocus()
//                    .getWindowToken(), 0);
//        }
//    }
//    return ret;
//}
