package edu.pdx.cs410J.huanhua;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

/**
 *
 */
public class FragmentEnterPhoneCall extends Fragment {

    private View thisView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thisView = inflater.inflate(R.layout.fragment_enter_phone_call, container, false);

        Bundle bundle = this.getArguments();
        int toDelete = 0;

        if (bundle != null) {
            PhoneBill bill = (PhoneBill) bundle.getSerializable("bill");

            // remove customer input view
            toDelete = R.id.input_customer_in_enter;

            // show customer name
            TextView customerDisplayView = thisView.findViewById(R.id.display_customer_name);
            customerDisplayView.setText(bill.getCustomer());
        } else {
            // remove customer display
            toDelete = R.id.display_customer_name;
        }

        // remove customer input view
        View toRemove = thisView.findViewById(toDelete);
        ((ViewGroup) toRemove.getParent()).removeView(toRemove);

        return thisView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_phone_call_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FragmentEnterPhoneCall.this)
                        .navigate(R.id.action_enter_to_main);
            }
        });
    }
}