package com.example.flycodeveloper.hastaveritabani;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class HastaVeriTabani extends ListActivity {
Context context = this;
    DBController controller = new DBController(context);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasta_veri_tabani);

        ArrayList<HashMap<String,String>> hastaliste = controller.tumHastalar();
        if (hastaliste.size() != 0) {
            ListView lv = getListView();
            ListAdapter adapter = new SimpleAdapter(context,hastaliste,R.layout.hasta_bilgi, new String[]{"id","hastaadi"},new int[]{R.id.hastaid,R.id.hastaAdi});
            setListAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    LinearLayout listItem = (LinearLayout)view;
                    TextView hastaid = (TextView) listItem.findViewById(R.id.hastaid);
                    String dId = hastaid.getText().toString().trim();
                    Intent intent = new Intent(context,HastaDuzenle.class);
                    intent.putExtra("hastaid",dId);
                    startActivity(intent);
                }
            });
        }
    }


    public void yeniHastaEkleme(View view) {
        Intent ii = new Intent(context,HastaEkle.class);
        startActivity(ii);
    }
}
