package com.example.bankapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
public class HeaderFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vue = inflater.inflate(R.layout.fragment_header, container, false);

        // Récupération des vues  (attention : VUE.findViewById)
        TextView tvNom = vue.findViewById(R.id.tvNomHeader);
        TextView tvSolde = vue.findViewById(R.id.tvSoldeHeader);

        String nom = getActivity().getIntent().getStringExtra("nom_utilisateur");
        if(nom != null){
            tvNom.setText("Bonjour, " + nom);
        }
        // Solde en dur pour l'instant
        tvSolde.setText("12 450,00 MAD");

        return vue;

    }

}
