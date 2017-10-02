package commyc.example.blu_ray91111.timslinesoluation;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Blu-Ray on 30/01/2017.
 */

public class FourthFragment extends Fragment {
    View myView;
    //ListView listView;
    ProgressDialog prgDialog;
    public ListView nw;
    private ListView lv;
    private ListView m;
    private String TAG = MainActivity.class.getSimpleName();
    ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

    // ArrayList<HashMap<String, String>> imglists = new ArrayList<HashMap<String, String>>();
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fourthlayout, container, false);
        prgDialog = new ProgressDialog(getActivity());
        prgDialog.setMessage("Please wait............");
        prgDialog.setCancelable(false);
        String serverURL = "http://phone.tmsline.com/view_employee";
        String url = "http://phone.tmsline.com/images/uploads/";
        StringBuilder value;
        lv = (ListView) myView.findViewById(R.id.listed);
        //TextView v=(TextView) myView.findViewById(R.id.);

//        v.setText("whyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
        //m=(ListView) myView.findViewById(R.id.imglist);
        // adapter = new CustomListAdapter(this, Iv);
        //listView.setAdapter(adapter);
        new LoadService().execute(serverURL);

        return myView;
    }

    private class LoadService extends AsyncTask<String, Void, Void> {

        private final HttpClient Client = new DefaultHttpClient();
        private String Content;
        private String Error = null;
        private final String TAG = null;
        private ProgressDialog Dialog = new ProgressDialog(getActivity());
        String id;

        //TextView uiUpdate = (TextView) findViewById(R.id.text);
        //TextView projectname = (TextView) findViewById(R.id.projectname);
        //TextView status = (TextView) findViewById(R.id.status);
        // TextView descrip = (TextView) findViewById(R.id.description);
       // ImageView im = (ImageView) myView.findViewById(R.id.img1);


        protected void onPreExecute() {
            //      uiUpdate.setText("");
            //projectname.setText("");
            // status.setText("");
//            descrip.setText("hello");
            Dialog.setMessage("Loading service..");
            Dialog.show();
            Dialog.dismiss();
            //Dialog.setCancelable(false);
//            descrip.setText("bye");


        }

        protected Void doInBackground(String... urls) {
            try {


                HttpGet httpget = new HttpGet(urls[0]);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                Content = Client.execute(httpget, responseHandler);


            } catch (ClientProtocolException e) {
                Error = e.getMessage();
                cancel(true);
            } catch (IOException e) {
                Error = e.getMessage();
                cancel(true);
            }

            return null;
        }

        protected void onPostExecute(Void unused) {
            // Close progress dialog
            Dialog.dismiss();
            Log.e(TAG, "Raw output "
                    + Content);


            try {

                // Load json data and display
                JSONObject json = new JSONObject(Content);
                //String responseString = json.getString("name");
                JSONArray jre = json.getJSONArray("employees");
                //HashMap<String, String> project = new HashMap<String, String>();
                //JSONArray images = json.getJSONArray("screenshots");
                for (int j = 0; j < jre.length(); j++) {

                    JSONObject jobject = jre.getJSONObject(j);
                    //JSONObject imgobject = images.getJSONObject(j);


                    String name11 = jobject.getString("name");
                    String description = jobject.getString("position");
                    //String statu = jobject.getString("status");
                   //String id = jobject.getString("id");

                    //projectname.setText(name11);
                    //tatus.setText(description);
                    //descrip.setText(statu);
                    HashMap<String, String> contact = new HashMap<>();

                    // adding each child node to HashMap key => value
                    contact.put("name", name11);
                    contact.put("position", description);
                    //contact.put("status", statu);
                    contactList.add(contact);


                }

                // RelativeLayout serv = (RelativeLayout) myView.findViewById(R.id.serviced1);
                //serv.setVisibility(View.VISIBLE);

            } catch (JSONException e) {

                e.printStackTrace();
            }


            // uiUpdate.setText( Content);
            ListAdapter adapter = new SimpleAdapter(getActivity(), contactList, R.layout.list_item, new String[]{"name", "position"}, new int[]{R.id.item1, R.id.item2});

            lv.setAdapter(adapter);


        }

    }
}






