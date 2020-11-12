package com.example.registrodeexamenescovid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.registrodeexamenescovid.dao.PacientesDAO;
import com.example.registrodeexamenescovid.dao.PacientesDAOSQLite;
import com.example.registrodeexamenescovid.dto.Paciente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CrearPacienteActivity extends AppCompatActivity {
    TextView tv;
    Spinner selector_area_trabajo;
    Switch switch_sintomas;
    Switch switch_tos;
    private Toolbar toolbar;
    private EditText nombretxt;
    private EditText apellidotxt;
    private EditText RUTtxt;
    private TextView fechatxt;
    private EditText temperaturatxt;
    private EditText presiontxt;
    private Button crearBtn;
    private TextView text_Switch_sintomas;
    private TextView text_Switch_tos;
    private PacientesDAO PacienDAO = new PacientesDAOSQLite(this);

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_paciente);
        tv = findViewById(R.id.fecha);
        selector_area_trabajo = this.findViewById(R.id.idSpinner_area_trabajo);

        ArrayList<String> SpinnerAreaTrabajo = new ArrayList<>();
        SpinnerAreaTrabajo.add("Atención de Publico");
        SpinnerAreaTrabajo.add("Otro");
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, SpinnerAreaTrabajo);
        this.selector_area_trabajo.setAdapter(adapter);
        this.switch_sintomas = findViewById(R.id.idSwitch_presenta_sintomas);
        this.switch_tos = findViewById(R.id.idSwitch_presenta_tos);
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        //INTERFAZ
        this.text_Switch_tos = findViewById(R.id.texto_switch_Tos);
        this.text_Switch_sintomas = findViewById(R.id.texto_switch);
        this.nombretxt = findViewById(R.id.nombre_paciente_txt_cr);
        this.apellidotxt = findViewById(R.id.apellido_paciente_txt_cr);
        this.RUTtxt = findViewById(R.id.rut_paciente_txt_cr);
        this.fechatxt = findViewById(R.id.fecha);
        this.temperaturatxt = findViewById(R.id.tempertura_paciente_txt_cr);
        this.presiontxt = findViewById(R.id.presion_paciente_txt_cr);
        this.crearBtn = findViewById(R.id.registrarBtn);

        this.crearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();
                String nombre = nombretxt.getText().toString().trim();
                if (nombre.isEmpty()) {
                    errores.add("Debe Ingresar un nombre");
                }
                String apellido = apellidotxt.getText().toString().trim();
                if (apellido.isEmpty()) {
                    errores.add("Debe Ingresar un apellido");
                }
                String rut = RUTtxt.getText().toString().trim();
                if (rut.isEmpty()) {
                    errores.add("Debe Ingresar un rut");
                }
                String fecha_examen = fechatxt.getText().toString().trim();
                if (fecha_examen.isEmpty()) {
                    errores.add("Debe Ingresar una Fecha de Examen");
                }
                int temperatura = 0;
                try {
                    temperatura = Integer.parseInt(temperaturatxt.getText().toString());

                } catch (Exception ex) {
                    errores.add("Debe ingresar una Temperatura Valida");
                }

                int presion = 0;
                try {
                    presion = Integer.parseInt(temperaturatxt.getText().toString());

                } catch (Exception ex) {
                    errores.add("Debe ingresar una Presión Arterial  Valida");
                }

                String sintomascovid = text_Switch_sintomas.getText().toString();

                if (errores.isEmpty()) {
                    //preguntar si tiene covid o no
                    //si lop tiene lo manda al creador con foto
                    //si no lo crea con una imagen vacia
                    if (sintomascovid == "Si") {

                        Paciente p = new Paciente();
                        p.setNombre(nombre);
                        p.setApellido(apellido);
                        p.setRut(rut);
                        p.setFecha_de_examen(fecha_examen);
                        p.setArea_de_trabajo(selector_area_trabajo.getSelectedItem().toString());
                        p.setSintomas(text_Switch_sintomas.getText().toString());
                        p.setTemperatura(temperatura);
                        p.setPresenta_tos(text_Switch_tos.getText().toString());
                        p.setPresion(presion);
                        p.setFoto("https://image.flaticon.com/icons/png/512/2659/2659980.png");
                        PacienDAO.save(p);
                        Intent intent = new Intent(CrearPacienteActivity.this, MainActivity.class);
                        startActivity(intent);

                    }else {
                        if (sintomascovid == "No") {

                            Paciente p = new Paciente();
                            p.setNombre(nombre);
                            p.setApellido(apellido);
                            p.setRut(rut);
                            p.setFecha_de_examen(fecha_examen);
                            p.setArea_de_trabajo(selector_area_trabajo.getSelectedItem().toString());
                            p.setSintomas(text_Switch_sintomas.getText().toString());
                            p.setTemperatura(temperatura);
                            p.setPresenta_tos(text_Switch_tos.getText().toString());
                            p.setPresion(presion);
                            p.setFoto(nombre);
                            PacienDAO.save(p);
                            Intent intent = new Intent(CrearPacienteActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }else {
                    AlertDialog.Builder alertBuilder =
                            new AlertDialog.Builder(CrearPacienteActivity.this);
                    String mensaje = "";
                    for (String e : errores) {
                        mensaje += "-" + e + "\n";

                    }
                    alertBuilder.setTitle("Errores de validación")
                            .setMessage(mensaje)
                            .setPositiveButton("Aceptar", null)
                            .create()
                            .show();
                }

                //crear paciente
            /*    Paciente p = new Paciente();
                p.setNombre(nombretxt.getText().toString());
                p.setApellido(apellidotxt.getText().toString());
                p.setRut(RUTtxt.getText().toString());
                p.setFecha_de_examen(fechatxt.getText().toString());
                //p.setArea_de_trabajo(nombretxt.getText().toString());
                p.setArea_de_trabajo(selector_area_trabajo.getSelectedItem().toString());
                p.setSintomas(text_Switch_sintomas.getText().toString());
                //text_Switch_sintomas.getText().toString()
                p.setTemperatura(Integer.parseInt(temperaturatxt.getText().toString()));
                p.setPresenta_tos(text_Switch_tos.getText().toString());
                p.setPresion(Integer.parseInt(presiontxt.getText().toString()));
                p.setFoto(nombretxt.getText().toString());
                PacienDAO.save(p);
                Intent intent = new Intent(CrearPacienteActivity.this, MainActivity.class);
                startActivity(intent);*/

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void abrirCalendario(View view) {
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(CrearPacienteActivity.this, R.style.styleCalendario, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha_selecionada = dayOfMonth + "-" + month + "-" + year;

                String fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                tv.setText(fecha_selecionada);

            }
        }, anio, mes, dia);
        dpd.show();

    }
    public void switch_sintomas(View view) {
        if (view.getId() == R.id.idSwitch_presenta_sintomas) {
            if (switch_sintomas.isChecked()) {
                switch_sintomas.setText("Si");
                text_Switch_sintomas.setText("Si");


            } else {
                switch_sintomas.setText("No");
                text_Switch_sintomas.setText("No");
            }
        }
    }
    public void switch_tos(View view) {
        if (view.getId() == R.id.idSwitch_presenta_tos) {
            if (switch_tos.isChecked()) {
                switch_tos.setText("Si");
                text_Switch_tos.setText("Si");
            } else {
                switch_tos.setText("No");
                text_Switch_tos.setText("No");
            }
        }
    }
}
