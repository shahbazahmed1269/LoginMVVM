package com.example.shahbazahmed.loginmvvmdatabinding.validators;

import android.support.annotation.NonNull;

import com.rengwuxian.materialedittext.validation.METValidator;

/**
 * Created by shahbazahmed on 14/08/17.
 */

public class EmailValidator extends METValidator {

    public EmailValidator(String defaultErrString) {
        super(defaultErrString);
    }

    @Override
    public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
        String regex = "[a-zA-Z]+[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
        return (!isEmpty && text.toString().matches(regex));
    }
}
