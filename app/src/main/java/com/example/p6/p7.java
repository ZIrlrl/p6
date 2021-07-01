package com.example.p6;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;//추가

import com.google.zxing.integration.android.IntentIntegrator;//추가
import com.google.zxing.integration.android.IntentResult;//추가

import org.json.JSONException;//추가
import org.json.JSONObject;//추가

import static java.security.AccessController.getContext;

public class p7 extends AppCompatActivity {
    //view Objects
    private Button button; //다음
    private Button qr; //QR
    private TextView sendac,sendad,sendman,receiveac,receivead,receiveman;

    //qr code scanner object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p7);
        //view objects
        qr = findViewById(R.id.qr);
        sendac = findViewById(R.id.sendac);
        sendad = findViewById(R.id.sendad);
        sendman = findViewById(R.id.sendman);
        receiveac = findViewById(R.id.receiveac);
        receivead = findViewById(R.id.receivead);
        receiveman = findViewById(R.id.receiveman);

        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //qr onclick
        qr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //scan option
                qrScan.setPrompt("Scanning");
                //qrScan.setOrientationLocked(false);
                qrScan.initiateScan();
            }
        });
//여기부터
        Intent intent = getIntent();
        String str = intent.getStringExtra("str");

        sendac.setText(str);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(p7.this , MainActivity.class);
                startActivity(intent); //액티비티 이동.
            }
        });
 //여기까지
    }
    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(p7.this, "취소!", Toast.LENGTH_SHORT).show();
            }
            else {
                //qrcode 결과가 있으면
                Toast.makeText(p7.this, "스캔완료!", Toast.LENGTH_SHORT).show();
            }
            try {
                //data를 json으로 변환
                JSONObject obj = new JSONObject(result.getContents());
                sendman.setText(obj.getString("sendman"));
                sendac.setText(obj.getString("sendac"));
                sendad.setText(obj.getString("sendad"));
                receiveman.setText(obj.getString("receiveman"));
                receiveac.setText(obj.getString("receiveac"));
                receivead.setText(obj.getString("receivead"));
            } catch (JSONException e) {
                e.printStackTrace();
                //Toast.makeText(p7.this, result.getContents(), Toast.LENGTH_LONG).show();
                sendman.setText(result.getContents());
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}