package br.com.ho.estoque;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.ho.estoque.entidade.Produto;
import br.com.ho.estoque.repositorio.produto.shared_preference.RepositorioProdutoSharedPreference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mViewHolder.nome = findViewById(R.id.nomeEdit);
        this.mViewHolder.quantidade = findViewById(R.id.quantidadeEdit);
        this.mViewHolder.preco = findViewById(R.id.precoEdit);
        this.mViewHolder.salvar = findViewById(R.id.salvarButton);

        this.mViewHolder.salvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(view.getId()==R.id.salvarButton){
            Produto produto = new Produto();
            produto.nome = this.mViewHolder.nome.getText().toString();
            produto.quantidade = mViewHolder.quantidade.getText().toString();
            produto.preco = mViewHolder.preco.getText().toString();

            boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;

            if(produto.nome.isEmpty()) {
                this.mViewHolder.nome.setError("o nome do produto se encontra vazio");
                flag1 = false;
            } else {
                mViewHolder.nome.setError(null);
                flag1 = true;
            }

            if (produto.quantidade.isEmpty()) {
                mViewHolder.quantidade.setError("a quantidade se encontra vazia");
                flag2 = false;
            } else {
                mViewHolder.quantidade.setError(null);
                flag2 = true;
            }

            if (produto.preco.isEmpty()) {
                mViewHolder.preco.setError("o preço se encotra vazio");
                flag3 = false;
            } else {
                mViewHolder.preco.setError(null);
                flag3 = true;
            }

            flag = flag1&&flag2&&flag3;

            if (flag == true) {
                RepositorioProdutoSharedPreference repositorioProdutoSharedPreference = new RepositorioProdutoSharedPreference(MainActivity.this.getApplicationContext());
                repositorioProdutoSharedPreference.criaProduto(produto);

                Intent intent = new Intent(MainActivity.this, salvarActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }
    }

    private static class ViewHolder {
        EditText nome;
        EditText quantidade;
        EditText preco;
        Button salvar;
    }
}
