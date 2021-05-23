package com.example.ux_design.Models;

        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TextView;

        import androidx.recyclerview.widget.RecyclerView;

        import com.example.ux_design.R;

        import java.util.List;

public class AdapterPatientAgenda extends RecyclerView.Adapter<AdapterPatientAgenda.ViewHolder> {

    private List<tupleRDVPatient> listRDVPatient;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewNomMedecin;
        private final TextView textViewAdresseMedecin;
        private final TextView textViewHeureRDV;



        public ViewHolder(View view) {
            super(view);
            textViewNomMedecin = (TextView) view.findViewById(R.id.textViewNomMedecinAgendaPatient);
            textViewAdresseMedecin = (TextView) view.findViewById(R.id.textViewAdresseMedecinAgendaPatient);
            textViewHeureRDV = (TextView) view.findViewById(R.id.textViewHeureAgendaPatient);
        }

        public TextView getTextViewNomMedecin() {
            return textViewNomMedecin;
        }

        public TextView getTextViewAdresseMedecin() {
            return textViewAdresseMedecin;
        }

        public TextView getTextViewHeureRDV() {
            return textViewHeureRDV;
        }



    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public AdapterPatientAgenda(List<tupleRDVPatient> dataSet) {
        listRDVPatient = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_agenda_patient, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        String heure = listRDVPatient.get(position).getDaterdv().split(" ")[0];

        viewHolder.getTextViewHeureRDV().setText(heure);
        viewHolder.getTextViewNomMedecin().setText(listRDVPatient.get(position).getNom());
        viewHolder.getTextViewAdresseMedecin().setText(listRDVPatient.get(position).getAdresse());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listRDVPatient.size();
    }
}
