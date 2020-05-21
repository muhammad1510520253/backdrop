package com.ketikanmd.backdrop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private RelativeLayout frontLayout;
    private LinearLayout backLayout;
    private TextView textView;
    private RadioButton rbYa,rbTidak;
    private RelativeLayout.LayoutParams lp;
    MenuItem itemCheck;
    boolean showBackLayout = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        frontLayout = findViewById(R.id.front_layout);
        backLayout = findViewById(R.id.back_layout);
        textView = findViewById(R.id.text);
        rbYa = findViewById(R.id.rb_ya);
        rbTidak = findViewById(R.id.rb_tidak);
        setSupportActionBar(toolbar);
        initListener();


    }

    private void initListener(){
        rbYa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Anda pilih ya");
            }
        });

        rbTidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Anda pilih tidak");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item, menu);
        itemCheck=menu.findItem(R.id.action_pilih);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_pilih) {
           dropLayout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void dropLayout() {
        showBackLayout = !showBackLayout;
        itemCheck.setIcon(ContextCompat.getDrawable(MainActivity.this, showBackLayout ? R.drawable.ic_close : R.drawable.ic_pilih));
         lp = (RelativeLayout.LayoutParams) frontLayout.getLayoutParams();
        if (showBackLayout) {
            ValueAnimator varl =ValueAnimator.ofInt(backLayout.getHeight());
            varl.setDuration(100);
            varl.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    lp.setMargins(0, (Integer) animation.getAnimatedValue(), 0, 0);
                    frontLayout.setLayoutParams(lp);
                }
            });
            varl.start();
        }else {
            lp.setMargins(0, 0, 0, 0);
            frontLayout.setLayoutParams(lp);
            TranslateAnimation anim = new TranslateAnimation(0, 0, backLayout.getHeight(), 0);
            anim.setStartOffset(0);
            anim.setDuration(200);
            frontLayout.startAnimation(anim);
        }
    }
}
