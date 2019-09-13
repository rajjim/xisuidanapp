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

public class num extends AppCompatActivity {

    Button btret;
    Button bthistory;
    Button btstart;
    EditText et;
    RadioGroup rg;
    ImageButton ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num);
        this.et = ((EditText)findViewById(R.id.et));

        this.bthistory = ((Button)findViewById(R.id.bthistory));
        this.bthistory.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent intent = new Intent(num.this, numhistory.class);
                num.this.startActivity(intent);
                finish();
            }
        });
        this.rg = ((RadioGroup)findViewById(R.id.rg));
        this.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
            {
                paramAnonymousInt = paramAnonymousRadioGroup.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)num.this.findViewById(paramAnonymousInt);
                num.this.et.setText(rb.getText());
            }
        });
        this.btstart = ((Button)findViewById(R.id.btstart));
        this.btstart.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent intent = new Intent(num.this, numjiyi.class);
                int level=5;
                String levelstring=num.this.et.getText().toString().trim();
                switch(levelstring){
                    case "10个数字":{
                        level = 10;
                        break;
                    }
                    case "20个数字":{
                        level = 20;
                        break;
                    }
                    case "40个数字":{
                        level = 40;
                        break;
                    }
                    case "100个数字":{
                        level = 100;
                        break;
                    }
                    case "200个数字":{
                        level = 200;
                        break;
                    }
                }
                intent.putExtra("level", level);
                num.this.startActivity(intent);
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
