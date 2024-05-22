package com.DistributedSystems.room_booking_android_app.homepage;

import com.DistributedSystems.room_booking_android_app.utils.Dao;

public class HomepagePresenter {

    private final HomepageView view;


    public HomepagePresenter(HomepageView view) {
        this.view = view;
    }


    void onManagerConnection(String managerName, boolean insertButtonEnabled) {
        String ERROR_NO_MANAGER_NAME = "Please give a manager name.";

        if (!insertButtonEnabled) {
            view.showToast(ERROR_NO_MANAGER_NAME);
            return;
        }

        new SendManagerNameThread(managerName).start();
        Dao.setManagerName(managerName);

        view.managerConnection();
    }

    void onCustomerConnection() {
        new SendCustomerThread().start();
        view.customerConnection();
    }
}
