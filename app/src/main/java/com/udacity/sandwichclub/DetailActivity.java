package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView alsoKnownAs_TextView;
    TextView ingredients_TextView;
    TextView origing_TextView;
    TextView description_TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        alsoKnownAs_TextView = (TextView) findViewById(R.id.also_known_tv);
        ingredients_TextView = (TextView) findViewById(R.id.ingredients_tv);
        origing_TextView = (TextView) findViewById(R.id.origin_tv);
        description_TextView = (TextView) findViewById(R.id.description_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }


    /**
     * this method is used to convert a java list
     * to a string separated with commas to display it in my UI
     * @param data
     * @return
     */
    private String getStringWithCommas(List<String> data) {
        String s = "";
        if (data == null || data.size() <= 0) return s;
        for (int i = 0; i < data.size(); i++) {
            s = s + " , " + data.get(i);

        }
        // I used the next line to remove the unncecessary null value with its comma
        // without it the string will be like null , ... values
        s = s.substring(2, s.length() - 2).trim();
        return s;
    }

    private void populateUI(Sandwich sandwich) {

        alsoKnownAs_TextView.setText(getStringWithCommas(sandwich.getAlsoKnownAs()));
        ingredients_TextView.setText(getStringWithCommas(sandwich.getIngredients()));
        origing_TextView.setText(sandwich.getPlaceOfOrigin());
        description_TextView.setText(sandwich.getDescription());


    }
}
