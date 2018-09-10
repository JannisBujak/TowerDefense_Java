public class Way {
    private double x, y;
    private Way nextWay;

    Way(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void print(){
        System.out.println("(" + x + "|" + y + ")");
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Way getNextWay() {
        return nextWay;
    }

    public void add(Way way){
        if(nextWay == null){
            nextWay = way;
        }else{
            nextWay.add(way);
        }
    }
}
