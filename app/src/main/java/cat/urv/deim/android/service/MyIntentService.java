package cat.urv.deim.android.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

public class MyIntentService extends IntentService {

    public static final String KEY_ACTION = "KEY_ACTION";
    public static final String KEY_STOP = "KEY_STOP";

    public static final String TAG = "initService";
    private String resultado = "gamusino";
    public static final String KEY_RESULTADO = "resultado"; //Rannable: for objects that wish to execute code while they(threads) are active

    private Handler handler = new Handler(); //A Handler allows you to send and process Message and Runnable objects associated with a thread's MessageQueue. Each Handler instance is associated with a single thread and that thread's message queue.
    private Runnable runnable;               //The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread. The class must define a method of no arguments called run.
    private boolean stopService = false;

    //private boolean toggle = true;

    public MyIntentService() {
        super("MyIntentService");

        runnable = new Runnable() {
            @Override          //Runnable is implemented by class Thread.
            public void run() {//run(): to create a thread. Starting the thread causes the object's run method to be called in that separately executing thread
                Intent intent = new Intent("AppService"); //para que la secondActivity reciba los broadcast que tenga de nombre AppService
                intent.putExtra(KEY_RESULTADO, resultado); //ApService es el filtro

                /*if(toggle){
                    resultado = String.valueOf(Integer.parseInt(resultado)/100);
                }else{
                    resultado = String.valueOf(Integer.parseInt(resultado)*100);
                }
                toggle = !toggle;*/


                sendBroadcast(intent);//se envia a todas las aplicaciones del movil
                if (!stopService)
                    handler.postDelayed(runnable, 1000); //postDelayed: añade el hilo en una cola. Se ejecutaa a los 5 segundos
            }
        };
    }

    @Override//se ejecuta con el stratService
    protected void onHandleIntent(Intent intent) { //metodo que contiene la tarea a ejecutar en segundo plano
                                                    //recibe el intent de cada solicitud de inicio para que se pueda realizar el trabajo en segundo plano

        if (intent.getExtras().getString(MyIntentService.KEY_ACTION).equals(MainActivity.KEY_START)) {

            String numero = intent.getExtras().getString("operacion");
            Log.v(TAG, numero);
            int numInt = Integer.parseInt(numero);

            int multiplicacion = numInt * 100;
            Log.v(TAG, String.valueOf(multiplicacion));
            resultado = String.valueOf(multiplicacion);

            runnable.run();
            stopService=false;

        } else if (intent.getExtras().getString(MyIntentService.KEY_ACTION).equals(KEY_STOP)) { //esto pasa desde la secondActivity: Para parar el service
            stopService=true;
        }



    }

}
