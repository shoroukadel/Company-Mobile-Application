package commyc.example.blu_ray91111.timslinesoluation;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.internal.http.multipart.MultipartEntity;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.entity.mime.HttpMultipartMode;

import org.apache.http.entity.mime.content.StringBody;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



import static android.app.Activity.RESULT_OK;
import static android.content.Context.CONTEXT_RESTRICTED;

/**
 * Created by Blu-Ray on 30/01/2017.
 */

public class ThirdFragment extends Fragment {
    View myView;
    private static final int FILE_SELECT_CODE = 0;
    private static final int REQUEST_CODE = 6384;
    private static final String TAG = "ThirdFragment";
    TextView add;
    ImageButton open;
    TextView path;
    EditText email;
    EditText phone;
    Button send;
    public static String URL = "http://phone.tmsline.com/api/request_job";


    Projects m;
    Projects s;
    String [] t=new String[1];


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.third_layout, container, false);
        ImageButton open = (ImageButton) myView.findViewById(R.id.open);
        path = (TextView) myView.findViewById(R.id.path);
        EditText email = (EditText) myView.findViewById(R.id.email);
        EditText phone = (EditText) myView.findViewById(R.id.email);
         String eemail=email.getText().toString();
        String pphone=phone.getText().toString();
        EditText first  = (EditText) myView.findViewById(R.id.firstname);
        EditText second= (EditText) myView.findViewById(R.id.lastname);
        TextView ms=(TextView)myView.findViewById(R.id.messageserver);
       String firstname=first.getText().toString();
        String lastname=second.getText().toString();
        t[0]="";


        open.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goo();
                        // new AsyncTaskPayment().execute();



                    }
                }


        );
        send = (Button) myView.findViewById(R.id.sendbtu);

        send.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText email = (EditText) myView.findViewById(R.id.email);
                        EditText phone = (EditText) myView.findViewById(R.id.email);
                        String eemail=email.getText().toString();
                        String pphone=phone.getText().toString();
                        EditText first  = (EditText) myView.findViewById(R.id.firstname);
                        EditText second= (EditText) myView.findViewById(R.id.lastname);
                        TextView ms=(TextView)myView.findViewById(R.id.messageserver);
                        String firstname=first.getText().toString();
                        String lastname=second.getText().toString();
                        String ss=t[0];


                        // new AsyncTaskPayment().execute();
                        if(eemail.trim().equals("")||pphone.trim().equals("")||firstname.trim().equals("")||lastname.trim().equals("")||ss.trim().equals("")) {
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

    public String goo() {
        email = (EditText) myView.findViewById(R.id.email);
        phone = (EditText) myView.findViewById(R.id.email);


        JSONObject postData = new JSONObject();
        try {
            postData.put("email", email.getText().toString());
            postData.put("phone", phone.getText().toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }


        Intent chooseFile = new Intent(Intent.ACTION_OPEN_DOCUMENT);
// Ask specifically for something that can be opened:
        chooseFile.addCategory(Intent.CATEGORY_OPENABLE);
        chooseFile.setType("application/pdf");
        /**startActivityForResult(
         Intent.createChooser(chooseFile, "Choose a file"),
         REQUEST_CODE
         );
         */startActivityForResult(
                Intent.createChooser(chooseFile, "Choose File"),
                REQUEST_CODE);
        return  "ok";
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Uri content_describer = data.getData();

            //String s = getRealPathFromURI(content_describer);
            add = (TextView) myView.findViewById(R.id.path);
            //add.setText(content_describer.toString());
            //editText1.setText(s);

            BufferedReader reader = null;
            String uriString = content_describer.toString();
            File myFile = new File(uriString);
            String pathing = myFile.getAbsolutePath();
            String displayName = null;

            m=new Projects();
            s=new Projects();

            if (uriString.startsWith("content://")) {
                Cursor cursor = null;
                try {
                     Uri uri = data.getData();
                    final String path =uri.getPath();
                    cursor = getActivity().getContentResolver().query(content_describer, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                        add.setText("/sdcard/Download/"+displayName);
                        m.setyourText("/sdcard/Download/"+displayName);
                        s.setYourversion(displayName);
                        mnn(displayName);


                    }
                } finally {
                    cursor.close();
                }

            } else if (uriString.startsWith("file://")) {
                displayName = myFile.getName();
                TextView pathee = (TextView) myView.findViewById(R.id.path);
                //add.setText(displayName);
                String filepath = pathing;
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class postData extends AsyncTask<String, Void, String> {

      private final ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
            "Saving data to server. Please wait...", true);
        EditText email = (EditText) myView.findViewById(R.id.email);
        EditText phone = (EditText) myView.findViewById(R.id.email);
        String eemail=email.getText().toString();
        String pphone=phone.getText().toString();
        EditText first  = (EditText) myView.findViewById(R.id.firstname);
        EditText second= (EditText) myView.findViewById(R.id.lastname);
        String firstname=first.getText().toString();
        String lastname=second.getText().toString();
        TextView ms=(TextView)myView.findViewById(R.id.messageserver);


        @Override
        protected String doInBackground(String... params) {
            // perform long running operation operation

            // SharedPreferences settings = context.getSharedPreferences(PREFS_FILE, 0);
            //String server = settings.getString("server", "");
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://phone.tmsline.com/api/request_job");
            String json = "";
            String responseStr="";
            String result="true";
            byte[] data;

            String filee=m.getyourText();


            try {
                // Add your data

                //File file = new File(filee);



                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
                nameValuePairs.add(new BasicNameValuePair("email", eemail));
                nameValuePairs.add(new BasicNameValuePair("phone", pphone));

                nameValuePairs.add(new BasicNameValuePair("firstname", firstname));
                nameValuePairs.add(new BasicNameValuePair("lastname", lastname));
                nameValuePairs.add(new BasicNameValuePair("cv", "cv"));
               // httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));


                //String load="sherouk adel.pdf";
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),
                      "/sdcard/Download/Testing foundation.pdf");

               File uploadFile1 = new File("/sdcard/Download/Testing foundation.pdf");
             FileBody fileBody = new FileBody( file , ContentType.DEFAULT_BINARY);
                FileBody bin = new FileBody(new File(filee));
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                //FileInputStream fileInputStream = new FileInputStream(uploadFile1);
                //data = IOUtils.toByteArray(fileInputStream);
              // InputStreamBody inputStreamBody = new InputStreamBody(new ByteArrayInputStream(data), "Testing foundation.pdf");
               //InputStreamBody inputStreamm = new InputStreamBody(fileInputStream,"Testing foundation.pdf");
                builder.addPart("firstname", new StringBody(firstname));
                builder.addPart("lastname", new StringBody(lastname));
                builder.addPart("email", new StringBody(eemail));
                builder.addPart("phone", new StringBody(pphone));
                builder.addPart("cv",bin);
                HttpEntity entity = builder.build();

               //httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
              httppost.setEntity(entity);


                try {
                   // httpclient.execute(httppost);
                   // filetest();
                    HttpResponse response = httpclient.execute(httppost);

                    responseStr = EntityUtils.toString(response.getEntity());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                   Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();

                }
                return  responseStr;


                // Execute HTTP Post Request
                // ResponseHandler<String> responseHandler=new BasicResponseHandler();
                //String responseBody = httpclient.execute(httppost, responseHandler);

                //  if (Boolean.parseBoolean(responseBody)) {
                // 	dialog.cancel();
                // }
               //HttpResponse response = httpclient.execute(httppost);

              // responseStr = EntityUtils.toString(response.getEntity());




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


            String s=m.getyourText();
            add.setText(s);
            dialog.cancel();

           try {
                JSONArray pp=new JSONArray(responseStr);


                result = pp.getString(x);


               if(result.equals("1")){
                   // Update your Button here

                   Toast.makeText(getActivity(),"Your information was submitted Successfully",Toast.LENGTH_LONG).show();
                   ms.setText("Your information was submitted Successfully");
                   for (int i=0;i<7000;i++){
                       for (int j=0;j<8000;j++){

                       }


                   }



               }
               else {
                   Toast.makeText(getActivity(),"something wronge ,please try again  ",Toast.LENGTH_LONG).show();
                   ms.setText("something wronge ,please try again");
                   for (int i=0;i<2000;i++){
                       for (int j=0;j<800;j++){

                       }


                   }


               }


                returnn();




               //result is key for which you need to retrieve data
            } catch (JSONException e) {
                e.printStackTrace();
               Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();


            }



        }

        }
    public void returnn () {
        for (int i=0;i<7000;i++){
            for (int j=0;j<800;j++){

            }


        }
        mn();
    }
    public  void mn(){
        Intent intent = new Intent(getActivity(), mainpage.class);
        startActivity(intent);



    }
    public  void mnn(String n){
       String ss=n;
       t[0]=ss;



    }
    }









































