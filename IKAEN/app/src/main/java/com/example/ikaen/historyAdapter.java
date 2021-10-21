package com.example.ikaen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.type.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class historyAdapter extends FirebaseRecyclerAdapter<history,historyAdapter.historyViewholder> {

    public historyAdapter(
            @NonNull FirebaseRecyclerOptions<history> options)
    {
        super(options);

    }

    @Override
    protected void
    onBindViewHolder(@NonNull historyAdapter.historyViewholder holder,
                     int position, @NonNull history model) {


        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")

        if (model.getActivity() == "1"){
            holder.activity.setText("The device is moving");
        }
        else{
            holder.activity.setText("The device is in idle state");
        }


        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        String formatstring = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(formatstring);
        String date = format.format(new Date(Long.parseLong(model.getTimestamp())));
        holder.timestamp.setText(date);

        // Add age from model class (here
        // "person.class")to appropriate view




    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public historyViewholder
    onCreateViewHolder(@NonNull ViewGroup history,
                       int viewType)
    {
        View view
                = LayoutInflater.from(history.getContext())
                .inflate(R.layout.history, history, false);
        return new historyAdapter.historyViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class historyViewholder
            extends RecyclerView.ViewHolder {
        TextView activity,timestamp;

        public historyViewholder(@NonNull View itemView)
        {
            super(itemView);

            activity = itemView.findViewById(R.id.activityTV);
            timestamp = itemView.findViewById(R.id.timestampTV);

        }
    }
}
