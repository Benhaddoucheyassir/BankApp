package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class VirementActivity extends AppCompatActivity {
    private EditText etBeneficiaire, etMontant, etMotif;
    private TextView tvErreur;
    private static final int CODE_CAMERA = 200;
    private ImageView ivJustificatif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virement);

        etBeneficiaire = findViewById(R.id.etBeneficiaire);
        etMontant = findViewById(R.id.etMontant);
        etMotif = findViewById(R.id.etMotif);
        tvErreur = findViewById(R.id.tvErreurVirement);

        Button btnVirement = findViewById(R.id.btnVirement);
        btnVirement.setOnClickListener(v -> effectuerVirement());

        ivJustificatif = findViewById(R.id.ivJustificatif);
        Button btnPhoto = findViewById(R.id.btnPhoto);
        btnPhoto.setOnClickListener(v -> ouvrirCamera());
    }

    private void ouvrirCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CODE_CAMERA);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, CODE_CAMERA);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            // Récupérer la miniature
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            // Afficher dans l'ImageView
            ivJustificatif.setImageBitmap(photo);
            ivJustificatif.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODE_CAMERA && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            ouvrirCamera();
        }
    }




    private void effectuerVirement() {
        tvErreur.setVisibility(View.GONE);
        String benef = etBeneficiaire.getText().toString().trim();
        String montantStr = etMontant.getText().toString().trim();
        String motif = etMotif.getText().toString().trim();
        // Validation
        if (benef.isEmpty() || montantStr.isEmpty() || motif.isEmpty()) {
            afficherErreur("Veuillez remplir tous les champs.");
            return;
        }
        double montant;
        try {
            montant = Double.parseDouble(montantStr);
        } catch (NumberFormatException e) {
            afficherErreur("Montant invalide.");
            return;
        }
        if (montant <= 0) {
            afficherErreur("Le montant doit être positif.");
            return;
        }
        // Insérer dans SQLite (montant négatif car c'est un débit)
        DatabaseHelper db = new DatabaseHelper(this);
        String aujourdhui = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
        db.ajouterTransaction(aujourdhui, "Virement : " + benef, -montant);
        Toast.makeText(this, "Virement effectué", Toast.LENGTH_SHORT).show();
        finish(); // revenir à l'écran précédent
    }
    private void afficherErreur(String msg) {
        tvErreur.setText(msg);
        tvErreur.setVisibility(View.VISIBLE);
    }


}
