//coded by John Esco
import java.applet.*; //gives functionality to create windows
import java.awt.*; //ability to draw
import java.util.*;
public class sCarpet extends Applet { //need to extend Applet which contains main method
    private Image display; //Consider this the window...kind of
    private Graphics drawingArea; //these are the drawing tools

    public void init()//called by Applet
    {
        setSize(640,640);
        int height = getSize().height; //getSize comes from applet
        int width = getSize().width;
        //super. this can be used to see other methods
        display = createImage(width,height); //creates the window
        drawingArea = display.getGraphics(); //could leave this out and do this way Trading time for space. the more methods you call the more time it takes
        //display.getGraphics().fillRect(); this works too
        //TODO call the recursive method
        drawGasket(0,0,width,drawingArea);
    }
    public void paint(Graphics g){
        g.drawImage(display,0,0,null);
    }
    public static void drawGasket(int x, int y, int side, Graphics g){ //g is needed to draw it
        int sub = side/3;
        g.fillRect(x+sub,y+sub,sub,sub);//origin +1/3 length
        if(sub>=3){ //MAGIC NUMBER
            //Top three squares
            drawGasket(x,y,sub,g);//left
            drawGasket(x+sub,y,sub,g);//top middle
            drawGasket(x+sub*2,y,sub,g);//Right
            //Middle two sqaures
            drawGasket(x,y+sub,sub,g);//middle left
            drawGasket(x+sub*2,y+sub,sub,g);//Right
            //bottom three
            drawGasket(x,y+sub*2,sub,g);//left
            drawGasket(x+sub,y+sub*2,sub,g);//top middle
            drawGasket(x+sub*2,y+sub*2,sub,g);//Right
        }
    }

}
