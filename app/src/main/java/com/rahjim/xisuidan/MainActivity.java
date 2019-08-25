package com.rahjim.xisuidan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btc;
    Button btn;
    Button btp;
    Button btdd;
    TextView tvtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(7);
//        getWindow().setFeatureInt(7, 2130903058);
        setContentView(R.layout.activity_main);
//        this.tvtitle=(TextView)this.findViewById(R.id.tvtitle);
//        this.tvtitle.setText("首页");
        this.btc = ((Button)findViewById(R.id.btci));
        this.btn = ((Button)findViewById(R.id.btnum));
        this.btp = ((Button)findViewById(R.id.btpu));
        this.btdd=(Button)findViewById(R.id.btdd);
        this.btc.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(MainActivity.this, ci.class);
                MainActivity.this.startActivity(localIntent);

            }
        });
        this.btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(MainActivity.this, num.class);
                MainActivity.this.startActivity(localIntent);
            }
        });
        this.btp.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent localIntent = new Intent(MainActivity.this, pu.class);
                MainActivity.this.startActivity(localIntent);
            }
        });
        this.btdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent localIntent=new Intent(MainActivity.this,didian.class);
                MainActivity.this.startActivity(localIntent);
            }
        });
    }
}
