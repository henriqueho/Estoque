package br.com.ho.estoque;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.ho.estoque.entidade.Produto;
import br.com.ho.estoque.repositorio.produto.ProdutoTabela;

public class SalvoActivity extends AppCompatActivity {

    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvo);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("EXTRA_SESSION_ID");
            //The key argument here must match that used in the other activity
        }

        Button salvar = findViewById(R.id.button_salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalvoActivity.this, SalvarActivity.class);
                SalvoActivity.this.startActivity(intent);
            }
        });

        /*
        ProdutoDbHelper mDbHelper = new ProdutoDbHelper(SalvarActivity.this.getApplicationContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                TbProduto.ProdutoEntry._ID,
                TbProduto.ProdutoEntry.COLUMN_NAME,
                TbProduto.ProdutoEntry.COLUMN_PRECO
        };

        String selection = TbProduto.ProdutoEntry.COLUMN_NAME + " = ?";
        String[] selectionArgs = { "Titulo do Post" };

        String sortOrder =
                TbProduto.ProdutoEntry.COLUMN_PRECO + " DESC";

        Cursor c = db.query(
                TbProduto.ProdutoEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        c.moveToFirst();
*/
       // c.getString(c.getColumnIndex(PostContract.PostEntry.COLUMN_NAME_TITLE));
        //long itemId = c.getLong(
        //        c.getColumnIndexOrThrow(PostContract.PostEntry._ID)
        //);

        //

        //c.getColumnName()

        TextView nome = (TextView) findViewById(R.id.nomeView);
        TextView quantidade = (TextView) findViewById(R.id.quantidadeView);
        TextView preco = (TextView) findViewById(R.id.precoView);
        /*RepositorioProdutoSharedPreference repositorioProdutoSharedPreference = new RepositorioProdutoSharedPreference(SalvarActivity.this.getApplicationContext());
        Produto produto = repositorioProdutoSharedPreference.lerProduto();
*/
        ProdutoTabela produtoTabela = new ProdutoTabela(SalvoActivity.this.getApplicationContext());
        Produto produto = produtoTabela.lerProduto(value);

        nome.setText(produto.nome);
        quantidade.setText(produto.quantidade);
        preco.setText(produto.preco);
    }
}
