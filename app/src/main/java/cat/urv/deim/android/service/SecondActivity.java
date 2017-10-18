package cat.urv.deim.android.service;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by HP on 04/10/2017.
 */

public class SecondActivity extends AppCompatActivity{

    private Button send_button;
    private TextView text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        text = (TextView)findViewById(R.id.textView3);
    }

    public class NumberBroadcastReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            String resultado = intent.getStringExtra("resultado");
            text.setText(resultado);
        }
    }




}
