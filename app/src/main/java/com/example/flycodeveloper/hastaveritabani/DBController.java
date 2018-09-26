package com.example.flycodeveloper.hastaveritabani;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBController extends SQLiteOpenHelper {

    private static final String VT = "hastalar";
    private static final int SURUM=1;
    public DBController(Context context) {
        super(context, VT, null, SURUM);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL("CREATE TABLE hasta (id INTEGER primary key AUTOINCREMENT, hastaadi TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS hasta");
    }

    //id'ye göre hastaadi
    public HashMap<String,String> getHastaBilgi(String id) {
        HashMap<String,String> hastaListe = new HashMap<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sorgu = "SELECT * FROM hasta WHERE id='"+id+"'";
        Cursor c = db.rawQuery(sorgu,null);
        if (c.moveToFirst()) {
            do {
                hastaListe.put("hastaadi",c.getString(1));
            }while (c.moveToNext());
        }
        return hastaListe;
    }

    //yeni hasta ekleme işlemi
    public void hastaEkle(HashMap<String,String> sorguDeger) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues degerler = new ContentValues();
        degerler.put("hastaadi",sorguDeger.get("hastaadi"));
        db.insert("hasta",null,degerler);
        db.close();
    }

    //hasta guncelle
    public int hastaGuncelle(HashMap<String,String> sorguDeger) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hastaadi",sorguDeger.get("hastaadi"));
        return db.update("hasta",values,"id"+ "=?",new String[]{sorguDeger.get("id")});
    }

    //hasta silme

    public void hastaSil(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM hasta WHERE id ='"+id+"'");
    }

    //tum hasta listesi

    public ArrayList<HashMap<String,String>> tumHastalar() {
        ArrayList<HashMap<String,String>> hastaListe;
        hastaListe = new ArrayList<>();
        String sorgu = "SELECT * FROM hasta";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sorgu,null);
        if (cursor.moveToFirst()) {
             do {
                HashMap<String,String> map = new HashMap<>();
                map.put("id",cursor.getString(0));
                map.put("hastaadi",cursor.getString(1));
                hastaListe.add(map);
             }while (cursor.moveToNext());

        }
        return hastaListe;
    }
}
