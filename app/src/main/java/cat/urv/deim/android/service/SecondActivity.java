package cat.urv.deim.android.service;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by HP on 04/10/2017.
 */

public class SecondActivity extends AppCompatActivity{

    private Button send_button;
    private static TextView text;
    protected static final String TAG = SecondActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        text = (TextView)findViewById(R.id.textView3);
    }

    public static class NumberBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
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
    }




}
