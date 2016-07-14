package by.yegorov.nasa.ui.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Toast;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import by.yegorov.nasa.R;
import timber.log.Timber;


@SuppressWarnings("unused")
public abstract class BaseBusActivity extends BaseActivity {

    @Inject
    Bus bus;

    private boolean isRegistered;

    @Override
    public void onStart() {
        super.onStart();
        if (!isRegistered) {
            isRegistered = true;
            Timber.i("bus.register " + this);
            bus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (isRegistered) {
            isRegistered = false;
            Timber.i("bus.unregister " + this);
            bus.unregister(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!isRegistered) {
            isRegistered = true;
            Timber.i("bus.register " + this);
            bus.register(this);
        }
    }

    protected ProgressDialog progressDialog;

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.setCancelable(true);
    }

    protected void progress(boolean show) {
        if (!isFinishing()) {
            if (show) {
                if (progressDialog == null) {
                    initProgressDialog();
                }

                progressDialog.show();
            } else if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

    protected void handleMessage(String message, boolean hasError) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else if (hasError) {
            Toast.makeText(this, getString(R.string.server_error), Toast.LENGTH_SHORT).show();
        }
    }
}
