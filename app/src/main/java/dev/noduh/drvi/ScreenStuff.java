package dev.noduh.drvi;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ScreenStuff {
    private static final String TAG = "dev.noduh.drvi/ScreenStuff";
    public static Bitmap bitmap;
    public static ImageView imageView;
    // leftPadding, topPadding, rightLimit, and downLimit will be used to convert to bitmap coordinates and help determine if it's in bounds
    public static int leftLimit;
    public static int upLimit;
    public static int rightLimit;
    public static int downLimit;

    @SuppressLint("ClickableViewAccessibility")
    public static void setListeners(View view) {
        view.setOnTouchListener((v, event) -> {
            switch (event.getAction()) { // test what action is happening
                case MotionEvent.ACTION_DOWN: // if you touch the screen
//                case MotionEvent.ACTION_MOVE: // if you move your finger along it
                    int x = Math.round(event.getX());
                    int y = Math.round(event.getY());
                    x = getBitmapCoords(x, "x");
                    y = getBitmapCoords(y, "y");
                    if (x != -1 && y != -1) { // if it's in bounds
                        SoundPlay.transitionSound(true, false, getColor(x, y));
                    } else {
                        SoundPlay.transitionSound(false, true, -1); // stop the sound and no need to vibrate
                    }
                    break; // and then break
                case MotionEvent.ACTION_UP: // when you lift up your finger
                    SoundPlay.transitionSound(false, false, -1); // stop the sound and no need to vibrate
                    break;
            }
            return true; // done handling all the finger stuff
        });
    }

    public static int getColor (int x, int y) {
        int color = bitmap.getPixel(x, y); // returns the color without pre-multiplying
        return color;
    }

    public static int getBitmapCoords (int screenCoords, String xy) { // get the bitmap coordinates from the screen coordinates
        int bitmapCoords = -1; // -1 is out of bounds
        switch (xy) {
            case "x":
                if (leftLimit < screenCoords && screenCoords < rightLimit) { // if it's in bounds then continue
                    bitmapCoords = screenCoords - leftLimit; // adjusting coordinates by taking out padding
                }
                break;
            case "y":
                if (upLimit < screenCoords && screenCoords < downLimit) { // if it's in bounds then continue
                    bitmapCoords = screenCoords - upLimit; // adjusting coordinates by taking out padding
                }
                break;
        }
        return bitmapCoords;
    }

    public static void setBoundaries () { // figuring out where the boundaries
        double viewWidth = imageView.getWidth();
        double viewHeight = imageView.getHeight();
        double mapWidth = bitmap.getWidth();
        double mapHeight = bitmap.getHeight();
        double scale;
        int imageWidth;
        int imageHeight;

        // greater ratio than 1.0 means it's portrait, less than is landscape, equal to is square
        // the bigger ratio is the skinnier one
        double viewRatio = viewHeight / viewWidth;
        double mapRatio = mapHeight / mapWidth;


        if (viewRatio > mapRatio) {
            scale = viewWidth/mapWidth;
        } else {
            scale = viewHeight/mapHeight;
        }

        imageWidth = (int)(scale * mapWidth); // scale the width
        imageHeight = (int)(scale * mapHeight); // scale the height

        leftLimit = (int)(viewWidth/2) - (int)((0.0 + imageWidth)/2); // take the left half of the screen and subtract the part that's the image
        rightLimit = leftLimit + imageWidth;
        upLimit = (int)(viewHeight/2) - (int)((0.0 + imageHeight)/2); // take the top half of the screen and subtract the part that's the image
        downLimit = upLimit + imageHeight;
    }
}
