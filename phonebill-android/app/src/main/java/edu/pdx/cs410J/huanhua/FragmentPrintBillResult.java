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
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPrintBillResult#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPrintBillResult extends Fragment {

    View thisView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thisView = inflater.inflate(R.layout.fragment_print_bill_result, container, false);
        TextView viewPrint = thisView.findViewById(R.id.view_print);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            PhoneBill bill = (PhoneBill) bundle.getSerializable("bill");
            String prettyString = PrettyPrinter.constructPrettyOutput(bill, null, null);

            viewPrint.setText(prettyString);
        } else {
            viewPrint.setText("No result.");
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
        view.findViewById(R.id.button_go_back_print_result_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleGoBack();
            }
        });
    }

    /**
     * handle event when click go back
     */
    private void handleGoBack() {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_FragmentPrintBillResult_to_FragmentPrintBill);
    }
}