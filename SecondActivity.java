package id.ac.id.telkomuniversity.tass.praktikactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView tampilan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tampilan = findViewById(R.id.hasilnya);

        Bundle bundle = getIntent().getExtras();
        String muncul  = bundle.getString("input");

        tampilan.setText(String.valueOf(muncul));

    }
}