//swing or convas libraries may exist
import javafx.scene.paint.Stop;

import java.applet.*; //helps draw the window
import java.awt.*; //drawing
import java.util.*; //utilities
public class RandomFractal extends Applet {
    private Image display; //canvas
    private Graphics drawingArea;

    public void init()//called by the applet
    {
        int height = getSize().height; //comes from applet should technically call super
        int width = getSize().width;
        //super. shows the methods
        display  = createImage(width,height);
        drawingArea = display.getGraphics();
        //TODO call random fractal
        randomFractal(0,height/2,width,height/2,drawingArea);
    }
    public void paint(Graphics g){
        g.drawImage(display, 0, 0, null);
    }
    //TODO double buffereing. show one up front. while creating one in the background then switches when done

    //constants
    public static final int STOP = 4;
    public static final int MAX_Y = 12;
    public static void randomFractal(int leftX, int leftY, int rightX, int rightY, Graphics g){
        if((rightX - leftX)<= STOP){
            //need to draw the line
            g.drawLine(leftX,leftY,rightX,rightY); //draws the line
        }
        else{
            int midX = (leftX+rightX)/2;
            int midY = (leftY+rightY)/2;
            Random r = new Random();
            int delta = r.nextInt(MAX_Y);
            midY =+ delta;
            //recursive calls
            //left side
            randomFractal(leftX,leftY,midX,midY,g);
            //rightside
            randomFractal(midX,midY,rightX,rightY,g);
        }
    }
}
