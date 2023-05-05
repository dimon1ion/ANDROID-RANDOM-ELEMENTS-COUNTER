package com.my.mw_random_elements;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int count = 0;
    int maxCount = 20;
    Button button;
    List<String> stringList;
    List<Integer> styles;
    List<Integer> sizes;
    List<String> colors;

    String backgroundColor;


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
        outState.putFloat("size", button.getTextSize());
        outState.putString("text", button.getText().toString());
        outState.putInt("style", button.getTypeface().getStyle());
        outState.putInt("textColor", button.getCurrentTextColor());
        if (backgroundColor != null){
            outState.putString("backgroundColor", backgroundColor);
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("count", 0);
        getSupportActionBar().setTitle(getString(R.string.count) + ++count + "/" + maxCount);
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX ,savedInstanceState.getFloat("size", button.getTextSize()));
        button.setText(savedInstanceState.getString("text", button.getText().toString()));
        button.setTypeface(Typeface.DEFAULT, savedInstanceState.getInt("style", button.getTypeface().getStyle()));
        button.setTextColor(savedInstanceState.getInt("textColor", button.getCurrentTextColor()));
        String bgColor = savedInstanceState.getString("backgroundColor");
        if (bgColor != null){
            button.setBackgroundColor(Color.parseColor(bgColor));
            backgroundColor = bgColor;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stringList = Arrays.asList(getString(R.string.hello),
                getString(R.string.hi),
                getString(R.string.why),
                getString(R.string.joke),
                getString(R.string.pls_stop),
                getString(R.string.close_app),
                getString(R.string.restart),
                getString(R.string.start));
        styles = Arrays.asList(Typeface.BOLD, Typeface.ITALIC, Typeface.NORMAL);
        sizes = Arrays.asList(10,20,30,40,50,60);
        button = findViewById(R.id.btn_one);
        colors = Arrays.asList("#000000",
                "#FFFFFF",
                "#F52367",
                "#ABCDEF");
        button.setOnClickListener(view -> {

            getSupportActionBar().setTitle(getString(R.string.count) + ++count + "/" + maxCount);
            if (count >= maxCount){
                button.setEnabled(false);
                button.setText(R.string.played_out);
                return;
            }
            Random random = new Random();
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizes.get(random.nextInt(sizes.size())));
            button.setText(stringList.get(random.nextInt(stringList.size())));
            button.setTypeface(Typeface.DEFAULT,
                    styles.get(random.nextInt(styles.size())));
            int randForeground, randBackground;
            randForeground = random.nextInt(colors.size());
            randBackground = random.nextInt(colors.size());
            if (randForeground == randBackground){
                randBackground++;
                if (randBackground == colors.size()){
                    randBackground = 0;
                }
            }
            backgroundColor = colors.get(randBackground);
            button.setTextColor(Color.parseColor(colors.get(randForeground)));
            button.setBackgroundColor(Color.parseColor(colors.get(randBackground)));
        });



    }

}