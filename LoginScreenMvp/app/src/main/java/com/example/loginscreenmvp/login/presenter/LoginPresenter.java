package com.example.loginscreenmvp.login.presenter;

import com.example.loginscreenmvp.login.activity.ILogin;

public class LoginPresenter implements ILoginPresenter {
    private ILogin mILogin;

    public LoginPresenter(ILogin mILogin) {
        this.mILogin = mILogin;
    }

    @Override
    public boolean validate(String name, String password) {
        if (name.isEmpty() && password.isEmpty()) {
            mILogin.showMessage("Please Enter Username and Password ");
            return false;
        } else {
            return name.equalsIgnoreCase("Admin") &&
                    password.equalsIgnoreCase("1234");
        }

    }
}
