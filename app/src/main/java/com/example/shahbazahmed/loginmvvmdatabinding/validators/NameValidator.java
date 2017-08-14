package com.example.shahbazahmed.loginmvvmdatabinding.validators;

import android.support.annotation.NonNull;

import com.rengwuxian.materialedittext.validation.METValidator;

/**
 * Created by shahbazahmed on 14/08/17.
 */

public class NameValidator extends METValidator {

    public NameValidator(String defaultErrString) {
        super(defaultErrString);
    }

    @Override
    public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
        return !isEmpty;
    }
}
