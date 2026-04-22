package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class ComptesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comptes);

        ArrayList<Compte> liste = new ArrayList<>();
        liste.add(new Compte("0011-2233-4455-6677", "Compte courant", 8250.00));
        liste.add(new Compte("0011-2233-4455-8899", "Compte épargne", 3700.00));
        liste.add(new Compte("0011-2233-4455-1100", "Compte sur carnet", 500.00));
        CompteAdapter adapter = new CompteAdapter(this, liste);
        ListView lv = findViewById(R.id.lvComptes);
        lv.setAdapter(adapter);
    }
}