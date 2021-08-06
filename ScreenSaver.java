/**
 * Screen saver for CE4702
 *
 * @author Tom Meehan
 * @version 01/04/19
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.util.Date;
import java.util.Random;
import java.text.SimpleDateFormat;

public class ScreenSaver extends JPanel {
    private static ScreenSaver.Star[] stars = new ScreenSaver.Star[500]; 
    private final Color colours[] =
    {
        Color.YELLOW,
        Color.CYAN,Color.WHITE//Color.ORANGE,
    };
    private static Random rng = new Random();
    private static double speed = 0.1;
    private static int screenHeight;
    private static int screenWidth;
    private static int centreX;
    private static int centreY;
    private static int spectatorZ = 100; //point which we see from
    private static int viewPlaneZ = 80; //plane which persepctive images are projected(our screen)
    private static int specViewDist = spectatorZ-viewPlaneZ;
    private class Star { 
        private double x = 0;
        private double y = 0;
        private double z = 0;
        private double length = 1;
        private double angle;
        
        private Color c;
        public Star(double x, double y, double z) { //constructor
            this.x = x;
            this.y = y;
            this.z = z;
            setA();
            this.c = colours[rng.nextInt(colours.length)];
        }
        public void setA() { //sets angle, with error handling(in case tan goes to infinity)
            double a = 0;
            try {
                a = Math.atan2(centreY-y,centreX-x);
            } catch (ArithmeticException e) {
                a = 0;
            }
            this.angle = a;
        }
        public void setPos(double x, double y, double z) { //position setter
            this.x = x;
            this.y = y;
            this.z = z;
        }
        public double centreDist() { //calculates dist to centre
            double dy = centreX-x;
            double dx = centreY-y;
            double d = Math.pow(dy*dy + dx*dx,0.5);
            return d;
        }
 
        public void calcLength() { //calculates true length for 3D perspectivw
            
            length = speed*0.1;
        }
        public double calcAlpha() { //calculates the angle between the spectator point and the star position on the XZ plane
            double a = Math.atan2((centreX-x),(spectatorZ - z)); //this is on the XZ plane, so x replaces y and z replaces x when using trig
            return a;
        }
        public double calcBeta() { //calculates the angle between the spectator point and the star length position on the XZ plane
            double a = Math.atan2((centreX-x),(spectatorZ - (z-length))); //this is on the XZ plane, so x replaces y and z replaces x when using trig
            return a;
        }
        public double perspectiveLength() { //finds the perspective length of line on the view plane, when looking at the XZ plane
            calcLength();
            double dx = centreX - x;
            double dz = spectatorZ - z;
            double d0 = Math.sqrt(Math.pow(dx,2)+Math.pow(dz,2));
            double d1 = Math.tan(calcBeta())*length;
            double d2 = specViewDist/Math.cos(calcAlpha());
            double L =(d1*d2)/d0;
            return L;
        }
        public double perspectiveX() { //calculates the actual X position in perspective space
            double pX = centreX - specViewDist/Math.tan(calcAlpha());
            return pX;
        }
        public double perspectiveY() {//calculates the actual Y position in perspective space
            double pY = y + perspectiveX()*Math.tan(angle); 
            return pY;
        }
        /**
         * This method draws and calculates the stars position and length
         * Most of the commented out code was our attempt to create a 3D perspective view of the stars shooting past.
         * We had to settle for something simple in the end.
         * @param g 
         */
        public void draw(Graphics g) { 
            g.setColor(c);
            /*double px = perspectiveX(); 
            double py = perspectiveY();
            double px1 = Math.cos(angle)*perspectiveLength();
            double py1 = Math.sin(angle)*perspectiveLength();
            System.out.println((int)px1 + ","+ (int)py1);
            System.out.println((int)px + ","+ (int)py);*/
            if(x>=screenWidth+100 || x<=-100 || y <=-100 || y >= screenHeight+100) { //if(z<spectatorZ) {
                x = (centreX +(rng.nextGaussian()*100)); //nextGaussian ranges from 1 to -1
                y = (centreY +(rng.nextGaussian()*100));
                z = 0;
                setA();
            }
            double dy = (Math.sin(angle)*-speed);
            double dx = (Math.cos(angle)*-speed);
            x+=dx;
            y+=dy;
            int x1 = (int)(x-(dx));
            int y1 = (int)(y-(dy));
            
            g.drawLine((int)x,(int)y,x1,y1);
            int d = (int)(centreDist()*0.006);
            g.fillOval((int)x-d/2,(int)y-d/2,d,d);
            
            //z += speed;
            //g.drawLine((int)(px),(int)(py),(int)(Math.cos(angle)*perspectiveLength()),(int)(Math.sin(angle)*perspectiveLength()));
            
        }
    }
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        super.paintComponent(g);
        for(int i =0;i<stars.length;i++) {
           stars[i].draw(g);
        }
        g.fillOval(centreX,centreY,2,2);
    }

    
    public static void main(String args[]) {
        JFrame frame = new JFrame("Screensaver");
        ScreenSaver ssPanel = new ScreenSaver();
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenDim.getWidth();
        screenHeight = (int) screenDim.getHeight();
        centreX = screenWidth/2;
        centreY = screenHeight/2;
        Random rng = new Random();
        for(int i =0;i<stars.length;i++) {
            stars[i] = ssPanel.new Star((centreX +(rng.nextGaussian()*centreX)),centreY +(rng.nextGaussian()*centreY),rng.nextInt(100));
        }
        
        System.out.println(stars[0].toString());
        
        ssPanel.setBackground(Color.BLACK);
        frame.add(ssPanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);    // maximise window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);                           // display frame
        int a = 1;
        do {  // Redraw window continuously, with a short delay.
            frame.repaint();
            if(speed > 200 || speed < 0.1)  {
                
                a=-a;
            }
            speed+=0.01*speed*a;
            //System.out.println(speed);
            try {
                Thread.sleep(25);      // Delay the given ms
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        } while (true);
    }
}
