package com.example.atividadepam;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class PessoaDAO {

    private DB db;
    private SQLiteDatabase bd_cliente;

    public PessoaDAO(Context context){
        db = new DB(context);
        bd_cliente = db.getWritableDatabase();
    }
    public long inserir(Pessoa pessoa){
        ContentValues values = new ContentValues();
        values.put("email", pessoa.getEmail());
        values.put("senha", pessoa.getSenha());
        return bd_cliente.insert("pessoa", null, values);
    }
}

































