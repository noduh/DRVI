package dev.noduh.drvi;

import android.content.Context;
import android.graphics.Color;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

public class SoundPlay {
    private static final String TAG = "dev.noduh.drvi/SoundPlay";
    private static ToneGenerator.Tone latestTone; // variable to hold the data associated to the tone that's currently being played
    public static Vibrator vibrator;
    private static boolean shouldVibrate = false;

    public static void transitionSound (boolean inBounds, boolean vibrate, int color) { // simple transition to a new tone while being able to stop the previous tone (or vibrate if needed)
        ToneGenerator.Tone newTone;
        if (inBounds) {
            int red = Color.red(color);
            int green = Color.green(color);
            int blue = Color.blue(color);
            if (latestTone != null) { // only needs to end the tone if the tone exists
                latestTone.end(); // end the old tone now that the new one is taken care of
            }
            newTone = generateSound(red, green, blue, "B/W"); // gets the new playing tone
            latestTone = newTone;
            vibrate(shouldVibrate); // vibrate if it needs to or don't if it doesn't (boolean set in generateSound)

        } else { // if it's out of bounds then basically stop everything
            if (latestTone != null) { // only needs to end the tone if the tone exists
                latestTone.end(); // end the old tone because it needs to be silent when it's out of bounds
            }
            vibrate(vibrate); // vibrate if it should
        }
    }

    public static void vibrate(boolean shouldVibrate) {
        if (shouldVibrate) {
            vibrator.vibrate(VibrationEffect.createOneShot(30000,VibrationEffect.DEFAULT_AMPLITUDE)); // start a vibration that will last at most 30 seconds (if it lasts longer then something is wrong lol)
        } else {
            vibrator.cancel(); // cancel the vibration
        }
    }

    public static ToneGenerator.Tone generateSound (int red, int green, int blue, String type) { // play sound based on a value (specifically color value)
        double LOWER_LIMIT = 100; // (in Hz)
        double TWELFTH_ROOT_OF_TWO = 1.059463094359;
        double FRACTION_OF_HALF_STEP = 6; // fraction of half step to increment upwards
        int VIBRATE = 0; // the color for it to vibrate at
        ToneGenerator.Tone tone = null;

        if (type.equals("B/W")) { // if it's going to generate sound based off of a black and white scale
            int bw = (red + green + blue)/3; // getting an average of all three to get a black and white scale
            if (bw != VIBRATE) {
                int frequency = (int) Math.round(LOWER_LIMIT * Math.pow(TWELFTH_ROOT_OF_TWO, bw / FRACTION_OF_HALF_STEP)); // for reference you can look at https://pages.mtu.edu/~suits/NoteFreqCalcs.html
                tone = new ToneGenerator().start(frequency);
                shouldVibrate = false;
            } else {
                shouldVibrate = true;
            }
        }

        return tone;
    }
}
