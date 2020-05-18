package me.jagdishchoudhary.paytminsider.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import me.jagdishchoudhary.paytminsider.R;
import me.jagdishchoudhary.paytminsider.WebActivity;
import me.jagdishchoudhary.paytminsider.models.EventModel;

import java.util.List;

public class FeaturedEventsAdapter extends RecyclerView.Adapter<FeaturedEventsAdapter.ViewHolder> {

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

    public FeaturedEventsAdapter(List<EventModel> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @NonNull
    @Override
    public FeaturedEventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_card_item, parent, false);

        return new FeaturedEventsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedEventsAdapter.ViewHolder holder, int position) {

        EventModel eventModel = eventList.get(position);
        holder.event_name.setText(eventModel.getName());
        holder.event_venue.setText(eventModel.getVenue_name());
        holder.event_time.setText(eventModel.getVenue_date_string());
        holder.event_price.setText("Rs. "+eventModel.getPrice_display_string());
        holder.event_type.setText(eventModel.getCategory().get("name").getAsString());
       // Glide.with(context).load(eventModel.getVertical_image()).into(holder.event_image);
        Picasso.get().load(eventModel.getVertical_image()).into(holder.event_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.putExtra("url", "https://insider.in/"+ eventModel.getSlug() + "/event");
                    context.startActivity(intent);

                }
                catch (Exception e){
                    Log.d("error", e.toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
