package com.example.registrodeexamenescovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.registrodeexamenescovid.adapters.PacientesArrayAdapter;
import com.example.registrodeexamenescovid.dao.PacientesDAO;
import com.example.registrodeexamenescovid.dao.PacientesDAOLista;
import com.example.registrodeexamenescovid.dao.PacientesDAOSQLite;
import com.example.registrodeexamenescovid.dto.Paciente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton agregarBtn;
    private ListView pacientes_lv;
    private List<Paciente>pacientes;
    private PacientesArrayAdapter adapter;
    private PacientesDAO pacientesDAO = new PacientesDAOSQLite(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.agregarBtn = findViewById(R.id.agregar_btn_fb);
        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this
                , CrearPacienteActivity.class));

            }
        });
    }
    protected void onResume(){
        super.onResume();
        pacientes = this.pacientesDAO.getAll();
        adapter = new PacientesArrayAdapter(this,R.layout.pacientes_list, pacientes);
        pacientes_lv = findViewById(R.id.pacientes_lv);
        pacientes_lv.setAdapter(adapter);
        pacientes_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,VerPacientesActivity.class);
                Paciente pacienteActual = pacientes.get(i);
                intent.putExtra("paciente",pacienteActual);
                startActivity(intent);
            }
        });

    }


}