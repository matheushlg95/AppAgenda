package br.com.senac.appagenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.senac.appagenda.dao.EventoDAO;
import br.com.senac.appagenda.Modelo.Evento;

public class ListActivity extends Activity {
    private ListView listevento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        EventoDAO dao = new EventoDAO(this);
        List<Evento> eventos = dao.listevento();

        listevento = (ListView) findViewById(R.id.listevento);

        listevento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventos = (Evento) listevento.getItemAtPosition(position);
                Intent linkalterar = new Intent(ListActivity.this,FormularioActivity.class);
                linkalterar.putExtra("eventos",eventos);

                startActivity(linkalterar);
            }
        });
        ArrayAdapter<Evento> adapter = new ArrayAdapter<Evento>(this, android.R.layout.simple_list_item_activated_1, eventos);
        listevento.setAdapter(adapter);

        Button btback = findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent linkback = new Intent(ListActivity.this,FormularioActivity.class);
                startActivity(linkback);
            }
        });
        registerForContextMenu(listevento);

    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
                Evento eventos = (Evento) listevento.getItemAtPosition(info.position);
                Toast.makeText(ListActivity.this,eventos.getEvento() + " " + "foi deletado ", Toast.LENGTH_LONG).show();

                EventoDAO dao = new EventoDAO(ListActivity.this);
                dao.deleta(eventos);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void carregaLista(){
        EventoDAO dao = new EventoDAO(this);
        List<Evento> eventos = dao.listevento();
        dao.close();

        ArrayAdapter<Evento> adapter = new ArrayAdapter<Evento>(this,android.R.layout.simple_list_item_1, eventos);
        listevento.setAdapter(adapter);
    }

}
