package com.example.shahbazahmed.loginmvvmdatabinding.validators;

import android.support.annotation.NonNull;

import com.rengwuxian.materialedittext.validation.METValidator;

/**
 * Created by shahbazahmed on 14/08/17.
 */

public class PasswordValidator extends METValidator {

    public PasswordValidator(String defaultErrString) {
        super(defaultErrString);
    }

    @Override
    public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
        return text.length() >= 6 && text.length() <= 15;
    }
}
