package com.example.covid_19apps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19apps.Activities.MainActivity;
import com.example.covid_19apps.Model.User;
import com.example.covid_19apps.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {


    private Context mCtx;
    private List<User> userList;

    public UserAdapter(Context mCtx, List<User> userList) {
        this.mCtx = mCtx;
        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_data, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = userList.get(position);

        //loading the image

        holder.provinsi.setText(user.getProvinsi());
        holder.positif.setText(String.valueOf(user.getPositif()));
        holder.meninggal.setText(String.valueOf(user.getMeninggal()));
        holder.sembuh.setText(String.valueOf(user.getSembuh()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView provinsi, positif, meninggal, sembuh;

        public UserViewHolder(View itemView) {
            super(itemView);

            provinsi = itemView.findViewById(R.id.provinsi);
            positif = itemView.findViewById(R.id.positif);
            meninggal = itemView.findViewById(R.id.meninggal);
            sembuh = itemView.findViewById(R.id.sembuh);
        }
    }

}

