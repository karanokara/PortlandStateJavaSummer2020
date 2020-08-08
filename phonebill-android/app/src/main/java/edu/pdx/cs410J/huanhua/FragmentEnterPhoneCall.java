package edu.pdx.cs410J.huanhua;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import edu.pdx.cs410J.ParserException;

/**
 * a fragment for entering phone call
 */
public class FragmentEnterPhoneCall extends Fragment {

    private View thisView = null;

    private PhoneBill bill = null;
    private int backFragmentId = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thisView = inflater.inflate(R.layout.fragment_enter_phone_call, container, false);

        Bundle bundle = this.getArguments();
        int toDelete = 0;

        if (bundle != null) {
            this.backFragmentId = bundle.getInt("back");

            this.bill = (PhoneBill) bundle.getSerializable("bill");

            // remove customer input view
            toDelete = R.id.input_customer_in_enter;

            // show customer name
            TextView customerDisplayView = thisView.findViewById(R.id.display_customer_name);
            customerDisplayView.setText(this.bill.getCustomer());
        }
        else {
            // remove customer display
            toDelete = R.id.display_customer_name;

            this.backFragmentId = R.id.action_FragmentEnterPhoneCall_to_FragmentMain;
        }

        // remove customer input view
        View toRemove = thisView.findViewById(toDelete);
        ((ViewGroup) toRemove.getParent()).removeView(toRemove);

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

        // when click add phone call
        view.findViewById(R.id.button_phone_call_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOnEnter();
            }
        });

        // when click go back
        view.findViewById(R.id.button_go_back_enter_fragment).setOnClickListener(new View.OnClickListener() {
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
        NavHostFragment.findNavController(FragmentEnterPhoneCall.this)
                .navigate(this.backFragmentId);
    }

    /**
     * handle when click enter phone call
     */
    private void handleOnEnter() {
        // String path = thisView.getContext().getApplicationInfo().dataDir;       // app dir path
        // String path = thisView.getContext().getFilesDir().getPath();
        File path = thisView.getContext().getFilesDir();

        PhoneCall call = null;
        File file = null;

        EditText caller = (EditText) thisView.findViewById(R.id.input_caller);
        EditText callee = (EditText) thisView.findViewById(R.id.input_callee);

        EditText date1 = (EditText) thisView.findViewById(R.id.input_start_date);
        EditText time1 = (EditText) thisView.findViewById(R.id.input_start_time);
        RadioGroup marker1 = (RadioGroup) thisView.findViewById(R.id.input_search_start_marker);
        EditText date2 = (EditText) thisView.findViewById(R.id.input_end_date);
        EditText time2 = (EditText) thisView.findViewById(R.id.input_end_time);
        RadioGroup marker2 = (RadioGroup) thisView.findViewById(R.id.input_search_end_marker);

        String callerStr = caller.getText().toString();
        String calleeStr = callee.getText().toString();
        String date1Str = date1.getText().toString();
        String date2Str = date2.getText().toString();
        String time1Str = time1.getText().toString();
        String time2Str = time2.getText().toString();
        RadioButton marker1Btn = marker1.findViewById(marker1.getCheckedRadioButtonId());
        RadioButton marker2Btn = marker2.findViewById(marker2.getCheckedRadioButtonId());

        String marker1Str = "";
        String marker2Str = "";

        if (marker1Btn != null && marker2Btn != null) {
            marker1Str = marker1Btn.getText().toString();
            marker2Str = marker2Btn.getText().toString();
        }

        if (bill == null) {
            // not from fragment of creating phone bill
            EditText customer = (EditText) thisView.findViewById(R.id.input_customer_in_enter);
            String customerStr = customer.getText().toString();

            // search phone bill
            file = new File(path, customerStr);

            try {
                bill = TextParser.parseFile(file);
            }
            catch (ParserException e) {
                Snackbar.make(thisView, e.getMessage(), 5000)
                        .setAction("Action", null).show();
                return;
            }

            if (bill == null) {
                // create phone bill non-existing customer
                try {
                    bill = new PhoneBill(customerStr);
                }
                catch (IllegalArgumentException e) {
                    Snackbar.make(thisView, e.getMessage(), 5000)
                            .setAction("Action", null).show();
                    return;
                }
            }
        }
        else {
            // get a file for customer
            file = new File(path, bill.getCustomer());
        }

        // create phone call
        try {
            call = new PhoneCall(callerStr, calleeStr, date1Str + " " + time1Str + " " + marker1Str, date2Str + " " + time2Str + " " + marker2Str);
        }
        catch (IllegalArgumentException e) {
            Snackbar.make(thisView, e.getMessage(), 5000)
                    .setAction("Action", null).show();
            return;
        }

        int callCountBefore = bill.getPhoneCalls().size();

        // add call to bill
        bill.addPhoneCall(call);

        int callCountAfter = bill.getPhoneCalls().size();

        if (callCountAfter == callCountBefore) {
            Snackbar.make(thisView, "Phone call already exist.", 5000)
                    .setAction("Action", null).show();
            return;
        }

        // save bill to file
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.println(TextDumper.formatOutput(bill, null, null));
            pw.close(); // need to be closed so can write out
        }
        catch (FileNotFoundException e) {
            Snackbar.make(thisView, e.getMessage(), 5000)
                    .setAction("Action", null).show();
            return;
        }

        // prepare data to send to another fragment
        Bundle bundle = new Bundle();
        // bundle.putString("customer", customer);
        bundle.putSerializable("call", call);

        Snackbar.make(thisView, "Add a phone call for \"" + bill.getCustomer() + "\" successfully.", 5000)
                .setAction("Action", null).show();

        NavHostFragment.findNavController(FragmentEnterPhoneCall.this)
                .navigate(R.id.action_enter_to_result, bundle);
    }
}