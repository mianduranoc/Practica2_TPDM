package mx.edu.ittepic.tpdm_u4_practica2_delarosaguerrerogabriela;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Icon;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Lienzo extends View {

   String perdiste;
   String ganaste;
   List <Icono> iconos;
   Icono moscon;
   int puntaje;
   int segundos;
   boolean moscas,mostrarMoscon;

    public Lienzo(Context context) {
        super(context);
        iconos=new ArrayList<>();
        segundos=60;
        puntaje=0;
        moscas=true;
        perdiste="";
        ganaste="";
    }
    protected  void  onDraw(Canvas c) {
        Paint p = new Paint();
        //el objeto de convas ejecuta el dibujo
        //El objeto Paint  indica sus caracteristicas como tamaño color , etc
        p.setColor(Color.BLUE);
        p.setTextSize(40f);
        c.drawText("Puntaje: "+puntaje, 50, 50, p);
        c.drawText("Tiempo restante: "+segundos, 300, 50, p);
        if (moscas){
            mosquitas(c,p);
        }
        else if (mostrarMoscon){
            if (moscon!=null){
                moscon.pintar(c,p);
            }
        }
        p.setFakeBoldText(true);
        p.setColor(Color.RED);
        c.drawText(perdiste,(getWidth()/2)-300,getHeight()/2,p);
        p.setColor(Color.GREEN);
        c.drawText(ganaste,(getWidth()/2)-300,getHeight()/2,p);
    }
    public boolean onTouchEvent(MotionEvent me){
        //El evento on touchEvent permite detectar los toques de una o mas dedos que hacen en el area de dbujo
       int accion= me.getAction();
       int posx=(int)me.getX();
       int posy=(int)me.getY();

       switch (accion) {
           case MotionEvent.ACTION_DOWN:
               if (moscas){
                   for (int i = 0; i < iconos.size(); i++) {
                       if (iconos.get(i).estaEnArea(posx, posy)) {
                           iconos.remove(i);
                           incrementarPuntaje();
                       }
                   }
               }
               else if (mostrarMoscon){
                   if (moscon.estaEnArea(posx,posy)){
                       moscon.setX((int)(Math.random()*getWidth()));
                       moscon.setY((int)(Math.random()*getHeight())+50);
                       incrementarPuntaje();
                   }
               }

               break;
       }
       invalidate();
       return true;
    }
    public void add(Icono icono){
        iconos.add(icono);
        invalidate();
    }
    public void decrementarSegundos(){
        segundos--;
        invalidate();
    }
    public void incrementarPuntaje(){
        puntaje++;
        invalidate();
    }
    public int getPuntaje(){
        return puntaje;
    }
    public int getSegundos(){
        return segundos;
    }
    public void remove(){
        iconos.removeAll(iconos);
        invalidate();
    }
    public void setPerdiste(){
        perdiste="Lo siento, has perdido la partida :c";
        invalidate();
    }
    public void setMoscas(boolean mosquitas){
        moscas=mosquitas;
    }
    public void mosquitas(Canvas c, Paint p){
        for (int i=0;i<iconos.size();i++){
            iconos.get(i).pintar(c,p);
        }
    }
    public void reiniciar(){
        puntaje=0;
        segundos=10;
        invalidate();
    }
    public void setMoscon(Icono mosca){
        moscon = mosca;
        setMostrarMoscon(true);
        invalidate();
    }

    public void removeMoscon() {
        moscon=null;
    }
    public void setGanaste(){
        ganaste="Felicidades!! Has ganado el juego :D";
    }

    public void setMostrarMoscon(boolean mostrarMoscon) {
        this.mostrarMoscon = mostrarMoscon;
    }
}
