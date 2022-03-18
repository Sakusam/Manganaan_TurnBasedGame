package mcm.edu.ph.manganaan_turnbasedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView leafy, ghosts, maindps, enemy, todo;
    Button changeturn;
    ImageButton leafone, leaftwo , leafthree;
    ProgressBar lifebarone, lifebartwo;

    //Hero Stats
    String Hero = "Leafy";
    int leafyHP = 2000;
    int leafysmalldmg = 100;
    int leafybigdmg = 150;
    int leafyHPpercent;


    //Monster Stats
    String Enemy  = "Ghosts";
    int ghostsHP = 2500;
    int ghostssmalldmg = 5;
    int ghostsbigdmg = 20;
    int ghostsHppercent;


    //Game Turn
    int turnNumber= 1;
    Boolean disablestatus= false;
    int buttoncounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextViews

        leafy = findViewById(R.id.leafy);
        ghosts = findViewById(R.id.ghosts);
        maindps = findViewById(R.id.maindps);
        enemy = findViewById(R.id.enemy);
        todo = findViewById(R.id.todo);

        //ProgressBars

        lifebarone = findViewById(R.id.lifebarone);
        lifebartwo = findViewById(R.id.lifebartwo);

        //ImageButton

        leafone = findViewById(R.id. leafone);
        leaftwo = findViewById(R.id.leaftwo);
        leafthree = findViewById(R.id.leafthree);

        //Button
        changeturn = findViewById(R.id.changeturn);

        leafone.setOnClickListener(this);
        leaftwo.setOnClickListener(this);
        leafthree.setOnClickListener(this);

        changeturn = findViewById(R.id.changeturn);
        maindps = findViewById(R.id.maindps);
        enemy = findViewById(R.id.enemy);


        leafy.setText(Hero);
        leafy.setText(String.valueOf(leafyHP));

        ghosts.setText(Enemy);
        ghosts.setText(String.valueOf(ghostsHP));

        maindps.setText(String.valueOf(leafysmalldmg)+ " ~ "+ String.valueOf(leafybigdmg));
        enemy.setText(String.valueOf(ghostssmalldmg)+ " ~ "+ String.valueOf(ghostsbigdmg));

        //Skills
        leafone = findViewById(R.id.leafone);
        leaftwo = findViewById(R.id.leaftwo);
        leafthree = findViewById(R.id.leafthree);


        changeturn.setOnClickListener(this);
        leafone.setOnClickListener(this);
        leaftwo.setOnClickListener(this);
        leafthree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Random randomizer = new Random();
        int maindps = randomizer.nextInt(leafybigdmg - leafysmalldmg) + leafysmalldmg ;
        int monsdps = randomizer.nextInt(ghostsbigdmg - ghostssmalldmg) + ghostssmalldmg ;

        if(turnNumber % 2 != 1){//if it is enemy's turn, disable button
            leafone.setEnabled(false);
        }
        else if(turnNumber%2 == 1){
            leafone.setEnabled(true);
        }

        if(buttoncounter>0){
            leafone.setEnabled(false);
            // buttoncounter--;
        }
        else if(buttoncounter==0){
            leafone.setEnabled(true);
        }

        if (turnNumber % 2 == 1) {
            leaftwo.setEnabled(false);
        } else if (turnNumber % 2 != 1) {
            leaftwo.setEnabled(true);
        }
        if (buttoncounter > 0) {
            leaftwo.setEnabled(false);
            buttoncounter--;
        } else if (buttoncounter == 0) {
            leafthree.setEnabled(true);
        }
        if (turnNumber % 2 == 1) {
            leafthree.setEnabled(false);
        } else if (turnNumber % 2 != 1) {
            leafthree.setEnabled(true);
        }
        if (buttoncounter > 0) {
            leafthree.setEnabled(false);
            buttoncounter--;
        } else if (buttoncounter == 0) {
            leafthree.setEnabled(true);
        }

        //Leafy lifebar
        if ((int) leafyHPpercent > 80 && (int) leafyHPpercent <= 115) {
            lifebarone.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
        } else if ((int) leafyHPpercent >= 60 && (int) leafyHPpercent <= 72) {
            lifebarone.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
        } else if ((int) leafyHPpercent >= 32 && (int) leafyHPpercent <= 40) {
            lifebarone.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.yellow)));
        } else if ((int) leafyHPpercent >= 15 && (int) leafyHPpercent <= 20) {
            lifebarone.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange)));
        } else {
            lifebarone.setProgressTintList(ColorStateList.valueOf((getResources().getColor(R.color.red))));
        }

        //monster hp
        if ((int) ghostsHppercent > 80 && (int) ghostsHppercent <= 100) {
            lifebartwo.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
        } else if ((int) ghostsHppercent >= 50 && (int) ghostsHppercent <= 75) {
            lifebartwo.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
        } else if ((int) ghostsHppercent >= 25 && (int) ghostsHppercent <= 50) {
            lifebartwo.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.yellow)));
        } else if ((int) ghostsHppercent >= 10 && (int) ghostsHppercent <= 25) {
            lifebartwo.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange)));
        } else {
            lifebartwo.setProgressTintList(ColorStateList.valueOf((getResources().getColor(R.color.red))));
        }

        switch(v.getId()) {
            case R.id.leafone:
                        ghostsHP = ghostsHP - 250;
                        ghostsHppercent = ghostsHP * 80 / 2500;
                        lifebartwo.setProgress((int) ghostsHppercent, true);
                        turnNumber++;
                        leafy.setText(String.valueOf(leafyHP));
                        changeturn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                        todo.setText(" You " + String.valueOf(Hero) + " dealt damage" + String.valueOf(250) + " to the ghosts!");
                        disablestatus = false;

                        if (ghostsHP < 0) {
                            todo.setText("You " + String.valueOf(Hero) + " dealt damage" + String.valueOf(maindps) + " to the monster. You have protected your land!.");
                            leafyHP = 2000;
                            ghostsHP = 2500;
                            turnNumber = 1;
                            changeturn.setText("Reset Game");
                        }
            case R.id.leaftwo:
                ghostsHP = ghostsHP - 250;
                ghostsHppercent = ghostsHP * 80 / 2500;
                lifebartwo.setProgress((int) ghostsHppercent, true);
                turnNumber++;
                leafy.setText(String.valueOf(leafyHP));
                changeturn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                todo.setText(" You " + String.valueOf(Hero) + " dealt damage" + String.valueOf(250) + " to the ghosts!");
                disablestatus = false;

                if (ghostsHP < 0) {
                    todo.setText("You " + String.valueOf(Hero) + " dealt damage" + String.valueOf(maindps) + " to the monster. You have protected your land!.");
                    leafyHP = 2000;
                    ghostsHP = 2500;
                    turnNumber = 1;
                    changeturn.setText("Reset Game");
                }

            case R.id.leafthree:
                ghostsHP = ghostsHP - 250;
                ghostsHppercent = ghostsHP * 80 / 2500;
                lifebartwo.setProgress((int) ghostsHppercent, true);
                turnNumber++;
                leafy.setText(String.valueOf(leafyHP));
                changeturn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                todo.setText(" You " + String.valueOf(Hero) + " dealt damage" + String.valueOf(250) + " to the ghosts!");
                disablestatus = false;

                if (ghostsHP < 0) {
                    todo.setText("You " + String.valueOf(Hero) + " dealt damage" + String.valueOf(maindps) + " to the monster. You have protected your land!.");
                    leafyHP = 2000;
                    ghostsHP = 2500;
                    turnNumber = 1;
                    changeturn.setText("Reset Game");
                }

                break;

                
                    case R.id.changeturn:
                        //
                        if (turnNumber % 2 == 1) { //add
                            ghostsHP = ghostsHP - maindps;
                            ghostsHppercent = ghostsHP * 80 / 2500;
                            lifebartwo.setProgress((int) ghostsHppercent, true);
                            turnNumber++;
                            ghosts.setText(String.valueOf(ghostsHP));
                            changeturn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                            todo.setText(" You " + String.valueOf(Hero) + " dealt damage" + String.valueOf(maindps) + " to the ghosts.");

                            if (ghostsHP < 0) {
                                todo.setText("You " + String.valueOf(Hero) + " dealt damage" + String.valueOf(maindps) + " to the ghosts. You have protected your Home!.");
                                leafyHP = 2000;
                                ghostsHP = 2500;
                                turnNumber = 1;
                                changeturn.setText("Reset Game");


                            }
                            buttoncounter--;


                        } else if (turnNumber % 2 != 1) {
                            if (disablestatus == false) {
                                    disablestatus = false;
                                }
                            } else {
                                leafyHP = leafyHP - monsdps;
                                leafyHPpercent = leafyHP * 60 / 2000;
                                lifebarone.setProgress((int) leafyHPpercent, true);
                                turnNumber++;
                                leafy.setText(String.valueOf(leafyHP));
                                changeturn.setText("Next Turn (" + String.valueOf(turnNumber) + ")");

                                todo.setText("Ghosts " + String.valueOf(Enemy) + " gave damage " + String.valueOf(monsdps) + " to you !.");
                                if (leafyHP < 0) {

                                    todo.setText("Ghosts " + String.valueOf(Enemy) + " gave damage " + String.valueOf(monsdps) + "your home is ruined!. Game Over.");
                                    leafyHP = 2000;
                                    ghostsHP = 2500;
                                    turnNumber = 1;
                                    changeturn.setText("Reset Game");
                                    {

                                    }
                                    buttoncounter--;

                                }
                                break;
                            }
                        }
                    }
                    // buttoncounter--;
                }

