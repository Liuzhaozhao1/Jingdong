package com.bwie.Jingdong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.bwie.Jingdong.R;
import com.bwie.Jingdong.fragment.FiveFragment;
import com.bwie.Jingdong.fragment.FourFragment;
import com.bwie.Jingdong.fragment.OneFragment;
import com.bwie.Jingdong.fragment.ThreeFragment;
import com.bwie.Jingdong.fragment.TwoFragment;
import com.umeng.socialize.UMShareAPI;

public class SecondActivity extends AppCompatActivity {

    private FrameLayout fram_layout;
    private RadioGroup radio_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        fram_layout = (FrameLayout) findViewById(R.id.fram_layout);
        radio_group = (RadioGroup) findViewById(R.id.radio_group);
        radio_group.check(R.id.btn_1);
        getSupportFragmentManager().beginTransaction().replace(R.id.fram_layout,new OneFragment()).commit();

        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId)
                {
                    case R.id.btn_1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fram_layout,new OneFragment()).commit();
                        break;
                    case R.id.btn_2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fram_layout,new TwoFragment()).commit();
                        break;
                    case R.id.btn_3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fram_layout,new ThreeFragment()).commit();
                        break;
                    case R.id.btn_4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fram_layout,new FourFragment()).commit();
                        break;
                    case R.id.btn_5:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fram_layout,new FiveFragment()).commit();
                        break;
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
