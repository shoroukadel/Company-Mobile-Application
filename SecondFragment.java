package commyc.example.blu_ray91111.timslinesoluation;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
//import com.hmkcode.android.vo.Person;

/**
 * Created by Blu-Ray on 30/01/2017.
 */

public class SecondFragment extends Fragment {
    View myView;
    EditText Name,Phone,Email,Requirmens;
    Button send;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.second_layout,container,false);
        Name = (EditText) myView.findViewById(R.id.name);
        //Phone = (EditText) myView.findViewById(R.id.phone);
        Email = (EditText) myView.findViewById(R.id.email);
       Requirmens = (EditText) myView.findViewById(R.id.requirments);
        send=(Button) myView.findViewById(R.id.sendit);
        Phone=(EditText) myView.findViewById(R.id.phonee);
        String email = Email.getText().toString();
        String requirments = Requirmens.getText().toString();
        String phone = Phone.getText().toString();
        String name = Name.getText().toString();
        send.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        String email = Email.getText().toString();
                        String requirments = Requirmens.getText().toString();
                        String phone = Phone.getText().toString();
                        String name = Name.getText().toString();
                        if(email.trim().equals("")||requirments.trim().equals("")||phone.trim().equals("")||name.trim().equals("")) {
                            Toast.makeText(getActivity(),"please complete your data",Toast.LENGTH_LONG).show();

                        }
                        else{
                            new postData().execute();

                        }



                    }
                }

        );


        return myView;
    }
    public void sendit(){
        send=(Button) myView.findViewById(R.id.sendit);
        send.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {

          new postData().execute();

                    }
                }

        );


    }

    private class postData extends AsyncTask<String, Void, String> {
    private final ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
             "Saving data to server. Please wait...", true);
       EditText Name = (EditText) myView.findViewById(R.id.name);

         EditText Email = (EditText) myView.findViewById(R.id.email);
         EditText Requirmens = (EditText) myView.findViewById(R.id.requirments);

       EditText Phone=(EditText) myView.findViewById(R.id.phonee);
        String email = Email.getText().toString();
        String requirments = Requirmens.getText().toString();
        String phone = Phone.getText().toString();
        String name = Name.getText().toString();
        String i="true";




        @Override
        protected String doInBackground(String... params) {
            // perform long running operation operation

            // SharedPreferences settings = context.getSharedPreferences(PREFS_FILE, 0);
            //String server = settings.getString("server", "");

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://phone.tmsline.com/api/request_project");
            String json = "";
            String responseStr="";
            String result="";



            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("requirements", requirments));
                nameValuePairs.add(new BasicNameValuePair("phone", phone));
                nameValuePairs.add(new BasicNameValuePair("name", name));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));



                try {
                    HttpResponse response = httpclient.execute(httppost);

                    responseStr = EntityUtils.toString(response.getEntity());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return  responseStr;



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
            return  responseStr;

        }
        protected void onPostExecute(String i) {
            super.onPostExecute(i);


            String result="";
            String  test="";
            String msg="msg";
            int x=1;
            int y=0;
            dialog.cancel();
            Toast.makeText(getActivity(),i,Toast.LENGTH_LONG).show();
            try {
                JSONArray pp=new JSONArray(i);


                result = pp.getString(x);
                //test = pp.getString(y);

                //Toast.makeText(loginpage.this,test,Toast.LENGTH_LONG).show();
                if(result.equals("true")){
                    // Update your Button here
                    Toast.makeText(getActivity(),"success",Toast.LENGTH_LONG).show();


                }
                else {
                    Toast.makeText(getActivity(),"password or username is wronge ,please try again",Toast.LENGTH_LONG).show();

                }



                //result is key for which you need to retrieve data
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();

            }



        }





        }
    }









