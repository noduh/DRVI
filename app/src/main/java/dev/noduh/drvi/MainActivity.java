package dev.noduh.drvi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting the image to the right picture
        ImageView map = findViewById(R.id.gradientMap);
        int imageResource = getResources().getIdentifier("drvi_bwgradient", "drawable", MainActivity.this.getPackageName());
        map.setImageResource(imageResource);

        if (ScreenStuff.bitmap == null) { ScreenStuff.bitmap = ((BitmapDrawable)map.getDrawable()).getBitmap(); } // if the bitmap hasn't been created yet, create it and push it to ScreenStuff

        SoundPlay.vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); // setting the vibrator variable in SoundPlay to the right thing (must be done in MainActivity)

        ScreenStuff.setListeners(map);
    }
}