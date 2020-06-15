package br.com.petersonmashni.appturmas.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import br.com.petersonmashni.appturmas.Models.Aluno;
import br.com.petersonmashni.appturmas.R;

public class AlunoAdapter extends BaseAdapter {
    private List<Aluno> listaAluno;
    private LayoutInflater inflater;

    public AlunoAdapter(Context context, List<Aluno> lista) {
        this.listaAluno = lista;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaAluno.size();
    }

    @Override
    public Object getItem(int i) {
        return listaAluno.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaAluno.get(i).getAluno_id();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ItemSuporte item;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_alunos, null);
            item = new ItemSuporte();
            item.tvNome = (TextView) convertView.findViewById(R.id.layout_alunos_tvNome);
            item.fundoTela = convertView.findViewById(R.id.layout_alunos);

            convertView.setTag(item);

        } else {
            item = (ItemSuporte) convertView.getTag();
        }

        Aluno aluno = listaAluno.get(i);
        item.tvNome.setText(aluno.getNome());

        if (i % 2 == 0) {
            item.fundoTela.setBackgroundColor(Color.WHITE);
        } else {
            item.fundoTela.setBackgroundColor(Color.rgb(230, 230, 230));
        }

        return convertView;
    }

    public class ItemSuporte {
        TextView tvNome;
        LinearLayout fundoTela;
    }
}
