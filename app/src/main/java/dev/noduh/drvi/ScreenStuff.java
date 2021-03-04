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
    // leftPadding, topPadding, rightLimit, and downLimit will be used to convert to bitmap coordinates and help determine if it's in bounds
    public static int leftPadding;
    public static int topPadding;
    public static int rightLimit;
    public static int downLimit;

    @SuppressLint("ClickableViewAccessibility")
    public static void setListeners(View view) {
        view.setOnTouchListener((v, event) -> {
            switch (event.getAction()) { // test what action is happening
                case MotionEvent.ACTION_DOWN: // if you touch the screen
                case MotionEvent.ACTION_MOVE: // if you move your finger along it
                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    x = getBitmapCoords(x, "x");
                    y = getBitmapCoords(y, "y");
                    if (!(x == -1 && y == -1)) { // if it's in bounds
                        SoundPlay.transitionSound(getColor(x, y));
                    } else {
                        SoundPlay.transitionSound(-1); // stop the sound and no need to vibrate
                    }
                    break; // and then break
                case MotionEvent.ACTION_UP: // when you lift up your finger
                    SoundPlay.transitionSound(-1); // stop the sound and no need to vibrate
                    break;
            }
            return true; // done handling all the finger stuff
        });
    }

    public static int getColor (int x, int y) {
        int color = bitmap.getPixel(x, y); // returns the color without pre-multiplying

//        Log.d(TAG, "" + color); // logging color (unimportant line)
        return color;
    }

    public static int getBitmapCoords (int screenCoords, String xy) { // get the bitmap coordinates from the screen coordinates
        int bitmapCoords = -1; // -1 is out of bounds
        switch (xy) {
            case "x":
                if (leftPadding < screenCoords || screenCoords < rightLimit) { // if it's in bounds then continue
                    bitmapCoords = screenCoords - leftPadding; // adjusting coordinates by taking out padding
                }
                break;
            case "y":
                if (topPadding < screenCoords || screenCoords < downLimit) { // if it's in bounds then continue
                    bitmapCoords = screenCoords - topPadding; // adjusting coordinates by taking out padding
                }
                break;
        }
        Log.d(TAG, "\nscreenCoords " + xy + ": " + screenCoords + "\nbitmapCoords " + xy + ": " + bitmapCoords);
        return bitmapCoords;
    }
}
