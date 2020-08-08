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

import edu.pdx.cs410J.ParserException;

/**
 * A fragment for print bill with searching functionaility
 */
public class FragmentPrintBillSearch extends Fragment {

    private View thisView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thisView = inflater.inflate(R.layout.fragment_print_bill_search, container, false);

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
        view.findViewById(R.id.button_print_bill_search_go_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleGoBack();
            }
        });

        // when click search bill
        view.findViewById(R.id.button_print_bill_search_submit).setOnClickListener(new View.OnClickListener() {
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
                .navigate(R.id.action_fragmentPrintBillSearch_to_FragmentMain);
    }

    /**
     * handle event when click search bill
     */
    private void handleSearch() {
        PhoneBill bill = null;
        PhoneCall call = null;
        TextView customerView = thisView.findViewById(R.id.input_print_bill_search_customer);
        String customer = customerView.getText().toString();

        EditText date1 = (EditText) thisView.findViewById(R.id.input_search_start_date);
        EditText time1 = (EditText) thisView.findViewById(R.id.input_search_start_time);
        RadioGroup marker1 = (RadioGroup) thisView.findViewById(R.id.input_search_start_marker);
        EditText date2 = (EditText) thisView.findViewById(R.id.input_search_end_date);
        EditText time2 = (EditText) thisView.findViewById(R.id.input_search_end_time);
        RadioGroup marker2 = (RadioGroup) thisView.findViewById(R.id.input_search_end_marker);

        String date1Str = date1.getText().toString();
        String date2Str = date2.getText().toString();
        String time1Str = time1.getText().toString();
        String time2Str = time2.getText().toString();
        RadioButton marker1Btn = marker1.findViewById(marker1.getCheckedRadioButtonId());
        RadioButton marker2Btn = marker2.findViewById(marker2.getCheckedRadioButtonId());

        String marker1Str = "";
        String marker2Str = "";

        if (customer.isEmpty()) {
            Snackbar.make(thisView, "Please enter a customer name.", 5000)
                    .setAction("Action", null).show();
            return;
        }

        if (marker1Btn != null && marker2Btn != null) {
            marker1Str = marker1Btn.getText().toString();
            marker2Str = marker2Btn.getText().toString();
        }

        // create phone call to verify start and end time
        try {
            call = new PhoneCall("111-111-1111", "222-222-2222", date1Str + " " + time1Str + " " + marker1Str, date2Str + " " + time2Str + " " + marker2Str);
        }
        catch (IllegalArgumentException e) {
            Snackbar.make(thisView, e.getMessage(), 5000)
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
        }
        else {
            // prepare data to send to another fragment
            Bundle bundle = new Bundle();
            // bundle.putString("customer", customer);
            bundle.putSerializable("bill", bill);
            bundle.putSerializable("start", call.getStartTime());
            bundle.putSerializable("end", call.getEndTime());

            bundle.putInt("back", R.id.action_FragmentPrintBillResult_to_fragmentPrintBillSearch);

            // go to result fragment
            NavHostFragment.findNavController(FragmentPrintBillSearch.this)
                    .navigate(R.id.action_fragmentPrintBillSearch_to_FragmentPrintBillResult, bundle);

        }
    }
}