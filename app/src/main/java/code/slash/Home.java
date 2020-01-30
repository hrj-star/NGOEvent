package code.slash;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import code.slash.Adapter.MyAdapter;
import code.slash.Common.Common;
import code.slash.Interface.ILoadMore;
import code.slash.Model.Event;
import code.slash.Model.User;

public class Home extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    TextView wlcMsg;
    List<Event> events;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        events=new ArrayList<>();
        wlcMsg=(TextView)findViewById(R.id.wlcmsg);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Events");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                   Event event=dataSnapshot1.getValue(Event.class);
                   events.add(event);
                   Log.e("GET DATA",event.getEvent_name());
                   Log.e("GET All DATA",events.toString());
               }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed",databaseError.getMessage());

            }
        });


        Log.e("GET All DATA",events.size()+"");
        Log.e("GET All DATA",events.get(0).getEvent_name()+"");


        List<Event> tmpevents=new ArrayList<>();

        for(int i=0;i<events.size();i++){
            Event a=(Event)events.get(i);
            tmpevents.add(new Event(a.getEvent_name(),a.getNgo_name(),a.getDate(),a.getTime()));
        }



        RecyclerView  recyclerView=(RecyclerView)findViewById(R.id.eventRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MyAdapter(recyclerView,this,tmpevents);
        recyclerView.setAdapter(adapter);

        wlcMsg.setText("Hey, "+Common.currentuser.getName());


//        adapter.setiLoadMore(new ILoadMore() {
//            @Override
//            public void onLoadMore() {
//                if(events.size()<=20){
//                    events.add(null);
//                    adapter.notifyItemInserted(events.size()-1);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            events.remove(events.size()-1);
//                            adapter.notifyItemRemoved(events.size());
//
//                            int index=events.size();
//                            int end=index+10;
//
//                            adapter.notifyDataSetChanged();
//                            adapter.setLoaded();
//
//                        }
//                    },5000);
//                }
//                else {
//                    Toast.makeText(Home.this, "Data Loaded", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }


}
