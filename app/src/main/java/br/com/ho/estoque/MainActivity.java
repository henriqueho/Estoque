package br.com.ho.estoque;

import android.content.Intent;
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
        final EditText quantidade = (EditText) findViewById(R.id.quantidadeEdit);
        final EditText preco = (EditText) findViewById(R.id.precoEdit);

        Button salvar = (Button) findViewById(R.id.salvarButton);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produto produto = new Produto();
                produto.nome = nome.getText().toString();
                produto.quantidade = quantidade.getText().toString();
                produto.preco = preco.getText().toString();

                boolean flag = false;

                if(produto.nome.isEmpty()) {
                    nome.setError("o nome do produto se encontra vazio");
                    flag = false;
                } else {
                    nome.setError(null);
                    flag = true;
                }

                if (produto.quantidade.isEmpty()) {
                    quantidade.setError("a quantidade se encontra vazia");
                    flag = false;
                } else {
                    quantidade.setError(null);
                    flag = true;
                }

                if (produto.preco.isEmpty()) {
                    preco.setError("o preço se encotra vazio");
                    flag = false;
                } else {
                    preco.setError(null);
                    flag = true;
                }

                if (flag == true) {
                    RepositorioProdutoSharedPreference repositorioProdutoSharedPreference = new RepositorioProdutoSharedPreference(MainActivity.this.getApplicationContext());
                    repositorioProdutoSharedPreference.criaProduto(produto);

                    Intent intent = new Intent(MainActivity.this, salvarActivity.class);
                    MainActivity.this.startActivity(intent);
                }
            }
        });
    }
}
