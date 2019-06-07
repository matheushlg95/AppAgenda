package br.com.senac.appagenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.senac.appagenda.Modelo.Evento;

public class EventoDAO extends SQLiteOpenHelper {
    public EventoDAO(Context context) {
        super(context, "dbevento",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create Table tbevento(id integer Primary Key,evento text,dia text,hora text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS tbevento";
        db.execSQL(sql);
    }
    public void inserirEvento(Evento eventos) {
        SQLiteDatabase inserir = getWritableDatabase();

        ContentValues dados = gtdados(eventos);

        inserir.insert("tbevento",null, dados);
    }
    public List<Evento> listevento(){
        String sql = "Select * from tbevento;";
        SQLiteDatabase db = getReadableDatabase();

        List<Evento> evento1 = new ArrayList<Evento>();
        Cursor c = db.rawQuery(sql, null);


        while(c.moveToNext()) {
            Evento eventos = new Evento();
            eventos.setId(c.getLong(c.getColumnIndex("id")));
            eventos.setEvento(c.getString(c.getColumnIndex("evento")));
            eventos.setDia(c.getString(c.getColumnIndex("dia")));
            eventos.setHora(c.getString(c.getColumnIndex("hora")));

            evento1.add(eventos);
        }
        c.close();
        return evento1;
    }
    public void deleta (Evento eventos){
        SQLiteDatabase db = getWritableDatabase();
        String [] params = {eventos.getId().toString()};
        db.delete("tbevento","id = ?",params);

    }
    public void altera (Evento eventos){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = gtdados(eventos);
        String [] params = {eventos.getId().toString()};
        db.update("tbevento",dados,"id = ?",params);
    }

    private ContentValues gtdados(Evento eventos) {
        ContentValues dados = new ContentValues();

        dados.put("evento", eventos.getEvento());
        dados.put("dia",eventos.getDia());
        dados.put("hora",eventos.getHora());

        return dados;
    }
}
