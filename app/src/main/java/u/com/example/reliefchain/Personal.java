package u.com.example.reliefchain;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Personal extends AppCompatActivity {
    public SharedPreferences pref;
    public TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        desc = (TextView)findViewById(R.id.descp);
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String as =pref.getString("aadhar","00000");

        String sen =pref.getString("sender1","None");
        int amt = pref.getInt("amt1",0);

        String text = "Aadhar : "+as +"\n"+"Sender : "+sen+"\n"+"Amount :"+amt+"\n";
        desc.setText(text);
    }
}
