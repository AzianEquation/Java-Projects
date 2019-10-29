import java.applet.*;
import java.awt.*;
import java.util.*;
public class SierpenskiHexaCarpetGon extends Applet{
    private Image display;
    private Graphics drawingArea;

    public void init(){
        setSize(700,700);
        int height = getSize().height;
        int width = getSize().width;
        display = createImage(width,height);
        drawingArea = display.getGraphics();
        //TODO draw thing
        drawGasket(0,0,width,drawingArea);
    }
    public void paint(Graphics g){
        g.drawImage(display,0,0,null);
    }
    public static void drawGasket(int x, int y, int side, Graphics g){
        int sub = side/2; //each triangle side is divided by two
        //draw polygon uses arrays
        drawHexagon(x,y,side,g);;
        if (sub >= 3) {
            drawGasket(x+sub,y,sub,g);//top middle
            drawGasket(x,y+sub,sub,g);//middle left
            drawGasket(x+sub*2,y+sub,sub,g);//Middle right
            drawGasket(x+sub,y+sub*2,sub,g);//bottom middle
        }
    }
    private static void drawHexagon(int x, int y, int side, Graphics g){
        int[] xS = {x,x+side/4,x+side*3/4,x+side,x+side*3/4,x+side/4,x}; //use curly braces comma delims entries //the last x is just to reconnect. some libraries make that assumption and it can be omitted
        int[] yS = {y+side/2,y,y,y+side/2, y+side,y+side,y+side/2};
        g.drawPolygon(xS,yS,xS.length);
    }
}
