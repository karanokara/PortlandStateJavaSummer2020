package edu.pdx.cs410J.huanhua;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

public class FragmentCreatePhoneBill extends Fragment {

    private EditText customerText;
    private View thisView;
    private FragmentManager fragmentManager;


    /**
     * Before create a view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thisView = inflater.inflate(R.layout.fragment_create_phone_bill, container, false);
        fragmentManager = this.getFragmentManager();


        return thisView;
    }

    /**
     * after a vew is created
     *
     * @param view
     * @param savedInstanceState
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        customerText = (EditText) view.findViewById(R.id.input_customer);

        // when click the submit button
        view.findViewById(R.id.button_phone_bill_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewClick) {
                handleOnCreate();
            }
        });

        // when click go back
        view.findViewById(R.id.button_go_back_create_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleGoBack();
            }
        });
    }

    /**
     * handle when click enter phone call
     */
    private void handleGoBack() {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_create_to_main);
    }

    /**
     * handle event when click create phone bill btn
     */
    private void handleOnCreate() {
        // create phone bill
        String customer = customerText.getText().toString();
        PhoneBill bill = null;

        try {
            bill = new PhoneBill(customer);
        } catch (IllegalArgumentException e) {
            Snackbar.make(thisView, e.getMessage(), 5000)
                    .setAction("Action", null).show();
            return;
        }

        // prepare data to send to another fragment
        Bundle bundle = new Bundle();
        // bundle.putString("customer", customer);
        bundle.putSerializable("bill", bill);


        // go to enter phone call fragment
        NavHostFragment.findNavController(FragmentCreatePhoneBill.this)
                .navigate(R.id.action_create_to_enter, bundle);

        Snackbar.make(thisView, "Created a Phone Bill for \"" + customer + "\".", 5000)
                .setAction("Action", null).show();
    }
}