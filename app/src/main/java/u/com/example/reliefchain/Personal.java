package u.com.example.reliefchain;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Personal extends AppCompatActivity {
    public SharedPreferences pref;
    public TextView desc;
    public TextView name_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        desc = (TextView)findViewById(R.id.descp);
        name_text = (TextView)findViewById(R.id.name);
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String as =pref.getString("aadhar","00000");
        String ph = pref.getString("phone","99");

        String name = "ID: "+as+"  "+"\n"+"Phone: "+ph;
        name_text.setText(name);
        String sen =pref.getString("sender1","None");
        int amt = pref.getInt("amt1",0);

        String text = "Cardholder "+as+","+"\n " +"Collect "+amt + " from "+sen+"\n";
        desc.setText(text);
    }
}
