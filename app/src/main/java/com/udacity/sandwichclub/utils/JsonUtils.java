package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {
            // create json object, passing in string to constructor
            JSONObject jsonObj = new JSONObject(json);

            JSONObject jsonName = jsonObj.getJSONObject("name");
            String mainName = jsonName.getString("mainName");

            JSONObject aka = jsonObj.getJSONObject("mainName");
            JSONArray akaJSONArray = aka.getJSONArray("alsoKnownAs");

            // go through the list and assign the strings
            List<String> akaStrings = new ArrayList<>(akaJSONArray.length());

            for (int count = 0; count < akaJSONArray.length(); count++) {
                akaStrings.add(akaJSONArray.getString(count));
            }

            String placeOfOrigin = jsonObj.getString("placeOfOrigin");
            String description = jsonObj.getString("description");
            String image = jsonObj.getString("image");

            JSONArray ingredientsJsonArray = jsonObj.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>(ingredientsJsonArray.length());

            for (int count1 = 0; count1 < jsonObj.length(); count1++) {
                ingredients.add(ingredientsJsonArray.getString(count1));
            }

            return new Sandwich(mainName, akaStrings, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            // TODO: print something meaningful
            return null;
        }

    }
}
