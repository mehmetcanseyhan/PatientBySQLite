package com.example.flycodeveloper.hastaveritabani;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

public class HastaEkle extends AppCompatActivity {

    Context context = this;
    DBController controller = new DBController(context);
    EditText hastaAdi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasta_ekle);
         hastaAdi = findViewById(R.id.edtTextHastaAdiEkle);
    }

    public  void yeniHastaEkle(View view) {
        HashMap<String,String> sorgu  = new HashMap<>();
        sorgu.put("hastaadi",hastaAdi.getText().toString().trim());
        controller.hastaEkle(sorgu);
        anasayfaAc(view);
    }

    public void anasayfaAc(View view) {
        Intent i = new Intent(context,HastaVeriTabani.class);
        startActivity(i);
    }
}
