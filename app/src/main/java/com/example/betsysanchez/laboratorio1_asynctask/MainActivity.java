package com.example.betsysanchez.laboratorio1_asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button calcular;
    EditText num;
    TextView resultado;
    int cuantos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcular=findViewById(R.id.btnCalcular);
        num=findViewById(R.id.numero);
        resultado=findViewById(R.id.tvResultado);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cuantos=Integer.parseInt(num.getText().toString());
                resultado.setText("Resultado"+"\n"+"0");
                AsyncTarea asyncTarea=new AsyncTarea();
                asyncTarea.execute(cuantos);
            }
        });
    }
    private void UnSegundo() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private class  AsyncTarea extends AsyncTask<Integer, String,Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Boolean doInBackground(Integer... params) {
            int p=params[0];
            int f0=0;
            int f1=1;
            int f_2=0;
            int f_1=0;
            int f_actual;
            for (int i=2; i<=p; i++){
                UnSegundo();
                if(i==2){
                    f_actual=f0+f1;
                }else{
                    f_actual=f_2+f_1;
                }
                publishProgress(   f_actual+"");
                f_2=f_1;
                f_1=f_actual;
                if (isCancelled()){
                    break;
                }
            }
            return true;
        }
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            //Actualizar la barra de progreso
            //progressBar1.setProgress(values[0].intValue());
            String cadActual=resultado.getText().toString();
            resultado.setText(cadActual+"\n"+values[0]);
        }
        @Override
        protected void onPostExecute(Boolean aVoid) {
            //super.onPostExecute(aVoid);
            if (aVoid){
                Toast.makeText(getApplicationContext(),"Tarea finalizada AsyncTask 1",Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(getApplicationContext(),"Tarea NO finalizada AsyncTask 1",Toast.LENGTH_SHORT).show();
        }
    }
}
