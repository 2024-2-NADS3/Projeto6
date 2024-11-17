package br.fecap.pi.fitsync;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class TreinoFragment extends Fragment {

    public TreinoFragment() {
        // Requer um construtor vazio
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_treino, container, false);

        ImageButton btnBack = view.findViewById(R.id.btnBack);
        ImageButton btnArms = view.findViewById(R.id.btnArms);
        ImageButton btnLegs = view.findViewById(R.id.btnLegs);
        ImageButton btnChest = view.findViewById(R.id.btnChest);

        // Definir um listener para o botão de costas
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TreinoBack.class);
                startActivity(intent);
            }
        });

        // Definir um listener para o botão de braços
        btnArms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArmsActivity.class);
                startActivity(intent);
            }
        });

        // Definir um listener para o botão de pernas
        btnLegs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LegsActivity.class);
                startActivity(intent);
            }
        });

        // Definir um listener para o botão de peito
        btnChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChestActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
