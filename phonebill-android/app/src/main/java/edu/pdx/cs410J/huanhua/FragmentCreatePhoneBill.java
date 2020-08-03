package edu.pdx.cs410J.huanhua;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

public class FragmentCreatePhoneBill extends Fragment {

    private EditText customerText;
    private View thisView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_phone_bill, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        thisView = view;
        customerText = (EditText) view.findViewById(R.id.input_customer);

        // when click the submit button
        view.findViewById(R.id.button_phone_bill_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewClick) {

                // create phone bill
                String customer = customerText.getText().toString();

                try {
                    PhoneBill bill = new PhoneBill(customer);
                } catch (IllegalArgumentException e) {
                    // Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG)
                    //         .setAction("Action", null).show();
                    Snackbar.make(thisView, e.getMessage(), 5000)
                            .setAction("Action", null).show();
                    return;
                }


                // go to enter phone call fragment
                NavHostFragment.findNavController(FragmentCreatePhoneBill.this)
                        .navigate(R.id.action_create_to_main);
            }
        });
    }
}