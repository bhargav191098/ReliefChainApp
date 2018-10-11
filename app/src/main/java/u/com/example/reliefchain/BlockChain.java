package u.com.example.reliefchain;

import android.content.SharedPreferences;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BlockChain extends AppCompatActivity {

    public RequestQueue MyRequestQueue;
    public JsonObjectRequest MyJsonRequest;
    public StringRequest MyStringRequest;
    public SharedPreferences pref;
    public String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_chain);
        MyRequestQueue = Volley.newRequestQueue(this);
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        final TextView trans = (TextView)findViewById(R.id.desc);
        url = "http://192.168.0.108:5000/chain";
        final TextView chain = (TextView)findViewById(R.id.desc);
        Log.d("output", url);
        /*MyStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
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
        */
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
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
                                    //Log.d("inside",response.toString());
                                    // Get the JSON array
                                    JSONArray array = response.getJSONArray("chain");

                                    JSONObject object = array.getJSONObject(1);
                                    JSONArray tran = object.getJSONArray("transactions");

                                    for(int i=0;i<tran.length();i++){
                                        JSONObject each = tran.getJSONObject(i);
                                        String sender = each.getString("sender");
                                        String rec = each.getString("recipient");
                                        int amt = each.getInt("amount");
                                        String as =pref.getString("aadhar","98");
                                        if(rec.equals(as)){
                                            Toast.makeText(getApplicationContext(),"Go collect Money",Toast.LENGTH_LONG).show();
                                            final SharedPreferences.Editor editor = pref.edit();
                                            editor.putString("tag","1");
                                        }
                                        String text = "Sender: "+sender+"\n"+"Recipient: "+rec+"\n"+"amount :"+amt+"\n";
                                        trans.append(text);


                                    }





                                    Log.d("helloooo",object.toString());

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
                            public void onErrorResponse(VolleyError error){
                                // Do something when error occurred
                                Log.d("error",error.toString());

                            }
                        }
                );
                jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                        5000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


                // Add JsonObjectRequest to the RequestQueue
                MyRequestQueue.add(jsonObjectRequest);



    }
}
