package me.blog.hwani6736.socar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import me.blog.hwani6736.socar.minsweeper.MineSweeperActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputHorizontal;
    private EditText inputVertical;
    private EditText inputLandMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputHorizontal = findViewById(R.id.input_horizontal);
        inputVertical = findViewById(R.id.input_vertical);
        inputLandMine = findViewById(R.id.input_landmine);

        inputHorizontal.setFilters(new InputFilter[]{ new MinMaxFilter("1", "20")});
        inputVertical.setFilters(new InputFilter[]{ new MinMaxFilter("1", "50")});

        findViewById(R.id.btn_play).setOnClickListener((v)->{
            if (isValidNumber()) {
                launchMineSweeperActivity();
            } else {
                Toast.makeText(getBaseContext(), R.string.toast_landmine_over, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void launchMineSweeperActivity() {

        int numberHor = extractNumber(inputHorizontal);
        int numberVer = extractNumber(inputVertical);
        int numberMine = extractNumber(inputLandMine);

        Intent intent = new Intent(MainActivity.this.getBaseContext(), MineSweeperActivity.class);
        intent.putExtra(MineSweeperActivity.EXTRA_HORIZONTAL, numberHor);
        intent.putExtra(MineSweeperActivity.EXTRA_VERTICAL, numberVer);
        intent.putExtra(MineSweeperActivity.EXTRA_LANDMINE, numberMine);

        startActivity(intent);
    }

    private boolean isValidNumber() {

        int numberHor = extractNumber(inputHorizontal);
        int numberVer = extractNumber(inputVertical);
        int numberMine = extractNumber(inputLandMine);

        return numberHor * numberVer > numberMine;
    }

    private int extractNumber(EditText input) {
        Editable inputText = input.getText();
        return TextUtils.isEmpty(inputText) ? 10 : Integer.valueOf(inputText.toString());
    }

    private class MinMaxFilter implements InputFilter {

        private int mIntMin, mIntMax;

        public MinMaxFilter(int minValue, int maxValue) {
            this.mIntMin = minValue;
            this.mIntMax = maxValue;
        }

        public MinMaxFilter(String minValue, String maxValue) {
            this.mIntMin = Integer.parseInt(minValue);
            this.mIntMax = Integer.parseInt(maxValue);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(mIntMin, mIntMax, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }
}
