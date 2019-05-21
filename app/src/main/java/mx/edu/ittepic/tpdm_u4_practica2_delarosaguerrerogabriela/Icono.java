package mx.edu.ittepic.tpdm_u4_practica2_delarosaguerrerogabriela;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Icono {
    Bitmap imagen;
    int x,y;
    public Icono(Lienzo pantalla,int imagen, int posx, int posy){
        this.imagen= BitmapFactory.decodeResource(pantalla.getResources(),imagen);
        x=posx;
        y=posy;

    }
    public void pintar(Canvas c, Paint p){
        x=((x+imagen.getWidth())>=c.getWidth())?x-imagen.getWidth():x;
        y=((y+imagen.getHeight())>=c.getHeight())?y-imagen.getHeight():y;
        c.drawBitmap(imagen,x,y,p);
    }
    public boolean estaEnArea(int xd, int yd){

        int x2= x+(imagen.getWidth());
        int y2= y+(imagen.getHeight());

        if ( xd >= x && xd<=x2){
            if (yd >=y && yd <= y2){
                return true;
            }

        }
        return false;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
