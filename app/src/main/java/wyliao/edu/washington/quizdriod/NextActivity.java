package wyliao.edu.washington.quizdriod;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Hashtable;
import java.util.Map;


public class NextActivity extends ActionBarActivity {
    //Product the Data




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction);




        Intent launchingIntent = getIntent();
        final int topicID = launchingIntent.getIntExtra("topic",0);
        TextView introText = (TextView) findViewById(R.id.intrContext);
        Log.v("Wen",String.valueOf(topicID));
        switch (topicID) {
            case R.id.math:
                introText.setText("Math Section: \n\nTest your basic calculation! +,-,*,/ test\nThere are nine questions !");
                break;
            case R.id.physics:
                introText.setText("Physics Section: \n\n" +
                        "Test your basic conceptual physics knowledge! \n" +
                        "There are nine questions !");
                break;
            case R.id.marvel:
                introText.setText("Marvel Super Heroes Section:\n\n" +
                        "I don't know what is this ! Just random questions about author\n" +
                        "There are nine questions !");
                break;
            case R.id.circuit:
                introText.setText("Circuit Section:\n\n" +
                        "Test your basic circuit term!\n" +
                        "There are nine questions !");
                break;
            default:
                Log.v("Wen","Fail to catch the ID");
                break;
        }

        Button bgBtn = (Button)findViewById(R.id.begin);
        bgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map testSet = new TestSet().getTestSet();
                Intent questionIntent = new Intent(NextActivity.this,QuestionActivity.class);
                final String[][] qtSet;

                switch (topicID) {
                    case R.id.math:
                        qtSet = (String[][])testSet.get("mathSet");
                        break;
                    case R.id.physics:
                        qtSet = (String[][])testSet.get("physicsSet");
                        break;
                    case R.id.marvel:
                        qtSet = (String[][])testSet.get("marvelSet");
                        break;
                    case R.id.circuit:
                        qtSet = (String[][])testSet.get("circuitSet");
                        break;
                    default:
                        Log.v("Wen","Data doesn't load");
                        break;

                }


                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, new SecondFragment())
                        .setTransition(0)
                        .commit();




                questionIntent.putExtra("catag",topicID);
                questionIntent.putExtra("state",1);
                startActivity(questionIntent);
                finish();

            }
        });







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

    public static class PlaceholderFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.introduction, container, false);
            return rootView;
        }
    }
}
