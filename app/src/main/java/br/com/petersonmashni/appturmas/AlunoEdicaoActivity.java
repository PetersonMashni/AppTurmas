package br.com.petersonmashni.appturmas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.petersonmashni.appturmas.DAO.AlunoDAO;
import br.com.petersonmashni.appturmas.Models.Aluno;

public class AlunoEdicaoActivity extends AppCompatActivity {
    private Aluno aluno;
    private TextView tvAluno_id;
    private EditText etNome, etCpf, etDataNascimento;
    private Button btSalvar;
    private String acao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_edicao);

        tvAluno_id = (TextView) findViewById(R.id.aluno_edicao_tvAluno_id);
        etNome = (EditText) findViewById(R.id.aluno_edicao_etNome);
        etCpf = (EditText) findViewById(R.id.aluno_edicao_etCpf);
        etDataNascimento = (EditText) findViewById(R.id.aluno_edicao_etDataNascimento);

        btSalvar = (Button) findViewById(R.id.aluno_edicao_btSalvar);

        acao = getIntent().getStringExtra("acao");

        if (acao.equals("editar")) {
            carregarFormulario();
        }

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

    }

    private void carregarFormulario() {
        int aluno_id = getIntent().getIntExtra("aluno_id", 0);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        aluno = AlunoDAO.getAlunoById(this, aluno_id);

        tvAluno_id.setText(Integer.toString(aluno.getAluno_id()));
        etNome.setText(aluno.getNome());
        etCpf.setText(aluno.getCpf());
        etDataNascimento.setText(dateFormat.format(aluno.getData_nascimento()));
    }

    private void limpar() {
        aluno = null;

        tvAluno_id.setText("");
        etNome.setText("");
        etCpf.setText("");
        etDataNascimento.setText("");
    }

    private void salvar() {
        if (acao.equals("inserir")) {
            aluno = new Aluno();
        }

        String nome = etNome.getText().toString();
        String cpf = etCpf.getText().toString();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data_nascimento = null;

        try {
            data_nascimento = dateFormat.parse(etDataNascimento.getText().toString());
        } catch (ParseException ex) {
            Toast.makeText(this, getString(R.string.aluno_edicao_valida_data_nascimento), Toast.LENGTH_LONG).show();
            return;
        }

        if (nome.isEmpty()) {
            Toast.makeText(this, getString(R.string.aluno_edicao_valida_nome), Toast.LENGTH_LONG).show();
        } else if (cpf.isEmpty()) {
            Toast.makeText(this, getString(R.string.aluno_edicao_valida_cpf), Toast.LENGTH_LONG).show();
        } else {
            aluno.setNome(nome);
            aluno.setCpf(cpf);
            aluno.setData_nascimento(data_nascimento);

            if (acao.equals("inserir")) {
                AlunoDAO.inserir(this, aluno);
                limpar();
            } else {
                AlunoDAO.editar(this, aluno);
                finish();
            }
        }
    }

}
