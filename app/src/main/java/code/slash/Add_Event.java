package code.slash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import code.slash.Common.Common;
import code.slash.Model.Event;
import code.slash.Model.User;

public class Add_Event extends AppCompatActivity {

    Button addEvent,back;
    EditText event_name;

    EditText  event_date;
    EditText  event_time;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,ref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__event);

        back=(Button)findViewById(R.id.btnBack);
        addEvent=(Button)findViewById(R.id.btnAddEvent);

        event_name=(EditText)findViewById(R.id.new_event_date);
        event_date=(EditText)findViewById(R.id.new_event_date);
        event_time=(EditText)findViewById(R.id.new_event_time);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(Add_Event.this, ngo_home.class);
                startActivity(intent);
            }
        });

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addEventtoDB();
            }
        });


    }

    private void addEventtoDB() {

        final String eventname=event_name.getText().toString();
        final  String eventdate=event_date.getText().toString();
        final String eventtime=event_time.getText().toString();
        final String ngo_name= Common.currentuser.getName().toString();



        if(eventname.isEmpty()||eventdate.isEmpty()||eventtime.isEmpty()){
            Toast.makeText(Add_Event.this, "Please fill all details ", Toast.LENGTH_SHORT).show();
        }
        else{
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        Event newenvent=new Event(eventname,ngo_name,eventdate,eventtime);

                        databaseReference.setValue(newenvent);

                        Toast.makeText(Add_Event.this, "Registration Successful ", Toast.LENGTH_SHORT).show();
                        finish();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }

    }
}
