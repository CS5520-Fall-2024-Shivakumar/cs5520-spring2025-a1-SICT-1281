package edu.northeastern.numad25sp_sichunteng;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuicCalcActivity extends AppCompatActivity {

    private TextView calcText;
    private final StringBuilder curInput = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quic_calc);
        calcText = findViewById(R.id.calc_text);
        int[] buttonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
                R.id.btn_plus, R.id.btn_minus, R.id.btn_equals, R.id.btn_delete
        };
        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(v -> handleButtonClick(((Button) v).getText().toString()));
        }
    }


    private int generateAnswer(String expression) {
        String[] list = expression.split("(?<=\\d)(?=[+-])|(?<=[+-])(?=\\d)");
        int res = Integer.parseInt(list[0]);
        for (int i = 1; i < list.length; i += 2) {
            String operator = list[i];
            int nextNum = Integer.parseInt(list[i + 1]);
            if (operator.equals("+")) {
                res += nextNum;
            } else if (operator.equals("-")) {
                res -= nextNum;
            }
        }
        return res;
    }


    @SuppressLint("SetTextI18n")
    private void handleButtonClick(String text) {
        if (text.equals("=")) {
            try {
                int result = generateAnswer(curInput.toString());
                curInput.setLength(0);
                curInput.append(result);
            } catch (Exception e) {
                curInput.setLength(0);
                curInput.append("ERR");
            }
        } else if (text.equals("x")) {
            if (curInput.length() > 0) {
                curInput.deleteCharAt(curInput.length() - 1);
            }
        } else {
            curInput.append(text);
        }
        if (curInput.length() == 0) {
            calcText.setText("CALC");
        } else {
            calcText.setText(curInput.toString());
        }
    }


}
