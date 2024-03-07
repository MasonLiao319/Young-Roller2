import java.util.Random;

public class Shield extends Armory {
    private static final int BASE_DEFENCE = 20;
    private static final int ATTACK = 12; // Shields typically don't contribute to attack

    public Shield() {
        super("Shield", BASE_DEFENCE, ATTACK);
    }

    @Override
    public int calculateAttackEffect() {
        // Implement shield-specific attack logic, if any
        return getAttack() + random.nextInt(5);
    }

    @Override
    public int calculateDefenceEffect() {
        // Implement shield-specific defence logic
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
