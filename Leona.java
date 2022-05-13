import java.text.DecimalFormat;

/* Skriven av Tomas & Melvin */

public class Leona {
    // Simulerar sköldpaddan Leona

    private double xCord;
    private double yCord;
    private int angle;
    private String penColor;
    private boolean penUp;   //false = down, true = up

    private static final DecimalFormat df = new DecimalFormat("0.0000"); // Printa bara 4 decimaler

    //(x + d cos(pi*v=180); y + d sin(pi*v=180))
    public Leona() {
        xCord = 0.0;
        yCord = 0.0;
        angle = 0;
        penColor = "#0000FF";
        penUp = true;
    }

    public void penUp() {
        this.penUp = true;
    }

    public void penDown() {
        this.penUp = false;
    }

    public void turnRight(int degree) {
        angle = angle - degree;
    }

    public void turnLeft(int degree) {
        angle = angle + degree;
    }

    public void moveForw(int d) {
        move(d);        //no check is done if d is negative or not
    }

    public void moveBack(int d) {
        move(d*(-1));   //no check is done if d is negative or not
    }

    public void changeColor(String color) {
        penColor = color;
    }
    
    public void move(int d) {
        // Ska bara printas när Leona rör sig framåt / bakåt och pennan är i ned-läge (penUp = false)

        // Uppdatera koordinater enligt formel från labbhäftet
        double xCordOld = xCord;
        double yCordOld = yCord;
        xCord = xCord + d * Math.cos(Math.PI*angle/180);
        yCord = yCord + d * Math.sin(Math.PI*angle/180);

        if(!penUp) {
            //Skriv ut förflyttning
            System.out.println(this.penColor + " " + formatDouble(xCordOld) + " " + formatDouble(yCordOld) + " " + formatDouble(xCord) + " " + formatDouble(yCord));
        }
    }

    private String formatDouble(double d) {
        // Hjälparmetod för att skriva ut 4 decimaler
        // Löser även bugg med -0.0000
        String s = df.format(d);
        if(s.equals("-0.0000")) {
            return "0.0000";
        } else {
            return s;
        }
    }

    
}
