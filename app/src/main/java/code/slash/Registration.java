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

public class Registration extends AppCompatActivity {
    EditText uphone,uemail,username,upass,userid;
    TextView login;
    Button reg;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,ref1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().hide();

        uphone=(EditText) findViewById(R.id.txtPhone);
        uemail=(EditText)findViewById(R.id.txtEmail);
        username=(EditText)findViewById(R.id.txtName);
        upass=(EditText)findViewById(R.id.txtPwd);
        login = (TextView)findViewById(R.id.lnkLogin);
        reg=(Button)findViewById(R.id.btnLogin);

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

                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    void postDo(){
        final String usrphone=uphone.getText().toString();
        final  String usremail=uemail.getText().toString();
        final String usrname=username.getText().toString();
        final String usrpwd=upass.getText().toString();



        if(usrname.isEmpty()||usremail.isEmpty()||usrphone.isEmpty()||usrpwd.isEmpty()){
            Toast.makeText(Registration.this, "Please fill all details ", Toast.LENGTH_SHORT).show();
        }
        else{
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(dataSnapshot.child(usrphone).exists()){

                        Toast.makeText(Registration.this, "Phone Number Already Exists", Toast.LENGTH_SHORT).show();

                    }else {




                        User user1=new User(usremail,usrname,usrpwd,"User",usrphone);

                        databaseReference.child(usrphone).setValue(user1);

                        Toast.makeText(Registration.this, "Registration Successful ", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }

    }
}
