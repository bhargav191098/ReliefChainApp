package u.com.example.reliefchain;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        submit = (Button)findViewById(R.id.btn_login);
        MyRequestQueue = Volley.newRequestQueue(this);
        url = "http://192.168.0.108:3000/login";
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String aadhar = aadhar_field.getText().toString();
                final String mobile = mobile_field.getText().toString();
                editor.putString("aadhar",aadhar);
                editor.apply();
                String res;
                final Intent intent = new Intent(getApplicationContext(),DashBoard.class);
                Log.d("output",url);
                MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("sysres",response);
                        if(response.equals("1"))
                            startActivity(intent);
                        else{
                            Toast.makeText(getApplicationContext(),"Oops!Wrong",Toast.LENGTH_LONG).show();
                        }
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
                Log.d("outside","creation");
                /*JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Do something with response
                                //mTextView.setText(response.toString());
                                Log.d("hey","dei are you here");
                                // Process the JSON
                                try{
                                    Log.d("inside",response.toString());
                                    // Get the JSON array
                                    //JSONObject array = response.getJSONObject(aadhar);
                                    String a = response.getString(aadhar);
                                    Log.d("helloooo",a);
                                    // Loop through the array elements
                                    //for(int i=0;i<array.length();i++){
                                        // Get current json object
                                        //JSONObject student = array.getJSONObject(i);

                                        // Get the current student (json object) data
                                        //String firstName = student.getString(aadhar);
                                        //String lastName = student.getString("lastname");
                                        //String age = student.getString("age");
                                        //Log.d("hello",firstName);
                                        // Display the formatted json data in text view
                                        //mTextView.append(firstName +" " + lastName +"\nage : " + age);
                                        //mTextView.append("\n\n");
                                    //}
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErroerResponse(VolleyError error){
                                // Do something when error occurred
                                Log.d("error",rror.toString());

                            }
                        }
                );
                jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                        5000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


                // Add JsonObjectRequest to the RequestQueue
                MyRequestQueue.add(jsonObjectRequest);
                */

                //startActivity(intent);


            }
        });



    }
}
