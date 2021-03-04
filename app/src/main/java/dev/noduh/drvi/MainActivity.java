package dev.noduh.drvi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "dev.noduh.drvi/MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting the image to the right picture
        ImageView map = findViewById(R.id.gradientMap);
        int imageResource = getResources().getIdentifier("drvi_bwgradient", "drawable", MainActivity.this.getPackageName());
        map.setImageResource(imageResource);

        // setting ScreenStuff variables and listeners
        ScreenStuff.bitmap = ((BitmapDrawable)map.getDrawable()).getBitmap(); // create bitmap and push it to ScreenStuff
        ScreenStuff.setListeners(map);
        ScreenStuff.leftPadding = map.getPaddingLeft();
        ScreenStuff.topPadding = map.getPaddingTop();
        Log.d(TAG, "width = " + map.getMaxWidth());
        ScreenStuff.rightLimit = map.getMaxWidth() + ScreenStuff.leftPadding;
        ScreenStuff.downLimit = map.getMaxHeight() + ScreenStuff.topPadding;

        // setting SoundPlay variables
        SoundPlay.vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); // setting the vibrator variable in SoundPlay to the right thing (must be done in MainActivity)
    }
}