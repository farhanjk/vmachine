package com.vending.machine.app.common.mvp.viewmodel;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.vending.machine.BR;
import com.vending.machine.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class DropDownFieldViewModel<KEY> extends InputFieldViewModel {

    private Map<KEY, String> mapValues = new LinkedHashMap<>();

    private int selectedPosition;

    public KEY getKey() {

        if (selectedPosition != -1 && mapValues != null && mapValues.size() > 0) {
            return (new ArrayList<>(mapValues.keySet())).get(selectedPosition);
        }

        return null;
    }

    @Bindable
    public Map<KEY, String> getMapValues() {
        return mapValues;
    }

    public void setMapValues(Map<KEY, String> mapValues) {
        this.mapValues = mapValues;
        notifyPropertyChanged(BR.mapValues);
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    private static ArrayAdapter<KeyValuePair> createSpinnerAdapter(Spinner spinner, DropDownFieldViewModel<?> viewModel) {

        ArrayAdapter<KeyValuePair> adapter = new ArrayAdapter<>(spinner.getContext(), R.layout.invisible_spinner_item);
        for (Map.Entry<?, String> mapEntry : viewModel.getMapValues().entrySet()) {
            adapter.add(new KeyValuePair<>(mapEntry.getKey(), mapEntry.getValue()));
        }

        spinner.setAdapter(adapter);

        return adapter;
    }

    @BindingAdapter("bindSpinner")
    public static void bindSpinner(Spinner spinner, DropDownFieldViewModel<?> viewModel) {
        if (viewModel == null || viewModel.getMapValues() == null || viewModel.getMapValues().size() == 0) {
            return;
        }

        ArrayAdapter<KeyValuePair> adapter = createSpinnerAdapter(spinner, viewModel);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            boolean mIsSpinnerFirstCall = true;

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (!mIsSpinnerFirstCall) {
                    viewModel.setSelectedPosition(position);
                    viewModel.setValue(adapter.getItem(position).getValue());
                }
                mIsSpinnerFirstCall = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                viewModel.setSelectedPosition(-1);
                viewModel.setValue("");
            }
        });

    }

}
