package com.aoeng.views.uis;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aoeng.views.R;
import com.aoeng.views.views.GuaGuaKa;

public class TearClotherUI extends ActionBarActivity {

    private GuaGuaKa guaguaka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tear_clother_ui);


        guaguaka = (GuaGuaKa) findViewById(R.id.guaguaka);
//        guaguaka.setBackgroundResource(R.drawable.fg_guagua);
//        guaguaka.setFrontColor(Color.parseColor("#c0c0c0"));

        guaguaka.setOnClearComplateListener(new GuaGuaKa.OnClearComplateListener() {
            @Override
            public void onClearComplate() {
                Toast.makeText(TearClotherUI.this, "恭喜你，美女衣服撕完了 ，哈哈", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tear_clother_ui, menu);
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
