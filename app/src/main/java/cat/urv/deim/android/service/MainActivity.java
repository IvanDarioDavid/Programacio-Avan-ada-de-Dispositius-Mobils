package cat.urv.deim.android.service;

import android.app.Activity;
import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button send_button1;
    private Button send_button2;
    private ProgressBar pbarProgreso;

    Intent intent_send_service;

    //public int num = Integer.parseInt(String.valueOf((EditText)findViewById(R.id.info_text)));



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.send_button1 = (Button)findViewById(R.id.button1);
        this.send_button2 = (Button)findViewById(R.id.button2);
        this.send_button1.setClickable(true);
        this.send_button2.setClickable(true);

        //Boton SEND
        send_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_send_service = new Intent();
                intent_send_service.setClass(getApplicationContext(),SecondActivity.class);
                intent_send_service.putExtra("InitialValue",((EditText)findViewById(R.id.info_text)).getText().toString());
                startActivity(intent_send_service);
            }
        });

        //Boton SEND SERVICE
        send_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_send_service = new Intent(MainActivity.this, MyIntentService.class);
                intent_send_service.putExtra(  "operacion",( (EditText)findViewById(R.id.info_text) ).getText().toString()  );
                startService(intent_send_service);
                Intent secondActivityIntent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(secondActivityIntent);
            }
        });
    }


    //BroadcastReceiver: permite ver el progreso de la aplicacion y la terminacio
    //                     captura los mensajes broadcast que el servicio envia durante su ejecucion


}
