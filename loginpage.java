package commyc.example.blu_ray91111.timslinesoluation;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class loginpage extends AppCompatActivity {
    private EditText editTextUserName;
    private EditText editTextPassword;
    private TextView message;
    public static final String USER_NAME = "USERNAME";

    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);


       editTextUserName = (EditText) findViewById(R.id.user);
        editTextPassword = (EditText) findViewById(R.id.password);
        message=(TextView) findViewById(R.id.mess);
        String username = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();
        Button send =(Button)findViewById(R.id.send);
        send.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                      // invokeLogin();
                      new postData().execute();


                    }
                }

        );
    }


    public void clickbuttonRecieve() {
        JSONObject postData = new JSONObject();
        JSONObject data = new JSONObject();
        editTextUserName = (EditText) findViewById(R.id.user);
        editTextPassword = (EditText) findViewById(R.id.password);
        try {
            postData.put("username",editTextUserName.getText().toString());
            postData.put("password", editTextPassword.getText().toString());

             data.put("ticket","hello");


        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
    private class postData extends AsyncTask<String, Void, String> {

        // private final ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
        //       "Saving data to server. Please wait...", true);
       EditText editTextUserName = (EditText) findViewById(R.id.user);
        EditText editTextPassword = (EditText) findViewById(R.id.password);
        String username=editTextUserName.getText().toString();
        String password=editTextPassword.getText().toString();

        TextView ticc=(TextView)findViewById(R.id.ticmsg);
        String ticket=ticc.getText().toString();
        @Override
        protected String doInBackground(String... params) {
            // perform long running operation operation

            // SharedPreferences settings = context.getSharedPreferences(PREFS_FILE, 0);
            //String server = settings.getString("server", "");

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://phone.tmsline.com/api/check_user");
            String json = "";
            String responseStr="";
            String result="";
            Intent in = getIntent();;
            String projectid = in.getStringExtra("projectid");


            try {
                // Add your data

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("username", username));
                nameValuePairs.add(new BasicNameValuePair("password", password));
                nameValuePairs.add(new BasicNameValuePair("projectid", projectid));
                nameValuePairs.add(new BasicNameValuePair("ticket", ticket));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                try {
                    HttpResponse response = httpclient.execute(httppost);

                    responseStr = EntityUtils.toString(response.getEntity());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } return  responseStr;


                // Execute HTTP Post Request
                // ResponseHandler<String> responseHandler=new BasicResponseHandler();
                //String responseBody = httpclient.execute(httppost, responseHandler);

                //  if (Boolean.parseBoolean(responseBody)) {
                // 	dialog.cancel();
                // }








            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.i("HTTP Failed", e.toString());
            }
            return responseStr;
        }
        protected void onPostExecute(String responseStr) {
            super.onPostExecute(responseStr);

            String result="";
            String  test="";
            String msg="msg";
            int x=1;
            int y=0;
            Toast.makeText(loginpage.this,responseStr,Toast.LENGTH_LONG).show();
            try {
                JSONArray pp=new JSONArray(responseStr);


                result = pp.getString(x);
                //test = pp.getString(y);

                //Toast.makeText(loginpage.this,test,Toast.LENGTH_LONG).show();
                if(result.equals("1")){
                    // Update your Button here
                    Toast.makeText(loginpage.this,"success",Toast.LENGTH_LONG).show();
                   returnn();

                }
                else {
                    Toast.makeText(loginpage.this,"password or username is wronge ,please try again",Toast.LENGTH_LONG).show();
                    returnn();
                }



                //result is key for which you need to retrieve data
            } catch (JSONException e) {
                e.printStackTrace();
              Toast.makeText(loginpage.this,e.toString(),Toast.LENGTH_LONG).show();

            }



        }
    }

    public void returnn () {
        Intent intent = new Intent(loginpage.this, loginpage.class);
        startActivity(intent);
    }




    }



