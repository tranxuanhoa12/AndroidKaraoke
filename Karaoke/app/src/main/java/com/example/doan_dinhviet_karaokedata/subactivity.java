package com.example.doan_dinhviet_karaokedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class subactivity extends AppCompatActivity {
    TextView txtmaso, txtbaihat, txtloibaihat, txttacgia;
    ImageButton btnthich, btnkhongthich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subactivity);
        txtmaso = findViewById(R.id.txtmaso);
        txtbaihat = findViewById(R.id.txtbaihat);
        txtloibaihat = findViewById(R.id.txtloibaihat);
        txttacgia = findViewById(R.id.txttacgia);
        btnthich = findViewById(R.id.btnthich);
        btnkhongthich = findViewById(R.id.btnkhongthich);
        Intent callerIntent1 = getIntent();
        Bundle backagecaller1 = callerIntent1.getBundleExtra("package");
        String maso = backagecaller1.getString("maso");
    //Truy van du lieu tu ma so -> Hien thi du lieu (bai hat, ten...);
        Cursor c = MainActivity.database.rawQuery("SELECT * FROM ArirangSongList WHERE MABH LIKE '"+maso+"' ", null);
        txtmaso.setText(maso);
        c.moveToFirst();
        txtbaihat.setText(c.getString(2));
        txtloibaihat.setText(c.getString(3));
        txttacgia.setText(c.getString(4));
        if(c.getInt(6) == 0){
            btnthich.setVisibility(View.INVISIBLE);
            btnkhongthich.setVisibility(View.VISIBLE);
        }else{
            btnkhongthich.setVisibility(View.INVISIBLE);
            btnthich.setVisibility(View.VISIBLE);
        }
        c.close();
        //xu ly su kien khi click vao thich va khong thih;
        //Cap nhat du lieu vao CSDL, thay doi trang thai hien thi cho Button thich va khong thich;
        btnthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 0);
                MainActivity.database.update("ArirangSongList", values, "MABH=?", new String[]{txtmaso.getText().toString()});
                btnthich.setVisibility(View.INVISIBLE);
                btnkhongthich.setVisibility(View.VISIBLE);
            }
        });
        btnkhongthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 1);
                MainActivity.database.update("ArirangSongList", values, "MABH=?", new String[]{txtmaso.getText().toString()});
                btnkhongthich.setVisibility(View.INVISIBLE);
                btnthich.setVisibility(View.VISIBLE);
            }
        });
    }
}