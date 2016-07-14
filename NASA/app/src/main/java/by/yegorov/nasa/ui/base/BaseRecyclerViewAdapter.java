package by.yegorov.nasa.ui.base;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

@SuppressWarnings("WeakerAccess")
public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder> {

    protected final Context context;
    protected final LayoutInflater inflater;

    public BaseRecyclerViewAdapter(final Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        inject();
    }

    protected abstract void inject();

    public abstract ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindViewHolder(ViewHolder holder, int position);

    public abstract int getItemCount();


    public static abstract class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
