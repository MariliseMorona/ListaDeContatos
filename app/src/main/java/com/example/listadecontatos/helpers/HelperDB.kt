package com.example.listadecontatos.helpers

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HelperDB(
    context: Context
    ): SQLiteOpenHelper(context, NOME_BANCO, null, VERSAO_ATUAL) {

    companion object{
        private val NOME_BANCO = "contato.db"
        private val VERSAO_ATUAL = 1
    }

    val TABLE_NAME = "contato"
    val COLUMNS_ID = "id"
    val COLUMNS_NOME = "nome"
    val COLUMNS_TELEFONE = "telefone"
    val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
            "$COLUMNS_ID INTEGER NOT NULL, " +
            "$COLUMNS_NOME INTEGER NOT NULL, " +
            "$COLUMNS_TELEFONE INTEGER NOT NULL, " +
            "" +
            "PRIMARY KEY($COLUMNS_ID AUTOINCREMENT)" +
            ")"

    //CRIA A INSTANCIA DO BANCO DE DADOS, E INSTANCIA E AMNAZENA AS INFORMAÇÕES
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }
    //REPARO NAS VERSÕES
    //upgrade da tabela ou criar uma nova tabela
    //mantem a integridade do app
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion != newVersion){
            db?.execSQL(DROP_TABLE)


        }
        onCreate(db)
    }
    fun buscarContatos(busca: String): List<ContatosVO>{
        val db = readableDatabase ?: return mutableListOf()
        var lista = mutableListOf<ContatosVO>()
        //db.query()
        val sql = "SELECT * FROM $TABLE_NAME"

        val cursor: Cursor = db.rawQuery(sql, arrayOf())?: return mutableListOf()
        while(cursor.moveToNext()){
            var contato = ContatosVO(
                cursor.getInt(cursor.getColumnIndex(COLUMNS_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMNS_NOME)),
                cursor.getString(cursor.getColumnIndex(COLUMNS_TELEFONE))
            )
            lista.add(contato)
        }
        return lista
    }
}