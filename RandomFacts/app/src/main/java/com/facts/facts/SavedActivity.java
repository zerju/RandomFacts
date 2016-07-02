package com.facts.facts;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SavedActivity extends Activity {


    private DatabaseHelper db;
    private List<String> facts;
    private ListView listView;
    public static String savedF;
    float historicX = Float.NaN, historicY = Float.NaN;
    static final int DELTA = 50;
    enum Direction {LEFT, RIGHT;}
    private Button homeButton;
    private Button deleteAllButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        db = new DatabaseHelper(this);
        listView = (ListView)findViewById(R.id.listView);

        deleteAllButton = (Button)findViewById(R.id.deleteAllButton);
        homeButton = (Button)findViewById(R.id.backButton);

        facts = new ArrayList<>();
        Cursor res = db.getSavedFacts();
        while (res.moveToNext()){
            facts.add(res.getString(1));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.custom_textview,
                facts);

        listView.setAdapter(arrayAdapter);


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StartActivity.class));
                System.gc();

            }
        });


        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteAll();
                startActivity(new Intent(getApplicationContext(), SavedActivity.class));
                Toast.makeText(getApplicationContext(), "All deleted", Toast.LENGTH_SHORT).show();
                System.gc();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                savedF = getViewByPosition(position, listView);
                startActivity(new Intent(getApplicationContext(), ShowSavedActivity.class));
                System.gc();
            }
        });



    }

    private void FunctionDeleteRowWhenSlidingRight() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        MotionEvent event;



    }

    private void FunctionDeleteRowWhenSlidingLeft() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
    }

    public String getViewByPosition(int position, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (position < firstListItemPosition || position > lastListItemPosition ) {
            View nekaj = listView.getAdapter().getView(position, null, listView);
            TextView mhm = (TextView)nekaj;
            String aha = mhm.getText().toString();
            return aha;
        } else {
            final int childIndex = position - firstListItemPosition;
            View nekaj = listView.getChildAt(childIndex);
            TextView mhm = (TextView)nekaj;
            String aha = mhm.getText().toString();
            return aha;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_saved, menu);
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
