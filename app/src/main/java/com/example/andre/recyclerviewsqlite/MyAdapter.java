package com.example.andre.recyclerviewsqlite;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by andre on 23-Jun-17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Player> players;

    public MyAdapter(Context ctx, ArrayList<Player> players)
    {
        //ASSIGN THEM LOCALLY
        this.c = ctx;
        this.players = players;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //VIEW OBJECT FROM XML
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model,null);

        //HOLDER
        MyHolder holder = new MyHolder(v);

        return holder;
    }

    //BIND DATA TO VIEWA
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.posTxt.setText(players.get(position).getPosition());
        holder.nameTxt.setText(players.get(position).getName());

        //HANDLE ITEM CLICKS
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos)
            {
                //OPEN DETAIL ACTIVITY AND PASS DATA

                //CREATE INTENT
                Intent i = new Intent(c, DetailActivity.class);

                //LOAD DATA TO INTENT
                i.putExtra("NAME",players.get(pos).getName());
                i.putExtra("POSITION",players.get(pos).getPosition());
                i.putExtra("ID",players.get(pos).getId());

                //START ACTIVITY
                c.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}
