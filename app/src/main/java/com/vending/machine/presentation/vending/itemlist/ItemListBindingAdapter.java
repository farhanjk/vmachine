package com.vending.machine.presentation.vending.itemlist;

import android.databinding.ViewDataBinding;

import com.vending.machine.R;
import com.vending.machine.databinding.LayoutListItemBinding;
import com.vending.machine.presentation.common.list.ViewModelBindingAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for the list of items.
 */
public class ItemListBindingAdapter extends ViewModelBindingAdapter<ItemViewModel, ViewDataBinding> {

    public ItemListBindingAdapter() {
        super(createViewTypes());
    }

    @Override
    protected void updateBinding(final ViewDataBinding binding, final int position) {

        ItemViewModel viewModel = getViewModels().get(position);

        LayoutListItemBinding itemBinding = (LayoutListItemBinding) binding;
        itemBinding.setViewModel(viewModel);
    }

    private static List<ViewType> createViewTypes() {
        List<ViewType> viewTypes = new ArrayList<>();
        viewTypes.add(new ViewType(R.layout.layout_list_item, ItemViewModel.class));
        return viewTypes;
    }
}
