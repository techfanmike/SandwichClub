package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String NAME_TAG = "name";
    public static final String MAIN_NAME_TAG = "mainName";
    public static final String AKA_TAG = "alsoKnownAs";
    public static final String ORIGIN_TAG = "placeOfOrigin";
    public static final String DESCRIPTION_TAG = "description";
    public static final String IMAGE_TAG = "image";
    public static final String INGREDIENTS_TAG = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        try {
            // create json object, passing in string to constructor
            JSONObject jsonObj = new JSONObject(json);

            // get the string from the main name
            JSONObject jsonName = jsonObj.getJSONObject(NAME_TAG);
            String mainName = jsonName.getString(MAIN_NAME_TAG);

            // get the list of  aka
            JSONObject aka = jsonObj.getJSONObject(MAIN_NAME_TAG);
            JSONArray akaJSONArray = aka.getJSONArray(AKA_TAG);

            // go through the aka list and assign the strings
            List<String> akaStrings = new ArrayList<>(akaJSONArray.length());

            for (int count = 0; count < akaJSONArray.length(); count++) {
                akaStrings.add(akaJSONArray.getString(count));
            }

            // get the place of origin, description, and image string
            String placeOfOrigin = jsonObj.getString(ORIGIN_TAG);
            String description = jsonObj.getString(DESCRIPTION_TAG);
            String image = jsonObj.getString(IMAGE_TAG);

            // go through the list of ingredients and assign to strings
            JSONArray ingredientsJsonArray = jsonObj.getJSONArray(INGREDIENTS_TAG);
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
