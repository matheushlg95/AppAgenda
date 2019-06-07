package br.com.senac.appagenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button botaocomecar = (Button) findViewById(R.id.btstart);
        botaocomecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linkcomecar = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(linkcomecar);
            }
        });

    }
}
