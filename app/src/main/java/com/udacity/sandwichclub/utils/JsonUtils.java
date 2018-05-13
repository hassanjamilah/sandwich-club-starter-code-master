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


    static String sam = "name:{mainName:Gua bao," +
            "alsoKnownAs:[Steamed bao,Pork belly bun]}," +
            "placeOfOrigin:Taiwan," +
            "description:Gua bao is a Taiwanese snack food   consisting of a slice of" +
            " stewed meat and other condiments sandwiched between flat" +
            "steamed bread. The steamed bread is typically 6–8 centimetres (2.4–3.1 in) in size," +
            "semi-circular and flat in form, with a horizontal fold that, when opened, gives the" +
            "appearance that it has been sliced. The traditional filling for gua bao is a slice of" +
            "red-cooked porkbelly, typically dressed with stir-fried suan cai (pickled mustard" +
            "greens), cilantro, and ground" +
            "peanuts.," +
            "image:https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Steamed_Sandwich%2Ctaken_by_LeoAlmighty.jpg/600px-Steamed_Sandwich%2Ctaken_by_LeoAlmighty.jpg," +
            "ingredients:[Steamed bread,Stewed meat,Condiments] ";

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


    private static List<String> getJsonArrayAsList(JSONArray values) throws JSONException {

        if (values.length() < 1) return null;
        String s = null;
        List<String> vals = new ArrayList<String>();

        for (int i = 0; i < values.length(); i++) {
            s = values.getString(i);
            Log.i("hassan", s);
            vals.add(s);
        }
        return vals;
    }


}
