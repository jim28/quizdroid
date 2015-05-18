package wyliao.edu.washington.quizdriod;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.widget.SearchViewCompatIcs;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Hashtable;
import java.util.Map;


public class NextActivity extends ActionBarActivity {
    //Product the Data
    int userAns;
    int rightAnsNum;
    int corrAns;
    int count;
    int catag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction);


        Intent launchingIntent = getIntent();
        final int topicID = launchingIntent.getIntExtra("topic", 0);

        TextView introText = (TextView) findViewById(R.id.intrContext);
        Log.v("Wen", String.valueOf(topicID));

        final QuizApp quizApp = (QuizApp) getApplication();

        introText.setText(quizApp.shortDescr(topicID)+"\n\n"+quizApp.longDescr(topicID));



        Button bgBtn = (Button) findViewById(R.id.begin);
        bgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                catag = topicID;
                count =0;

                showQues();

            }
        });

    }

    public void loadAns() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.animator.enter_from_right,R.animator.exit_to_left);

        count++;
        Bundle ansBundle = new Bundle();
        ansBundle.putInt("status", count);
        ansBundle.putInt("catag", catag);
        ansBundle.putInt("rightAns", rightAnsNum);
        ansBundle.putInt("userAns", userAns);
        Log.v("Wen", "call loadAnd");

        AnswerFragment answerFragment = new AnswerFragment();
        answerFragment.setArguments(ansBundle);

        ft.replace(R.id.contentContainer, answerFragment);
        ft.commit();
    }

    public void showQues() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Bundle quesBundle = new Bundle();
        quesBundle.putInt("status", count);
        quesBundle.putInt("catag", catag);
        quesBundle.putInt("rightAns", rightAnsNum);

        QuestionFragment quesFragment = new QuestionFragment();
        quesFragment.setArguments(quesBundle);

        ft.replace(R.id.contentContainer, quesFragment);
        ft.commit();

    }

    public void goBackMain() {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
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

    //Question call back function
    public void onRadioButtonClicked(View view) {


        boolean checked = ((RadioButton) view).isChecked();
        final View radioClickBtn = view;

        //check the answer
        if (checked) {
            Button submitBtn = (Button) findViewById(R.id.submit);
            submitBtn.setVisibility(View.VISIBLE);

            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (radioClickBtn.getId()) {
                        case R.id.aws1:
                            userAns = 1;
                            break;
                        case R.id.aws2:
                            userAns = 2;
                            break;
                        case R.id.aws3:
                            userAns = 3;
                            break;
                        case R.id.aws4:
                            userAns = 4;
                            break;
                    }
                    if (userAns == corrAns)
                        rightAnsNum++;

                    loadAns();

                }
            });
        }

    }


    public void updateCond(int new_ans,int new_rightAnsNum, int new_count, int new_catag, int new_corrAns) {

        if (new_ans!=0)
            userAns = new_ans;
        if (new_rightAnsNum!=0)
            rightAnsNum = new_rightAnsNum;
        if (new_count!=0)
            count = new_count;
        if (new_catag!=0)
            catag = new_catag;
        if (new_corrAns!=0)
            corrAns = new_corrAns;
    }

}
