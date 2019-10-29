import java.applet.*;
import java.awt.*;
import java.util.*;
public class SierpenskiCarpet extends Applet{
    private Image display;
    private Graphics drawingArea;
    public void init(){
        int height = getSize().height;
        int width = getSize().width;
        display = createImage(width,height);
        drawingArea = display.getGraphics();
        drawGasket(0,0,width,drawingArea);
    }
    public void paint(Graphics g){
        g.drawImage(display,0,0,null);
    }
    //in pcs the origin is always the top left.  y increases down
    public static final int STOP =4;
    public static void drawGasket(int x, int y, int side, Graphics g){
        int sub = side/3;
        g.fillRect(x+sub, y+sub,sub,sub);
        if(sub >= STOP) {
            //8 calls
            //top three squares
            drawGasket(x, y, sub, g); //left
            drawGasket(x+sub,y,sub,g); //Middle
            drawGasket(x+sub*2,y,sub,g); //right
            //middle 2 squares. the absolute middle is filled in
            drawGasket(x,y+sub,sub,g); //left
            drawGasket(x+sub*2,y+sub,sub,g); //right
            //bottom three squares
            drawGasket(x,y+sub*2,sub,g);//left
            drawGasket(x+sub,y+sub*2,sub,g);//middle
            drawGasket(x+sub*2,y+sub*2,sub,g);//right
        }
    }

}
