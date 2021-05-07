package com.example.listadecontatos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import java.lang.Exception

class MainActivity : BaseActivity() {
    //main herda a baseactivity

    private var adapter: ContatoAdapter? = null
    //adapter faz o de -> para

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        geralListaDeContatos()
        setupToolBar(toolBar, "Lista de Contatos", false)
        setupRecyclerView()
        setupOnClicks()
    }

        private fun setupOnClick(){
            fab.setOnClickListener {onClickAdd()}
            ivBuscar.setOnClickListener{onClickBuscar()}

    }
    private fun setupRecyclerView(){
        recyclerView.LayoutManager = LinearLayoutManager(this)
        adapter = ContatoAdapter(this, ContatoSingleton.lista){onClickItemRecyclerView(it)}
        recyclerView.adapter = adapter
    }
    private fun geralListaDeContatos(){
        ContatosSingleton.lista.add(ContatosVO(1, "Fulano", "(00) 0000-0000"))
        ContatosSingleton.lista.add(ContatosVO(2, "Beltrano", "(00) 0000-0001"))
        ContatosSingleton.lista.add(ContatosVO(3, "Sicrano", "(00) 0000-0002"))
    }

    override fun onResume() {
        super.onResume()
        adapter?.notifyDataSetChanged()
    }
    private fun onClickAdd(){
        val intent = Intent(this, ContatoActivity::class.java)
        startActivity(intent)
    }
    private fun onClickItemRecyclerView(index: Int){
        val intent = Intent(this, ContatoActivity::class.java)
        intent.putExtra("index", inex)
        startActivity(intent)
    }
    private fun onClickBuscar(){
        val buscar = etBuscar.text.toString()
        var listaFiltrada: List<ContatosVO> = mutableListOf()

        //não necessariamente a chamada vai funcionar
        //mas toda chamada de banco de dados deve ser feita em try catch
        // mesmo que o kotlin não obrigue
        //pois pode estourar uma exceção quando for chama a instancia do banco
        //e se essa exceção não for tratada pode crachar o app
        try {
            listaFiltrada = ContatoApplication.instance.helperDB?.buscarContatos(busca)?: mutableListOf()

            }catch (ex: Exception){
                ex.printStackTrace()
        }

        adapter = ContatoAdapter(this, listaFiltrada){onClickItemRecyclerView(it)}
        recyclerView.adapter = adapter
        Toast.makeText(this, "Buscando por $busca",Toast.LENGTH_SHORT).show()
    }
}