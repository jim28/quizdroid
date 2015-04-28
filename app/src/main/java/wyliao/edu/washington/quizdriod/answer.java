package wyliao.edu.washington.quizdriod;

/**
 * Created by jimliao on 2015/4/27.
 */



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


public class answer extends ActionBarActivity {

    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);


        final Intent answerIntent = getIntent();
        count = answerIntent.getIntExtra("status",0);
        final int catag = answerIntent.getIntExtra("catag",0);
        final int rightAns = answerIntent.getIntExtra("rightAns",0);
        int userAns = answerIntent.getIntExtra("userAns",0);




        String[][] testDataArSet = (String[][]) new TestSet().getTestSet().get(catag);
        String[] testDataAr = testDataArSet[count];

        //setting layout
        TextView usrAnsView = (TextView)findViewById(R.id.usrAns);
        usrAnsView.setText(testDataAr[userAns+1]);

        TextView corAnsView = (TextView) findViewById(R.id.corAns);
        corAnsView.setText(testDataAr[Integer.valueOf(testDataAr[1])+1]);

        TextView report = (TextView) findViewById(R.id.report);
        report.setText("You have "+rightAns+" out of 9 correct");


        final Button nextBtn = (Button)findViewById(R.id.next);
        if(count ==8)
            nextBtn.setText("Finish");

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 8) {
                    Intent mainActivity = new Intent(answer.this,MainActivity.class);
                    startActivity(mainActivity);
                } else {
                    Intent question = new Intent(answer.this,QuestionActivity.class);
                    question.putExtra("status",++count);
                    question.putExtra("catag",catag);
                    question.putExtra("rightAns",rightAns);
                    startActivity(question);
                }
                finish();
            }
        });



    }
}