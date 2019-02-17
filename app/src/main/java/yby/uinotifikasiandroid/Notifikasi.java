package yby.uinotifikasiandroid;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class Notifikasi {

    private Context context;
    private Class<?> classActivityTujuan;

    private String judul;
    private String teksKonten;
    private int idIconKecil;
    private int idIconBesar;

    private int prioritas; // diisi oleh konst dari class Notification
    private int tingkatKepentingan; // diisi oleh konst dari class NotificationManager

    private String idChannel;
    private String namaChannel;

    public Notifikasi(Context context) {
        this.context = context;

        this.judul = "Hello World!";
        this.teksKonten = "Smartphone anda mengucapkan Hello World!";
        this.idIconKecil = R.drawable.ic_launcher_foreground;
        this.idIconBesar = R.drawable.ic_launcher_background;
        this.prioritas = Notification.PRIORITY_MAX;
        this.tingkatKepentingan = NotificationManager.IMPORTANCE_HIGH;
        this.classActivityTujuan = context.getClass();
        this.idChannel = "MYCHANNELID";
        this.namaChannel = "MyNotifChannel";
    }

    public Context getContext() {
        return context;
    }

    public Class<?> getClassActivityTujuan() {
        return classActivityTujuan;
    }

    public String getJudul() {
        return judul;
    }

    public String getTeksKonten() {
        return teksKonten;
    }

    public int getIdIconKecil() {
        return idIconKecil;
    }

    public int getIdIconBesar() {
        return idIconBesar;
    }

    public int getPrioritas() {
        return prioritas;
    }

    public String getIdChannel() {
        return idChannel;
    }

    public String getNamaChannel() {
        return namaChannel;
    }

    public int getTingkatKepentingan() {
        return tingkatKepentingan;
    }

    public Notifikasi setContext(Context context) {
        this.context = context;
        return this;
    }

    public Notifikasi setClassActivityTujuan(Class<?> classActivityTujuan) {
        this.classActivityTujuan = classActivityTujuan;
        return this;
    }

    public Notifikasi setJudul(String judul) {
        this.judul = judul;
        return this;
    }

    public Notifikasi setTeksKonten(String teksKonten) {
        this.teksKonten = teksKonten;
        return this;
    }

    public Notifikasi setIdIconKecil(int idIconKecil) {
        this.idIconKecil = idIconKecil;
        return this;
    }

    public Notifikasi setIdIconBesar(int idIconBesar) {
        this.idIconBesar = idIconBesar;
        return this;
    }

    public Notifikasi setPrioritas(int prioritas) {
        this.prioritas = prioritas;
        return this;
    }

    public Notifikasi setTingkatKepentingan(int tingkatKepentingan) {
        this.tingkatKepentingan = tingkatKepentingan;
        return this;
    }

    public Notifikasi setIdChannel(String idChannel) {
        this.idChannel = idChannel;
        return this;
    }

    public Notifikasi setNamaChannel(String namaChannel) {
        this.namaChannel = namaChannel;
        return this;
    }

    public void tampilkan() {
        Intent inten = new Intent(context, classActivityTujuan);
        PendingIntent pi = PendingIntent.getActivity(context, 0, inten, 0);

        NotificationCompat.Builder notifikasi =
                new NotificationCompat.Builder(context, idChannel);
        notifikasi.setContentTitle(judul);
        notifikasi.setContentText(teksKonten);
        notifikasi.setSmallIcon(idIconKecil);
        notifikasi.setDefaults(Notification.DEFAULT_ALL);
        notifikasi.setLargeIcon(
                BitmapFactory.decodeResource(context.getResources(), idIconBesar));
        notifikasi.setContentIntent(pi);
        notifikasi.setPriority(prioritas);

        NotificationManager notifMan =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        // Check if notification channel exists and if not create one
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    notifMan.getNotificationChannel(idChannel);

            if (notificationChannel == null) {
                notificationChannel = new NotificationChannel(
                        idChannel, namaChannel, tingkatKepentingan);

                notificationChannel.enableVibration(true);
                notifMan.createNotificationChannel(notificationChannel);
            }
        }

        notifMan.notify(1, notifikasi.build());
    }
}
