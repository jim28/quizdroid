package wyliao.edu.washington.quizdriod;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        TableLayout topicTable = (TableLayout)findViewById(R.id.table);
        for(int i=0;i>-1;i++) {
            View row = topicTable.getChildAt(i);

            if(row==null)
                break;
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View textView =((ViewGroup)v).getChildAt(0);
                    Intent next = new Intent(MainActivity.this,NextActivity.class);
                    Log.v("WenIDShit",String.valueOf(textView.getId()));
                    next.putExtra("topic",textView.getId());
                    startActivity(next);
                    finish();
                }
            });
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
