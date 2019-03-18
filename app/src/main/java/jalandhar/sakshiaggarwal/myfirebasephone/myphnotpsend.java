package jalandhar.sakshiaggarwal.myfirebasephone;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class myphnotpsend extends AppCompatActivity {
    EditText vcode;
    Button signin;
    private String verificationId;
    FirebaseAuth mAuth;
    ProgressBar pbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myphnotpsend);
        String phoneno=getIntent().getStringExtra("phoneno");
        vcode=findViewById(R.id.vcode);
        signin=findViewById(R.id.signin);
        pbar=findViewById(R.id.pbar);
        mAuth=FirebaseAuth.getInstance();
        sendVerificationCode(phoneno);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
      /*if the autodetection works user didnt have to click on the sign-in button manually*/
            String code=vcode.getText().toString().trim();
            if(code.isEmpty() || code.length()<6)
            {
                vcode.setError("Enter Correct Code");
                vcode.requestFocus();
                return; }
            pbar.setVisibility(View.VISIBLE);
            // checking here
                Log.v("sakshi","Sakshi");
            verifyCode(code); }
        });
    }


    /*method to verify the code entered by the user*/
    private void verifyCode(String code)
    {
        /*code that is entered by user or received automatically*/
     PhoneAuthCredential credential=PhoneAuthProvider.
             getCredential(verificationId,code);
     Log.v("sakshi1","sakshi");
     signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential)
    {
     mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {
          if(task.isSuccessful()) {
              startActivity(new Intent(myphnotpsend.this, mynavigation.class));
              finish();
          }
          else {
              Toast.makeText(myphnotpsend.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
          }
         }
     });
    }

    private  void sendVerificationCode(String number)
    {
        //here number is the phone number
        PhoneAuthProvider.getInstance().
                verifyPhoneNumber(
                        number,
                        60,
                        TimeUnit.SECONDS,
                        this,
                        mCallBacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId=s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        String code=phoneAuthCredential.getSmsCode();
        //in some cases the value of code is null
            // so in that case user has to add
            // the verification code manually
            if(code!=null) {
                pbar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(myphnotpsend.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
}
