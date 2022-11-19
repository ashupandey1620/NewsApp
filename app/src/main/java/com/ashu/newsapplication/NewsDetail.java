package com.ashu.newsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetail extends AppCompatActivity {
    String title,desc,content,imageURL,url;
    private TextView titleTV,subDescTV,contentTV;
    private ImageView newsIV;
    private Button readNewsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");
        content = getIntent().getStringExtra("content");
        imageURL = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");

        titleTV= findViewById(R.id.idTVTitle);
        subDescTV= findViewById(R.id.idEllaborate);
        contentTV = findViewById(R.id.idTVContent);
        newsIV = findViewById(R.id.idIVnews);
        readNewsBtn = findViewById(R.id.idBtnReadNews);

        Picasso.get().load(imageURL).into(newsIV);
        titleTV.setText(title);
        subDescTV.setText(desc);
        contentTV.setText(content);
        
        readNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                
            }
        });
        
        


                // idBtnReadNews;idIVnews;idEllaborate,idTVContent,idTVTitle;
    }
}