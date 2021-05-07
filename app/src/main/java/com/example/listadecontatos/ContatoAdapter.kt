package com.example.listadecontatos

import android.content.Context
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContatoAdapter (
    private val context : Context,
            private val lista : List<ContatosVD>,
                    private val onClick : ((Int) -> Unit)
) : RecyclerView.Adapter<ContatoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_contato, parent, false)
        return ContatoViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    }
override fun onBindViewHolder(holder: ContatoViewHolder, position: Int){
    val contato = lista[position]
    //holder.itemView.tvNome
    with(holder.itemView){
        tvNome.text = contato.nome
        tvTelefone.text = contato,telefone
        llItem.setOnClickListener{onClick(position)}
    }
}
}
        class ContatoViewHolder(itemView: View): RecyclerView,RecyclerView.ViewHolder(itemView)