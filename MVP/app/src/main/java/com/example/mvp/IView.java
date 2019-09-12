package com.example.mvp;

public interface IView {
    void onEditTextEmptyError();
    void userSavedToDatabase();
    void onDataLoaded();
    void showLoading();
}
