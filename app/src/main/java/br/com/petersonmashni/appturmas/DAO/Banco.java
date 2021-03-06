package br.com.petersonmashni.appturmas.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Banco extends SQLiteOpenHelper {

    private static final String NOME = "AppTurmas";
    private static final int VERSAO = 1;

    public Banco(Context contexto) {
        super(contexto, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS turma ( " +
                "  turma_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "  nome TEXT, " +
                "  sala INTEGER, " +
                "  ativa INTEGER ) ");

        db.execSQL("CREATE TABLE IF NOT EXISTS aluno ( " +
                "  aluno_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "  cpf TEXT, " +
                "  nome TEXT," +
                "  data_nascimento TEXT ) ");

        db.execSQL("CREATE TABLE IF NOT EXISTS turma_aluno ( " +
                "  turma_id INTEGER NOT NULL, " +
                "  aluno_id INTEGER NOT NULL, " +
                "  data_matricula TEXT," +
                "  PRIMARY KEY(turma_id, aluno_id) ) ");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}