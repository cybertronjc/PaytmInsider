package me.jagdishchoudhary.paytminsider.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import me.jagdishchoudhary.paytminsider.R;
import me.jagdishchoudhary.paytminsider.models.EventModel;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private List<EventModel> eventList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView event_name, event_type, event_price, event_time, event_venue;
        private ImageView event_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            event_image = itemView.findViewById(R.id.event_image);
            event_name = itemView.findViewById(R.id.event_name);
            event_price = itemView.findViewById(R.id.event_price);
            event_time = itemView.findViewById(R.id.event_time);
            event_type = itemView.findViewById(R.id.event_type);
            event_venue = itemView.findViewById(R.id.event_venue);



        }
    }

    public EventsAdapter(List<EventModel> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @NonNull
    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_event_card, parent, false);

        return new EventsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.ViewHolder holder, int position) {

        EventModel eventModel = eventList.get(position);
        holder.event_name.setText(eventModel.getName());
        holder.event_venue.setText(eventModel.getVenue_name());
        holder.event_time.setText(eventModel.getVenue_date_string());
        holder.event_price.setText("Rs. "+eventModel.getPrice_display_string());
        holder.event_type.setText(eventModel.getCategory().get("name").getAsString());

            Picasso.get().load(eventModel.getHorizontal_cover_image()).into(holder.event_image);


    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
