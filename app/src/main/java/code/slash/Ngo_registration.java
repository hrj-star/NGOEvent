package code.slash;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import code.slash.Model.User;

//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;

public class Ngo_Registration extends AppCompatActivity {
    EditText ngouphone,ngouemail,ngousername,ngoupass,ngouserid,ngoaddress,ngodesc,ngoreg;
    TextView login;
    Button reg;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,ref1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().hide();

        ngouphone=(EditText) findViewById(R.id.ngotxtPhone);
        ngouemail=(EditText)findViewById(R.id.ngotxtEmail);
        ngousername=(EditText)findViewById(R.id.ngotxtName);
        ngoaddress=(EditText)findViewById(R.id.ngotxtAdd);
        ngodesc=(EditText)findViewById(R.id.ngotxtDesc);
        ngoreg=(EditText)findViewById(R.id.ngotxtReg);
        ngoupass=(EditText)findViewById(R.id.ngotxtPwd);
        login = (TextView)findViewById(R.id.lnkLogin);
        reg=(Button)findViewById(R.id.ngobtnLogin);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("User");


        reg.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                postDo();
            }
        });


        login.setMovementMethod(LinkMovementMethod.getInstance());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Ngo_Registration.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    void postDo(){
        final String ngousrphone=ngouphone.getText().toString();
        final  String ngousremail=ngouemail.getText().toString();
        final String ngousrname=ngousername.getText().toString();
        final String ngousrpwd=ngoupass.getText().toString();
        final String ngousradd= ngoaddress.getText().toString();
        final String ngousrdes= ngodesc.getText().toString();
        final String ngousrregis= ngoreg.getText().toString();




        if(ngousrname.isEmpty()||ngousremail.isEmpty()||ngousrphone.isEmpty()||ngousradd.isEmpty() ||ngousrdes.isEmpty() ||ngousrregis.isEmpty() ||ngousrpwd.isEmpty()){
            Toast.makeText(Ngo_Registration.this, "Please fill all details ", Toast.LENGTH_SHORT).show();
        }
        else{
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(dataSnapshot.child(ngousrphone).exists()){

                        Toast.makeText(Ngo_Registration.this, "Phone Number Already Exists", Toast.LENGTH_SHORT).show();

                    }else {




                        User user1=new User(ngousremail,ngousrname,ngousrpwd,"User",ngousrphone);

                        databaseReference.child(ngousrphone).setValue(user1);

                        Toast.makeText(Ngo_Registration.this, "Registration Successful ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }

    }
}
