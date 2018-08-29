package u.com.example.reliefchain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public EditText aadhar_field;
    public EditText mobile_field;
    public Button submit;
    public RequestQueue MyRequestQueue;
    public StringRequest MyStringRequest;
    public String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        aadhar_field= (EditText) findViewById(R.id.input_aadhar);
        mobile_field = (EditText) findViewById(R.id.input_phone);
        submit = (Button)findViewById(R.id.btn_login);
        MyRequestQueue = Volley.newRequestQueue(this);
        url = "http://192.168.43.94:5000/login";
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String aadhar = aadhar_field.getText().toString();
                final String mobile = mobile_field.getText().toString();
                Intent i = new Intent();
                Log.d("output",url);
                MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("sysres",response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("out","varla");
                        Log.d("JsonObErrorResponse",error.toString());
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> MyData = new HashMap<String, String>();
                        MyData.put("aadhar", aadhar);
                        MyData.put("phone",mobile);
                        return MyData;
                    }
                };
                MyStringRequest.setRetryPolicy(new DefaultRetryPolicy(
                        5000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                MyRequestQueue.add(MyStringRequest);


            }
        });



    }
}
