
public class Player {
    private String name;
    private int healthPoints;
    private Character character;
    private final Inventory inventory;
    private boolean isDefending;

    // Constructor without the name parameter
    public Player() {

        this.healthPoints = 100; // Default value for health points
        this.inventory = new Inventory();
        this.isDefending = false;

    }

    // Setter for the name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the name
    public String getName() {
        return this.name;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }


    public int attack(Opponent opponent) {

            int attackPower = character.calculateAttack();
            return opponent.receiveDamage(attackPower);  // Call receiveDamage and return actual damage dealt
        }


    public int receiveDamage(int incomingDamage) {
        int defense = character.calculateDefense();
        int damageDealt = Math.max(incomingDamage - defense, 0);
        healthPoints -= damageDealt;
        healthPoints = Math.max(healthPoints, 0);
        return damageDealt;  // Return actual damage received after defense
    }

    public void defend() {
        this.isDefending = true;
    }

    // Setter for health points
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    // Getter for health points
    public int getHealthPoints() {
        return healthPoints;
    }

    // Getter for character
    public Character getCharacter() {
        return character;
    }

    public void reset() {
        this.name = ""; // Reset the name to an empty string
        this.healthPoints = 100; // Reset health points to the default value
        this.character = null; // Reset the character to null
        this.inventory.clear(); // Clear the inventory, assuming there's a clear method
        this.isDefending = false; // Reset the defending state to false
    }

    // Getter for inventory
    public Inventory getInventory() {
        return inventory;
    }

    // Method to check if the player is alive
    public boolean isAlive() {
        return this.healthPoints > 0;
    }

    public String getCharacterImagePath() {
        if (this.character instanceof Warrior) {
            return "/warrior.jpg";
        } else if (this.character instanceof Mage) {
            return "/mage.jpg";
        } else {
            return "/DefaultCareer.jpg"; // Return a default image path if the character type is not recognized
        }
    }

}
