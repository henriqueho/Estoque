package br.com.ho.estoque.repositorio.produto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.ho.estoque.repositorio.ProdutoDbHelper;
import br.com.ho.estoque.entidade.Produto;
import br.com.ho.estoque.entidade.TbProduto;

public class ProdutoTabela implements ContratoRepositorioDeProduto {

    ProdutoDbHelper databaseUtil;

    private static final String ASPA_SIMPLES = "'";

    public ProdutoTabela(Context context){

        databaseUtil =  new ProdutoDbHelper(context);

    }

    @Override
    public void criaProduto(Produto produto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbProduto.ProdutoEntry.COLUMN_ID, produto.id);
        contentValues.put(TbProduto.ProdutoEntry.COLUMN_NAME, produto.nome);
        contentValues.put(TbProduto.ProdutoEntry.COLUMN_QUANTIDADE, produto.quantidade);
        contentValues.put(TbProduto.ProdutoEntry.COLUMN_PRECO, produto.preco);

        databaseUtil.getWritableDatabase().insert(TbProduto.ProdutoEntry.TABLE_NAME,null, contentValues);
    }

    @Override
    public Produto lerProduto() {
        SQLiteDatabase db = databaseUtil.getReadableDatabase();

        String[] projection = {
                TbProduto.ProdutoEntry.COLUMN_ID,
                TbProduto.ProdutoEntry.COLUMN_NAME,
                TbProduto.ProdutoEntry.COLUMN_QUANTIDADE,
                TbProduto.ProdutoEntry.COLUMN_PRECO
        };

        String selection = TbProduto.ProdutoEntry.COLUMN_NAME + " = ?";
        String[] selectionArgs = {"Titulo do Post"};

        String sortOrder = TbProduto.ProdutoEntry.COLUMN_NAME + " DESC";

        Cursor c = db.query(
                TbProduto.ProdutoEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);

        c.moveToFirst();
        Produto produto = new Produto();
        //while (!c.isAfterLast()){
            produto.nome = c.getString(c.getColumnIndexOrThrow(TbProduto.ProdutoEntry.COLUMN_NAME));
            produto.quantidade = c.getString(c.getColumnIndexOrThrow(TbProduto.ProdutoEntry.COLUMN_QUANTIDADE));
            produto.preco = c.getString(c.getColumnIndexOrThrow(TbProduto.ProdutoEntry.COLUMN_PRECO));
        //}
        return produto;
    }

    @Override
    public Produto lerProduto(String codigo) {

        Cursor cursor = databaseUtil.getWritableDatabase().rawQuery("SELECT * FROM " + TbProduto.ProdutoEntry.TABLE_NAME + " WHERE " + TbProduto.ProdutoEntry.COLUMN_ID + " = " + ASPA_SIMPLES + codigo + ASPA_SIMPLES, null);
        cursor.moveToFirst();
        Produto produto = new Produto();
        produto.id = cursor.getString(cursor.getColumnIndex(TbProduto.ProdutoEntry.COLUMN_ID));
        produto.nome = cursor.getString(cursor.getColumnIndex(TbProduto.ProdutoEntry.COLUMN_NAME));
        produto.quantidade = cursor.getString(cursor.getColumnIndex(TbProduto.ProdutoEntry.COLUMN_QUANTIDADE));
        produto.preco = cursor.getString(cursor.getColumnIndex(TbProduto.ProdutoEntry.COLUMN_PRECO));
        cursor.isClosed();
        return produto;

    }
    public List<Produto> preencher(){
        List<Produto> produtoList = new ArrayList<>();

        Cursor cursor = databaseUtil.getWritableDatabase().rawQuery("SELECT * FROM " + TbProduto.ProdutoEntry.TABLE_NAME, null);
        cursor.moveToFirst();

        Produto produto;
        while (!cursor.isAfterLast()){
            produto = new Produto();
            produto.id = cursor.getString(cursor.getColumnIndexOrThrow(TbProduto.ProdutoEntry.COLUMN_ID));
            produto.nome = cursor.getString(cursor.getColumnIndexOrThrow(TbProduto.ProdutoEntry.COLUMN_NAME));
            produto.quantidade = cursor.getString(cursor.getColumnIndexOrThrow(TbProduto.ProdutoEntry.COLUMN_QUANTIDADE));
            produto.preco = cursor.getString(cursor.getColumnIndexOrThrow(TbProduto.ProdutoEntry.COLUMN_PRECO));

            produtoList.add(produto);

            cursor.moveToNext();
        }

        return produtoList;
    }

    public int lerRepetido(String codigo){
        Cursor cursor = databaseUtil.getWritableDatabase().rawQuery("SELECT * FROM " + TbProduto.ProdutoEntry.TABLE_NAME + " WHERE " + TbProduto.ProdutoEntry.COLUMN_ID + " = " + ASPA_SIMPLES + codigo + ASPA_SIMPLES, null);
        cursor.moveToFirst();
        int x= 0;
        x = cursor.getCount();
        cursor.isClosed();
        return x;
    }

    public void update(Produto produto) {
        SQLiteDatabase db = databaseUtil.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(TbProduto.ProdutoEntry.COLUMN_NAME, produto.nome);
        values.put(TbProduto.ProdutoEntry.COLUMN_QUANTIDADE, produto.quantidade);
        values.put(TbProduto.ProdutoEntry.COLUMN_PRECO, produto.preco);

        String selection = TbProduto.ProdutoEntry.COLUMN_ID + " = ?";
        String[] selectionArgs = { produto.id };

        int count = db.update(
                TbProduto.ProdutoEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public void deletar(String code){
        String selection = TbProduto.ProdutoEntry.COLUMN_ID + " = ?";
        String[] selectionArgs = { code };
        SQLiteDatabase db = databaseUtil.getReadableDatabase();
        db.delete(TbProduto.ProdutoEntry.TABLE_NAME,selection,selectionArgs);
    }
}
