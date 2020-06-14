package br.com.petersonmashni.appturmas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import br.com.petersonmashni.appturmas.DAO.TurmaDAO;
import br.com.petersonmashni.appturmas.Models.Turma;

public class TurmaEdicaoActivity extends AppCompatActivity {
    private Turma turma;
    private TextView tvTurma_id;
    private EditText etNome, etSala;
    private Switch swAtiva;
    private Button btSalvar;
    private String acao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turma_edicao);

        tvTurma_id = (TextView) findViewById(R.id.turma_edicao_tvTurma_id);
        etNome = (EditText) findViewById(R.id.turma_edicao_etNome);
        etSala = (EditText) findViewById(R.id.turma_edicao_etSala);
        swAtiva = (Switch) findViewById(R.id.turma_edicao_swAtiva);
        btSalvar = (Button) findViewById(R.id.turma_edicao_btSalvar);

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
        int turma_id = getIntent().getIntExtra("turma_id", 0);
        turma = TurmaDAO.getTurmaById(this, turma_id);

        tvTurma_id.setText(Integer.toString(turma.getTurma_id()));
        etNome.setText(turma.getNome());
        etSala.setText(Integer.toString(turma.getSala()));
        swAtiva.setChecked(turma.getAtiva() == 1);

    }

    private void limpar() {
        turma = null;

        tvTurma_id.setText("");
        etNome.setText("");
        etSala.setText("");
        swAtiva.setChecked(false);
    }

    private void salvar() {
        if (acao.equals("inserir")) {
            turma = new Turma();
        }

        String nome = etNome.getText().toString();
        String sala = etSala.getText().toString();

        if (nome.isEmpty()) {
            Toast.makeText(this, "Você deve informar o nome da turma!", Toast.LENGTH_LONG).show();
        } else if (sala.isEmpty()) {
            Toast.makeText(this, "Você deve informar uma sala para turma!", Toast.LENGTH_LONG).show();
        } else {
            turma.setNome(nome);
            try {
                turma.setSala(Integer.parseInt(sala));
            } catch (Exception ex) {
                Toast.makeText(this, "Você deve informar um número válido para a sala da turma!", Toast.LENGTH_LONG).show();
            }
            turma.setAtiva(swAtiva.isChecked() ? 1 : 0);

            if (acao.equals("inserir")) {
                TurmaDAO.inserir(this, turma);
                limpar();
            } else {
                TurmaDAO.editar(this, turma);
                finish();
            }
        }
    }

}
