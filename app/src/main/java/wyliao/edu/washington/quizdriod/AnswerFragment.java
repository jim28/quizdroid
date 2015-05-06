package wyliao.edu.washington.quizdriod;

/**
 * Created by jimliao on 2015/4/27.
 */



import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


public class AnswerFragment extends Fragment {

    int count;
    int catag;
    int rightAns;
    int userAns;

    private Activity hostActivity;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.hostActivity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            count = getArguments().getInt("status", 0);
            catag = getArguments().getInt("catag",0);
            rightAns = getArguments().getInt("rightAns",0);
            userAns = getArguments().getInt("userAns",0);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_answer, container, false);

        String[][] testDataArSet = (String[][]) new TestSet().getTestSet().get(catag);
        String[] testDataAr = testDataArSet[count];

        //setting layout
        TextView usrAnsView = (TextView)v.findViewById(R.id.usrAns);
        usrAnsView.setText(testDataAr[userAns+1]);

        TextView corAnsView = (TextView)v.findViewById(R.id.corAns);
        corAnsView.setText(testDataAr[Integer.valueOf(testDataAr[1])+1]);

        TextView report = (TextView) v.findViewById(R.id.report);
        report.setText("You have "+rightAns+" out of 9 correct");


        final Button nextBtn = (Button)v.findViewById(R.id.next);
        if(count ==8)
            nextBtn.setText("Finish");

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 8) {
                    if(hostActivity instanceof NextActivity) {
                        //go back the main menu
                        ((NextActivity) hostActivity).goBackMain();
                    }
                } else {
                    if(hostActivity instanceof NextActivity) {
                        //show next Ques
                        count++;
                        ((NextActivity) hostActivity).updateCond(userAns,rightAns,count,catag,0);
                        ((NextActivity)hostActivity).showQues();
                    }
                }
            }
        });

        return v;
    }






}