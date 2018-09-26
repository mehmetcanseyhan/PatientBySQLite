package com.example.flycodeveloper.hastaveritabani;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class  HastaDuzenle extends AppCompatActivity {
    Context context = this;
    DBController controller = new DBController(context);
    EditText hastaAdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasta_duzenle);
        hastaAdi = findViewById(R.id.edtTextHastaAdiDuzenleSil);

        Intent iiii = getIntent();
        String hastaId = iiii.getStringExtra("hastaid");
      HashMap<String,String> hastaListe = controller.getHastaBilgi(hastaId);
        if (hastaListe.size() > 0) {
            hastaAdi.setText(hastaListe.get("hastaadi"));
        }

    }

    public void hastaDuzenle(View view) {
        HashMap<String,String> sorguDeger = new HashMap<>();
        Intent i = getIntent();
        String hastaId = i.getStringExtra("hastaid");
        sorguDeger.put("id",hastaId);
        sorguDeger.put("hastaadi",hastaAdi.getText().toString().trim());
        controller.hastaGuncelle(sorguDeger);
        anasayfaAc(view);
    }

    public void anasayfaAc(View view) {
        Intent iii = new Intent(context,HastaVeriTabani.class);
        startActivity(iii);
    }

    public void hastaSil(View view) {
    Intent ii = getIntent();
    String hastaId= ii.getStringExtra("hastaid");
    controller.hastaSil(hastaId);
    anasayfaAc(view);
    }
}
