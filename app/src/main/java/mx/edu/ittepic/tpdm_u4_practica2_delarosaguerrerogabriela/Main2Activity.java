package mx.edu.ittepic.tpdm_u4_practica2_delarosaguerrerogabriela;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {
    Lienzo lienzo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo=new Lienzo(this);
        setContentView(lienzo);
        Thread hilo=new Thread(new Runnable() {
            @Override
            public void run() {
                boolean bandera=true;
                while (bandera){
                    try {
                        Thread.sleep(1000);
                        Icono mosca=new Icono(lienzo,R.drawable.mosca,(int)(Math.random()*lienzo.getWidth()),(int)(Math.random()*lienzo.getHeight())+50);
                        lienzo.add(mosca);
                        lienzo.decrementarSegundos();
                        if (lienzo.getPuntaje()==30){
                            lienzo.remove();
                            bandera=false;
                            lienzo.setMoscas(false);
                            lienzo.reiniciar();
                            moscon();
                        }
                        if (lienzo.getSegundos()==0){
                            lienzo.setPerdiste();
                            bandera=false;
                            lienzo.setMoscas(false);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        hilo.start();
    }
    public void moscon(){
        Icono mosca=new Icono(lienzo,R.drawable.moscon,(int)(Math.random()*lienzo.getWidth()),(int)(Math.random()*lienzo.getHeight())+50);
        lienzo.setMoscon(mosca);
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean mosconJugando=true;
                while (mosconJugando){
                    try {
                        Thread.sleep(1000);
                        lienzo.decrementarSegundos();
                        if (lienzo.getPuntaje()>=5){
                            lienzo.removeMoscon();
                            lienzo.setGanaste();
                            lienzo.setMostrarMoscon(false);
                            lienzo.reiniciar();
                            mosconJugando=false;
                            //moscon();
                        }
                        if (lienzo.getSegundos()==0){
                            lienzo.setPerdiste();
                            lienzo.setMostrarMoscon(false);
                            mosconJugando=false;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
