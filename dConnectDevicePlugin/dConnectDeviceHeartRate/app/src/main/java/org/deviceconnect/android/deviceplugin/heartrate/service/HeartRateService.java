package org.deviceconnect.android.deviceplugin.heartrate.service;


import android.bluetooth.BluetoothDevice;

import org.deviceconnect.android.service.DConnectService;

public class HeartRateService extends DConnectService {

    public HeartRateService(final BluetoothDevice device) {
        super(device.getAddress());
        setName(device.getName());
        setNetworkType(NetworkType.BLE);
    }

}
