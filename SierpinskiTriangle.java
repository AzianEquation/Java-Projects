//coded by John Esco
import java.applet.*;
import java.awt.*;
import java.util.*;
//NOTE for some reason in Intellij the applet window needs to be extended slightly downwards to show full image
public class SierpinskiTriangle extends Applet {
    //square root(3)/2 constant
    public static int returnIntRoot(int value){ //method to return the sqrt(3)/2 as closest pixel int
       Double doubleValue = (double)value;
       Double root = doubleValue*(Math.sqrt(3))/2;
       int ret = root.intValue();
       return ret;
    }
    //Attributes
    private Image display;
    private Graphics drawingArea;

    //initializing method
    public void init() {
        //setSize(640,640);
        int width = getSize().width;
        int height = returnIntRoot(width);
        System.out.println(width + " " + height);
        int counter = 0; //method to recursively count which step
        display = createImage(width, height); //creates an area that will be the display
        drawingArea = display.getGraphics();  //gives ability to draw
        drawSierpenskiSponge(0, 0, width, counter, drawingArea);
    }

    public void paint(Graphics g) {
        g.drawImage(display, 0, 0, null);
    }

    public static void drawSierpenskiSponge(int x, int y, int side, int counter, Graphics g) {
        int sub = side / 2; //x's will be at 1/4 of side while y's will be 1/2
        //int height = height/2;//sub divides the height
        drawTriangle(x, y, side, counter, g);//constructs initial Triangle shape
        counter++;
        drawTriangle(x, y, side, counter, g);//initially draws the two triangles
        counter++; //increments past 1 so a new origin can be created
        if (sub >= 4) {
            //4 is pixel amount described in prompt
            //top
            drawSierpenskiSponge(x+sub/2,y,sub,counter,g);
            //left
            drawSierpenskiSponge(x,y+returnIntRoot(sub),sub,counter,g);
            //right
            drawSierpenskiSponge(x+sub,y+returnIntRoot(sub),sub,counter,g);
        }
    }

    public static void drawTriangle(int x, int y, int side, int counter, Graphics g) {
        //fillPolygon takes int[]x int[]y, number of points
        //two cases
        Color white = new Color(255, 255, 255); //color white
        if (counter == 0) { //initial triangle that points up only needs to be drawn once
            int[] xPoints = {x, x + (side/ 2), x + side, x};
            int[] yPoints = {y + returnIntRoot(side), y, y + returnIntRoot(side), y + returnIntRoot(side)};  //x and y point arrays to draw a triangle
            g.fillPolygon(xPoints, yPoints, xPoints.length); //only need to draw the initial triangle
        } else if (counter == 1) { //triangle that points down
            int[] xPoints1 = {x + (side/4), x + ((3*side)/4), x + (side/2), x + (side/4)};
            int[] yPoints1 = {y + returnIntRoot(side/2), y + returnIntRoot(side/2), y + returnIntRoot(side), y + returnIntRoot(side/2)};
            g.setColor(white);
            g.fillPolygon(xPoints1, yPoints1, xPoints1.length);
        }
        else{ //not the initial two triangles so different origin
            int[] xPoints1 = {x + (side/4), x + ((3*side)/4), x + (side/2), x + (side/4)};
            int[] yPoints1 = {y + returnIntRoot(side/2), y + returnIntRoot(side/2), y + returnIntRoot(side), y + returnIntRoot(side/2)};
            g.setColor(white);
            g.fillPolygon(xPoints1, yPoints1, xPoints1.length);
        }
    }
}

