package com.ualr.recyclerviewassignment.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;

public class SharedModel extends ViewModel {
    private static final int NO_SELECTION = -1;
    private final MutableLiveData<List<Inbox>> inboxList = new MutableLiveData<>();
    private final MutableLiveData<Integer> selectedIndex = new MutableLiveData<>(NO_SELECTION);

    public LiveData<List<Inbox>> getInboxList() {
        return inboxList;
    }

    public void setInboxList(List<Inbox> inboxList) {
        this.inboxList.setValue(inboxList);
    }

    public LiveData<Integer> getSelectedIndex() {
        return selectedIndex;
    }
    public void setSelectedIndex(int selected) {
        this.selectedIndex.setValue(selected);
    }
}
