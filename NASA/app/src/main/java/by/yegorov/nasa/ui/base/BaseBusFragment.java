package by.yegorov.nasa.ui.base;

import android.content.Intent;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import timber.log.Timber;

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class BaseBusFragment extends BaseFragment {

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!isRegistered) {
            isRegistered = true;
            Timber.i("bus.register " + this);
            bus.register(this);
        }
    }
}