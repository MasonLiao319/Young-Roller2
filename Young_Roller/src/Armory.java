import java.util.Random;

public abstract class Armory {
    private String name;
    protected int defence;
    protected int attack;
    protected Random random = new Random();

    public Armory(String name, int defence, int attack) {
        this.name = name;
        this.defence = defence;
        this.attack = attack;
    }

    public abstract int calculateAttackEffect();
    public abstract int calculateDefenceEffect();

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefence() {
        return defence;
    }



    public int getAttack() {
        return attack;
    }


}