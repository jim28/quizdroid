package wyliao.edu.washington.quizdriod;

/**
 * Created by jimliao on 2015/4/27.
 */



import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;


public class QuestionFragment extends Fragment{

    private Activity hostActivity;

    boolean checked;
    int count;
    int catag;
    int rightAns;
    int ans;
    Button submitBtn;
    String[] testDataAr;
    Quiz curQues;

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
            catag = getArguments().getInt("catag", 0);
            rightAns = getArguments().getInt("rightAns", 0);

//            Map test = new TestSet().getTestSet();
//            String[][] testDataArSet = (String[][]) new TestSet().getTestSet().get(catag);


            QuizApp quizApp = (QuizApp) getActivity().getApplication();
            ArrayList<Quiz> quizSet = quizApp.getQuestionSet(catag);


            curQues = quizSet.get(count);


        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        //Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_question, container, false);



        //setting layout
        TextView qtTitle = (TextView)v.findViewById(R.id.qtTitle);
        qtTitle.setText(curQues.quesText);
        ans = curQues.ans;

        RadioButton op1 = (RadioButton) v.findViewById(R.id.aws1);
        op1.setText(curQues.choiceList.get(0));
        RadioButton op2 = (RadioButton) v.findViewById(R.id.aws2);
        op2.setText(curQues.choiceList.get(1));
        RadioButton op3 = (RadioButton) v.findViewById(R.id.aws3);
        op3.setText(curQues.choiceList.get(2));
        RadioButton op4 = (RadioButton) v.findViewById(R.id.aws4);
        op4.setText(curQues.choiceList.get(3));

        submitBtn = (Button) v.findViewById(R.id.submit);

        ((NextActivity)hostActivity).updateCond(0,0,0,0,ans);


        return  v;
    }
}