package code.slash;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    TextView wlcmUser;
    Button btnSearch,btnSell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        wlcmUser=(TextView)findViewById(R.id.wlcmUser);
//        btnSearch=(Button)findViewById(R.id.searchBook);
//        btnSell=(Button)findViewById(R.id.sellBook);
//
//        String msg="Hello ! "+Common.currentuser.getName();
//        wlcmUser.setText(msg);
//        btnSell.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(Home.this, "You pressed browse button", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(Home.this, "You pressed create button", Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }


}
