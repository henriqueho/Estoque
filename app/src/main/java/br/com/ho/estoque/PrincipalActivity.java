package br.com.ho.estoque;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Button salvar = findViewById(R.id.button3);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intents = new Intent(PrincipalActivity.this, SalvarActivity.class);
                PrincipalActivity.this.startActivity(intents);

            }
        });

        Button estoque = findViewById(R.id.button2);
        estoque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalActivity.this, EstoqueActivity.class);
                PrincipalActivity.this.startActivity(intent);
            }
        });
    }
}