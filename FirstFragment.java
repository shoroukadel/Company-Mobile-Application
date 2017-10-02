package commyc.example.blu_ray91111.timslinesoluation;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Blu-Ray on 30/01/2017.
 */

public class FirstFragment extends Fragment {
    View myView;
    public static TextView projectname;
    public static TextView descrip;
    public static TextView statue;
    ProgressDialog prgDialog;
    private ListView lv;
    private ListView m;
    private loadersfragment mmLoaderAdapter;
    private ArrayList<Projects> mArrayList = null;
    Projects mProduct;
    private String TAG = MainActivity.class.getSimpleName();
    ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
   // ArrayList<HashMap<String, String>> imglists = new ArrayList<HashMap<String, String>>();
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.first_layout,container,false);
        prgDialog = new ProgressDialog(getActivity());
        prgDialog.setMessage("Please wait............");
        prgDialog.setCancelable(false);
        String serverURL = "http://phone.tmsline.com/view_project";
        String url="http://phone.tmsline.com/images/uploads/";
        StringBuilder value;
       ImageButton ticket=(ImageButton)myView.findViewById(R.id.ticket) ;
        //ImageView image=(ImageView) myView.findViewById(R.id.test);
       // Picasso.with(getActivity()).load("http://phone.tmsline.com/images/uploads/0db15972ce6ab793837b9281ccf3c13b.png").into(image);


        lv = (ListView) myView.findViewById(R.id.list);
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
        String[] images4=new String[3];
        String[] images5=new String[3];
        String[] images7=new String[3];
        String[] images6=new String[3];
        String[] images1=new String[3];
        String[] images2=new String[3];
        String[] images3=new String[3];
        String[] totals=new String[3];
        String aa;

        ArrayList<String> arrList= new ArrayList<>();
        ArrayList<String> arrList1= new ArrayList<>();
        ArrayList<String> arrList2= new ArrayList<>();
        ArrayList<String> arrList3= new ArrayList<>();
        ArrayList<String> arrListt= new ArrayList<>();

        //TextView mess=(TextView) myView.findViewById(R.id.mesed);

        //ImageView imagee = (ImageView) myView.findViewById(R.id.image);
        String url = "http://phone.tmsline.com/images/uploads/";
        String[] img = new String[1000];
        // lv.setAdapter(new ImageLoader(, img));


        protected void onPreExecute() {

            Dialog.setMessage("Loading service..");
            Dialog.show();
            Dialog.dismiss();

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


                JSONObject json = new JSONObject(Content);
                JSONArray jre = json.getJSONArray("projects");
                JSONArray jreimages = json.getJSONArray("screenshots");
                mArrayList = new ArrayList<Projects>();

                for (int j = 0; j < jre.length(); j++) {

                    mProduct = new Projects();
                    JSONObject jobject = jre.getJSONObject(j);
                    String name11 = jobject.getString("name");
                    String description = jobject.getString("description");
                    String status = jobject.getString("status");
                    String version = jobject.getString("version");
                    String id =jobject.getString("id");

                    mProduct.setyourText(name11);
                        mProduct.setyourstatu(status);
                        mProduct.setYourversion(version);
                        mProduct.setyourdescription(description);
                    mProduct.setid(id);

                          int k=0;
                    int l=0;
                    int o=0;
                    int p=0;
                    int w=0;
                    int z=0;
                    int x=0;

                    String[] images1=new String[5];

                        for (int i = 0; i < jreimages.length(); i++) {
                            JSONObject jjobject = jreimages.getJSONObject(i);
                            String imageid=jjobject.getString("project_id");
                            //mess.setText(imageid);
//                            Toast.makeText(getActivity(),imageid,Toast.LENGTH_LONG);
                           // arrList.clear();

                            if (imageid.equals(id)) {
                                String urlimage = jjobject.getString("screenshot");
                                String total = url + urlimage;
                               // mess.setText(total);
                                  String aa="";
                                images1[k]=total;
                                k++;
                                totals=images1;



                              /**  if (imageid.equals("105")){
                                   //arrList.add(total);
                                    images1[k]=total;
                                    k++;
                                    totals=images1;

                                    }
                                else if (imageid.equals("106")){
                                    //arrList.add(total);
                                    images2[l]=total;
                                    l++;
                                    totals=images2;

                                }

                                else if (imageid.equals("107")){
                                    //arrList.add(total);
                                    images3[o]=total;
                                    o++;
                                    totals=images3;

                                }
                                else if (imageid.equals("108")){
                                   // arrList.add(total);
                                    images4[w]=total;
                                    w++;
                                    totals=images4;

                                }
                                else if (imageid.equals("109")){
                                    //arrList.add(total);
                                    images5[p]=total;
                                    p++;
                                    totals=images5;

                                }
                                else if (imageid.equals("110")){
                                    //arrList.add(total);
                                    images6[z]=total;
                                    z++;
                                    totals=images6;

                                }
                                else if (imageid.equals("111")){
                                    //arrList.add(total);
                                    images7[x]=total;
                                    x++;
                                    totals=images7;} */



                            }



                            //images[0]="https://s-media-cache-ak0.pinimg.com/564x/9a/0d/c9/9a0dc96ac4d64f88f163441282a830b0.jpg";
                            //images[1]="https://s-media-cache-ak0.pinimg.com/564x/9a/0d/c9/9a0dc96ac4d64f88f163441282a830b0.jpg";
                            //images[2]="https://s-media-cache-ak0.pinimg.com/564x/9a/0d/c9/9a0dc96ac4d64f88f163441282a830b0.jpg";
                           // String [] it={"http://img1.sendscraps.com/se/042/004.jpg","http://i.telegraph.co.uk/multimedia/archive/03589/Wellcome_Image_Awa_3589699k.jpg","http://www.freedigitalphotos.net/images/category-images/131.jpg"};

                    }


                    mProduct.setYourimages(images1);
                    mProduct.setImageno(k);
                    mArrayList.add(mProduct);
                    mProduct = null;
                    //arrList.clear();
                    totals=null;



                }


            } catch (JSONException e) {

                e.printStackTrace();
            }

            mmLoaderAdapter = new loadersfragment (getActivity(),mArrayList);

            lv.setAdapter(mmLoaderAdapter);



        }

    }


}
