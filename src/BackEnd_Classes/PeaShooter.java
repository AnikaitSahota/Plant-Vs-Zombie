package BackEnd_Classes;

public class PeaShooter extends Plant{

    class Pea {
        double PeaPower ;
        Pea() { PeaPower = 10 ; }
    }
    public PeaShooter() {
        super("Peashooter",200) ; // assuming max Health of pea shooter to be 200
    }
    @Override
    public void uniqueAbility() {
        // this is a funtion for attacking zombie ;
    }
}
