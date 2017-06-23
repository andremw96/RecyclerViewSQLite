package com.example.andre.recyclerviewsqlite;

import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by andre on 23-Jun-17.
 */

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView img;
    TextView nameTxt, posTxt;
    ItemClickListener itemClickListener;

    public MyHolder(View itemView) {
        super(itemView);

        //ASSIGN
        img = (ImageView) itemView.findViewById(R.id.playerImage);
        nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
        posTxt = (TextView) itemView.findViewById(R.id.posTxt);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener = ic;
    }
}
