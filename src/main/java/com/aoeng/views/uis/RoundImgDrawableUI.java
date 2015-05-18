package com.aoeng.views.uis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.aoeng.views.R;
import com.aoeng.views.views.RoundImageDrawable;


public class RoundImgDrawableUI extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_img_drawable_ui);

        ImageView iv = (ImageView) findViewById(R.id.ivRoundImg);
        ImageView iv2 = (ImageView) findViewById(R.id.ivRoundImg2);
        ImageView iv3 = (ImageView) findViewById(R.id.ivRoundImg3);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_round);
        iv.setImageDrawable(new RoundImageDrawable(bitmap));
        iv2.setImageDrawable(new RoundImageDrawable(bitmap));
        iv3.setImageDrawable(new RoundImageDrawable(bitmap));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_round_img_drawable_ui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
