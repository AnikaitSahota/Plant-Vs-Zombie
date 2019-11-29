package BackEnd_Classes;

abstract public class Plant extends PC {
    Plant(String Name , double Health) {
        super(Name , Health) ;
        System.out.println("plant created :)");
    }
    abstract public void uniqueAbility();
}
