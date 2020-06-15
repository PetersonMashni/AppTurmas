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

public class AlunoDAO {

    public static void inserir(Context context, Aluno aluno) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues valores = new ContentValues();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        valores.put("nome", aluno.getNome());
        valores.put("cpf", aluno.getCpf());
        valores.put("data_nascimento", dateFormat.format(aluno.getData_nascimento()));

        db.insert("aluno", null, valores);
    }

    public static void editar(Context context, Aluno aluno) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues valores = new ContentValues();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        valores.put("nome", aluno.getNome());
        valores.put("cpf", aluno.getCpf());
        valores.put("data_nascimento", dateFormat.format(aluno.getData_nascimento()));

        db.update("aluno", valores, " aluno_id = " + aluno.getAluno_id(), null);
    }

    public static void excluir(Context context, int aluno_id) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.execSQL("DELETE FROM aluno WHERE aluno_id = " + aluno_id);
    }

    public static List<Aluno> listar(Context context, String where) {
        List<Aluno> lista = new ArrayList<Aluno>();

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        String sqlWhere = where == null | where =="" ? "" : ("WHERE " + where);
        Cursor cursor = db.rawQuery("SELECT * FROM aluno " + sqlWhere + " ORDER BY nome", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                lista.add(new Aluno(cursor));
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public static Aluno getAlunoById(Context context, int aluno_id) {
        List<Aluno> lista = AlunoDAO.listar(context, "aluno_id=" + aluno_id);

        if (lista.size() > 0)
            return lista.get(0);
        else
            return null;
    }

    public static int getCount(Context context, String where) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        String sqlWhere = where == null | where.isEmpty() ? "" : ("WHERE " + where);
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM aluno " + sqlWhere, null);
        cursor.moveToFirst();

        return cursor.getInt(0);
    }

}
