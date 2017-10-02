package commyc.example.blu_ray91111.timslinesoluation;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blu-Ray on 01/03/2017.
 */

public class loadersfragment extends ArrayAdapter<Projects> {

    public Context context;
    private ArrayList<Projects> values;
    CustomerAdapter adapter;
    List<CustomerAdapter> adapters = new ArrayList<>();
    private LayoutInflater inflater;
    String[] imagesfortest={"http://img1.sendscraps.com/se/042/004.jpg","http://i.telegraph.co.uk/multimedia/archive/03589/Wellcome_Image_Awa_3589699k.jpg","http://www.freedigitalphotos.net/images/category-images/131.jpg"};
     int number;
    private String[] img;

    public loadersfragment(Context context, ArrayList<Projects> values) {
        super(context,R.layout.listitem,values);

        this.context = context;
        this.values = values;

        inflater = LayoutInflater.from(context);

    }

    @Override
  public View getView(int position, View convertView, ViewGroup parent) {


        if (null == convertView) {
            convertView = inflater.inflate(R.layout.listitem, parent, false);

        }

        TextView textView = (TextView) convertView.findViewById(R.id.name);
        TextView textView2 = (TextView) convertView.findViewById(R.id.description);
        TextView textView3 = (TextView) convertView.findViewById(R.id.status);
        TextView textView4 = (TextView) convertView.findViewById(R.id.version);
        ImageButton tic=(ImageButton) convertView.findViewById(R.id.ticket);
        final String projectid;
        String [] imageses;


        Projects mProduct = values.get(position);

        textView.setText(mProduct.getyourText());
        textView2.setText(mProduct.getyourdescription());
        textView3.setText(mProduct.getyourstatu());
        textView4.setText(mProduct.getYourversion());
        projectid=mProduct.getid();
      imageses=mProduct.getYourimages();
        number=mProduct.getImageno();

        ViewPager viewPager = (ViewPager)convertView.findViewById(R.id.view_pager);
        adapter = new CustomerAdapter(getContext(),imageses,number);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
       // adapter.setTimer(viewPager,5000,4,imagesfortest.length);
        //viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

         adapter.autoSlider(viewPager);
        tic.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                        go(projectid);

                    }
                }
        );


        return convertView;

    }
    /**public View getView(int position, View convertView, ViewGroup parent) {


        if (null == convertView) {
            convertView = inflater.inflate(R.layout.listitem, parent, false);

        }

        TextView textView = (TextView) convertView.findViewById(R.id.name);
        TextView textView2 = (TextView) convertView.findViewById(R.id.description);
        TextView textView3 = (TextView) convertView.findViewById(R.id.status);
        TextView textView4 = (TextView) convertView.findViewById(R.id.version);
        String [] imageses;


        Projects mProduct = values.get(position);

        textView.setText(mProduct.getyourText());
        textView2.setText(mProduct.getyourdescription());
        textView3.setText(mProduct.getyourstatu());
        textView4.setText(mProduct.getYourversion());
        imageses=mProduct.getYourimages();
        ViewPager viewPager = (ViewPager)convertView.findViewById(R.id.view_pager);

        CustomerAdapter adapter = new CustomerAdapter(getContext(),imageses);
        adapters.add(adapter);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        // adapter.setTimer(viewPager,5000,4,imagesfortest.length);
       viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        adapters.get(position).autoSlider(viewPager);



        return convertView;

    }*/


    public void go( String idd) {
        Intent intent = new Intent(getContext(), loginpage.class);
        getContext().startActivity(intent);
        Intent one = new Intent(getContext(), loginpage.class);
        one.putExtra("projectid", idd);
        getContext().startActivity(one);}



}
