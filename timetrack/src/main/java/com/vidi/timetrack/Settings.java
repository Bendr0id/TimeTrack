package com.vidi.timetrack;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref(value= SharedPref.Scope.UNIQUE)
public interface Settings
{
    String email();
    String password();
}
