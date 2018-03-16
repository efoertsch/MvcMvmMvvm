package com.fisincorporated.mvprxbus;

import android.view.View;

public interface IMvpRxBusEngineeringView {

    IMvpRxBusEngineeringView assignView(View viewById);

    void onDestroy();

}
