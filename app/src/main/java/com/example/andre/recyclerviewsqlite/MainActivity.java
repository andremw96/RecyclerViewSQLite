package com.example.andre.recyclerviewsqlite;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText nameTxt, posTxt;
    RecyclerView rv;
    MyAdapter adapter;
    ArrayList<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
                showDialog();
            }
        });

        //Recycler view
        rv = (RecyclerView) findViewById(R.id.myRecycler);

        //SET PROPERTIES
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
        adapter = new MyAdapter(this,players);

        retrieve();
    }

    private void showDialog()
    {
        Dialog d = new Dialog(this);

        //NO TITLE
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //LAYOUT OF DIALOG
        d.setContentView(R.layout.custom_layout);

        nameTxt = (EditText) d.findViewById(R.id.nameEditTxt);
        posTxt = (EditText) d.findViewById(R.id.posEditTxt);
        Button savebtn = (Button) d.findViewById(R.id.saveBtn);
        Button retrievebtn = (Button) d.findViewById(R.id.retrieveBtn);

        //ONCLICK LISTENER
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(nameTxt.getText().toString(), posTxt.getText().toString());
            }
        });

        retrievebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieve();
            }
        });

        //SHOW DIALOG
        d.show();
    }

    //SAVE
    private void save(String name, String pos)
    {
        DBAdapter db = new DBAdapter(this);

        //OPEN DB
        db.openDB();

        //INSERT DATA
        long result = db.add(name, pos);

        if(result>0)
        {
            nameTxt.setText("");
            posTxt.setText("");
        }
        else
        {
            Snackbar.make(nameTxt, "Unable to Insert", Snackbar.LENGTH_SHORT).show();
        }

        //CLOSE DB
        db.closeDB();

        //REFRESH DATA
        retrieve();
    }

    //RETRIEVE
    private void retrieve()
    {
        DBAdapter db = new DBAdapter(this);

        //OPEN DB
        db.openDB();

        players.clear();

        //SELECT
        Cursor c = db.getAllPlayers();

        //LOOP THRU THE DATA ADDING TO ARRAYLIST
        while (c.moveToNext())
        {
            int id = c.getInt(0);
            String name = c.getString(1);
            String pos = c.getString(2);

            //CREATE PLAYER
            Player p = new Player(name,pos,id);

            //ADD TO PLAYERS
            players.add(p);
        }

        //SET ADAPTER TO RECYCLE VIEW
        if(!(players.size()<1))
        {
            rv.setAdapter(adapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieve();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
