package com.example.ux_design.Models;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ux_design.R;
import com.example.ux_design.UI.Choisircreneau;
import com.example.ux_design.UI.MenuPatient;
import com.example.ux_design.UI.PrisedeRDV;

import java.util.List;

public class AdapterPrendreRDV extends RecyclerView.Adapter<AdapterPrendreRDV.ViewHolder> {

    private List<Medecin> localDataSet;
    private String emailPatient;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNomMedecin;
        private final TextView textViewAdresseMedecin;
        private final Button boutonCreerCreneau;


        public TextView getTextViewNomMedecin() {
            return textViewNomMedecin;
        }

        public TextView getTextViewAdresseMedecin() {
            return textViewAdresseMedecin;
        }

        public ViewHolder(View view) {
            super(view);
            textViewNomMedecin = (TextView) view.findViewById(R.id.textViewNomMedecin);
            textViewAdresseMedecin = (TextView) view.findViewById(R.id.textViewAdresseMedecin);
            boutonCreerCreneau = view.findViewById(R.id.buttonPrendreRDV);
        }



        public Button getBoutonCreerCreneau() {
            return boutonCreerCreneau;
        }

    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     * @param s
     */
    public AdapterPrendreRDV(List<Medecin> dataSet, String s) {
        localDataSet = dataSet;
        emailPatient = s;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_prendre_rdv, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextViewNomMedecin().setText("Dr."+localDataSet.get(position).getNom());
        viewHolder.getTextViewAdresseMedecin().setText(localDataSet.get(position).getAdresse());
        viewHolder.getBoutonCreerCreneau().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Choisircreneau.class);
                intent.putExtra("NomMedecin",localDataSet.get(position).getNom());
                intent.putExtra("AdresseMedecin",localDataSet.get(position).getAdresse());
                intent.putExtra("EmailMedecin",localDataSet.get(position).getEmail());
                intent.putExtra("EmailPatient",emailPatient);
                v.getContext().startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
