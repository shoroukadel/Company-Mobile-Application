package commyc.example.blu_ray91111.timslinesoluation;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Blu-Ray on 02/03/2017.
 */

public class CustomerAdapter extends PagerAdapter {
    //private int[] images = {R.mipmap.img1,R.mipmap.img2,R.mipmap.img3,R.mipmap.img4};
    private Context ctx;
    private  String[] urls;
    private LayoutInflater inflater;
    final Handler handler = new Handler();
    public Timer swipeTimer ;
    private  int numbers;

    public CustomerAdapter(Context ctx,String []urls,int numbers){
        this.ctx = ctx;
        this.urls=urls;
        this.numbers=numbers;
    }
    @Override
    public int getCount() {
        return numbers;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view ==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.swip,container,false);
        ImageView img =(ImageView)v.findViewById(R.id.itemview);

        Picasso.with(ctx).load(urls[position]).into(img);


        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        container.refreshDrawableState();
    }
    public void autoSlider(final ViewPager viewPager) {

        final Runnable rr = new Runnable() {
            public void run() {
                int pos = viewPager.getCurrentItem();
                int i=0;
                if(pos > i && pos != numbers - 1){
                    i = pos;
                    i++;
                }

                else if(pos < (i-1)){
                    i = pos;
                    i++;
                }
                else if(pos == i){
                    i = pos;
                    i++;
                }
                viewPager.setCurrentItem(i, true);
                //viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
                i++;
                if (i >= numbers)
                    i = 0;
                autoSlider(viewPager);
            }};
        handler.postDelayed(rr, 20000);
    }
   /** public void setTimer(final ViewPager myPager, int time, final int numPages, final int curPage){


        final Runnable Update = new Runnable() {
            int NUM_PAGES =numPages;
            int currentPage = curPage ;
            boolean s=true;


                public void run () {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }

                for (int i = 0; i < NUM_PAGES; i++) {
                    for (int j = 0; j < 100000; j++) {

                    }

                    myPager.setCurrentItem(i, true);

                    myPager.setPageTransformer(true, new ZoomOutPageTransformer());
                }
            }

        };

        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, time*1000);

    }
    public void stopTimer(){
        //handler.removeCallbacks(null);
        swipeTimer.cancel();
    }*/
}
