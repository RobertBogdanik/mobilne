package com.example.galeria;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    int[] images = {
            R.drawable.pumpkins_8338100_640,
            R.drawable.beach_9027513_640,
            R.drawable.cat_8579018_640,
            R.drawable.snake_9059936_640
    };
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("currentIndex");
        }

        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(images[currentIndex]);

        Button left = (Button) findViewById(R.id.left);
        Button right = (Button) findViewById(R.id.right);

        left.setOnClickListener(v -> {
            currentIndex = (currentIndex - 1 + images.length) % images.length;
            image.setImageResource(images[currentIndex]);
        });

        right.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % images.length;
            image.setImageResource(images[currentIndex]);
        });

        TextView godzina = (TextView) findViewById(R.id.godzina);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                godzina.setText(format.format(new Date()));
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentIndex", currentIndex);
    }
}