package wyliao.edu.washington.quizdriod;

import java.util.ArrayList;

/**
 * Created by MSR-2 on 5/17/2015.
 */
public class Topic {
    String title;
    String shortDiscr;
    String longDiscr;
    ArrayList<Quiz> quizList;

    public Topic() {
        quizList = new ArrayList<Quiz>();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLongDiscr(String longDiscr) {
        this.longDiscr = longDiscr;
    }

    public void setQuizList(ArrayList<Quiz> quizList) {
        this.quizList = quizList;
    }

    public void setShortDiscr(String shortDiscr) {
        this.shortDiscr = shortDiscr;
    }
}
