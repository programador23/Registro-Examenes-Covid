package com.example.registrodeexamenescovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.registrodeexamenescovid.dto.Paciente;
import com.squareup.picasso.Picasso;

public class VerPacientesActivity extends AppCompatActivity {

   private TextView nombreTxt;
    private Toolbar toolbar;
    private ImageView imagePacie;
    private TextView apellidoTxt;
    private TextView RUTTxt;
    private TextView fechaTxt;
    private TextView areaTranajoTxt;
    private TextView sintomasTxt;
    private TextView tosTxt;
    private TextView temperaturaTxt;
    private TextView presionTxt;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pacientes);
        this.nombreTxt = findViewById(R.id.nombrePacienteTxt);
        this.apellidoTxt = findViewById(R.id.apellidoPacienteTxt);
        this.RUTTxt = findViewById(R.id.RUTPacienteTxt);
        this.fechaTxt = findViewById(R.id.fechaPacienteTxt);
        this.areaTranajoTxt = findViewById(R.id.areaPacienteTxt);
        this.sintomasTxt = findViewById(R.id.sintomasPacienteTxt);
        this.tosTxt = findViewById(R.id.tosPacienteTxt);
        this.temperaturaTxt = findViewById(R.id.temperaturaPacienteTxt);
        this.presionTxt = findViewById(R.id.presionPacienteTxt);
        this.toolbar = findViewById(R.id.toolbar);
        this.imagePacie = findViewById(R.id.image_pacie_view);
        this.setSupportActionBar(this.toolbar );
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(getIntent() !=null){
            Paciente paciente = (Paciente) getIntent().getSerializableExtra("paciente");

            Picasso.get().load(paciente.getFoto()).resize(100,100)
                    .centerCrop().into(this.imagePacie);

            this.nombreTxt.setText(paciente.getNombre());
            this.apellidoTxt.setText(paciente.getApellido());
            this.RUTTxt.setText(paciente.getRut());
            this.fechaTxt.setText(paciente.getFecha_de_examen());
            this.areaTranajoTxt.setText(paciente.getArea_de_trabajo());
            this.sintomasTxt.setText("si");
            this.tosTxt.setText("si");
            this.temperaturaTxt.setText("°"+paciente.getTemperatura());
            this.presionTxt.setText(" "+paciente.getPresion());


        }
    }
}