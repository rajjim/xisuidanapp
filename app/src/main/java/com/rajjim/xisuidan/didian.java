package com.rajjim.xisuidan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class didian extends AppCompatActivity {

    Button btret;
    Button bthistory;
    Button btstart;
    EditText et;
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
                finish();
            }
        });

        this.btstart = ((Button)findViewById(R.id.btstart));
        this.btstart.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent intent = new Intent(didian.this, ddjiyi.class);
                didian.this.startActivity(intent);
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
