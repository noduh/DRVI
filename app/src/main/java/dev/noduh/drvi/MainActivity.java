package dev.noduh.drvi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView map = (ImageView) findViewById(R.id.gradientMap);
        int imageResource = getResources().getIdentifier("@drawable/drvi_bwgradient", null, this.getPackageName());
        map.setImageResource(imageResource);
        SoundPlay.vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }
}