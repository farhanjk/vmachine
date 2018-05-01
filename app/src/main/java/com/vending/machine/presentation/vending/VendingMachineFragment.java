package com.vending.machine.presentation.vending;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vending.machine.App;
import com.vending.machine.R;
import com.vending.machine.app.common.concurrency.BackgroundThreadPool;
import com.vending.machine.app.common.concurrency.threadqueue.UiThreadQueue;
import com.vending.machine.app.data.ItemInitializer;
import com.vending.machine.app.data.ItemRepository;
import com.vending.machine.app.data.MoneyRepository;
import com.vending.machine.databinding.FragmentVendingMachineBinding;
import com.vending.machine.domain.Item;
import com.vending.machine.domain.Money;
import com.vending.machine.domain.MoneyFormat;
import com.vending.machine.presentation.common.util.AppSnackbar;
import com.vending.machine.presentation.vending.itemlist.ItemListBindingAdapter;

import javax.inject.Inject;

/**
 * Implements VendingMachineView. In MVP, a fragment (or view) is only responsible for
 * on screen updates and does not deal with any business logic (which is delegated to presenter).
 */
public class VendingMachineFragment extends Fragment implements VendingMachineView {

    @Inject
    UiThreadQueue uiThreadQueue;

    @Inject
    BackgroundThreadPool backgroundThreadPool;

    @Inject
    VendingMachineViewModelFactory viewModelFactory;

    @Inject
    ItemInitializer itemInitializer;

    @Inject
    AppSnackbar appSnackbar;

    @Inject
    MoneyRepository moneyRepository;

    @Inject
    ItemRepository itemRepository;

    private FragmentVendingMachineBinding binding;

    private VendingMachinePresenter presenter;
    private ItemListBindingAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vending_machine, container, false);
        binding = DataBindingUtil.bind(view);
        if (binding != null) {
            binding.itemsList.setLayoutManager(new LinearLayoutManager(this.getContext()));
            binding.coinSelection.coinSelector.dropDownButton.setOnClickListener(
                    (view1) -> binding.coinSelection.coinSelector.spinner.performClick());
            binding.itemSelection.itemSelector.dropDownButton.setOnClickListener(
                    (view1) -> binding.itemSelection.itemSelector.spinner.performClick());
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            ((App) getActivity().getApplication()).appComponent().inject(this);

            presenter = new VendingMachinePresenter(uiThreadQueue, backgroundThreadPool, viewModelFactory, moneyRepository, itemRepository, itemInitializer);

            adapter = new ItemListBindingAdapter();
            binding.itemsList.setAdapter(adapter);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        presenter.attach(this);
    }

    @Override
    public void onStop() {
        presenter.detach();

        super.onStop();
    }

    @Override
    public void show(VendingMachineViewModel viewModel) {
        binding.setViewModel(viewModel);

        adapter.setViewModels(viewModel.getItems());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showNoItemSelected() {
        appSnackbar.showFailureSnackbar(getActivity(), getString(R.string.v_m_please_select));
    }

    @Override
    public void onRefundClicked(@NonNull Money total) {
        appSnackbar.showSnackbar(getActivity(), getString(R.string.v_m_refunded, MoneyFormat.formatDisplay(total)));
    }

    @Override
    public void showNoCoinSelected() {
        appSnackbar.showFailureSnackbar(getActivity(), getString(R.string.v_m_please_enter));
    }

    @Override
    public void showTotalFundsExceedAllowedLimit() {
        appSnackbar.showFailureSnackbar(getActivity(), getString(R.string.v_m_no_more));
    }

    @Override
    public void showOutOfInventory(@NonNull Item item) {
        appSnackbar.showFailureSnackbar(getActivity(), getString(R.string.v_m_cannot_dispatch, item));
    }

    @Override
    public void showPurchaseSuccessful(@NonNull Item item) {
        appSnackbar.showSuccessSnackbar(getActivity(), getString(R.string.v_m_item_successfully, item));
    }

    @Override
    public void showNotEnoughFundsToPurchaseItem(@NonNull Item item, @NonNull Money currentTotal) {
        appSnackbar.showFailureSnackbar(getActivity(), getString(R.string.v_m_insufficient_funds, item, MoneyFormat.formatDisplay(currentTotal)));
    }

    @Override
    public void showResetToDefaultComplete() {
        appSnackbar.showSuccessSnackbar(getActivity(), getString(R.string.v_m_item_inventory_back));
    }
}
