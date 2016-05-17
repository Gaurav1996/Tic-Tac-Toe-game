package gauravagrawal.com.tictactoe;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button a1,b1,c1,a2,b2,c2,a3,b3,c3,Refresh;
    Button[] bArray;
    boolean turn=true;//X=true;O=false
    boolean isWinner = false;
    int turn_count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a1=(Button)findViewById(R.id.a1);
        b1=(Button)findViewById(R.id.b1);
        c1=(Button)findViewById(R.id.c1);
        a2=(Button)findViewById(R.id.a2);
        b2=(Button)findViewById(R.id.b2);
        c2=(Button)findViewById(R.id.c2);
        a3=(Button)findViewById(R.id.a3);
        b3=(Button)findViewById(R.id.b3);
        c3=(Button)findViewById(R.id.c3);
        Refresh=(Button)findViewById(R.id.refresh);
        bArray=new Button[] {a1,b1,c1,a2,b2,c2,a3,b3,c3};
        for(final Button b : bArray)
        {
            //Toast.makeText(getApplicationContext(),"X turn",Toast.LENGTH_LONG).show();
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isWinner)
                    {
                        if (turn_count % 2 != 0)
                            Toast.makeText(getApplicationContext(), "X turn", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(), "O turn", Toast.LENGTH_SHORT).show();
                    }
                    if(turn)
                    {
                        b.setText("X");
                    }
                    else
                    {
                        b.setText("O");
                    }
                    turn_count++;
                    b.setBackgroundColor(Color.LTGRAY);
                    b.setClickable(false);
                    turn = !turn;
                    check_winner();
                }
            });
        }
        Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Confirm Reset...");
                alertDialog.setMessage("Are you sure you want to reset everything ?");
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        turn=true;
                        turn_count=0;
                        enable_disable_All_Buttons(true);

                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertDialog.show();
            }
        });
    }
    private void check_winner()
    {

        //Horizontal Check
        if(a1.getText() == a2.getText() && a2.getText() == a3.getText() && !a1.isClickable())
            isWinner=true;
        else if(b1.getText() == b2.getText() && b2.getText() == b3.getText() && !b1.isClickable())
            isWinner=true;
        else if(c1.getText() == c2.getText() && c2.getText() == c3.getText() && !c1.isClickable())
            isWinner=true;
        //Vertical Check
        else if(a1.getText() == b1.getText() && b1.getText() == c1.getText() && !a1.isClickable())
            isWinner=true;
        else if(a2.getText() == b2.getText() && b2.getText() == c2.getText() && !b2.isClickable())
            isWinner=true;
        else if(a3.getText() == b3.getText() && b3.getText() == c3.getText() && !c3.isClickable())
            isWinner=true;
        //Diagonal Check
        else if(a1.getText() == b2.getText() && b2.getText() == c3.getText() && !a1.isClickable())
            isWinner=true;
        else if(a3.getText() == b2.getText() && b2.getText() == c1.getText() && !b2.isClickable())
            isWinner=true;

        if(isWinner)
        {
            if(!turn)
                Toast.makeText(getApplicationContext(),"X wins",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(),"O wins",Toast.LENGTH_LONG).show();
            enable_disable_All_Buttons(false);
        }
        else if(turn_count==9)
        {
            Toast.makeText(getApplicationContext(),"Uhouh,It's a Draw",Toast.LENGTH_LONG).show();
        }
    }
    private void enable_disable_All_Buttons(Boolean enable)
    {
        //for disable false
        for(Button b:bArray)
        {
            b.setClickable(enable);
            if(enable)
            {
                b.setBackgroundColor(Color.parseColor("#00bfff"));
                b.setText("");
            }
            else
                b.setBackgroundColor(Color.LTGRAY);
        }
    }
}