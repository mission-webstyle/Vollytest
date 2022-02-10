package de.rhistel.vollytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
	
	public static final String TAG = MainActivity.class.getSimpleName();
	private RequestQueue       requestQueue;
	private TextView           txtOutput;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.main_activity_layout);
		
		this.txtOutput = findViewById(R.id.txtOutput);
		
		
		
	}
	
	public void onTestClick(View v){
		// Instantiate the RequestQueue.
		this.requestQueue = Volley.newRequestQueue(this);
		String       url   = "http://Beast:3000/api/testrest/products";
		
		// Request a string response from the provided URL.
//		StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//				response -> {
//					// Display the first 500 characters of the response string.
//					txtOutput.setText("Response is: " + response.substring(0, 500));
//				}, error -> {
//
//		});
		
		JSONArray jsonArray = new JSONArray();
		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, jsonArray, new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {
				txtOutput.setText(response.toString());
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				txtOutput.setText(error.toString());
				Log.d(TAG,error.toString());
				NoConnectionError
			}
		});
		
		// Add the request to the RequestQueue.
//		requestQueue.add(stringRequest);
		requestQueue.add(jsonArrayRequest);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		
		if(requestQueue!=null){
			requestQueue.cancelAll(TAG);
		}
	}
}