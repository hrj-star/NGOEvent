package code.slash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import code.slash.Common.Common;

public class ngo_home extends AppCompatActivity {

    CardView event_card,ngoallevents;
    TextView wclmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_home);

        event_card=(CardView)findViewById(R.id.event_card);
        ngoallevents=(CardView)findViewById(R.id.ngo_all_events);
        wclmsg=(TextView)findViewById(R.id.wlcmsgngo);
        wclmsg.setText("Hello, "+ Common.currentuser.getName());

        event_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(ngo_home.this, "Create Event Click ", Toast.LENGTH_SHORT).show();


                Intent intent=new Intent(ngo_home.this, Add_Event.class);
                startActivity(intent);

            }
        });

        ngoallevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(ngo_home.this, "Create Event Click ", Toast.LENGTH_SHORT).show();


                Intent intent=new Intent(ngo_home.this, Ngo_All_Events.class);
                startActivity(intent);

            }
        });
    }
}
