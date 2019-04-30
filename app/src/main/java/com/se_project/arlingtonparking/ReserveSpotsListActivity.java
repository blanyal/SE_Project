package com.se_project.arlingtonparking;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReserveSpotsListActivity extends AppCompatActivity implements MyRecyclerViewAdapter2.ItemClickListener {
    Button return_button;
    private String new_username = "";
    private int type_string = 1;
    private int options_string = 1;
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter2 adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_reserve_spots_list);
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> extra = extras.getStringArrayList("key");
        final int type = Integer.parseInt(extra.get(0));
        final int options = Integer.parseInt(extra.get(1));
        final String user_name = extra.get(2);

        new_username = user_name;
        type_string = type;
        options_string = options;

        return_button = (Button) findViewById(R.id.return_button);
        recyclerView = (RecyclerView) findViewById(R.id.spots_results_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        UserDatabase rb = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "database-name").allowMainThreadQueries().build();

        ReservationDao reserDAO = rb.reservationDao();
        List<Reservation> reservations = reserDAO.getReservationType(type_string, false);

        adapter = new MyRecyclerViewAdapter2(this, reservations);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(view.getContext(), ViewReserveSpotActivity.class);
        ArrayList<String> extra = new ArrayList<>();
        extra.add(String.valueOf(type_string));
        extra.add(String.valueOf(options_string));
        extra.add(String.valueOf(adapter.getItem(position).getUid()));
        extra.add(new_username);

        intent.putExtra("key", extra);
        startActivity(intent);
    }

    // On pressing the back button
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ReserveSpotsActivity.class);
        intent.putExtra("key", new_username);
        startActivity(intent);
    }
}

class MyRecyclerViewAdapter2 extends RecyclerView.Adapter<MyRecyclerViewAdapter2.ViewHolder> {

    private List<Reservation> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter2(Context context, List<Reservation> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_reserve_spots_list_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Reservation reservation = mData.get(position);
        holder.datetime.setText(reservation.getDatetime());
        holder.area.setText(reservation.getArea());

        if (reservation.getType() == 1) {
            holder.type.setText("Basic");
        } else if (reservation.getType() == 2) {
            holder.type.setText("Premium");
        } else if (reservation.getType() == 3) {
            holder.type.setText("Access");
        } else {
            holder.type.setText("Midrange");
        }

        if (reservation.getOptions() == 1) {
            holder.options.setText("Camera");
        } else if (reservation.getOptions() == 2) {
            holder.options.setText("Cart");
        } else {
            holder.options.setText("History");
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView datetime, type, options, area;

        ViewHolder(View itemView) {
            super(itemView);
            datetime = itemView.findViewById(R.id.datetime);
            type = itemView.findViewById(R.id.type);
            options = itemView.findViewById(R.id.options);
            area = itemView.findViewById(R.id.area);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Reservation getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
