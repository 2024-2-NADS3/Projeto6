package com.br.Ft.fitsync;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class DietaFragment extends Fragment {

    public DietaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dieta, container, false);


        ImageButton btnCafe = view.findViewById(R.id.btnCafe);
        ImageButton btnRefeicao = view.findViewById(R.id.btnRefeicao);
        ImageButton btnLanche = view.findViewById(R.id.btnLanche);
        ImageButton btnSuplemento = view.findViewById(R.id.btnSuplemento);

        btnCafe.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CafeActivity.class);
            startActivity(intent);
        });

        btnRefeicao.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RefeicaoActivity.class);
            startActivity(intent);
        });

        btnLanche.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LancheActivity.class);
            startActivity(intent);
        });

        btnSuplemento.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SuplementosActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
