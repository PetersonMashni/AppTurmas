package br.com.petersonmashni.appturmas.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Debug;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

import br.com.petersonmashni.appturmas.Models.Turma;
import br.com.petersonmashni.appturmas.R;

public class TurmaAdapter extends BaseAdapter {
    private List<Turma> listaTurma;
    private LayoutInflater inflater;

    public TurmaAdapter(Context context, List<Turma> lista) {
        this.listaTurma = lista;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaTurma.size();
    }

    @Override
    public Object getItem(int i) {
        return listaTurma.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaTurma.get(i).getTurma_id();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ItemSuporte item;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_turmas, null);
            item = new ItemSuporte();
            item.ivAtiva = (ImageView) convertView.findViewById(R.id.layout_turmas_ivAtiva);
            item.tvNome = (TextView) convertView.findViewById(R.id.layout_turmas_tvNome);
            item.fundoTela = convertView.findViewById(R.id.layout_turmas);

            convertView.setTag(item);

        } else {
            item = (ItemSuporte) convertView.getTag();
        }

        Turma turma = listaTurma.get(i);
        item.tvNome.setText(turma.getNome());
        item.ivAtiva.setImageResource(turma.getAtiva()==1? R.drawable.ic_checked : R.drawable.ic_unchecked);

        if (i % 2 == 0) {
            item.fundoTela.setBackgroundColor(Color.WHITE);
        } else {
            item.fundoTela.setBackgroundColor(Color.rgb(230, 230, 230));
        }

        return convertView;
    }

    public class ItemSuporte {
        ImageView ivAtiva;
        TextView tvNome;
        ConstraintLayout fundoTela;
        float downX = 0;
    }
}
