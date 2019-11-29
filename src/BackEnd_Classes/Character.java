package BackEnd_Classes;

abstract public class Character {
    private String Name ;
    Character(String Name) {
        this.Name = Name ;
    }

    public String getName() {
        return Name;
    }
}
