package edu.pdx.cs410J.huanhua;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Date;

/**
 * A simple
 */
public class FragmentPrintBillResult extends Fragment {

    private View thisView = null;
    private int backFragmentId = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thisView = inflater.inflate(R.layout.fragment_print_bill_result, container, false);

        TextView viewPrint = thisView.findViewById(R.id.view_print_enter_call);
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            this.backFragmentId = bundle.getInt("back");

            PhoneBill bill = (PhoneBill) bundle.getSerializable("bill");
            Date start = (Date) bundle.getSerializable("start");
            Date end = (Date) bundle.getSerializable("end");
            if (bill != null) {
                String prettyString = PrettyPrinter.constructPrettyOutput(bill, start, end);
                viewPrint.setText(prettyString);
            }
            else {
                viewPrint.setText("No result.");
            }
        }
        else {
            viewPrint.setText("Error.");
        }


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
        view.findViewById(R.id.button_enter_result_go_back_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleGoBack();
            }
        });

        // when click go back home
        view.findViewById(R.id.button_print_reuslt_go_back_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleGoBackHome();
            }
        });
    }

    /**
     * handle event when click go back
     */
    private void handleGoBack() {
        NavHostFragment.findNavController(this)
                .navigate(this.backFragmentId);
    }

    /**
     * handle event when click go back
     */
    private void handleGoBackHome() {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_FragmentPrintBillResult_to_FragmentMain);
    }
}