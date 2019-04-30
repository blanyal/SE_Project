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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchUsersResultsActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    Button return_button;
    private String new_username = "";
    private String last_name = "";
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search_users_results);
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> extra = extras.getStringArrayList("key");
        final String user_name = extra.get(1);
        final String lastn = extra.get(0);

        new_username = user_name;
        last_name = lastn;

        return_button = (Button) findViewById(R.id.return_button);
        recyclerView = (RecyclerView) findViewById(R.id.search_user_results_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        UserDatabase rb = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "database-name").allowMainThreadQueries().build();

        UserDao userDAO = rb.userDao();
        List<User> users = userDAO.searchUser(lastn);

        adapter = new MyRecyclerViewAdapter(this, users);
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
        Intent intent = new Intent(view.getContext(), AdminViewProfileActivity.class);
        ArrayList<String> extra = new ArrayList<>();
        extra.add(last_name);
        extra.add(new_username);
        extra.add(adapter.getItem(position).getUsername());
        intent.putExtra("key", extra);
        startActivity(intent);
    }

    // On pressing the back button
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SearchUsersActivity.class);
        intent.putExtra("key", new_username);
        startActivity(intent);
    }
}

class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<User> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<User> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_search_users_results_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mData.get(position);
        holder.username.setText(user.getUsername());
        holder.uta_id.setText(String.valueOf(user.getUta_id()));
        holder.firstn.setText(user.getFirstn());
        holder.lastn.setText(user.getLastn());
        holder.phone.setText(String.valueOf(user.getPhone()));
        holder.email.setText(user.getEmail());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView username, uta_id, firstn, lastn, phone, email;

        ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            uta_id = itemView.findViewById(R.id.uta_id);
            firstn = itemView.findViewById(R.id.firstn);
            lastn = itemView.findViewById(R.id.lastn);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    User getItem(int id) {
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
