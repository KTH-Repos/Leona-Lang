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

    
    public void move(int d) {
        xCord = xCord + d * Math.cos(Math.PI*angle/180);
        yCord = yCord + d * Math.sin(Math.PI*angle/180);
    }

    
}
