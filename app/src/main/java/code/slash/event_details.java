package code.slash;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import code.slash.Common.Common;
import code.slash.Model.Event;

public class event_details extends AppCompatActivity {

    TextView eventname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Event ent = Common.selectedEvent;

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + ent.getEvent_name() + "</font>"));


    }
}
