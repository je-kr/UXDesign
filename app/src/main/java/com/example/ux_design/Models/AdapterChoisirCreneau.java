package com.example.ux_design.Models;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ux_design.R;
import com.example.ux_design.UI.Confirmation;

import java.util.List;

public class AdapterChoisirCreneau extends RecyclerView.Adapter<AdapterChoisirCreneau.ViewHolder> {

    private List<Rendezvous> localDataSet;
    private String emailPatient;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewHeureRDV;
        private final Button boutonPrendreRDV;




        public ViewHolder(View view) {
            super(view);
            textViewHeureRDV= (TextView) view.findViewById(R.id.textView13);
            boutonPrendreRDV = view.findViewById(R.id.buttonPrendreRDVChoisirCreneau);
        }

        public TextView getTextViewHeureRDV() {
            return textViewHeureRDV;
        }

        public Button getBoutonPrendreRDV() {
            return boutonPrendreRDV;
        }



    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dateChoisie
     * @param dataSet String[] containing the data to populate views to be used
 * by RecyclerView.
     * @param emailPatient
     */
    public AdapterChoisirCreneau(List<Rendezvous> dataSet, String s) {
        localDataSet = dataSet;
        emailPatient = s;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterChoisirCreneau.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.choisir_creneau_recycler, viewGroup, false);


        return new AdapterChoisirCreneau.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AdapterChoisirCreneau.ViewHolder viewHolder, final int position) {

        String heure = localDataSet.get(position).getDaterdv().split(" ")[0];

        viewHolder.getTextViewHeureRDV().setText(heure);

        viewHolder.getBoutonPrendreRDV().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Confirmation.class);
                intent.putExtra("DateRDV",localDataSet.get(position).getDaterdv());
                intent.putExtra("EmailMedecin",localDataSet.get(position).getEmailMedecin());
                intent.putExtra("EmailPatient",emailPatient);
                v.getContext().startActivity(intent);
            }
        });




    }


    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
    // Return the size of your dataset (invoked by the layout manager)
}
