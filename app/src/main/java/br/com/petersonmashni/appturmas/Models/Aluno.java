package br.com.petersonmashni.appturmas.Models;

import android.database.Cursor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Aluno {
    private int aluno_id;
    private String nome;
    private String cpf;
    private Date data_nascimento;

    public Aluno(String nome, String cpf, Date data_nascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
    }

    public Aluno ()
    {
    }

    public Aluno(Cursor cursor) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date data_nascimento = null;

        try {
            data_nascimento = dateFormat.parse(cursor.getString(3));
        } catch (ParseException ex) {
        }

        this.setAluno_id(cursor.getInt(0));
        this.setNome(cursor.getString(2));
        this.setCpf(cursor.getString(1));
        this.setData_nascimento(data_nascimento);
    }

    public int getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(int aluno_id) {
        this.aluno_id = aluno_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
}
