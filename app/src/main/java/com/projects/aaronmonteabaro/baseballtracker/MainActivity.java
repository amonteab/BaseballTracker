package com.projects.aaronmonteabaro.baseballtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{
    public int homeRuns = 0;
    public int homeOuts = 0;
    public int homeStrikes = 0;
    public int homeBalls = 0;

    public TextView homeRunsText;
    public TextView homeOutsText;
    public TextView homeStrikesText;
    public TextView homeBallsText;

    public int awayRuns = 0;
    public int awayOuts = 0;
    public int awayStrikes = 0;
    public int awayBalls = 0;

    public TextView awayRunsText;
    public TextView awayOutsText;
    public TextView awayStrikesText;
    public TextView awayBallsText;

    public boolean isHomeAtBat = true;
    public boolean counterStarted = false;

    public RelativeLayout homeLayout;
    public RelativeLayout awayLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeLayout = (RelativeLayout) findViewById(R.id.homeLayout);
        awayLayout = (RelativeLayout) findViewById(R.id.awayLayout);

        homeRunsText = (TextView)findViewById(R.id.homeRuns);
        homeOutsText = (TextView)findViewById(R.id.homeOuts);
        homeStrikesText = (TextView)findViewById(R.id.homeStrikes);
        homeBallsText = (TextView)findViewById(R.id.homeBalls);

        awayRunsText = (TextView)findViewById(R.id.awayRuns);
        awayOutsText = (TextView)findViewById(R.id.awayOuts);
        awayStrikesText = (TextView)findViewById(R.id.awayStrikes);
        awayBallsText = (TextView)findViewById(R.id.awayBalls);

        displayStats();
    }

    public void startCount(View view)
    {
        if(counterStarted)
        {
            return;
        }
        isHomeAtBat = true;
        resetCount(view);
        awayLayout.setAlpha(.25f);
        counterStarted = true;
    }

    public void resetCount(View view)
    {
        homeRuns = 0;
        homeOuts = 0;
        homeStrikes = 0;
        homeBalls = 0;

        awayRuns = 0;
        awayOuts = 0;
        awayStrikes = 0;
        awayBalls = 0;

        displayStats();
        homeLayout.setAlpha(1f);
        awayLayout.setAlpha(1f);
        counterStarted = false;
    }

    public void addRun(View view)
    {
        if(!counterStarted)
        {
            return;
        }
        if(isHomeAtBat)
        {
            homeRuns += 1;
        }
        else
        {
            awayRuns += 1;
        }
        Toast.makeText(getApplicationContext(), "And it's good!", Toast.LENGTH_SHORT).show();
        displayStats();
    }

    public void addOut(View view)
    {
        if(!counterStarted)
        {
            return;
        }
        if(isHomeAtBat)
        {
            homeOuts += 1;
            homeStrikes = 0;
            homeBalls = 0;
            if(homeOuts >= 3)
            {
                homeOuts = 0;

                isHomeAtBat = false;
                homeLayout.setAlpha(.25f);
                awayLayout.setAlpha(1f);
            }
        }
        else
        {
            awayOuts += 1;
            awayStrikes = 0;
            awayBalls = 0;
            if(awayOuts >= 3)
            {
                awayOuts = 0;
                isHomeAtBat = true;
                homeLayout.setAlpha(1f);
                awayLayout.setAlpha(.25f);
            }
        }
        displayStats();
        Toast.makeText(getApplicationContext(), "He's Out!", Toast.LENGTH_SHORT).show();
    }

    public void addStrike(View view)
    {
        if(!counterStarted)
        {
            return;
        }
        if(isHomeAtBat)
        {
            homeStrikes += 1;
            if(homeStrikes >= 3)
            {
                homeBalls = 0;
                homeStrikes = 0;
                addOut(view);
            }
        }
        else
        {
            awayStrikes += 1;
            if(awayStrikes >= 3)
            {
                awayBalls = 0;
                awayStrikes = 0;
                addOut(view);
            }
        }
        displayStats();
    }

    public void addBall(View view)
    {
        if(!counterStarted)
        {
            return;
        }
        if(isHomeAtBat)
        {
            homeBalls += 1;
            if(homeBalls >= 4)
            {
                homeStrikes = 0;
                homeBalls = 0;
                Toast.makeText(getApplicationContext(), "Batter Walked", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            awayBalls += 1;
            if(awayBalls >= 4)
            {
                awayStrikes = 0;
                awayBalls = 0;
                Toast.makeText(getApplicationContext(), "Batter Walked", Toast.LENGTH_SHORT).show();
            }
        }
        displayStats();
    }

    private void displayStats()
    {
        homeRunsText.setText("RUNS: " + homeRuns);
        homeOutsText.setText("OUTS: " + homeOuts);
        homeStrikesText.setText("S: " + homeStrikes);
        homeBallsText.setText("B: " + homeBalls);

        awayRunsText.setText("RUNS: " + awayRuns);
        awayOutsText.setText("OUTS: " + awayOuts);
        awayStrikesText.setText("S: " + awayStrikes);
        awayBallsText.setText("B: " + awayBalls);
    }
}
