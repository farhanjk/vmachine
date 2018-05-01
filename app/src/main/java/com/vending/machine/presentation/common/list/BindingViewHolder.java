
package com.vending.machine.presentation.common.list;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;


public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    final T layoutBinding;

    public BindingViewHolder(T layoutBinding) {
        super(layoutBinding.getRoot());
        this.layoutBinding = layoutBinding;
    }

    public T getBinding() {
        return layoutBinding;
    }
}
