package com.example.bankapp;

public class Transaction {
    private String date;
    private String libelle;
    private Double montant;

    public Transaction(String date, String libelle, Double montant) {
        this.date = date;
        this.libelle = libelle;
        this.montant = montant;
    }
    public String getDate(){ return date; }
    public String getLibelle(){ return libelle; }
    public Double getMontant(){ return montant; }
    public boolean estCredit(){ return montant > 0; }
}
