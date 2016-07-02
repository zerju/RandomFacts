package com.facts.facts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;




public class ShowSavedActivity extends Activity {

    String fact = SavedActivity.savedF;
    TextView factText;
    Button backButton;
    Button deleteButton;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saved);
       /* FacebookSdk.sdkInitialize(getApplicationContext());
        // Initialize the SDK before executing any other operations,
        // especially, if you're using Facebook UI elements.*/

        db = new DatabaseHelper(this);

        factText = (TextView)findViewById(R.id.factText);
        backButton = (Button)findViewById(R.id.backButton);
        deleteButton = (Button)findViewById(R.id.deleteButton);

        factText.setText(fact);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SavedActivity.class));
                System.gc();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("delete ",factText.getText().toString());
                db.delete(factText.getText().toString());
                startActivity(new Intent(getApplicationContext(), SavedActivity.class));
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                System.gc();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_saved, menu);
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
