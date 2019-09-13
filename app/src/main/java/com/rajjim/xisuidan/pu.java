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

public class pu extends AppCompatActivity {

    Button btret;
    Button bthistory;
    Button btstart;
    EditText et;
    RadioGroup rg;
    ImageButton ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pu);
        this.et = ((EditText)findViewById(R.id.et));

        this.bthistory = ((Button)findViewById(R.id.bthistory));
        this.bthistory.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent intent = new Intent(pu.this, puhistory.class);
                pu.this.startActivity(intent);
                finish();
            }
        });
        this.rg = ((RadioGroup)findViewById(R.id.rg));
        this.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
            {
                paramAnonymousInt = paramAnonymousRadioGroup.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)pu.this.findViewById(paramAnonymousInt);
                pu.this.et.setText(rb.getText());
            }
        });
        this.btstart = ((Button)findViewById(R.id.btstart));
        this.btstart.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent intent = new Intent(pu.this, pujiyi.class);
                int level=5;
                String levelstring=pu.this.et.getText().toString().trim();
                switch(levelstring){
                    case "6张牌":{
                        level = 6;
                        break;
                    }
                    case "12张牌":{
                        level = 12;
                        break;
                    }
                    case "24张牌":{
                        level = 24;
                        break;
                    }
                    case "36张牌":{
                        level = 36;
                        break;
                    }
                    case "52张牌":{
                        level = 52;
                        break;
                    }
                }
                intent.putExtra("level", level);
                pu.this.startActivity(intent);
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
