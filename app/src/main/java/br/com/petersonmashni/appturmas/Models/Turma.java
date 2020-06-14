package br.com.petersonmashni.appturmas.Models;

import android.database.Cursor;

import java.util.ArrayList;

public class Turma {
    private int turma_id;
    private String nome;
    private int sala;
    private int ativa;

    private ArrayList<TurmaAluno> turmaAlunos;

    public Turma(){

    }

    public Turma(Cursor cursor)
    {
        this.setTurma_id( cursor.getInt(0) );
        this.setNome( cursor.getString(1) );
        this.setSala( cursor.getInt(2) );
        this.setAtiva( cursor.getInt(3) );
    }

    public Turma(String nome, int sala, int ativa){
        this.nome = nome;
        this.sala = sala;
        this.ativa = ativa;
        this.turmaAlunos = new ArrayList<>();
    }

    public int getTurma_id() {
        return turma_id;
    }

    public void setTurma_id(int turma_id) {
        this.turma_id = turma_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    public int getAtiva() {
        return ativa;
    }

    public void setAtiva(int ativa) {
        this.ativa = ativa;
    }
}
