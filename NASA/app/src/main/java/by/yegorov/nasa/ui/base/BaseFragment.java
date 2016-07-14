package by.yegorov.nasa.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressWarnings("WeakerAccess")
public abstract class BaseFragment extends Fragment {

    private Unbinder binder;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binder = ButterKnife.bind(this, view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        inject();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }

    protected abstract void inject();

    protected Bundle safeGetArguments() {
        if (getArguments() != null) {
            return getArguments();
        }

        return new Bundle();
    }
}
