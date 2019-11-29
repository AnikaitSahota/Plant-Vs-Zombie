package BackEnd_Classes;

abstract public class PC extends Character{ // player character
    private double Health ;

    PC(String Name , double health) {
        super(Name);
        Health = health ;
    }
    public double getHealth() { return Health ;}
    public void takeDamage(double reduceBy) {
        Health -= reduceBy ;
    }
    public boolean isAlive() {
        return ( Health > 0 );
    }
}