package code.slash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import code.slash.Common.Common;
import code.slash.Model.Event;
import code.slash.Model.User;

public class Add_Event extends AppCompatActivity {

    Button addEvent,back,datepickup;
    EditText event_name;
    EditText  event_date;
    EditText  event_time;
    EditText  event_description,event_address,event_city,event_pincode,event_oragnizer;
    Switch swtvol_req;

    Calendar c;
    DatePickerDialog datePickerDialog;

    TimePickerDialog timePicker;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,ref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__event);

        addEvent=(Button)findViewById(R.id.btnAddEvent);

        event_name=(EditText)findViewById(R.id.new_event_name);
        event_date= (EditText) findViewById(R.id.new_event_date);
        event_time=(EditText)findViewById(R.id.new_event_time);

        event_description=(EditText)findViewById(R.id.new_event_details);
        event_address=(EditText)findViewById(R.id.new_event_address);
        event_city=(EditText)findViewById(R.id.new_event_city);
        event_pincode=(EditText)findViewById(R.id.new_event_pincode);

        event_oragnizer=(EditText)findViewById(R.id.new_event_oragnizer);

        event_date.setFocusable(false);
        event_date.setClickable(true);


        event_time.setFocusable(false);
        event_time.setClickable(true);

        swtvol_req=(Switch)findViewById(R.id.volunteer_req);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Events");


        event_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c=Calendar.getInstance();
                int day=c.get(Calendar.DAY_OF_MONTH);
                int month=c.get(Calendar.MONTH);
                int year=c.get(Calendar.YEAR);

                Log.e("DAte","test");

                datePickerDialog= new DatePickerDialog(Add_Event.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myear, int mmonth, int mdayOfMonth) {

                        String MONTH[]=
                                {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

                        Log.e("Month",mmonth+"");
                        event_date.setText(mdayOfMonth+" "+MONTH[mmonth]+" "+myear);
                    }
                },year,month,day);

                datePickerDialog.show();
            }
        });

        event_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c=Calendar.getInstance();
                int hr=c.get(Calendar.HOUR);
                int min=c.get(Calendar.MINUTE);

                final String[] format = {""};

                timePicker=new TimePickerDialog(Add_Event.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {

                            if (hour == 0) {
                                hour += 12;
                                format[0] = "AM";
                            } else if (hour == 12) {
                                format[0] = "PM";
                            } else if (hour > 12) {
                                hour -= 12;
                                format[0] = "PM";
                            } else {
                                format[0] = "AM";
                            }

                            event_time.setText(new StringBuilder().append(hour).append(" : ").append(minute)
                                    .append(" ").append(format[0]));
                        }

                },hr,min,false);

                timePicker.show();
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

        final String eventnamestr=event_name.getText().toString();
        final  String eventdatestr=event_date.getText().toString();
        final String eventtimestr=event_time.getText().toString();

        final String ngo_namestr= Common.currentuser.getName().toString();
        final String ngo_idstr= Common.currentuser.getUserphone();

        final String eventdescriptionstr=event_description.getText().toString();
        final String eventaddressstr=event_address.getText().toString();
        final String eventcitystr=event_city.getText().toString();
        final String eventpincodestr=event_pincode.getText().toString();
        final String event_oragnizerstr=event_oragnizer.getText().toString();

        final String vol_reqstr;
        boolean vol_req=swtvol_req.isChecked();
        if(vol_req){
            vol_reqstr="Yes";
        }else {
            vol_reqstr="No";
        }

//                ,event_address,event_city,event_pincode;

        final List<String> annoucemts=new ArrayList<>();
        annoucemts.add(" ");
        final List<String> photos=new ArrayList<>();
        photos.add(" ");
        final List<String> comments=new ArrayList<>();
        comments.add(" ");

        if(eventnamestr.isEmpty()||eventdatestr.isEmpty()||eventtimestr.isEmpty()||eventdescriptionstr.isEmpty()||eventaddressstr.isEmpty()||eventcitystr.isEmpty()||eventpincodestr.isEmpty()){
            Toast.makeText(Add_Event.this, "Please fill all details ", Toast.LENGTH_SHORT).show();
        }else{

                        Event newenvent=new Event(
                                eventaddressstr,eventdescriptionstr,eventcitystr,eventpincodestr,
                                eventnamestr,ngo_namestr,"",eventdatestr,eventtimestr,eventcitystr,
                                event_oragnizerstr,vol_reqstr,ngo_idstr,"NO",photos,annoucemts,comments
                                );

//                        UUID eventkey= UUID.randomUUID();

                        long eventkey=new Date().getTime();
                        databaseReference.child(eventkey+"").setValue(newenvent);
                        Toast.makeText(Add_Event.this, "Event Added Successful ", Toast.LENGTH_SHORT).show();
                        finish();




        }

    }
}
