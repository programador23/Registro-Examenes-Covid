package com.example.registrodeexamenescovid.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.registrodeexamenescovid.R;
import com.example.registrodeexamenescovid.dto.Paciente;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PacientesArrayAdapter extends ArrayAdapter<Paciente> {
    private Activity activity;
    private List<Paciente>pacientes;
    public PacientesArrayAdapter(@NonNull Activity context, int resource, @NonNull List<Paciente> objects) {
        super(context, resource, objects);
        this.activity=context;
        this.pacientes=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.activity.getLayoutInflater();
        View fila = inflater.inflate(R.layout.pacientes_list,null,true);

        TextView nombreTv = fila.findViewById(R.id.nombre_paciente_lv);
        
        ImageView imageCovid =fila.findViewById(R.id.imagen_pacie_lv);

        Paciente actual = pacientes.get(position);
        nombreTv.setText(actual.getNombre());
        Picasso.get().load(actual.getFoto()).resize(300,300)
                .centerCrop()
                .into(imageCovid);
        return fila;
    }
}
