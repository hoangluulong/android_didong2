package nhom5.fit.tdc.edu.multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import nhom5.fit.tdc.edu.multimedia.widgets.Player;
import nhom5.fit.tdc.edu.multimedia.widgets.PlayerDelegation;

public class MainActivity extends AppCompatActivity implements PlayerDelegation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Player player = findViewById(R.id.playerID);
        player.setDelegate(this);


    }

    @Override
    public void play() {
        Toast.makeText(this, "play", Toast.LENGTH_LONG).show();
    }

    @Override
    public void pause() {
        Toast.makeText(this, "pause", Toast.LENGTH_LONG).show();
    }

    @Override
    public void prev() {
        Toast.makeText(this, "prev", Toast.LENGTH_LONG).show();
    }

    @Override
    public void stop() {
        Toast.makeText(this, "stop", Toast.LENGTH_LONG).show();
    }

    @Override
    public void next() {
        Toast.makeText(this, "next", Toast.LENGTH_LONG).show();
    }
}
