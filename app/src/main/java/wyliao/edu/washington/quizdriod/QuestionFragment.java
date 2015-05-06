package wyliao.edu.washington.quizdriod;

/**
 * Created by jimliao on 2015/4/27.
 */



import android.app.Activity;
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


public class QuestionFragment extends Fragment{

    private Activity hostActivity;

    boolean checked;
    int count;
    int catag;
    int rightAns;
    int ans;
    Button submitBtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            count = getArguments().getInt("status", 0);
            catag = getArguments().getInt("catag", 0);
            rightAns = getArguments().getInt("rightAns", 0);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        //Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_question, container, false);

        String[][] testDataArSet = (String[][]) new TestSet().getTestSet().get(catag);
        String[] testDataAr = testDataArSet[count];

        //setting layout
        TextView qtTitle = (TextView)v.findViewById(R.id.qtTitle);
        qtTitle.setText(testDataAr[0]);
        ans = Integer.valueOf(testDataAr[1]);

        RadioButton op1 = (RadioButton) v.findViewById(R.id.aws1);
        op1.setText(testDataAr[2]);
        RadioButton op2 = (RadioButton) v.findViewById(R.id.aws2);
        op2.setText(testDataAr[3]);
        RadioButton op3 = (RadioButton) v.findViewById(R.id.aws3);
        op3.setText(testDataAr[4]);
        RadioButton op4 = (RadioButton) v.findViewById(R.id.aws4);
        op4.setText(testDataAr[5]);

        submitBtn = (Button) v.findViewById(R.id.submit);

        RadioGroup radioGroup = (RadioGroup)v.findViewById(R.id.choices);
        radioGroup.setOnCheckedChangeListener(new onCheckedChangeListner(){

                                              }


        );

        return  v;
    }


    private class onCheckedChangeListner implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

          View view =group.findViewById(checkedId);


            checked = ((RadioButton) view).isChecked();
            final View radioClickBtn = view;

            //check the answer
            if(checked) {
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
                        Log.v("wen", "click bto");
                        if(userAns==ans)
                            rightAns++;
                        if(hostActivity instanceof NextActivity) {
                            ((NextActivity)hostActivity).loadAns(count,catag,rightAns,userAns);
                        }

                    }
                });
            }

        }
    }

}