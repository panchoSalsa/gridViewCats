package com.example.franciscofranco.gridview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DisplayImage extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        imageView = (ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        Picasso.with(this)
                .load(url)
                .skipMemoryCache()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
