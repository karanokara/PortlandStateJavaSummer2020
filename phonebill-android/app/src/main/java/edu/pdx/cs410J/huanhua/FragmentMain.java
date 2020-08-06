package edu.pdx.cs410J.huanhua;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FragmentMain extends Fragment {

    /**
     * Inflate the layout for this fragment
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    /**
     * fired after a view is created
     *
     * @param view
     * @param savedInstanceState
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_create_phone_bill).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentMain.this)
                        .navigate(R.id.action_main_to_create);
            }
        });

        view.findViewById(R.id.button_enter_phone_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentMain.this)
                        .navigate(R.id.action_main_to_enter);
            }
        });

        view.findViewById(R.id.button_print_phone_bill).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentMain.this)
                        .navigate(R.id.action_FragmentMain_to_FragmentPrintBill);
            }
        });
    }
}