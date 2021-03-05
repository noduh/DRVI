package dev.noduh.drvi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "dev.noduh.drvi/MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting the image to the right picture
        ImageView imageView = findViewById(R.id.gradientMap);
        int imageResource = getResources().getIdentifier("drvi_bwgradient", "drawable", MainActivity.this.getPackageName());
        imageView.setImageResource(imageResource);



        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // we only want to set these once the view has been created
            @Override
            public void onGlobalLayout() {
//                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this); // once we call this method in the listener we can stop listening

                // setting ScreenStuff variables and listeners
                ScreenStuff.bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap(); // create bitmap and push it to ScreenStuff
                ScreenStuff.imageView = imageView;
                ScreenStuff.setBoundaries();
                ScreenStuff.setListeners(imageView);
            }
        });

        // setting SoundPlay variables
        SoundPlay.vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); // setting the vibrator variable in SoundPlay to the right thing (must be done in MainActivity)
    }
}