public abstract class Character {
    private String name;
    protected int strength; // 角色基础攻击力
    protected int defense; // 角色基础防御力
    protected Armory armory;

    public Character(String name, int strength, int defense) {
        this.name = name;
        this.strength = strength;
        this.defense = defense;
    }

    // 获取角色名称
    public String getName() {
        return name;
    }

    // 设置角色名称
    public void setName(String name) {
        this.name = name;
    }

    // 获取角色力量（攻击力）
    public int getStrength() {
        return strength;
    }

    // 设置角色力量（攻击力）
    public void setStrength(int strength) {
        this.strength = strength;
    }

    // 获取角色防御力
    public int getDefense() {
        return defense;
    }

    // 设置角色防御力
    public void setDefense(int defense) {
        this.defense = defense;
    }

    // 设置角色的装备
    public void setArmory(Armory armory) {
        this.armory = armory;
    }

    // 获取角色的装备
    public Armory getArmory() {
        return armory;
    }

    // 计算角色的总攻击力
    public abstract int calculateAttack();


    // 计算角色的总防御力
    public abstract int calculateDefense();


    // 抽象方法，子类需要根据具体角色类型实现这些方法
    public abstract void performSpecialMove();
}
