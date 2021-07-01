package com.example.p6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;





public class MainActivity extends AppCompatActivity {

    private Button button2;
    private EditText strstr;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        strstr = findViewById(R.id.strstr);


        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = strstr.getText().toString();
                Intent intent = new Intent(MainActivity.this , p7.class);
                intent.putExtra("str",str);
                startActivity(intent); //액티비티 이동.
            }

        });
    }

}