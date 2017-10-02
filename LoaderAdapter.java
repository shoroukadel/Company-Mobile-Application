package commyc.example.blu_ray91111.timslinesoluation;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Blu-Ray on 28/02/2017.
 */

public class LoaderAdapter extends ArrayAdapter<Product> {

    public Context context;
    private ArrayList<Product> values;

    private LayoutInflater inflater;

    private String[] img;

    public LoaderAdapter(Context context, ArrayList<Product> values) {
        super(context, R.layout.item,values);

        this.context = context;
        this.values = values;

        inflater = LayoutInflater.from(context);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item, parent, false);

        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageview);
        TextView textView = (TextView) convertView.findViewById(R.id.title);
        TextView textView2 = (TextView) convertView.findViewById(R.id.description);



        Product mProduct = values.get(position);

        textView.setText(mProduct.getyourText());
        textView2.setText(mProduct.getyourdescription());
        Picasso.with(context).load(mProduct.getimgUrl()).into(imageView);

        return convertView;

    }
}
