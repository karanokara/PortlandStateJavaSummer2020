package edu.pdx.cs410J.huanhua;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPrintBill#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPrintBill extends Fragment {

    View thisView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thisView = inflater.inflate(R.layout.fragment_print_bill, container, false);

        return thisView;
    }

    /**
     * after view is created
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // when click go back
        view.findViewById(R.id.button_go_back_print_bill_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleGoBack();
            }
        });

        // when click search bill
        view.findViewById(R.id.button_phone_bill_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentPrintBill.this)
                        .navigate(R.id.action_FragmentPrintBill_to_FragmentPrintBillResult);
            }
        });
    }

    public void handleGoBack() {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_FragmentPrintBill_to_FragmentMain);
    }
}