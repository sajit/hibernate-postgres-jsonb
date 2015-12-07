package com.github.pires.example.hibernate.user.types;

/**
 * Created by sajit on 12/6/15.
 */
public class JSONUserType extends JSONBUserType {

    @Override
    protected String getType(){
        return "json";
    }
}
