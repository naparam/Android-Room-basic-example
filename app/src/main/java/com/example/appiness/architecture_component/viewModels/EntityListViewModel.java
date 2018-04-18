package com.example.appiness.architecture_component.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.appiness.architecture_component.db.AppDatabase;
import com.example.appiness.architecture_component.models.EntityModel;

import java.util.List;

/**
 * Created by appiness on 2/3/18.
 */

public class EntityListViewModel extends AndroidViewModel {
    private final LiveData<List<EntityModel>> itemAndPersonList;

    private AppDatabase appDatabase;

    public EntityListViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        itemAndPersonList = appDatabase.itemAndPersonModel().getAllBorrowedItems();
    }

    public LiveData<List<EntityModel>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public void deleteItem(EntityModel entityModel) {
        new deleteAsyncTask(appDatabase).execute(entityModel);
    }

    private static class deleteAsyncTask extends AsyncTask<EntityModel, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final EntityModel... params) {
            db.itemAndPersonModel().deleteBorrow(params[0]);
            return null;
        }

    }

}
