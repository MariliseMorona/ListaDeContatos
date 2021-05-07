package com.example.listadecontatos

import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.listadecontatos.R

class BaseActivity: AppCompatActivity() {
    protected fun setupToolBar(toolBar: Toolbar, title: String, navegationBack: Boolean){
        //navwsgationBack botao voltar
        toolBar.title = title
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(navegationBack)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.home -> {
                this.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}