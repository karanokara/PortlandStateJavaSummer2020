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
 * Use the {@link FragmentEnterPhoneCallResult#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEnterPhoneCallResult extends Fragment {

    private View thisView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thisView = inflater.inflate(R.layout.fragment_enter_phone_call_result, container, false);
        TextView viewPrint = thisView.findViewById(R.id.view_print_enter_call);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            PhoneCall call = (PhoneCall) bundle.getSerializable("call");

            viewPrint.setText(call.toString());
        }
        else {
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

        // when click go back home
        view.findViewById(R.id.button_enter_result_go_back_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleGoBackHome();
            }
        });
    }

    /**
     * handle event when click go back
     */
    private void handleGoBackHome() {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_fragmentEnterPhoneCallResult_to_FragmentMain);
    }

}