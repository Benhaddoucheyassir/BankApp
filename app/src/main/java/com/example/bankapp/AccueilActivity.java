package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
public class AccueilActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        // Récupération des vues
        TextView tvBienvenue = findViewById(R.id.tvBienvenue);
        CardView cardTransactions = findViewById(R.id.cardTransactions);
        CardView cardRecherche = findViewById(R.id.cardRecherche);
        CardView cardVirement = findViewById(R.id.cardVirement);
        CardView cardComptes = findViewById(R.id.cardComptes);
        CardView cardStats = findViewById(R.id.cardStats);
        CardView cardParams = findViewById(R.id.cardParams);

        // Récupérer le nom passé par LoginActivity
        String nom = getIntent().getStringExtra("nom_utilisateur");
        if(nom != null){
            tvBienvenue.setText(getString(R.string.accueil_bienvenue, nom));
        }
        cardTransactions.setOnClickListener(v -> afficherToast());
        cardRecherche.setOnClickListener(v -> afficherToast());
        cardVirement.setOnClickListener(v -> afficherToast());
        cardComptes.setOnClickListener(v -> afficherToast());
        cardStats.setOnClickListener(v -> afficherToast());
        cardParams.setOnClickListener(v -> afficherToast());

        cardTransactions.setOnClickListener(v -> {
            Intent intent = new Intent(this, TransactionsActivity.class);
            intent.putExtra("nom_utilisateur", nom);
            startActivity(intent);
        });
        cardComptes.setOnClickListener(v -> {
            Intent intent = new Intent(this, ComptesActivity.class);
            intent.putExtra("nom_utilisateur", nom);
            startActivity(intent);
        });


    }
    private void afficherToast() {
        Toast.makeText(this, R.string.toast_a_venir, Toast.LENGTH_SHORT).show();
    }







}

