package com.aust.austpc.austpcbeta6.Event;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aust.austpc.austpcbeta6.HomeActivity;
import com.aust.austpc.austpcbeta6.R;
import com.aust.austpc.austpcbeta6.database.DatabaseHandler;
import com.aust.austpc.austpcbeta6.database.ProfileSetupActivity;

public class EventCreateActivity extends AppCompatActivity {
        private EditText title, dste, venue, about;
        private DatabaseHandler myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_create);
        title = (EditText) findViewById(R.id.Titletext);
        dste = (EditText) findViewById(R.id.Datetext);
        venue = (EditText) findViewById(R.id.Venuetext);
        about = (EditText) findViewById(R.id.Abouttext);
        myDb = new DatabaseHandler(this);

    }

    public void makeEvent(View view)
    {
        boolean isInserted=myDb.insertEventData(title.getText().toString(),dste.getText().toString(),venue.getText().toString(),about.getText().toString());
        if(isInserted) {
            //Toast.makeText(ProfileSetupActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
            Toast.makeText(EventCreateActivity.this,"Event is Created",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(EventCreateActivity.this,HomeActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else
            Toast.makeText(EventCreateActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();
    }
}
