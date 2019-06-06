package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @BindView(R.id.also_known_tv)
    TextView mAlsoKnown;

    @BindView(R.id.origin_tv)
    TextView mOrigin;

    @BindView(R.id.description_tv)
    TextView mDescription;

    @BindView(R.id.ingredients_tv)
    TextView mIngredients;

    @BindView(R.id.image_iv)
    ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // bind variables to UI elements
        ButterKnife.bind(this);

        // grab the intent, bail out if null
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        // the starter code miffed the else that avoids a null warning on getIntExtra
        } else {
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);

            if (position == DEFAULT_POSITION) {
                // EXTRA_POSITION not found in intent
                closeOnError();
                return;
            }

            String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
            String json = sandwiches[position];
            Sandwich sandwich = JsonUtils.parseSandwichJson(json);

            if (sandwich == null) {
                // Sandwich data unavailable
                closeOnError();
                return;
            }

            populateUI();

            Picasso.with(this)
                    .load(sandwich.getImage())
                    .into(mImage);

            setTitle(sandwich.getMainName());

        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        ;
    }
}
