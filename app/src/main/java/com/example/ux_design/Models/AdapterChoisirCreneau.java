package com.example.ux_design.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ux_design.R;

public class AdapterChoisirCreneau extends RecyclerView.Adapter<AdapterMedecinAgenda.ViewHolder> {

    private String[] localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView13);
        }

        public TextView getTextView() {
            return textView;
        }

    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public AdapterChoisirCreneau(String[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterMedecinAgenda.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.choisir_creneau_recycler, viewGroup, false);

        return new AdapterMedecinAgenda.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AdapterMedecinAgenda.ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(localDataSet[position]);


    }


    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
    // Return the size of your dataset (invoked by the layout manager)
}
