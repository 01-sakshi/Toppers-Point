package jalandhar.sakshiaggarwal.myfirebasephone;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class splashScreen extends AppCompatActivity {


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            setContentView(R.layout.activity_splash_screen);

            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/
            int SPLASH_DISPLAY_LENGTH = 1000;
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {

                    /* Create an Intent that will start the Menu-Activity. */
                    if (user==null){

                        Intent mainIntent = new Intent(splashScreen.this,myphnno.class);
                        splashScreen.this.startActivity(mainIntent);
                        splashScreen.this.finish();
                    }
                    else {

                  startActivity(new Intent(splashScreen.this,mynavigation.class));
                  splashScreen.this.finish();
                    }
                }
            }, SPLASH_DISPLAY_LENGTH);
        }


}


