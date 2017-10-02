package commyc.example.blu_ray91111.timslinesoluation;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Employeeinformation  extends ArrayAdapter<person> {

    Context context;
    List<person> myList;

    public Employeeinformation(Context context, int resource, List<person> objects) {
        super(context, resource, objects);

        this.context = context;
        this.myList = objects;
    }


    @Override
    public int getCount() {
        if(myList != null)
            return myList.size();
        return 0;
    }

    @Override
    public person getItem(int position) {
        if(myList != null)
            return myList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        if(myList != null)
            return myList.get(position).hashCode();
        return 0;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;

        //If the listview does not have an xml layout ready set the layout
        if (convertView == null){

            //we need a new holder to hold the structure of the cell
            holder = new Holder();

            //get the XML inflation service
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //Inflate our xml cell to the convertView
            convertView = inflater.inflate(R.layout.item, null);

            //Get xml components into our holder class
            holder.txttitle = (TextView)convertView.findViewById(R.id.item1);
            holder.txtdescription = (TextView)convertView.findViewById(R.id.item2);
            holder.imageView = (ImageView)convertView.findViewById(R.id.imageview);

            //Attach our holder class to this particular cell
            convertView.setTag(holder);

        }else{

            //The listview cell is not empty and contains already components loaded, get the tagged holder
            holder = (Holder)convertView.getTag();

        }

        //Fill our cell with data

        //get our person object from the list we passed to the adapter
        person person = getItem(position);

        //Fill our view components with data
        holder.txttitle.setText(person.gettitle());
        holder.txtdescription.setText(person.getDescription());

        Picasso.with(context).load("https://lh3.googleusercontent.com/-Sa9kdnhuE5E/AAAAAAAAAAI/AAAAAAAAABs/ILmJ8_sk9aY/photo.jpg").fit().into(holder.imageView);

        return convertView;
    }

    /**
     * This holder must replicate the components in the person_cell.xml
     * We have a textview for the name and the surname and an imageview for the picture
     */
    private class Holder{

        TextView txttitle;
        TextView txtdescription;
        ImageView imageView;

    }
}
