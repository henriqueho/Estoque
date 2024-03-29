package br.com.ho.estoque;

import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
    private FloatingActionButton actionButton;
    private TextView numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        numero = findViewById(R.id.textNumero);
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
        Integer numImpressao = pessoasListas.size();
        if(numImpressao>0) {
            numero.setText(numImpressao + " itens");
        }
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
        actionButton = findViewById(R.id.floatingButtom);

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

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EstoqueActivity.this, SalvarActivity.class);
                EstoqueActivity.this.startActivity(intent);
            }
        });

    }
}

