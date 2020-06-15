package br.com.petersonmashni.appturmas.Models;

import android.database.Cursor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TurmaAluno {
    private int turma_id;
    private int aluno_id;
    private Date data_matricula;
    private Aluno aluno;

    public TurmaAluno(int turma_id, int aluno_id, Date data_matricula) {
        this.turma_id = turma_id;
        this.aluno_id = aluno_id;
        this.data_matricula = data_matricula;
    }

    public TurmaAluno(Cursor cursor)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data_matricula = null;

        try {
            data_matricula = dateFormat.parse(cursor.getString(2));
        } catch (ParseException ex) {
        }

        this.setTurma_id( cursor.getInt(0) );
        this.setAluno_id( cursor.getInt(1) );
        this.setData_matricula( data_matricula );
    }


    public int getTurma_id() {
        return turma_id;
    }

    public void setTurma_id(int turma_id) {
        this.turma_id = turma_id;
    }

    public int getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(int aluno_id) {
        this.aluno_id = aluno_id;
    }

    public Date getData_matricula() {
        return data_matricula;
    }

    public void setData_matricula(Date data_matricula) {
        this.data_matricula = data_matricula;
    }
}
