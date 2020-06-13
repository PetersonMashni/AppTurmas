package br.com.petersonmashni.appturmas.Models;

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
