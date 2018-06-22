package com.selltm.assignment.login;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.selltm.assignment.R;
import com.selltm.assignment.activities.MainActivity;
import com.selltm.assignment.home.fragments.HomeFragment;
import com.selltm.assignment.pojos.User;

/**
 * Created by Asim on 20/06/18.
 */
public class LoginFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();
    private EditText usernameET, passwordET;
    private Button buttonOk, buttonPwdReset;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_ok:
                    if((getActivity() instanceof MainActivity) && null != usernameET
                            && null != passwordET) {
                        boolean isEmailValid = isValidEmail(usernameET.getText());
                        boolean isPwdValid = isValidPassword(passwordET.getText());

                        if(isEmailValid && isPwdValid) {
                            loginSuccess();
                        } else if(!isEmailValid && !isPwdValid) {
                            Toast.makeText(getActivity(), getString(R.string.string_invalid_email_and_pwd), Toast.LENGTH_SHORT).show();
                        } else if(!isEmailValid) {
                            Toast.makeText(getActivity(), getString(R.string.string_invalid_email), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), getString(R.string.string_invalid_password), Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case R.id.button_reset_pwd:
                    //TODO: Check what needs to be done here
                    Toast.makeText(getActivity(), "Reset Password clicked", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    private void loginSuccess() {
        User user = User.getInstance();
        user.setUserEmail(String.valueOf(usernameET.getText()));
        usernameET.setText("");
        passwordET.setText("");
//        ((MainActivity) getActivity()).displayHomeFragment();
        ((MainActivity) getActivity()).setFragment(HomeFragment.Companion.getInstance(), HomeFragment.class.getSimpleName());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).hideActionBar();
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        resolveViewsFromXml(view);
        setOnclickListeners();
    }

    private void resolveViewsFromXml(View view) {
        usernameET = view.findViewById(R.id.username_ET);
        passwordET = view.findViewById(R.id.password_ET);
        buttonOk = view.findViewById(R.id.button_ok);
        buttonPwdReset = view.findViewById(R.id.button_reset_pwd);

        temp();
    }

    private void temp() {
        //TestTAG
        usernameET.setText("asim@gmail.com");
        passwordET.setText("asim");
    }

    private void setOnclickListeners() {
        buttonOk.setOnClickListener(onClickListener);
        buttonPwdReset.setOnClickListener(onClickListener);
    }

    private boolean isValidEmail(CharSequence email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean isValidPassword(CharSequence passsword) {
        return (!TextUtils.isEmpty(passsword));
    }

    public static LoginFragment getInstance() {
        return new LoginFragment();
    }
}
