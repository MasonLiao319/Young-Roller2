import java.util.Random;
public class Mage extends Character {
    private Random random;
    public Mage() {
        super("Mage", 20, 16);
        this.random = new Random();

    }

    @Override
    public int calculateAttack() {
        int attackValue = strength; // 角色的基础攻击力
        if (armory != null) {
            attackValue += armory.calculateAttackEffect();
        }
        return attackValue;
    }

    @Override
    public int calculateDefense() {
        int defenseValue = defense; // 角色的基础防御力
        if (armory != null) {
            defenseValue += armory.calculateDefenceEffect();
        }
        return defenseValue;

    }
    @Override
    public void performSpecialMove() {

    }

}