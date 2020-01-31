package code.slash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import code.slash.Adapter.MyAdapter;
import code.slash.Common.Common;
import code.slash.Model.Event;

public class Ngo_All_Events extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    //TextView wlcMsg;
    List<Event> events;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo__all__events);

        events=new ArrayList<>();
      //  wlcMsg=(TextView)findViewById(R.id.wlcmsg);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Events");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Event event=dataSnapshot1.getValue(Event.class);

                    if(event.getNgo_id().equals(Common.currentuser.getUserphone()))
                        events.add(event);
                }

                onEventLoad(events);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed",databaseError.getMessage());

            }
        });





   //     wlcMsg.setText("Hey, "+ Common.currentuser.getName());


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

    private void onEventLoad(List<Event> events) {

        Log.e("Event",events.size()+"");
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.ngoeventRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MyAdapter(recyclerView,this,events);
        recyclerView.setAdapter(adapter);
    }



}
