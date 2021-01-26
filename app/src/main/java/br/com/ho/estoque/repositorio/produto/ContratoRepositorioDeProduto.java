package br.com.ho.estoque.repositorio.produto;

import br.com.ho.estoque.entidade.Produto;

public interface ContratoRepositorioDeProduto {

    public void criaProduto(Produto produto);

    public Produto lerProduto();

    public Produto lerProduto(String codigo);
}
