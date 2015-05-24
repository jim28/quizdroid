package wyliao.edu.washington.quizdriod;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MSR-2 on 5/17/2015.
 */
public class QuizApp extends Application implements TopicRepository {
    //Singleton
    private  static QuizApp instance;
    private ArrayList<Topic> topicList;




    public QuizApp() {
        if(instance == null) {
            instance = this;
        }
        else {
            Log.e("QuizApp", "You tried to create more than 1 QuizApp");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("Wen", "QuizApp is running");

//        topicList = this.loadData();
//        if(topicList == null) {
//            Log.e("QuizApp","Data don't load !");
//        }

        //Start the Download service


        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        int time = Integer.parseInt(sharedPrefs.getString("time","10000"));
        DownloadService.startOrStopAlarm(this, true, time);
        topicList = loadData();

    }

    // reads InputStream of JSON file and returns the file in JSON String format
    public String readJSONFile(InputStream inputStream) throws IOException {

        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();

        return new String(buffer, "UTF-8");
    }


    @Override
    //load data from jason file to data structure
    public ArrayList<Topic> loadData() {
        ArrayList<Topic> topicList = new ArrayList<Topic>();


        //first check the file whether it is existing



        String json = null;

        // Fetch data.json in assets/ folder
        try {

            InputStream inputStream = null;
            //determine which file should be used
            File myFile = new File(getFilesDir().getAbsolutePath(), "/wen.json");
            if(myFile.exists()) {
                Log.i("Wen","wen.json exist");
                inputStream =openFileInput("wen.json");
            }
            else {
                Log.i("Wen", "Fetch from asset");
                inputStream = getAssets().open("questions.json");
            }

            json = readJSONFile(inputStream);

            //construct Domain objects
            JSONArray jsonArray = new JSONArray(json);


            for (int i = 0; i < jsonArray.length(); i++) {
                Topic topic = new Topic();



                JSONObject eachCata = jsonArray.getJSONObject(i);

                String questStr = eachCata.getString("questions");
                JSONArray questJSAry = new JSONArray(questStr);
                ArrayList<Quiz> quesList = new ArrayList<Quiz>();

                for (int j = 0; j < questJSAry.length(); j++) {
                    JSONObject eachQues = questJSAry.getJSONObject(j);
                    Quiz quiz = new Quiz();
                    quiz.quesText = eachQues.getString("text");


                    //choices
                    JSONArray choiseJSAry = new JSONArray(eachQues.getString("answers"));
                    ArrayList<String> ansList = new ArrayList<String>();

                    for (int k = 0; k < 4; k++) {

                        ansList.add(choiseJSAry.getString(k));
                    }
                    quiz.choiceList = ansList;

                    //ans
                    quiz.ans = eachQues.getInt("answer");

                    //form quiz list
                    quesList.add(quiz);
                }


                topic.title = eachCata.getString("title");
                topic.shortDiscr = eachCata.getString("title");
                topic.longDiscr = eachCata.getString("desc");
                topic.quizList = quesList;
                topicList.add(topic);

            }


            // get the array that exist in the key 'questions'
            /*
                {
                    question: "Why is it always raining here?",
                    food : 124
                }

             */

//            String questionString = jsonData.getString("question");
//            int food = jsonData.getInt("food");

//            this.questions.put(questionString, food); // populate your repository

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return topicList;
    }


    @Override
    public String[] getAllTopics() {
        String[] topics = new String[topicList.size()];
        for(int i =0;i< topicList.size();i++) {
            topics[i] = topicList.get(i).title;
        }
        return topics;
    }

    @Override
    public String longDescr(int id) {
        switch (id) {
            case R.id.math:
                return topicList.get(0).longDiscr;
            case R.id.physics:
                return topicList.get(1).longDiscr;

            case R.id.marvel:
                return topicList.get(2).longDiscr;

            default:
                Log.v("Wen", "Fail to catch the ID");
                return "Error";

        }
    }

    @Override
    public String shortDescr(int id) {
        switch (id) {
            case R.id.math:
                return topicList.get(0).shortDiscr;

            case R.id.physics:
                return topicList.get(1).shortDiscr;

            case R.id.marvel:
                return topicList.get(2).shortDiscr;

            default:
                Log.v("Wen", "Fail to catch the ID");
                return "Error";

        }
    }

    public ArrayList<Quiz> getQuestionSet(int id){
        switch (id) {
            case R.id.math:
                return topicList.get(0).quizList;

            case R.id.physics:
                return topicList.get(1).quizList;

            case R.id.marvel:
                return topicList.get(2).quizList;

            default:
                Log.v("Wen", "Fail to catch the ID");
                return null;

        }
    }




    public void writeToFile(String data) {
        try {
            Log.i("MyApp", "writing downloaded to file");

            File file = new File(getFilesDir().getAbsolutePath(), "wen.json");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data.getBytes());
            fos.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


}



