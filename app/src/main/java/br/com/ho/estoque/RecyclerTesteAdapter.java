package br.com.ho.estoque;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.ho.estoque.entidade.Produto;

public class RecyclerTesteAdapter extends RecyclerView.Adapter<RecyclerTesteAdapter.RecyclerProdutoViewHolder> {

    public static ClickRecyclerView_Interface clickRecyclerViewInterface;
    Context mctx;
    private List<Produto> mList;

    public RecyclerTesteAdapter(Context ctx, List<Produto> list, ClickRecyclerView_Interface clickRecyclerViewInterface) {
        this.mctx = ctx;
        this.mList = list;
        this.clickRecyclerViewInterface = clickRecyclerViewInterface;
    }

    @Override
    public RecyclerProdutoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemlist_itemdalista, viewGroup, false);
        return new RecyclerProdutoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerProdutoViewHolder viewHolder, int i) {
        Produto pessoa = mList.get(i);

        viewHolder.viewId.setText(pessoa.id);
        viewHolder.viewNome.setText(pessoa.nome);
        viewHolder.viewQuant.setText(pessoa.quantidade);
        viewHolder.viewPreco.setText(pessoa.preco);
        viewHolder.view.setBackgroundColor(ContextCompat.getColor(mctx,android.R.color.white));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    protected class RecyclerProdutoViewHolder extends RecyclerView.ViewHolder {

        protected TextView viewNome;
        protected TextView viewId;
        protected TextView viewQuant;
        protected TextView viewPreco;
        protected LinearLayout view;

        public RecyclerProdutoViewHolder(final View itemView) {
            super(itemView);

            viewNome = (TextView) itemView.findViewById(R.id.textview_nome);
            viewId = (TextView) itemView.findViewById(R.id.textview_id);
            viewQuant = (TextView) itemView.findViewById(R.id.textview_quat);
            viewPreco = (TextView) itemView.findViewById(R.id.textview_preco);
            view = itemView.findViewById(R.id.linear_itenslistas_background);

            //Setup the click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.setBackgroundColor(Color.parseColor("#9C9898"));
                    clickRecyclerViewInterface.onCustomClick(mList.get(getLayoutPosition()));

                }
            });


        }
    }
}
