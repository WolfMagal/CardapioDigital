package com.fiap.cardapiodigital.ui.produtocardapio.adapter

import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fiap.cardapiodigital.domain.entities.ProdutoCardapioEntity
import kotlinx.android.synthetic.main.item_produto_list.view.*

class ProductListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(produto: ProdutoCardapioEntity) {
        itemView.descricaoProduto.text = produto.descricao
        itemView.valorProduto.text = produto.valor.toString()

    }


}