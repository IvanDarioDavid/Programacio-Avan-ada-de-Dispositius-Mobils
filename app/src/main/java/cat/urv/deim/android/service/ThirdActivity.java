package cat.urv.deim.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {//onReceive(): se considera un proceso en segundo plano
                String resultado = intent.getExtras().getString(MyIntentService.KEY_RESULTADO);
                if (text != null) {
                    text.setText(resultado);
                    Intent intent_send_service = new Intent(context, MyIntentService.class);
                    intent_send_service.putExtra(  MyIntentService.KEY_ACTION, MyIntentService.KEY_STOP);
                    context.startService(intent_send_service);//ejecuta onHandleIntent
                }
                else
                    Log.e(TAG,"Encara no s'ha creat l'activity");
            }
        };

    private static TextView text;
    protected static final String TAG = SecondActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        text = (TextView)findViewById(R.id.textView3);

        registerReceiver(broadcastReceiver, new IntentFilter("AppService")); //El recibidor puede recibir broadcasts de diferentes Apps.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(broadcastReceiver);

    }
}

















//Definicion dinamica. La diferencia es que aqui se define cuando se va a usar mientras que en la estatica se usa siempre
//Definir el uso de un BroadcastReciver registrado
//No Se debe hacer el registro en el manifest dinamicamente, sino que cuando se arranque la activity tiene que hacerse el registro
//Cuando pare la activity tiene que desenregistrarse