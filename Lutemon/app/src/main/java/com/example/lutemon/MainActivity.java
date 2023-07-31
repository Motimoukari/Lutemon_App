package com.example.lutemon;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Storage storage;
    private TextView lutemonInfoTextView;
    private Spinner lutemonSpinner1;
    private Spinner lutemonSpinner2;
    private Button startBattleButton;
    private EditText lutemonNameEditText;
    private EditText lutemonAttackEditText;
    private EditText lutemonDefenseEditText;
    private ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = new Storage();
        lutemonInfoTextView = findViewById(R.id.lutemon_info_text_view);
        lutemonSpinner1 = findViewById(R.id.lutemon_spinner_1);
        lutemonSpinner2 = findViewById(R.id.lutemon_spinner_2);
        startBattleButton = findViewById(R.id.start_battle_button);
        lutemonNameEditText = findViewById(R.id.lutemon_name_edit_text);
        lutemonAttackEditText = findViewById(R.id.lutemon_attack_edit_text);
        lutemonDefenseEditText = findViewById(R.id.lutemon_defense_edit_text);

        // Create Lutemons and add to storage
        Lutemon lutemon1 = new Lutemon("Pink", 7, 2, 18);
        Lutemon lutemon2 = new Lutemon("Black", 9, 0, 16);
        Lutemon lutemon3 = new Lutemon("White", 5, 4, 20);
        Lutemon lutemon4 = new Lutemon("Green", 6, 3, 19);
        Lutemon lutemon5 = new Lutemon("Orange", 8, 1, 17);
        storage.addLutemon(1, lutemon1);
        storage.addLutemon(2, lutemon2);
        storage.addLutemon(3, lutemon3);
        storage.addLutemon(4, lutemon4);
        storage.addLutemon(5, lutemon5);

        updateLutemonInfoTextView();
        setupLutemonSpinners();

        startBattleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lutemonId1 = lutemonSpinner1.getSelectedItemPosition() + 1;
                int lutemonId2 = lutemonSpinner2.getSelectedItemPosition() + 1;
                battle(lutemonId1, lutemonId2);
                updateLutemonInfoTextView();
            }
        });

        Button createLutemonButton = findViewById(R.id.create_lutemon_button);
        createLutemonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewLutemon();
            }
        });
    }

    private void setupLutemonSpinners() {
        HashMap<Integer, Lutemon> lutemons = storage.getAllLutemons();
        String[] lutemonNames = new String[lutemons.size()];
        int i = 0;
        for (int lutemonId : lutemons.keySet()) {
            lutemonNames[i] = lutemons.get(lutemonId).getName();
            i++;
        }

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lutemonNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        lutemonSpinner1.setAdapter(spinnerAdapter);
        lutemonSpinner2.setAdapter(spinnerAdapter);
    }

    private void createNewLutemon() {
        String name = lutemonNameEditText.getText().toString().trim();
        int attack = Integer.parseInt(lutemonAttackEditText.getText().toString());
        int defense = Integer.parseInt(lutemonDefenseEditText.getText().toString());

        if (name.isEmpty()) {
            showToast("Please enter a name for the new Lutemon.");
            return;
        }

        if (isNameExists(name)) {
            showToast("A Lutemon with this name already exists. Please choose another name.");
            return;
        }

        Lutemon newLutemon = new Lutemon(name, attack, defense, 20);
        int newLutemonId = storage.getNextLutemonId();
        storage.addLutemon(newLutemonId, newLutemon);

        setupLutemonSpinners();
        updateLutemonInfoTextView();
        clearInputFields();
    }

    private boolean isNameExists(String name) {
        HashMap<Integer, Lutemon> lutemons = storage.getAllLutemons();
        for (Lutemon lutemon : lutemons.values()) {
            if (lutemon.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private void battle(int lutemonId1, int lutemonId2) {
        Battle battle = new Battle(storage);
        battle.startBattle(lutemonId1, lutemonId2);
    }

    private void updateLutemonInfoTextView() {
        HashMap<Integer, Lutemon> lutemons = storage.getAllLutemons();
        StringBuilder sb = new StringBuilder();
        for (int lutemonId : lutemons.keySet()) {
            sb.append(lutemons.get(lutemonId).toString()).append("\n");
        }
        lutemonInfoTextView.setText(sb.toString());
    }

    private void clearInputFields() {
        lutemonNameEditText.setText("");
        lutemonAttackEditText.setText("");
        lutemonDefenseEditText.setText("");
    }
}
