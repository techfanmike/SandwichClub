package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// TODO: static const the string literals
public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {
            // create json object, passing in string to constructor
            JSONObject jsonObj = new JSONObject(json);

            // get the string from the main name
            JSONObject jsonName = jsonObj.getJSONObject("name");
            String mainName = jsonName.getString("mainName");

            // get the list of string from the main name
            JSONObject aka = jsonObj.getJSONObject("mainName");
            JSONArray akaJSONArray = aka.getJSONArray("alsoKnownAs");

            // go through the list and assign the strings
            List<String> akaStrings = new ArrayList<>(akaJSONArray.length());

            for (int count = 0; count < akaJSONArray.length(); count++) {
                akaStrings.add(akaJSONArray.getString(count));
            }

            // get the place of origin, description, and image string
            String placeOfOrigin = jsonObj.getString("placeOfOrigin");
            String description = jsonObj.getString("description");
            String image = jsonObj.getString("image");

            // go through the list of ingredients and assign to strings
            JSONArray ingredientsJsonArray = jsonObj.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>(ingredientsJsonArray.length());

            for (int count1 = 0; count1 < jsonObj.length(); count1++) {
                ingredients.add(ingredientsJsonArray.getString(count1));
            }

            // return a sandwich object, constructed in the return statement
            return new Sandwich(mainName, akaStrings, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            // TODO: print something meaningful
            return null;
        }

    }
}
