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

import com.aust.austpc.austpcbeta6.HomeActivity;
import com.aust.austpc.austpcbeta6.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private EditText editemail, editpassword;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(this);
        editemail = (EditText)findViewById(R.id.Loginemail);
        editpassword = (EditText)findViewById(R.id.Loginpassword);
        mAuth = FirebaseAuth.getInstance();
    }
    public void loginuser(View view){
        String email = editemail.getText().toString().trim();
        String password = editpassword.getText().toString().trim();
        if(email.isEmpty())
        {
            editemail.setError("Email is required!");
            editemail.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            editpassword.setError("Password is required!");
            editpassword.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editemail.setError("Enter a valid email");
            editemail.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            editpassword.setError("Minimum length of password should be 6");
            editpassword.requestFocus();
            return;
        }

        progressDialog.setMessage("Logging in....");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public void signupButton(View view){
        Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
