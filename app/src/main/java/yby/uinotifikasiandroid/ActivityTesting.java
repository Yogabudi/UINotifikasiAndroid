package yby.uinotifikasiandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityTesting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        Notifikasi notif = new Notifikasi(this);
        notif.setJudul("Selamat Siang!!!").setTeksKonten("hello world!!!!!!!!");
        notif.tampilkan();
    }
}
