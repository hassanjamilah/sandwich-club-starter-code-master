package com.udacity.sandwichclub.utils;

import android.content.Context;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class JsonUtils {




    public static Sandwich parseSandwichJson(String json) throws JSONException {


        Sandwich mySandwich = new Sandwich();

        JSONObject obj = new JSONObject(json);

        JSONObject name = obj.getJSONObject("name");

        mySandwich.setMainName(name.getString("mainName"));
        mySandwich.setDescription(obj.getString("description"));
        mySandwich.setImage(obj.getString("image"));
        mySandwich.setPlaceOfOrigin(obj.getString("placeOfOrigin"));

        JSONArray knownAs = name.getJSONArray("alsoKnownAs");
        mySandwich.setAlsoKnownAs(getJsonArrayAsList(knownAs));

        JSONArray ingreds = obj.getJSONArray("ingredients") ;
        mySandwich.setIngredients(getJsonArrayAsList(ingreds));

        return mySandwich;
    }


    /**
     * tbis method is used to get the json array that i have readen
     * and convert it to java list
     * @param values
     * @return
     * @throws JSONException
     */
    private static List<String> getJsonArrayAsList(JSONArray values) throws JSONException {

        if (values.length() < 1) return null;
        String s = null;
        List<String> vals = new ArrayList<String>();

        for (int i = 0; i < values.length(); i++) {
            s = values.getString(i);
            vals.add(s);
        }
        return vals;
    }


}
