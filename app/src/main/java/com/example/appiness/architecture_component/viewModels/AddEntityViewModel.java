package com.example.appiness.architecture_component.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import com.example.appiness.architecture_component.db.AppDatabase;
import com.example.appiness.architecture_component.models.EntityModel;

/**
 * Created by appiness on 2/3/18.
 */

public class AddEntityViewModel extends AndroidViewModel {
    private AppDatabase appDatabase;

    public AddEntityViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

    }

    public void addBorrow(final EntityModel entityModel) {
        new addAsyncTask(appDatabase).execute(entityModel);
    }

    private static class addAsyncTask extends AsyncTask<EntityModel, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final EntityModel... params) {
            db.itemAndPersonModel().addBorrow(params[0]);
            return null;
        }

    }
}
