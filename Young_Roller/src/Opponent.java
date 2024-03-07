import java.util.Random;
public class Opponent {
    private String name;
    private int healthPoints;
    private int defence;
    private int attack;
    private Random random;
    public Opponent(String name, int healthPoints, int defence,int attack) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.defence = defence;
        this.attack = attack;
        this.random = new Random();
    }
    public int attack(Player player) {
        int attackPower = calculateAttack() + random.nextInt(10) + 1;
        return player.receiveDamage(attackPower);  // Call receiveDamage and return actual damage dealt
    }

    public int receiveDamage(int incomingDamage) {
        int damageDealt = Math.max(incomingDamage - defence, 0);
        healthPoints -= damageDealt;
        healthPoints = Math.max(healthPoints, 0);
        return damageDealt;  // Return actual damage received after defense
    }

    // 获取生命值
    public int getHealthPoints() {
        return healthPoints;
    }

    // 获取名字
    public String getName() {
        return name;
    }

    public int calculateAttack(){
        return attack;
    }

    public int calculateDefence(){
        return defence;
    }
    // 检查是否还活着
    public boolean isAlive() {
        return this.healthPoints > 0;
    }

    // 可以添加更多与对手相关的方法和属性
}
