package br.com.ho.estoque;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.ho.estoque.entidade.Produto;
import br.com.ho.estoque.repositorio.produto.shared_preference.RepositorioProdutoSharedPreference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nome = (EditText) findViewById(R.id.nomeEdit);
        EditText quantidade = (EditText) findViewById(R.id.quantidadeEdit);
        EditText preco = (EditText) findViewById(R.id.precoEdit);

        Button salvar = (Button) findViewById(R.id.salvarButton);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produto produto = new Produto();
                produto.nome = nome.getText().toString();
                RepositorioProdutoSharedPreference repositorioProdutoSharedPreference = new RepositorioProdutoSharedPreference(MainActivity.this.getApplicationContext());
                repositorioProdutoSharedPreference.criaProduto(produto);
            }
        });
    }
}
