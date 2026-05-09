package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;


public class TransactionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<Transaction> liste = db.getAllTransactions();


        // Créer l'Adapter et l'associer à la ListView
        TransactionAdapter adapter = new TransactionAdapter(this, liste);
        ListView lv = findViewById(R.id.lvTransactions);
        lv.setAdapter(adapter);
    }
}