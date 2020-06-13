package br.com.petersonmashni.appturmas.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.petersonmashni.appturmas.Models.Turma;

public class TurmaDAO {

    public static void inserir(Context context, Turma turma) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("nome", turma.getNome());
        valores.put("sala", turma.getSala());

        db.insert("turma", null, valores);
    }

    public static void editar(Context context, Turma turma) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("nome", turma.getNome());
        valores.put("sala", turma.getSala());

        db.update("turma", valores, " turma_id = " + turma.getTurma_id(), null);
    }

    public static void excluir(Context context, int turma_id) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.execSQL("DELETE FROM turma WHERE turma_id = " + turma_id);
    }

    public static List<Turma> listar(Context context, String where) {
        List<Turma> lista = new ArrayList<Turma>();

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        String sqlWhere = where == null ? "" : ("WHERE " + where);
        Cursor cursor = db.rawQuery("SELECT * FROM turma " + sqlWhere + " ORDER BY nome", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                lista.add(new Turma(cursor));
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public static Turma getTurmaById(Context context, int turma_id) {
        List<Turma> lista = TurmaDAO.listar(context, "turma_id=" + turma_id);

        if (lista.size() > 0)
            return lista.get(0);
        else
            return null;
    }

    public static int getCount(Context context, String where) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        String sqlWhere = where == null ? "" : ("WHERE " + where);
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM turma " + sqlWhere, null);

        return cursor.getInt(0);
    }

}
