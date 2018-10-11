package u.com.example.reliefchain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class BlockChain extends AppCompatActivity {

    public RequestQueue MyRequestQueue;
    public StringRequest MyStringRequest;
    public String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_chain);
        MyRequestQueue = Volley.newRequestQueue(this);
        url = "http://192.168.0.108:5000/chain";
        final TextView chain = (TextView)findViewById(R.id.desc);
        Log.d("output", url);
        MyStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("sysres", response);
                chain.setText(response);

                //if (response.equals("1"))
                //    startActivity(intent);
               // else {
                //    Toast.makeText(getApplicationContext(), "Oops!Wrong", Toast.LENGTH_LONG).show();
               // }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("out", "varla");
                Log.d("JsonObErrorResponse", error.toString());
            }
        }) ;
        MyStringRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        MyRequestQueue.add(MyStringRequest);
        Log.d("outside", "creation");


    }
}
