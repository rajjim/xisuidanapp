package com.rahjim.xisuidan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class didian extends AppCompatActivity {

    Button btret;
    Button bthistory;
    Button btstart;
    EditText et;
    RadioGroup rg;
    ImageButton ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_didian);
        this.et = ((EditText)findViewById(R.id.et));

        this.bthistory = ((Button)findViewById(R.id.bthistory));
        this.bthistory.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent intent = new Intent(didian.this, ddhistory.class);
                didian.this.startActivity(intent);
            }
        });
        this.rg = ((RadioGroup)findViewById(R.id.rg));
        this.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
            {
                paramAnonymousInt = paramAnonymousRadioGroup.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)didian.this.findViewById(paramAnonymousInt);
                didian.this.et.setText(rb.getText());
            }
        });
        this.btstart = ((Button)findViewById(R.id.btstart));
        this.btstart.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent intent = new Intent(didian.this, ddjiyi.class);
                intent.putExtra("level", didian.this.et.getText().toString());
                didian.this.startActivity(intent);
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