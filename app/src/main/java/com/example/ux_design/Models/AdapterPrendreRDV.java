package com.example.ux_design.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ux_design.R;

import java.util.List;

public class AdapterPrendreRDV extends RecyclerView.Adapter<AdapterPrendreRDV.ViewHolder> {

    private List<Medecin> localDataSet;

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
            boutonCreerCreneau = view.findViewById(R.id.buttonCreerCreneau);
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
     */
    public AdapterPrendreRDV(List<Medecin>  dataSet) {
        localDataSet = dataSet;
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
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
