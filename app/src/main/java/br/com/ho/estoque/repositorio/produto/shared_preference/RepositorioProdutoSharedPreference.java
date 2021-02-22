package br.com.ho.estoque.repositorio.produto.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.ho.estoque.R;
import br.com.ho.estoque.entidade.Produto;
import br.com.ho.estoque.repositorio.produto.ContratoRepositorioDeProduto;

public class RepositorioProdutoSharedPreference implements ContratoRepositorioDeProduto {

    SharedPreferences sharedPreferences = null;

    public RepositorioProdutoSharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences("saved", Context.MODE_PRIVATE);
    }

    @Override
    public void criaProduto(Produto produto) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("nome",produto.nome);
        edit.putString("quantidade",produto.quantidade);
        edit.putString("preco",produto.preco);
        edit.putString("imagem",produto.imagem);
        edit.apply();
    }

    @Override
    public Produto lerProduto() {
        Produto produto = new Produto();
        produto.nome = sharedPreferences.getString("nome", "");
        produto.quantidade = sharedPreferences.getString("quantidade", "");
        produto.preco = sharedPreferences.getString("preco", "");
        produto.imagem = sharedPreferences.getString("imagem", "");
        return produto;
    }

    @Override
    public Produto lerProduto(String codigo) {
        return null;
    }

}
