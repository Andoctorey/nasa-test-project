package by.yegorov.nasa.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        ButterKnife.bind(this);
        inject();
    }

    protected abstract int setContentView();

    protected abstract void inject();
}
