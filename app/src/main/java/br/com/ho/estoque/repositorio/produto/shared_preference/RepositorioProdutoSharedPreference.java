package br.com.ho.estoque.repositorio.produto.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.ho.estoque.entidade.Produto;
import br.com.ho.estoque.repositorio.produto.ContratoRepositorioDeProduto;

public class RepositorioProdutoSharedPreference implements ContratoRepositorioDeProduto {

    SharedPreferences saved = null;

    public RepositorioProdutoSharedPreference(Context context) {
        saved = context.getSharedPreferences("saved", Context.MODE_PRIVATE);
    }

    @Override
    public void criaProduto(Produto produto) {
        SharedPreferences.Editor edit = saved.edit();
        edit.putString("nome",produto.nome);
        edit.apply();
    }

    @Override
    public Produto lerProduto() {
        Produto produto = new Produto();
        produto.nome = saved.getString("nome", "");
        return produto;
    }

}
