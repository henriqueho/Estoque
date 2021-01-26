package br.com.ho.estoque.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.ho.estoque.entidade.TbProduto;

public class ProdutoDbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_PRODUTO =
            "CREATE TABLE " + TbProduto.ProdutoEntry.TABLE_NAME + " (" +
                    //TbProduto.ProdutoEntry._ID + " INTEGER PRIMARY KEY," +
                    TbProduto.ProdutoEntry.COLUMN_ID +TEXT_TYPE+ " NOT NULL PRIMARY KEY" + COMMA_SEP +
                    TbProduto.ProdutoEntry.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    TbProduto.ProdutoEntry.COLUMN_QUANTIDADE + TEXT_TYPE + COMMA_SEP +
                    TbProduto.ProdutoEntry.COLUMN_PRECO + TEXT_TYPE + " )";

    private static final String SQL_DELETE_PRODUTO =
            "DROP TABLE IF EXISTS " + TbProduto.ProdutoEntry.TABLE_NAME;

    private static final String SQL_UPDATE_PRODUTO =
            "UPDATE " + TbProduto.ProdutoEntry.TABLE_NAME +
            " SET " + TbProduto.ProdutoEntry.COLUMN_NAME + " = " +
            " WHERE " + TbProduto.ProdutoEntry.COLUMN_ID + " = ";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public ProdutoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PRODUTO);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PRODUTO);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /*MÉTODO QUE VAMOS USAR NA CLASSE QUE VAI EXECUTAR AS ROTINAS NO
    BANCO DE DADOS*/
    public SQLiteDatabase GetConexaoDataBase(){

        return this.getWritableDatabase();
    }
}
