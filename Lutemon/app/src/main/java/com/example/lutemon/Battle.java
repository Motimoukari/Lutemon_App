package com.example.lutemon;

import java.util.Random;

public class Battle {
    private Storage storage;

    public Battle(Storage storage) {
        this.storage = storage;
    }

    public void startBattle(int lutemonId1, int lutemonId2) {
        Lutemon lutemon1 = storage.getLutemon(lutemonId1);
        Lutemon lutemon2 = storage.getLutemon(lutemonId2);

        while (lutemon1.getHealth() > 0 && lutemon2.getHealth() > 0) {
            System.out.println("1: " + lutemon1.toString());
            System.out.println("2: " + lutemon2.toString());

            lutemon1.attack(lutemon2);
            System.out.println(lutemon1.getName() + " attacks " + lutemon2.getName());

            if (lutemon2.getHealth() > 0) {
                System.out.println(lutemon2.getName() + " manages to escape death.");
                Lutemon temp = lutemon1;
                lutemon1 = lutemon2;
                lutemon2 = temp;
            } else {
                System.out.println(lutemon2.getName() + " gets killed.");
                lutemon1.gainExperience(1);
            }
        }

        System.out.println("The battle is over.");
    }
}
