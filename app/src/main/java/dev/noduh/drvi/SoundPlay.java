package dev.noduh.drvi;

import android.content.Context;
import android.os.Vibrator;

public class SoundPlay {
    private static ToneGenerator.Tone latestTone; // variable to hold the data associated to the tone that's currently being played
    public static Vibrator vibrator;

    public void transitionSound (int newValue) { // simple transition to a new tone while being able to stop the previous tone (or vibrate if needed)
        ToneGenerator.Tone newTone = generateSound(newValue); // gets the new playing tone, or null if it should vibrate instead

        if (latestTone != null) { // only needs to end the tone if it's not already playing something
            latestTone.end();
        }
        latestTone = newTone; // setting the latest tone for future reference
        if (newTone != null) { // will vibrate only if needed
            vibrate(false);
        } else {
            vibrate(true);
        }
    }

    public static void vibrate(boolean shouldVibrate) {
        if (shouldVibrate) {
            // WIP
        } else {
            // WIP
        }
    }

    public static ToneGenerator.Tone generateSound (int value) { // play sound based on a value (specifically color value)
        ToneGenerator.Tone tone = null;

        // work in progress

        return tone; // will return null if it should vibrate instead
    }
}
