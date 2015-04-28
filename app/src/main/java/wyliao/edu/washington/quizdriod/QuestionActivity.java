package wyliao.edu.washington.quizdriod;

/**
 * Created by jimliao on 2015/4/27.
 */



import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Map;


public class QuestionActivity extends ActionBarActivity {

    boolean checked;
    int count;
    int catag;
    int rightAns;
    int ans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);


        Intent questionIntent = getIntent();
        count = questionIntent.getIntExtra("status",0);
        catag = questionIntent.getIntExtra("catag",0);
        rightAns = questionIntent.getIntExtra("rightAns",0);
        String[][] testDataArSet = (String[][]) new TestSet().getTestSet().get(catag);
        String[] testDataAr = testDataArSet[count];

        //setting layout
        TextView qtTitle = (TextView)findViewById(R.id.qtTitle);
        qtTitle.setText(testDataAr[0]);
        ans = Integer.valueOf(testDataAr[1]);

        RadioButton op1 = (RadioButton) findViewById(R.id.aws1);
        op1.setText(testDataAr[2]);
        RadioButton op2 = (RadioButton) findViewById(R.id.aws2);
        op2.setText(testDataAr[3]);
        RadioButton op3 = (RadioButton) findViewById(R.id.aws3);
        op3.setText(testDataAr[4]);
        RadioButton op4 = (RadioButton) findViewById(R.id.aws4);
        op4.setText(testDataAr[5]);





    }


    public void onRadioButtonClicked(View view) {

        checked = ((RadioButton) view).isChecked();
        final View radioClickBtn = view;

        //check the answer
        if(checked) {
            Button submitBtn = (Button)findViewById(R.id.submit);
            submitBtn.setVisibility(View.VISIBLE);

            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int userAns = 0;
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
                    if(userAns==ans)
                        rightAns++;

                    Intent answer = new Intent(QuestionActivity.this,answer.class);
                    answer.putExtra("status",count);
                    answer.putExtra("catag",catag);
                    answer.putExtra("rightAns",rightAns);
                    answer.putExtra("userAns",userAns);
                    startActivity(answer);
                    finish();
                }
            });
        }

    }


}