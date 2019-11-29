package BackEnd_Classes;

public class Player {
    protected String username , password ;
    protected int maxLevel ;
    Player(String username , String password) {
        this.username = username ;
        this.password = password ;
        maxLevel = 0 ;
    }
}
