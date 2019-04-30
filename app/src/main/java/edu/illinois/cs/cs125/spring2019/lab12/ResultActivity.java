package edu.illinois.cs.cs125.spring2019.lab12;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * result class for our UI design lab.
 */
public class ResultActivity extends Activity {
    /**resultImage.*/
    private ImageView resultImage;
    /**question.*/
    private TextView questionView;
    /**endButton.*/
    private Button endResultButton;
    /**endButton.*/
    private Button answer1Button;
    /**endButton.*/
    private Button answer2Button;
    /**endButton.*/
    private Button answer3Button;
    /**endButton.*/
    private Button answer4Button;
    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;

    /**
     * Run when this activity comes to the foreground.
     *
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        requestQueue = Volley.newRequestQueue(this);
        questionView = findViewById(R.id.questionView);
        answer1Button = findViewById(R.id.answer1);
        answer2Button = findViewById(R.id.answer2);
        answer3Button = findViewById(R.id.answer3);
        answer4Button = findViewById(R.id.answer4);
        resultImage = findViewById(R.id.resultImage);
        endResultButton = findViewById(R.id.endResultButton);
        questionView.setText("adsdsd");
        startAPICall();
        //questionView.setText("cvc");
        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        if (result == "win") {
            resultImage.setImageResource(R.drawable.winimage);
        } else {
            resultImage.setImageResource(R.drawable.looseimage);
        }
        endResultButton.setOnClickListener(v -> {
            finish();
        });

    }
    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Make a call to the IP geolocation API.
     */
    void startAPICall() {
        questionView.setText("start");
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://opentdb.com/api.php?amount=1&category=9&difficulty=medium&type=multiple",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            questionView.setText("a");
                            getAddress(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError error) {
                            questionView.setText("b");
                        }
                    });
            jsonObjectRequest.setShouldCache(false);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            questionView.setText("e");
            e.printStackTrace();
        }
    }

    /**
     * Handle the response from our IP geolocation API.
     *
     * @param response response from our IP geolocation API.
     */
    void getAddress(final JSONObject response) {
        questionView.setText("c");
        String arr = response.toString();
        questionView.setText(arr);
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(arr).getAsJsonObject();
        JsonArray data = result.get("results").getAsJsonArray();
        JsonObject ob = data.get(0).getAsJsonObject();
        String text = ob.get("question").getAsString();
        questionView.setText(text);
        String correctAnswer = ob.get("correct_answer").getAsString();
        String wrongAnswer1 = ob.get("incorrect_answers").getAsJsonArray().get(0).getAsString();
        String wrongAnswer2 = ob.get("incorrect_answers").getAsJsonArray().get(1).getAsString();
        String wrongAnswer3 = ob.get("incorrect_answers").getAsJsonArray().get(2).getAsString();
        answer1Button.setText(wrongAnswer1);
        answer2Button.setText(correctAnswer);
        answer3Button.setText(wrongAnswer2);
        answer4Button.setText(wrongAnswer3);
        answer1Button.setOnClickListener(v -> {
            answer1Button.setText("Stop playing game and go study");
        });
        answer2Button.setOnClickListener(v -> {
            answer2Button.setText("Good Job!");
        });
        answer3Button.setOnClickListener(v -> {
            answer3Button.setText("Stop playing game and go study");
        });
        answer4Button.setOnClickListener(v -> {
            answer4Button.setText("Stop playing game and go study");
        });
//        try {
//            questionView.setText("c");
//            String arr = response.toString();
////            String country = response.getJSONObject("main").getString("temp").toString();
////            String region = response.getJSONObject("main").get("pressure").toString();
////            String city = response.getJSONObject("main").get("humidity").toString();
////            String total = "You are in " + city + ", " + region + ", " + country + ".";
//            questionView.setText(arr);
//            JsonParser parser = new JsonParser();
//            JsonObject result = parser.parse(arr).getAsJsonObject();
//            JsonArray data = result.get("results").getAsJsonArray();
//            JsonObject ob = data.get(0).getAsJsonObject();
//            String text = ob.get("question").getAsString();
//            questionView.setText(text);
//        } catch (JSONException ignored) {
//            //questionView.setText("shit");
//        }
    }
}
