package commyc.example.blu_ray91111.timslinesoluation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public static TextView tm;
    public static ListView listview;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //makeTextViewDisappear();
        imgmove();
        img();
        yala();

    }
    public void img(){
        tm=(TextView) findViewById(R.id.texttm);

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(tm, "alpha",  1f, .3f);
        fadeOut.setDuration(3000);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(tm, "alpha", .3f, 1f);
        fadeIn.setDuration(3000);

        final AnimatorSet mAnimationSet = new AnimatorSet();

        mAnimationSet.play(fadeIn).after(fadeOut);

        mAnimationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimationSet.start();
            }
        });
        mAnimationSet.start();
    }
    public void imgmove(){
        final TextView t1=(TextView)findViewById(R.id.mobile) ;
        final  TextView t2=(TextView)findViewById(R.id.web) ;
        final TextView t3=(TextView)findViewById(R.id.marketing) ;


         //for 10 seconds
        tm=(TextView) findViewById(R.id.texttm);

        ImageView im=(ImageView) findViewById(R.id.imageView8);

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(t1, "alpha",  1f, .3f);
        fadeOut.setDuration(1000);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(t1, "alpha", .1f, 1f);
        fadeIn.setDuration(1000);
        ObjectAnimator fadeOut1 = ObjectAnimator.ofFloat(t2, "alpha",  1f, .3f);
        fadeOut.setDuration(1000);
        ObjectAnimator fadeIn1 = ObjectAnimator.ofFloat(t2, "alpha", .1f, 1f);
        fadeIn.setDuration(1000);
        ObjectAnimator fadeOut2 = ObjectAnimator.ofFloat(t3, "alpha",  1f, .3f);
        fadeOut.setDuration(1000);
        ObjectAnimator fadeIn2 = ObjectAnimator.ofFloat(t3, "alpha", .1f, 1f);
        fadeIn.setDuration(1000);

        final AnimatorSet mAnimationSet2 = new AnimatorSet();

        mAnimationSet2.play(fadeIn).after(fadeOut);
        mAnimationSet2.play(fadeIn1).after(fadeOut1);
        mAnimationSet2.play(fadeIn2).after(fadeOut2);

        mAnimationSet2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimationSet2.start();
            }
        });
        mAnimationSet2.start();
    }
    public void yala(){
        new Timer().schedule(new TimerTask(){
            public void run() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        start();
                    }
                });
            }
        }, 6000);
    }
    public void start() {
        Intent intent = new Intent(this, mainpage.class);
        startActivity(intent);
    }


        public void makeTextViewDisappear(){
            final TextView t1=(TextView)findViewById(R.id.mobile) ;
            final  TextView t2=(TextView)findViewById(R.id.web) ;
            final TextView t3=(TextView)findViewById(R.id.marketing) ;

            t1.setVisibility(View.VISIBLE);
            t2.setVisibility(View.VISIBLE);
            t3.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    t1.setVisibility(View.INVISIBLE);
                    t2.setVisibility(View.INVISIBLE);
                    t3.setVisibility(View.INVISIBLE);
                    // OR yourTV.setVisibility(View.GONE) to reclaim the space used by textview
                }
            }, 10000); //for 10 seconds
        }




}
