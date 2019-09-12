package com.example.loginapp.registration.presenter;

import com.example.loginapp.registration.activity.IRegistration;

public class RegistrationPresenter implements IRegistrationPresenter {
    private IRegistration mIRegistration;

    public RegistrationPresenter(IRegistration mIRegistration) {
        this.mIRegistration = mIRegistration;
    }

    @Override
    public boolean validate(String username, String Password, String email) {

        if (username.isEmpty() && email.isEmpty() && Password.isEmpty())
            return true;
        else
            return false;
    }
}
