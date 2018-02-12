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
    private Button send_button3;

    Intent intent_send_service;

    public static final String KEY_START = "KEY_START";

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
        this.send_button3 = (Button)findViewById(R.id.button3);

        //Boton SEND
        send_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_send_service = new Intent();
                intent_send_service.setClass(getApplicationContext(), SecondActivity.class);
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
                intent_send_service.putExtra(  MyIntentService.KEY_ACTION, KEY_START);//clave:KEY_ACTION   valor:KEY_START. ID, ID_START
                startService(intent_send_service);
                Intent secondActivityIntent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(secondActivityIntent);
            }
        });

        //Boton SEND SERVICE ThirdActivity
        send_button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent_send_service = new Intent(MainActivity.this, MyIntentService.class);
                intent_send_service.putExtra(  "operacion",( (EditText)findViewById(R.id.info_text) ).getText().toString()  );
                intent_send_service.putExtra(  MyIntentService.KEY_ACTION, KEY_START);//clave:KEY_ACTION   valor:KEY_START
                startService(intent_send_service);// aqui empieza el servicio
                Intent thirdActivityIntent = new Intent(getApplicationContext(), ThirdActivity.class);
                startActivity(thirdActivityIntent);
            }
        });
    }

}
