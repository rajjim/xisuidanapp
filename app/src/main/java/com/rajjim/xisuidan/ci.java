package com.rajjim.xisuidan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class ci extends AppCompatActivity {

    Button btret;
    Button bthistory;
    Button btstart;
    EditText et;
    RadioGroup rg;
    ImageButton ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ci);
        this.et = ((EditText)findViewById(R.id.et));

        this.bthistory = ((Button)findViewById(R.id.bthistory));
        this.bthistory.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent intent = new Intent(ci.this, cihistory.class);
                ci.this.startActivity(intent);
                finish();
            }
        });
        this.rg = ((RadioGroup)findViewById(R.id.rg));
        this.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
            {
                paramAnonymousInt = paramAnonymousRadioGroup.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)ci.this.findViewById(paramAnonymousInt);
                ci.this.et.setText(rb.getText());
            }
        });
        this.btstart = ((Button)findViewById(R.id.btstart));
        this.btstart.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent intent = new Intent(ci.this, cijiyi.class);
                int level=5;
                String levelstring=ci.this.et.getText().toString().trim();
                switch(levelstring){
                    case "5个词":{
                        level = 5;
                        break;
                    }
                    case "10个词":{
                        level = 10;
                        break;
                    }
                    case "20个词":{
                        level = 20;
                        break;
                    }
                    case "50个词":{
                        level = 50;
                        break;
                    }
                    case "100个词":{
                        level = 100;
                        break;
                    }
                }
                intent.putExtra("level", level);
                ci.this.startActivity(intent);
                finish();
            }
        });
        this.btret=(Button)findViewById(R.id.btret);
        this.btret.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                finish();
            }
        });
    }
}
