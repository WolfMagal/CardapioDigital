package com.fiap.cardapiodigital.ui.produtocardapio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiap.cardapiodigital.R
import com.fiap.cardapiodigital.databinding.ActivityProdutoCardapioBinding
import com.fiap.cardapiodigital.domain.entities.ProdutoCardapioEntity
import com.fiap.cardapiodigital.domain.helpers.TiposRestauranteEnum
import com.fiap.cardapiodigital.ui.produtocardapio.adapter.ProductListAdapter
import com.fiap.cardapiodigital.viewModel.produtocardapio.ProdutoCardapioContract
import com.fiap.cardapiodigital.viewModel.produtocardapio.ProdutoCardapioViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_produto_cardapio.*
import kotlinx.android.synthetic.main.activity_produto_cardapio.lista
import kotlinx.android.synthetic.main.activity_restaurantes.*
import org.koin.core.parameter.parametersOf
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProdutoCardapioActivity : AppCompatActivity(), ProdutoCardapioContract{

    var adapter = ProductListAdapter(arrayListOf(),{})
    var listaProdutos = arrayListOf<ProdutoCardapioEntity>()

    private lateinit var binding: ActivityProdutoCardapioBinding
    private val viewModel: ProdutoCardapioViewModel by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_produto_cardapio)
        binding.viewModel = viewModel

        viewModel.listaProdutosSelecionados = arrayListOf()

        val tipoRestaurante= TiposRestauranteEnum.valueOf(intent.extras?.get("tipoRestaurante").toString())
        viewModel.listarProdutosCardapioPorTipo(tipoRestaurante)
        carregaListaViewModel()
    }

    private fun carregaListaViewModel(){

        viewModel.produtos.observe(this, Observer {
            adapter = ProductListAdapter(it){item ->
                viewModel.listaProdutosSelecionados.add(item)
                Toast.makeText(this, "Item adicionado ao pedido", Toast.LENGTH_SHORT).show()
            }

                adapter.notifyDataSetChanged()
                lista.adapter = adapter
                lista.layoutManager = LinearLayoutManager(this)

        })



    }



    override fun goToMainActivity() {
        TODO("Not yet implemented")
    }
}