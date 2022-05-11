public class Leona {

    private double xCord;
    private double yCord;
    private int angle;
    private String penColor;
    private boolean penUp;   //false = down, true = up

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
        double xCordOld = xCord;
        double yCordOld = yCord;
        xCord = xCord + d * Math.cos(Math.PI*angle/180);
        yCord = yCord + d * Math.sin(Math.PI*angle/180);
        if(!penUp) {
            System.out.println("Move from" + "(" +xCordOld + "," + yCordOld + ")" + "to" + "(" +xCord + "," + yCord + ")");
        }
    }

    
}
