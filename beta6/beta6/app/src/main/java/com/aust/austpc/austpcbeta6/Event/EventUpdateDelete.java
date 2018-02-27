package com.aust.austpc.austpcbeta6.Event;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aust.austpc.austpcbeta6.HomeActivity;
import com.aust.austpc.austpcbeta6.R;
import com.aust.austpc.austpcbeta6.database.DatabaseHandler;

public class EventUpdateDelete extends AppCompatActivity {

    private String eventTitle;
    private DatabaseHandler db;
    private int id;
    private EditText setTitle, setDate, setVenue, setAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_update_delete);
        eventTitle=getIntent().getExtras().getString("Event Title");
        db=new DatabaseHandler(this);
        Toast.makeText(EventUpdateDelete.this,eventTitle,Toast.LENGTH_LONG).show();
            EventDesc eventDesc=db.getSingleEvent(eventTitle);
            //Toast.makeText(EventUpdateDelete.this,eventDesc.getDate(),Toast.LENGTH_LONG).show();
        id=eventDesc.getId();
        setTitle = (EditText) findViewById(R.id.editTitletext);
        setDate = (EditText) findViewById(R.id.editDatetext);
        setVenue = (EditText) findViewById(R.id.editVenuetext);
        setAbout = (EditText) findViewById(R.id.editAbouttext);
        setTitle.setText(eventTitle);
        setDate.setText(eventDesc.getDate());
        setVenue.setText(eventDesc.getVenue());
        setAbout.setText(eventDesc.getAbout());
    }
    public void updateEvent(View view)
    {
        if(db.dataUpdate(id,setTitle.getText().toString(), setDate.getText().toString(), setVenue.getText().toString(), setAbout.getText().toString()))
        {
            Toast.makeText(EventUpdateDelete.this,"Data has updated",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(EventUpdateDelete.this,HomeActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(EventUpdateDelete.this,"Data is not updated",Toast.LENGTH_LONG).show();
        }

    }

    public void deleteEvent(View view)
    {
        if(db.deleteContact(id))
        {

            Toast.makeText(EventUpdateDelete.this,"Event has Deleted",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(EventUpdateDelete.this,HomeActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(EventUpdateDelete.this,"Event is not deleted",Toast.LENGTH_LONG).show();
        }

    }
}
