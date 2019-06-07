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

        // the sandwich object we will return
        Sandwich sandwich = new Sandwich();

        try {
            // create json object, passing in string to constructor
            // then get the name sub object
            JSONObject jsonObj = new JSONObject(json);
            JSONObject nameObj = jsonObj.getJSONObject(NAME_TAG);

            // set the member variables of sandwich by extracting string from
            // the json objects.
            sandwich.setMainName(nameObj.getString(MAIN_NAME_TAG));
            sandwich.setDescription(jsonObj.getString(DESCRIPTION_TAG));
            sandwich.setPlaceOfOrigin(jsonObj.getString(ORIGIN_TAG));
            sandwich.setImage(jsonObj.getString(IMAGE_TAG));
            sandwich.setAlsoKnownAs(JsonArrayToStringList(nameObj.getJSONArray(AKA_TAG)));
            sandwich.setIngredients(JsonArrayToStringList(jsonObj.getJSONArray(INGREDIENTS_TAG)));

            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    // helper function to go through the json array and return a list of strings
    public static ArrayList<String> JsonArrayToStringList(JSONArray array) {
        ArrayList<String> strings = new ArrayList<>();
        try {
            // move through the JSONArray and get strings and put into string list
            for(int count = 0; count < array.length(); count++) {
                strings.add(array.getString(count));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return strings;
    }
}
