package br.com.senac.appagenda;

import android.widget.EditText;

import br.com.senac.appagenda.Modelo.Evento;

public class FormActivity {
    private final EditText evento;
    private  final EditText dia;
    private final EditText hora;

    private Evento eventos;

    public FormActivity(FormularioActivity Activity){
        evento = Activity.findViewById(R.id.evento);
        dia = Activity.findViewById(R.id.dia);
        hora = Activity.findViewById(R.id.hora);

        eventos = new Evento();
    }
    public Evento getEvento(){
        eventos.setEvento(evento.getText().toString());
        eventos.setDia(dia.getText().toString());
        eventos.setHora(hora.getText().toString());

        return eventos;
    }

    public void alterform(Evento eventos){
        evento.setText(eventos.getEvento());
        dia.setText(eventos.getDia());
        hora.setText(eventos.getHora());

        this.eventos=eventos;
    }
}
