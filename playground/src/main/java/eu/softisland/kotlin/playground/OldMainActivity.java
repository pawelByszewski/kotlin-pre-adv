package eu.softisland.kotlin.playground;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class OldMainActivity extends AppCompatActivity {

    Calculator calculator = new Calculator();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculator.setListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                Settings.INSTANCE.setEnabled(true);
                return null;
            }
        });
        calculator.calculate();
    }
}
