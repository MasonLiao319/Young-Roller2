import java.util.Random;

public class Sword extends Armory {
    private static final int BASE_ATTACK = 20;
    private static final int BASE_DEFENCE = 15;

    public Sword() {
        super("Sword", BASE_DEFENCE, BASE_ATTACK);
    }

    @Override
    public int calculateAttackEffect() {
        // Implement sword-specific attack logic
        return getAttack() + random.nextInt(10) + 1;
    }

    @Override
    public int calculateDefenceEffect() {
        // Implement sword-specific defence logic
        return getDefence() + random.nextInt(10) + 1;
    }

    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public int getAttack() {
        return attack;
    }

}
