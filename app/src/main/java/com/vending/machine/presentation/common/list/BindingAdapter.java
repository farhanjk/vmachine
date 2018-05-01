package com.vending.machine.presentation.common.list;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

public abstract class BindingAdapter<T extends ViewDataBinding> extends RecyclerView.Adapter<BindingViewHolder<T>> {

    private final List<Integer> layoutResourceIds;

    public BindingAdapter(int layoutResourceId) {
        this(Arrays.asList(layoutResourceId));
    }

    public BindingAdapter(List<Integer> resourceIds) {
        layoutResourceIds = resourceIds;
    }

    @Override
    public BindingViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        T binding = createBindingForViewType(parent, viewType);

        return new BindingViewHolder<>(binding);
    }

    protected T createBindingForViewType(ViewGroup parent, int viewType) {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutResourceIds.get(viewType), parent, false);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<T> holder, int position) {
        T binding = holder.getBinding();
        updateBinding(binding, position);
    }

    @Override
    public void onViewRecycled(BindingViewHolder<T> holder) {
        super.onViewRecycled(holder);
        T binding = holder.getBinding();
        recycleBinding(binding);
    }

    protected void recycleBinding(T binding) {
    }

    protected int getListWidth() {
        return 1;
    }

    protected abstract void updateBinding(T binding, int position);
}
