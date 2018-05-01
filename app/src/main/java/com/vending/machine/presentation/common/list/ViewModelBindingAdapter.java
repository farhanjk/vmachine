package com.vending.machine.presentation.common.list;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.vending.machine.app.util.QueryUtil;
import com.vending.machine.app.util.Verify;

import java.util.ArrayList;
import java.util.List;

public abstract class ViewModelBindingAdapter<T, B extends ViewDataBinding> extends BindingAdapter<B> {

    private List<ViewType> viewTypes;
    private List<T> viewModels;

    public ViewModelBindingAdapter(List<ViewType> viewTypes) {
        super(QueryUtil.select(viewTypes, v -> v.layout));
        this.viewTypes = viewTypes;
        viewModels = new ArrayList<>();
    }

    public final List<T> getViewModels() {
        return this.viewModels;
    }

    public void setViewModels(@NonNull List<T> viewModels) {
        this.viewModels = Verify.notNull(viewModels, "View models are null");
        notifyDataSetChanged();
    }

    public void remove(int position) {
        T viewModel = viewModels.get(position);
        viewModels.remove(viewModel);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemViewType(int position) {
        Object viewModel = viewModels.get(position);
        List<ViewType> viewTypes = this.viewTypes;
        for (int i = 0; i < viewTypes.size(); i++) {
            ViewType viewType = viewTypes.get(i);
            if (viewType.viewModelType.equals(viewModel.getClass())) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return viewModels.size();
    }

    public static class ViewType {
        private int layout;
        private Class viewModelType;

        public ViewType(int layout, Class viewModelType) {
            this.layout = layout;
            this.viewModelType = viewModelType;
        }
    }

}
