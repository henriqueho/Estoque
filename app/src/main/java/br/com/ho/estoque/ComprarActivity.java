package br.com.ho.estoque;


import android.content.DialogInterface;
import android.os.Bundle;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ComprarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar);
        TextView texto = findViewById(R.id.textFont);
        //texto.setText("o numero");
        Button botao = findViewById(R.id.buttonBotao);

        botao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });
    }

    private void postCotract() {
        String compra = "true";
        switch (compra){
            case "true":
                break;
            case "false":
                break;
            default:

        }
        new AlertDialog.Builder(this)
                .setTitle("Icone")
                .setMessage("Sim")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();


    }
}