package commyc.example.blu_ray91111.timslinesoluation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class showitem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showitem);
        ImageButton tic=(ImageButton)findViewById(R.id.ticket);
        tic.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {



                    }
                }
        );

    }
}
