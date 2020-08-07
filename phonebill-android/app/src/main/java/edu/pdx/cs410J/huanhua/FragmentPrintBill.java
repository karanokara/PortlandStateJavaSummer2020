package edu.pdx.cs410J.huanhua;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;

import edu.pdx.cs410J.ParserException;

/**
 * A fragmnent
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
                handleSearch();
            }
        });
    }

    /**
     * handle event when click go back
     */
    private void handleGoBack() {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_FragmentPrintBill_to_FragmentMain);
    }

    /**
     * handle event when click search bill
     */
    private void handleSearch() {
        TextView customerView = thisView.findViewById(R.id.input_print_bill_customer);
        String customer = customerView.getText().toString();
        PhoneBill bill = null;

        if (customer.isEmpty()) {
            Snackbar.make(thisView, "Please enter a customer name.", 5000)
                    .setAction("Action", null).show();
            return;
        }

        // search phone bill
        // String path = thisView.getContext().getApplicationInfo().dataDir;
        // String path = thisView.getContext().getFilesDir().getPath();
        File path = thisView.getContext().getFilesDir();
        File file = new File(path, customer);

        try {
            bill = TextParser.parseFile(file);
        }
        catch (ParserException e) {
            Snackbar.make(thisView, e.getMessage(), 5000)
                    .setAction("Action", null).show();
            return;
        }

        if (bill == null) {
            String msg = "No phone bill found for customer \"" + customer + "\"";
            Snackbar.make(thisView, msg, 5000)
                    .setAction("Action", null).show();
        } else {
            // prepare data to send to another fragment
            Bundle bundle = new Bundle();
            // bundle.putString("customer", customer);
            bundle.putSerializable("bill", bill);

            // go to result fragment
            NavHostFragment.findNavController(FragmentPrintBill.this)
                    .navigate(R.id.action_FragmentPrintBill_to_FragmentPrintBillResult, bundle);

        }
    }
}