package br.com.ho.estoque;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.ho.estoque.entidade.Produto;
import br.com.ho.estoque.repositorio.produto.ProdutoTabela;

public class SalvarActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvar);
        this.mViewHolder.codigo = findViewById(R.id.codigo_edit);
        this.mViewHolder.nome = findViewById(R.id.nomeEdit);
        this.mViewHolder.quantidade = findViewById(R.id.quantidadeEdit);
        this.mViewHolder.preco = findViewById(R.id.precoEdit);
        this.mViewHolder.salvar = findViewById(R.id.salvarButton);

        this.mViewHolder.salvar.setOnClickListener(this);
        /*
        ProdutoTabela produtoTabela = new ProdutoTabela(MainActivity.this.getApplicationContext());
        Produto produto = produtoTabela.lerProduto();

        this.mViewHolder.nome.setText(produto.nome);
*/
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.salvarButton){
            Produto produto = new Produto();
            produto.id = mViewHolder.codigo.getText().toString();
            produto.nome = this.mViewHolder.nome.getText().toString();
            produto.quantidade = mViewHolder.quantidade.getText().toString();
            produto.preco = mViewHolder.preco.getText().toString();

            boolean flag4 = false;
            boolean flag = false;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;

            if(produto.id.isEmpty()) {
                this.mViewHolder.codigo.setError("o código do produto se encontra vazio");
                flag4 = false;
            } else {
                mViewHolder.codigo.setError(null);
                flag4 = true;
            }

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

            flag = flag1&&flag2&&flag3&&flag4;

            if (flag == true) {
                /*
                RepositorioProdutoSharedPreference repositorioProdutoSharedPreference = new RepositorioProdutoSharedPreference(MainActivity.this.getApplicationContext());
                repositorioProdutoSharedPreference.criaProduto(produto);
                */

                //ProdutoTabela produtoTabela = new ProdutoTabela(MainActivity.this.getApplicationContext());
                //produtoTabela.criaProduto(produto);

                /*ProdutoDbHelper mDbHelper = new ProdutoDbHelper(MainActivity.this.getApplicationContext());
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(TbProduto.ProdutoEntry.COLUMN_NAME_TITLE, "Titulo do Post");
                values.put(TbProduto.ProdutoEntry.COLUMN_NAME_SUBTITLE, "Subtitulo do Post");

                long newRowId = db.insert(TbProduto.ProdutoEntry.TABLE_NAME, null, values);

                 */
                ProdutoTabela produtoTabela = new ProdutoTabela(SalvarActivity.this.getApplicationContext());
                int x = produtoTabela.lerRepetido(produto.id);

                if (x==0) {
                    produtoTabela = new ProdutoTabela(SalvarActivity.this.getApplicationContext());
                    produtoTabela.criaProduto(produto);

                    Intent intent = new Intent(SalvarActivity.this, SalvoActivity.class);
                    intent.putExtra("EXTRA_SESSION_ID", produto.id);
                    SalvarActivity.this.startActivity(intent);
                } else {
                    this.mViewHolder.codigo.setError("o código do produto é repetido");
                }
            }
        }
    }

    private static class ViewHolder {
        EditText codigo;
        EditText nome;
        EditText quantidade;
        EditText preco;
        Button salvar;
    }
}
