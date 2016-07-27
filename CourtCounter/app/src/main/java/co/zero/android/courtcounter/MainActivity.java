package co.zero.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int scoreTeamA = 0;
    private int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addOnePointToA(View view){
        scoreTeamA++;
        showScoresA();
    }

    public void addTwoPointsToA(View view){
        scoreTeamA += 2;
        showScoresA();
    }

    public void addThreePointsToA(View view){
        scoreTeamA += 3;
        showScoresA();
    }

    public void addOnePointToB(View view){
        scoreTeamB++;
        showScoresB();
    }

    public void addTwoPointsToB(View view){
        scoreTeamB += 2;
        showScoresB();
    }

    public void addThreePointsToB(View view){
        scoreTeamB += 3;
        showScoresB();
    }

    public void reset(View view){
        scoreTeamA = 0;
        scoreTeamB = 0;
        showScoresA();
        showScoresB();
    }

    private void showScoresA(){
        TextView scoreA = (TextView) findViewById(R.id.text_score_A);
        scoreA.setText(Integer.toString(scoreTeamA));
    }

    private void showScoresB(){
        TextView scoreB = (TextView) findViewById(R.id.text_score_B);
        scoreB.setText(Integer.toString(scoreTeamB));
    }
}
