package dev.noduh.drvi;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ScreenStuff {
    private static final String TAG = "dev.noduh.drvi/ScreenStuff";
    public static Bitmap bitmap;


    @SuppressLint("ClickableViewAccessibility")
    public static void setListeners(View view) {
        view.setOnTouchListener((v, event) -> {
            float x = event.getX();
            float y = event.getY();
            SoundPlay.transitionSound(getColor(x, y));
            SoundPlay.transitionSound(-1); // stop the sound and no need to vibrate
            return true;
        });
    }

    public static int getColor (float xFloat, float yFloat) {
        int x = Math.round(xFloat); // set x an y values to integers
        int y = Math.round(yFloat); // set x and y values to integers

        int color = bitmap.getPixel(x, y); // returns the color without pre-multiplying

        Log.d(TAG, "" + color); // logging color in Logcat (unimportant line)
        return color;
    }
}
