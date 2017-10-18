package cat.urv.deim.android.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

public class MyIntentService extends IntentService {
    //public static final String ACTION_PROGRESO = "cat.urv.deim.android.service.action.PROGRESO";
    //public static final String ACTION_FIN = "cat.urv.deim.android.service.action.FIN";

    public static final String TAG = "initService";
    private String resultado = "";
    private Handler handler = new Handler();
    private Runnable runnable;


    public MyIntentService() {
        super("MyIntentService");

        runnable = new Runnable() { //Rannable: for objects that wish to execute code while they(threads) are active
            @Override               //Runnable is implemented by class Thread.
            public void run() {//run(): to create a thread. Starting the thread causes the object's run method to be called in that separately executing thread
                Intent intent = new Intent("AppService"); //para que la secondActivity reciba los broadcast que tenga de nombre AppService
                intent.putExtra("resutado", resultado);
                sendBroadcast(intent);
                handler.postDelayed(runnable, 3000);
            }
        };
    }

    @Override
    protected void onHandleIntent(Intent intent) { //metodo que contiene la tarea a ejecutar en segundo plano
                                                    //recibe el intent de cada solicitud de inicio para que se pueda realizar el trabajo en segundo plano

        String numero = intent.getExtras().getString("operacion");
        Log.v(TAG, numero);
        int numInt = Integer.parseInt(numero);

        int multiplicacion = numInt*10;
        Log.v(TAG, String.valueOf(multiplicacion));
        resultado = String.valueOf(multiplicacion);

        runnable.run();



        //el parametro Intentse puede utilizar para pasar al servicio los datos de entrada
        /*tareaLarga();

        Intent bcIntent = new Intent();
        bcIntent.putExtra("progreso", n*10);
        sendBroadcast(bcIntent);*/

        /*int iter = intent.getIntExtra("iteraciones", 0);

        for(int i=1; i<=iter; i++) {
            tareaLarga();

            //Comunicamos el progreso
            Intent bcIntent = new Intent();
            bcIntent.setAction(ACTION_PROGRESO);
            bcIntent.putExtra("progreso", i*10); //incluir los datos a comunicar
            sendBroadcast(bcIntent); // comunicar el progreso
        }

        Intent bcIntent = new Intent();
        bcIntent.setAction(ACTION_FIN); //se le asocia nombre de accion
        sendBroadcast(bcIntent); // se envia el mensaje

    }

    private void tareaLarga() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {}
*/

    }

}
