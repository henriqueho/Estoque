package br.com.ho.estoque;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.ho.estoque.entidade.Produto;
import br.com.ho.estoque.repositorio.produto.shared_preference.RepositorioProdutoSharedPreference;

public class salvarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvar);

        TextView nome = (TextView) findViewById(R.id.nomeView);

        RepositorioProdutoSharedPreference repositorioProdutoSharedPreference = new RepositorioProdutoSharedPreference(salvarActivity.this.getApplicationContext());
        Produto produto = repositorioProdutoSharedPreference.lerProduto();
        nome.setText(produto.nome);
    }
}
