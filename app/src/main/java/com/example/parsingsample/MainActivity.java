package com.example.parsingsample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
Button okh;
EditText edtph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        okh=findViewById(R.id.ok);
        edtph=findViewById(R.id.editp);
        okh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String edtphstr=edtph.getText().toString();
                SharedPreferences shared=getApplicationContext().getSharedPreferences("pref",MODE_PRIVATE);
                SharedPreferences.Editor editor=shared.edit();
               editor.putString("key",edtphstr);
                editor.apply();
                Intent a=new Intent(MainActivity.this,lstvw.class);
                if(edtphstr.equals(""))
                {
                    edtph.setError("enter fields");
                }
                else {
                    startActivity(a);
                }

            }
        });
    }

}
