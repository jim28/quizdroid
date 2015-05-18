package wyliao.edu.washington.quizdriod;

import java.util.ArrayList;

/**
 * Created by MSR-2 on 5/17/2015.
 */
public interface TopicRepository {

    public ArrayList<Topic> loadData();

    public String[] getAllTopics();
    public String shortDescr(int id);
    public String longDescr(int id);
    public String[][] getQuestionSet(int id);

}
