package com.example.lutemon;

public class Lutemon {
    private String name;
    private int attack;
    private int defense;
    private int maxHealth;
    private int health;
    private int experience;

    public Lutemon(String name, int attack, int defense, int maxHealth) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.experience = 0;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getExperience() {
        return experience;
    }

    public void attack(Lutemon opponent) {
        int damage = Math.max(0, this.attack - opponent.getDefense());
        opponent.receiveDamage(damage);
    }

    public void receiveDamage(int damage) {
        this.health = Math.max(0, this.health - damage);
    }

    public void gainExperience(int points) {
        this.experience += points;
    }

    @Override
    public String toString() {
        return name + "(" + getClass().getSimpleName() + ") att: " + attack + "; def: " + defense +
                "; exp: " + experience + "; health: " + health + "/" + maxHealth;
    }
}
