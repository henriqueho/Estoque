package br.com.ho.estoque;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.ho.estoque.entidade.Produto;
import br.com.ho.estoque.repositorio.produto.ProdutoTabela;

public class AtualizarActivity extends AppCompatActivity {

    String value;
    TextView id;
    EditText nome;
    EditText quantidade;
    EditText preco;
    Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("EXTRA_SESSION_ID");
            //The key argument here must match that used in the other activity
        }

        id = findViewById(R.id.codigoAtualizar_text);
        nome = findViewById(R.id.nomeAtualizar_edit);
        quantidade = findViewById(R.id.quantidadeAtualizar_edit);
        preco = findViewById(R.id.precoAtualizar_edit);

        salvo();

        Button atualizar = findViewById(R.id.atualizar_button);
        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizar();
            }
        });

        Button deletar = findViewById(R.id.deletar_button);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletar();
            }
        });

        Button estoque = findViewById(R.id.estoqueAtualizar_button);
        estoque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AtualizarActivity.this, EstoqueActivity.class);
                AtualizarActivity.this.startActivity(intent);
            }
        });
    }

    private void deletar(){
        ProdutoTabela produtoTabela = new ProdutoTabela(AtualizarActivity.this.getApplicationContext());
        produtoTabela.deletar(produto.id);

        Intent intent = new Intent(AtualizarActivity.this, EstoqueActivity.class);
        AtualizarActivity.this.startActivity(intent);
    }

    private void salvo() {
        ProdutoTabela produtoTabela = new ProdutoTabela(AtualizarActivity.this.getApplicationContext());
        produto = produtoTabela.lerProduto(value);

        id.setText(produto.id);
        nome.setText(produto.nome);
        quantidade.setText(produto.quantidade);
        preco.setText(produto.preco);
    }

    private void atualizar(){
        produto.nome = nome.getText().toString();
        produto.quantidade = quantidade.getText().toString();
        produto.preco = preco.getText().toString();
        ProdutoTabela produtoTabela = new ProdutoTabela(AtualizarActivity.this.getApplicationContext());
        produtoTabela.update(produto);
        salvo();
    }
}