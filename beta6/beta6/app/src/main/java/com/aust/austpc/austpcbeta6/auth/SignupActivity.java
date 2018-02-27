package com.aust.austpc.austpcbeta6.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aust.austpc.austpcbeta6.R;
import com.aust.austpc.austpcbeta6.database.ProfileSetupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignupActivity extends AppCompatActivity {
    private EditText Editemail, Editpassword;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        progressDialog = new ProgressDialog(this);
        Editemail = (EditText)findViewById(R.id.Signupemail);
        Editpassword = (EditText)findViewById(R.id.Signuppassword);
        mAuth = FirebaseAuth.getInstance();
    }

    public void registerUser(View view){
        final String email = Editemail.getText().toString().trim();

        String password = Editpassword.getText().toString().trim();
        if(email.isEmpty())
        {
            Editemail.setError("Email is required!");
            Editemail.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            Editpassword.setError("Password is required!");
            Editpassword.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Editemail.setError("Enter a valid email");
            Editemail.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            Editpassword.setError("Minimum length of password should be 6");
            Editpassword.requestFocus();
            return;
        }
        progressDialog.setMessage("Registering User....");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, ProfileSetupActivity.class);
                    intent.putExtra("User Email",email);
                    startActivity(intent);
                }
                else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),"You are already registered",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }
}
