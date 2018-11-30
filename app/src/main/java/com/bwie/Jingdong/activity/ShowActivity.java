package com.bwie.Jingdong.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.Jingdong.R;
import com.squareup.picasso.Picasso;

public class ShowActivity extends AppCompatActivity {

    private TextView text_yonghu;
    private TextView text_name;
    private ImageView img_tou;
    private Button btn_show;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        text_yonghu = (TextView) findViewById(R.id.text_yonghu);
        text_name = (TextView) findViewById(R.id.text_name);
        img_tou = (ImageView) findViewById(R.id.img_tou);
        btn_show = (Button) findViewById(R.id.btn_show);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowActivity.this, ZhanActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Intent intent = getIntent();
        text_yonghu.setText(intent.getStringExtra("name"));
        text_name.setText(intent.getStringExtra("sex"));
        Picasso.with(context).load(intent.getStringExtra("icon")).into(img_tou);
    }
}
