package Objects;

public class Vector {

    private double x, y;

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
        double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        this.x /= length;
        this.y /= length;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
