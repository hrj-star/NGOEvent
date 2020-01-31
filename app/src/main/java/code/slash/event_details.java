package code.slash;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import code.slash.Common.Common;

public class event_details extends AppCompatActivity {

    TextView eventname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        eventname = (TextView) findViewById(R.id.eventdetailname);
        eventname.setText(Common.selectedEvent.getEvent_name());
    }
}
