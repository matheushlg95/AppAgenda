package br.com.senac.appagenda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.senac.appagenda.Modelo.Evento;
import br.com.senac.appagenda.dao.EventoDAO;

public class FormularioActivity extends Activity {
    private FormActivity helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        helper = new FormActivity(this);

        Intent intent = getIntent();
        Evento  eventos = (Evento) intent.getSerializableExtra("eventos");

        if(eventos != null){
            helper.alterform(eventos);
            Toast.makeText(FormularioActivity.this, "Está tudo certo",Toast.LENGTH_LONG).show();
        }

        Button botaosalvar = (Button) findViewById(R.id.btsave);
        botaosalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Evento eventos = helper.getEvento();
                EventoDAO dao = new EventoDAO(FormularioActivity.this);
                if (eventos.getId() != null){
                    dao.altera(eventos);
                    Toast.makeText(FormularioActivity.this,eventos.getEvento() + "foi alterado.",Toast.LENGTH_LONG).show();
                }else{
                    dao.inserirEvento(eventos);
                    Toast.makeText(FormularioActivity.this, "O evento " + eventos.getEvento() + " foi salvo.", Toast.LENGTH_SHORT).show();
                }

                dao.close();




            }
        });

        Button botaolista = (Button) findViewById(R.id.btlist);
        botaolista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linklista = new Intent(FormularioActivity.this, ListActivity.class);
                startActivity(linklista);
            }
        });
    }
}
