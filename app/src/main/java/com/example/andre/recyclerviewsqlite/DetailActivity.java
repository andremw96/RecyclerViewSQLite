package com.example.andre.recyclerviewsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import static android.R.attr.id;

public class DetailActivity extends AppCompatActivity {

    ImageView img;
    EditText nameTxt, posTxt;
    Button updateBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i = getIntent();

        final String name = i.getExtras().getString("NAME");
        final String pos = i.getExtras().getString("POSITION");
        final int id = i.getExtras().getInt("ID");

        updateBtn = (Button) findViewById(R.id.updateBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        nameTxt = (EditText) findViewById(R.id.nameEditTxt);
        posTxt = (EditText) findViewById(R.id.posEditTxt);

        //ASSIGN DATA TO THOSE VIEWS
        nameTxt.setText(name);
        posTxt.setText(pos);

        //UPDATE
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(id,nameTxt.getText().toString(),posTxt.getText().toString());
            }
        });

        //DELETE
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(id);
            }
        });
    }

    //UPDATE
    private void update(int id, String newName, String newPos)
    {
        DBAdapter db = new DBAdapter(this);
        db.openDB();
        long result = db.UPDATE(id,newName,newPos);

        if(result>0)
        {
            nameTxt.setText(newName);
            posTxt.setText(newPos);
            Snackbar.make(nameTxt, "Updated Succesfully",Snackbar.LENGTH_SHORT).show();
        }
        else
        {
            Snackbar.make(nameTxt, "Unable To Update",Snackbar.LENGTH_SHORT).show();
        }

        db.closeDB();
    }

    //DELETE
    private void delete(int id)
    {
        DBAdapter db = new DBAdapter(this);
        db.openDB();
        long result = db.DELETE(id);

        if(result>0)
        {
            this.finish();
        }
        else
        {
            Snackbar.make(nameTxt, "Unable To Delete",Snackbar.LENGTH_SHORT).show();
        }

        db.closeDB();
    }

}
