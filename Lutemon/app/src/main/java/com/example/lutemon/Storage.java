package com.example.lutemon;
import java.util.HashMap;

public class Storage {
    private HashMap<Integer, Lutemon> lutemons;
    private int nextLutemonId;

    public Storage() {
        lutemons = new HashMap<>();
        nextLutemonId = 1;
    }

    public void addLutemon(int id, Lutemon lutemon) {
        lutemons.put(id, lutemon);
    }

    public Lutemon getLutemon(int id) {
        return lutemons.get(id);
    }

    public HashMap<Integer, Lutemon> getAllLutemons() {
        return lutemons;
    }

    public int getNextLutemonId() {
        int id = nextLutemonId;
        nextLutemonId++;
        return id;
    }
}