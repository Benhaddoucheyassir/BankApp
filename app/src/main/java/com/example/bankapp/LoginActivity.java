package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
public class LoginActivity extends AppCompatActivity {
    // Variables d'instance : utilisées dans plusieurs méthodes
    private EditText etIdentifiant;
    private EditText etMotDePasse;
    private CheckBox cbRappeler;
    private TextView tvErreur;
    // Identifiants en dur (pour cet atelier uniquement)
    private static final String ID_VALIDE = "admin";
    private static final String MDP_VALIDE = "123456";
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Récupération des vues
        etIdentifiant = findViewById(R.id.etIdentifiant);
        etMotDePasse = findViewById(R.id.etMotDePasse);
        cbRappeler = findViewById(R.id.cbRappeler);
        tvErreur = findViewById(R.id.tvErreur);

        Button btnConnexion = findViewById(R.id.btnConnexion);
        btnConnexion.setOnClickListener(v -> tenterConnexion());
    }


    private void tenterConnexion() {
        // Réinitialiser l'erreur précédente
        tvErreur.setVisibility(View.GONE);

        // Lire les champs
        String id = etIdentifiant.getText().toString().trim();
        String mdp = etMotDePasse.getText().toString().trim();

        // --- Niveau 1 : champs vides ---
        if(id.isEmpty() || mdp.isEmpty()){
            afficherErreur(getString(R.string.erreur_champs_vides));
            return ;
        }
        // --- Niveau 2 : mot de passe trop court ---
        if (mdp.length() < 6) {
            afficherErreur(getString(R.string.erreur_mdp_court));
            return; // on s'arrête ici
        }

        if(id.equals(ID_VALIDE) && mdp.equals(MDP_VALIDE)){
            Log.d(TAG, "tenterConnexion: ");
            Intent intent = new Intent(this, AccueilActivity.class);
            intent.putExtra("nom_utilisateur", id);
            startActivity(intent);
        }else {
            afficherErreur(getString(R.string.erreur_identifiants));
        }
    }
    private void afficherErreur(String message) {
        tvErreur.setText(message);
        tvErreur.setVisibility(View.VISIBLE);
    }

}