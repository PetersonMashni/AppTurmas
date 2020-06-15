package br.com.petersonmashni.appturmas.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.petersonmashni.appturmas.Models.Aluno;
import br.com.petersonmashni.appturmas.Models.TurmaAluno;

public class TurmaAlunoDAO {
    public static void inserir(Context context, TurmaAluno turmaAluno) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues valores = new ContentValues();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        valores.put("turma_id", turmaAluno.getTurma_id());
        valores.put("aluno_id", turmaAluno.getAluno_id());
        valores.put("data_matricula", dateFormat.format(turmaAluno.getData_matricula()));

        db.insert("turma_aluno", null, valores);
    }

    public static void editar(Context context, TurmaAluno turmaAluno) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues valores = new ContentValues();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        valores.put("turma_id", turmaAluno.getTurma_id());
        valores.put("aluno_id", turmaAluno.getAluno_id());
        valores.put("data_matricula", dateFormat.format(turmaAluno.getData_matricula()));

        db.update("turma_aluno", valores,
                " turma_id = " + turmaAluno.getTurma_id() + " and "+
                " aluno_id = " + turmaAluno.getAluno_id(), null);
    }

    public static void excluir(Context context, int turma_id, int aluno_id) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.execSQL("DELETE FROM turma_aluno WHERE aluno_id = " + aluno_id + " and turma_id = " + turma_id);
    }

    public static List<TurmaAluno> listar(Context context, String where) {
        List<TurmaAluno> lista = new ArrayList<TurmaAluno>();

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        String sqlWhere = where == null | where =="" ? "" : ("WHERE " + where);
        Cursor cursor = db.rawQuery("SELECT * FROM turma_aluno " + sqlWhere, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                lista.add(new TurmaAluno(cursor));
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public static TurmaAluno getTurmaAlunoById(Context context, int turma_id, int aluno_id) {
        List<TurmaAluno> lista = TurmaAlunoDAO.listar(context, "turma_id=" + turma_id + " and aluno_id = "+ aluno_id);

        if (lista.size() > 0)
            return lista.get(0);
        else
            return null;
    }

    public static int getCount(Context context, String where) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        String sqlWhere = where == null | where.isEmpty() ? "" : ("WHERE " + where);
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM turma_aluno " + sqlWhere, null);
        cursor.moveToFirst();

        return cursor.getInt(0);
    }


}
