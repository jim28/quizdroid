package wyliao.edu.washington.quizdriod;

import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by jimliao on 2015/4/27.
 */
public class TestSet {








    public Map getTestSet() {
        Map testSet = new Hashtable();

        String[] mathTest1 = {
                "4+3=",
                "3",
                "2",
                "1",
                "7",
                "-8"
        } ;

        String[] mathTest2 = {
                "8-3=",
                "1",
                "5",
                "28",
                "11",
                "99"
        };

        String[] mathTest3 = {
                "75-07=",
                "4",
                "32",
                "92",
                "-10",
                "68"
        };

        String[] mathTest4 = {
                "07*31=",
                "2",
                "2000",
                "217",
                "-217",
                "452"
        };

        String[] mathTest5 = {
                "100/10=",
                "3",
                "11",
                "1",
                "10",
                "100"
        };

        String[] mathTest6 = {
                "100/2=",
                "1",
                "50",
                "5",
                "0.5",
                "0.55"

        };

        String[] mathTest7 = {
                "55*5",
                "4",
                "125",
                "400",
                "375",
                "275"

        };

        String[] mathTest8 = {
                "12-31",
                "2",
                "11",
                "-19",
                "1",
                "-9"

        };

        String[] mathTest9 = {
                "0-0",
                "4",
                "-0",
                "+0",
                "0",
                "All is true"

        };


        String[][] mathSet = {mathTest1,mathTest2,mathTest3,mathTest4,mathTest5,mathTest6,mathTest7,mathTest8,mathTest9};

        testSet.put( R.id.math,mathSet);


        String[] physicsTest1 = {
                "What does 'Gravity' means ?",
                "2",
                "Give you vision",
                "Give you weight",
                "Give you energy",
                "Give you dirty"

        };

        String[] physicsTest2 = {
                "What does 'Velocity' means ?",
                "1",
                "distance per second",
                "weight per second",
                "length per second",
                "light per second"

        };

        String[] physicsTest3 = {
                "What does 'Heat' means ?",
                "4",
                "Radiation of water",
                "Radiation of weight",
                "Radiation of light",
                "Radiation of Energy"

        };

        String[] physicsTest4 = {
                "What does 'Light' means ?",
                "4",
                "Has energy",
                "Wave",
                "Photos",
                "All above are right"

        };

        String[] physicsTest5 = {
                "What does 'Entropy' present for ?",
                "2",
                "Weigh",
                "Heat",
                "Light",
                "Electrons"

        };

        String[] physicsTest6 = {
                "What does 'F=ma' means ?",
                "1",
                "A Native Law",
                "A Human Law",
                "A Human assumption",
                "A useless Law"

        };

        String[] physicsTest7 = {
                "What does 'Mass' mean ?",
                "3",
                "Light",
                "Energy",
                "Weigh",
                "Velocity"

        };

        String[] physicsTest8 = {
                "Why the sky is blue ?'",
                "4",
                "Universal's color",
                "Human eye's problem",
                "Intrinsic Color",
                "Ocean reflected Color"

        };

        String[] physicsTest9 = {
                "What the different between particles and wave ?",
                "1",
                "no difference",
                "wave doesn't have particle",
                "particles doesn't have wave",
                "particles can not go through the wall"

        };

        String[][] physicsSet = {physicsTest1,physicsTest2,physicsTest3,physicsTest4,physicsTest5,physicsTest6,physicsTest7,physicsTest8,physicsTest9};

        testSet.put(R.id.physics,physicsSet);


        String[] marvelTest1 = {
                "Who doesn't in the marvel novel?",
                "2",
                "Spider Man",
                "TA",
                "Iron Man",
                "America Captain"

        };

        String[] marvelTest2 = {
                "What's the app's author name ?",
                "4",
                "Lee",
                "Ted",
                "David",
                "Wen"

        };

        String[] marvelTest3 = {
                "What's the major of the app's author ?",
                "1",
                "Electrical Engineering",
                "Computer Science",
                "Informatics",
                "Art"

        };

        String[] marvelTest4 = {
                "Where does the author live ?",
                "2",
                "Marvel",
                "Seattle",
                "Sun",
                "White House"

        };

        String[] marvelTest5 = {
                "What is the hottest job in US ?",
                "3",
                "Hero",
                "Saving the world",
                "Software Engineer",
                "Replace a nuclear heart"

        };

        String[] marvelTest6 = {
                "Who is the character of Iron Man ?",
                "2",
                "Bad",
                "Rich",
                "Dirty",
                "Fall down to the earth"

        };

        String[] marvelTest7 = {
                "What the career is Iron Man ?",
                "4",
                "Worker",
                "Teacher",
                "Poor Guy",
                "Rich man"

        };

        String[] marvelTest8 = {
                "Where can we find them ?",
                "1",
                "Comic Book",
                "United State",
                "Mar",
                "Sun"

        };

        String[] marvelTest9 = {
                "Which city does Iron Man live ?",
                "4",
                "China",
                "Africa",
                "Engliand",
                "Not above"

        };

        String[][] marvelSet = {marvelTest1,marvelTest2,marvelTest3,marvelTest4,marvelTest5,marvelTest6,marvelTest7,marvelTest8,marvelTest9};

        testSet.put( R.id.marvel,marvelSet);



        String[] circuitTest1 = {
                "What does circuit mean ?",
                "4",
                "Loop doesn't matter",
                "Drive equipment to work",
                "A serial connect components",
                "A close loop"

        };

        String[] circuitTest2 = {
                "What does P present for in the circuit ?",
                "1",
                "Power",
                "Pressure",
                "Potential Energy",
                "Push out"

        };

        String[] circuitTest3 = {
                "What does I present for int the circuit ?",
                "2",
                "Image",
                "Current",
                "Illumina",
                "Correct Rate"

        };

        String[] circuitTest4 = {

                "What does V present for in the circuit?",
                "3",
                "Verilog",
                "Vote",
                "Voltage",
                "Click"
        };

        String[] circuitTest5 = {

                "What is the V = IR ?",
                "1",
                "Ohm's law",
                "Relative Theory",
                "Damping Theory",
                "Quautum Theory"
        };

        String[] circuitTest6 = {
                "What is the capacitor ?",
                "2",
                "One plate",
                "Can store energy",
                "No voltage across",
                "Not all the above"
        };

        String[] circuitTest7 = {
                "What is the resistor ?",
                "3",
                "Doesn't matter of energy",
                "Causing voltage increase",
                "Causing voltage drop",
                "Useless"
        };

        String[] circuitTest8 = {
                "What is the current ?",
                "3",
                "A flow of people",
                "A flow of photons",
                "A flow of electrons",
                "A flow of bacterias"
        };

        String[] circuitTest9 = {
                "What is the voltage ?",
                "1",
                "Drive current through in",
                "Energy",
                "Power Equipment",
                "All above are right"

        };

        String[][] circuitSet = {circuitTest1,circuitTest2,circuitTest3,circuitTest4,circuitTest5,circuitTest6,circuitTest7,circuitTest8,circuitTest9};

        testSet.put(R.id.circuit,circuitSet);








        return testSet;
    }




}
