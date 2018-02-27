package com.aust.austpc.austpcbeta6.database;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aust.austpc.austpcbeta6.HomeActivity;
import com.aust.austpc.austpcbeta6.R;
import com.aust.austpc.austpcbeta6.auth.LoginActivity;
import static android.content.ContentValues.TAG;

public class ProfileSetupActivity extends AppCompatActivity {
    EditText editName,editContactno,editYear,editSemester,editRoll;
    String email;
    DatabaseHandler myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile_setup);
        myDb=new DatabaseHandler(this);
        editName=(EditText) findViewById(R.id.Nametext);
        editContactno=(EditText) findViewById(R.id.Contacttext);
        editYear=(EditText) findViewById(R.id.Yeartext);
        editSemester=(EditText) findViewById(R.id.Semestertext);
        editRoll=(EditText) findViewById(R.id.Rolltext);
        email=getIntent().getExtras().getString("User Email");
        //Log.i(TAG,email);

    }
    void registerProfile(View view)
    {

        boolean isInserted=myDb.insertData(editName.getText().toString(),editRoll.getText().toString(),editContactno.getText().toString(),editYear.getText().toString(),editSemester.getText().toString(),email);
        if(isInserted) {
            //Toast.makeText(ProfileSetupActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ProfileSetupActivity.this,HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
            else
            Toast.makeText(ProfileSetupActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();
    }
    /*void showProfile(View view)
    {
        Cursor cursor =myDb.getAllData();
        if(cursor.getCount()==0)
        {
            showMessage("Error","NO data");
            return;

        }
        StringBuffer stringBuffer=new StringBuffer();
        while ( cursor.moveToNext())
        {
            stringBuffer.append("ID :"+cursor.getString(0)+"\n");
            stringBuffer.append("NAME :"+cursor.getString(1)+"\n");
            stringBuffer.append("ROLL :"+cursor.getString(2)+"\n");
            stringBuffer.append("CONTACT :"+cursor.getString(3)+"\n");
            stringBuffer.append("YEAR :"+cursor.getString(4)+"\n");
            stringBuffer.append("SEMESTER :"+cursor.getString(5)+"\n");


        }
        showMessage("data",stringBuffer.toString());
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }*/
}

