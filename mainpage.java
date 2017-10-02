package commyc.example.blu_ray91111.timslinesoluation;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.nostra13.universalimageloader.core.ImageLoader;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.System.load;

public class mainpage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ProgressDialog prgDialog;
    private ListView lv;
    private String TAG = MainActivity.class.getSimpleName();
    private LoaderAdapter mLoaderAdapter;
    private ArrayList<Product> mArrayList = null;
    Product mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage("Please wait............");
        prgDialog.setCancelable(false);
        String serverURL = "http://phone.tmsline.com/view_update";
        String url = "http://phone.tmsline.com/images/uploads/";
        lv = (ListView) findViewById(R.id.updatelist);

        new LoadService().execute(serverURL);


        /** FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show();
        }
        });
         */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainpage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
            android.app.FragmentManager fragmentManager = getFragmentManager();
            if (id == R.id.project) {
                // Handle the camera action

            fragmentManager.beginTransaction().replace(R.id.content_frame, new FirstFragment()).commit();
        } else if (id == R.id.newproject) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SecondFragment()).commit();
        } else if (id == R.id.joboffers) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new ThirdFragment()).commit();
        } else if (id == R.id.employee) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new FourthFragment()).commit();
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);

            intent.setType("text/plain");

            intent.putExtra(Intent.EXTRA_TEXT, "http://www.tmsline.com");

            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this site!");

            startActivity(Intent.createChooser(intent, "Share"));

        } else if (id == R.id.nav_send) {
            start();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private class LoadService extends AsyncTask<String, Void, Void> {

        private final HttpClient Client = new DefaultHttpClient();
        private String Content;
        private String Error = null;
        private final String TAG = null;
        private ProgressDialog Dialog = new ProgressDialog(mainpage.this);

        ImageView imagee = (ImageView) findViewById(R.id.image);
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
                JSONArray jre = json.getJSONArray("updates");
                mArrayList = new ArrayList<Product>();

                for (int j = 0; j < jre.length(); j++) {

                    mProduct = new Product();
                    JSONObject jobject = jre.getJSONObject(j);
                    String name11 = jobject.getString("title");
                    String description = jobject.getString("description");
                    String image = jobject.getString("image");
                    String total = url + image;
                    img[j] = total;
                    mProduct.setyourText(name11);
                    mProduct.setimgUrl(total);
                    mProduct.setyourdescription(description);
                    mArrayList.add(mProduct);
                    mProduct = null;

                }

            } catch (JSONException e) {

                e.printStackTrace();
            }

            mLoaderAdapter = new LoaderAdapter (getApplicationContext(),mArrayList);

            lv.setAdapter(mLoaderAdapter);



        }

    }

    public void start() {
        Intent intent = new Intent(this, mainpage.class);
        startActivity(intent);
    }

}
