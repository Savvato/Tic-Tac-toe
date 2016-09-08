package savvato.tic_tac_toe;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import savvato.tic_tac_toe.game.Field;
import savvato.tic_tac_toe.game.Game;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private static Context context;
    private static ImageView[][] imageFields = new ImageView[3][3];
    private static Game game;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        MainActivity.context = getApplicationContext();
        this.initializeFields();
        this.initializeFieldResources();
        game = new Game(imageFields);
        startButton = (Button) findViewById(R.id.startButton);
        if (startButton != null) {
            startButton.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startButton:
                game = new Game(imageFields);
                for (int row = 0; row < 3; row++) {
                    for (int column = 0; column < 3; column++) {
                        imageFields[row][column].setClickable(true);
                    }
                }
                break;
        }
    }

    public static void blockFields() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                imageFields[row][column].setClickable(false);
            }
        }
    }

    public static void makeToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    private void initializeFields() {
        imageFields[0][0] = (ImageView) findViewById(R.id.image11);
        imageFields[0][1] = (ImageView) findViewById(R.id.image12);
        imageFields[0][2] = (ImageView) findViewById(R.id.image13);
        imageFields[1][0] = (ImageView) findViewById(R.id.image21);
        imageFields[1][1] = (ImageView) findViewById(R.id.image22);
        imageFields[1][2] = (ImageView) findViewById(R.id.image23);
        imageFields[2][0] = (ImageView) findViewById(R.id.image31);
        imageFields[2][1] = (ImageView) findViewById(R.id.image32);
        imageFields[2][2] = (ImageView) findViewById(R.id.image33);
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                ImageView image = imageFields[row][column];
                final int finalColumn = column;
                final int finalRow = row;
                image.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        MainActivity.game.doStep(finalRow, finalColumn);
                        Log.d("Click", String.format("Field clicked! Coordinates: row = %d, column = %d", finalRow, finalColumn));
                    }
                });
            }
        }
    }

    private void initializeFieldResources() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Field.initializeResources(
                    getDrawable(R.drawable.none),
                    getDrawable(R.drawable.cross),
                    getDrawable(R.drawable.toe)
            );
        }
        else {
            Field.initializeResources(
                    getResources().getDrawable(R.drawable.none),
                    getResources().getDrawable(R.drawable.cross),
                    getResources().getDrawable(R.drawable.toe)
            );
        }
    }
}
