package br.com.ho.estoque;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.ho.estoque.entidade.Produto;
import br.com.ho.estoque.repositorio.produto.ProdutoTabela;

public class EstoqueActivity extends AppCompatActivity implements ClickRecyclerView_Interface{

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerTesteAdapter adapter;
    private List<Produto> pessoasListas = new ArrayList<>();
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        setaRecyclerView();

        setaButtons();
        listenersButtons();

    }

    public void setaRecyclerView(){

        //Aqui é instanciado o Recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_recyclerteste);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
/*

        ProdutoTabela produtoTabela = new ProdutoTabela(EstoqueActivity.this.getApplicationContext());
        pessoasListas = produtoTabela.preencher();
        adapter = new RecyclerTesteAdapter(this, pessoasListas, this);
        mRecyclerView.setAdapter(adapter);

 */
    }

    private void carregarRecycler(){
        ProdutoTabela produtoTabela = new ProdutoTabela(EstoqueActivity.this.getApplicationContext());
        pessoasListas = produtoTabela.preencher();
        adapter = new RecyclerTesteAdapter(this, pessoasListas, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarRecycler();
    }

    public void setaButtons(){

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_fabteste);

    }

    /**
     * Aqui é o método onde trata o clique em um item da lista
     */
    @Override
    public void onCustomClick(Produto produto) {

        Intent intent = new Intent(EstoqueActivity.this, AtualizarActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", produto.id);
        EstoqueActivity.this.startActivity(intent);
    }

    /**
     * Chama os listeners para os botões
     */
    public void listenersButtons() {

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Cria uma nova pessoa chamada Renan Teles
                Produto pessoa1 = new Produto();
                pessoa1.nome ="Renan Teles";

                //Adiciona a pessoa1 e avisa o adapter que o conteúdo
                //da lista foi alterado
                pessoasListas.add(pessoa1);
                adapter.notifyDataSetChanged();

            }
        });
    }
}

