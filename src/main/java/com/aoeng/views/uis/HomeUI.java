package com.aoeng.views.uis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.aoeng.views.R;


public class HomeUI extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ui);

        findViews();
    }

    private void findViews() {
        findViewById(R.id.btnGuaGuaKa).setOnClickListener(this);
        findViewById(R.id.btnTearClother).setOnClickListener(this);
        findViewById(R.id.btnRoundImg).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_ui, menu);
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

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Intent toUI = null;
        switch (v.getId()) {
            case R.id.btnGuaGuaKa:
                toUI = new Intent(HomeUI.this, GuaGuaKaUI.class);
                break;
            case R.id.btnTearClother:
                toUI = new Intent(HomeUI.this, TearClotherUI.class);
                break;
            case R.id.btnRoundImg:
                toUI = new Intent(HomeUI.this, RoundImgDrawableUI.class);
                break;
        }
        if (null != toUI) {
            startActivity(toUI);
        }
    }
}
