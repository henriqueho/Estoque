package br.com.ho.estoque.entidade;

import android.provider.BaseColumns;

public final class TbProduto {
    private TbProduto() {}
    public static class ProdutoEntry implements BaseColumns {
        public static final String TABLE_NAME = "produto";
        public static final String COLUMN_ID = "codigo";
        public static final String COLUMN_NAME = "nome";
        public static final String COLUMN_QUANTIDADE = "quantidade";
        public static final String COLUMN_PRECO = "preco";
    }
}
