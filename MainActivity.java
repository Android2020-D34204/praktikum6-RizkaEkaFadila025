package id.ac.id.telkomuniversity.tass.praktikactivity;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView helloword;
    Button buttonpindah;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        helloword = findViewById(R.id.textView);
        buttonpindah = findViewById(R.id.button);
        editText = findViewById(R.id.editTextTextPersonName);


        buttonpindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input  = editText.getText().toString();
                if (TextUtils.isEmpty(input)) {
                    Toast.makeText(getApplicationContext(), "Maaf inputan tidak boleh kosong",Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("input",input);
                    alert(intent);
                }
            }
        });
    }



    public void alert(final Intent intent){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Yakin ingin pindah?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(intent);
                send();
            }
        });
        builder.setNegativeButton("Tidak", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void send(){
        int NOTIFICATION_ID = 25;
        String CHANNEL_ID   = "Rizka_Eka_Fadila";

        Intent intent       = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        //Cek versi android dan registrasi channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name           = CHANNEL_ID;
            String description          = CHANNEL_ID;
            int importance              = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Membuat notifikasi
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("Berhasil pindah ke activity kedua");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(NOTIFICATION_ID, builder.build());


    }
}