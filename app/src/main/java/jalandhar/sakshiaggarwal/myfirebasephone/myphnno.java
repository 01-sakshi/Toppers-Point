package jalandhar.sakshiaggarwal.myfirebasephone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static jalandhar.sakshiaggarwal.myfirebasephone.countryData.countryNames;

public class myphnno extends AppCompatActivity {
    Spinner spin;
    EditText phone;
    Button conti;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myphnno);
        spin=findViewById(R.id.spin);
        phone=findViewById(R.id.phone);
        conti=findViewById(R.id.conti);
        spin.setAdapter(new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_dropdown_item,countryNames));
        conti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String ccode=countryData.countryCodeAreas[spin.getSelectedItemPosition()];
              String phoneno=phone.getText().toString().trim();
              if(phoneno.isEmpty()) {
                  phone.setError("Enter Valid Phone Number");
                  phone.requestFocus();
                  return; }
                  //if validation succeeds start verification process
              String fphone="+"+ccode+phoneno;
              Intent intent=new Intent(myphnno.this,myphnotpsend.class);
              intent.putExtra("phoneno",fphone);
              startActivity(intent);

            }
        });
    }
}
