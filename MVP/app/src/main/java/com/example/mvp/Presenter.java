package com.example.mvp;

public class Presenter implements IContract{

    private IView iView;
    public Presenter(IView iView) {
        this.iView = iView;
    }

    @Override
    public void onLoginButtonClicked(String obj) {
        iView.onEditTextEmptyError();
    }
}
