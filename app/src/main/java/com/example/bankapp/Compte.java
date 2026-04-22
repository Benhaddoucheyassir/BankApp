package com.example.bankapp;

public class Compte {

    private String numero;
    private String libelle;
    private Double solde;

    public Compte(String numero, String libelle, Double solde) {
        this.numero = numero;
        this.libelle = libelle;
        this.solde = solde;
    }

    public String getNumero() {
        return numero;
    }

    public String getLibelle() {
        return libelle;
    }

    public Double getSolde() {
        return solde;
    }
}
