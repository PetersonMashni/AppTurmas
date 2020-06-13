package br.com.petersonmashni.appturmas.Models;

import java.util.ArrayList;

public class Turma {
    private int turma_id;
    private String nome;
    private int sala;
    private ArrayList<TurmaAluno> turmaAlunos;

    public Turma(String nome, int sala){
        this.nome = nome;
        this.sala = sala;
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
}
