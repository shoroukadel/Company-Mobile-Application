package commyc.example.blu_ray91111.timslinesoluation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.view.View;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Blu-Ray on 16/02/2017.
 */

public class ImageLoader extends ArrayAdapter {

    public Context context;
    private LayoutInflater inflater;

    private String[] img;

    public ImageLoader(Context context, ArrayList<Product> values) {
        super(context, R.layout.items, values);

        this.context = context;
        this.img = img;

        inflater = LayoutInflater.from(context);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.items, parent, false);
        }

       Glide.with(context)
                .load(img[position])
               .into((ImageView) convertView);

        return convertView;
    }

}






