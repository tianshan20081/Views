package com.aoeng.views.uis;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aoeng.views.R;
import com.aoeng.views.views.GuaGuaView;


public class GuaGuaKaUI extends ActionBarActivity {

    private GuaGuaView guagua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gua_gua_ka_ui);


        guagua = (GuaGuaView) findViewById(R.id.guagua);
        String guaguaInfo = "50，0000";
        guagua.setText(guaguaInfo);
        guagua.setOnClearPercentListener(new GuaGuaView.OnClearPercentListener() {
            @Override
            public void loadClearPercent(int percent) {
                Toast.makeText(GuaGuaKaUI.this, "已经刮了" + percent + "%", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gua_gua_ka_ui, menu);
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
