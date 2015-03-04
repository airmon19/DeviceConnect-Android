/*
 SWTouchProfile.java
 Copyright (c) 2015 NTT DOCOMO,INC.
 Released under the MIT license
 http://opensource.org/licenses/mit-license.php
 */
package org.deviceconnect.android.deviceplugin.sw.profile;

import org.deviceconnect.android.deviceplugin.sw.R;
import org.deviceconnect.android.deviceplugin.sw.SWConstants;
import org.deviceconnect.android.event.EventError;
import org.deviceconnect.android.event.EventManager;
import org.deviceconnect.android.message.MessageUtils;
import org.deviceconnect.android.profile.TouchProfile;
import org.deviceconnect.message.DConnectMessage;

import com.sonyericsson.extras.liveware.aef.control.Control;
import com.sonyericsson.extras.liveware.aef.registration.Registration;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;

/**
 * SonySW device plug-in {@link KeyEventProfile} implementation.
 * 
 * @author NTT DOCOMO, INC.
 */
public class SWTouchProfile extends TouchProfile {

    @Override
    protected boolean onPutOnTouch(final Intent request, final Intent response, final String serviceId,
            final String sessionKey) {
        BluetoothDevice device = SWUtil.findSmartWatch(serviceId);
        if (device == null) {
            MessageUtils.setNotFoundServiceError(response, "No service is found: " + serviceId);
            return true;
        }
        EventError error = EventManager.INSTANCE.addEvent(request);
        if (error == EventError.NONE) {
            setResult(response, DConnectMessage.RESULT_OK);
            displayTouchScreen(SWUtil.toHostAppPackageName(device.getName()), serviceId);
        } else if (error == EventError.INVALID_PARAMETER) {
            MessageUtils.setInvalidRequestParameterError(response);
        } else {
            MessageUtils.setUnknownError(response);
        }
        return true;
    }

    @Override
    protected boolean onPutOnTouchStart(final Intent request, final Intent response, final String serviceId,
            final String sessionKey) {
        BluetoothDevice device = SWUtil.findSmartWatch(serviceId);
        if (device == null) {
            MessageUtils.setNotFoundServiceError(response, "No service is found: " + serviceId);
            return true;
        }
        EventError error = EventManager.INSTANCE.addEvent(request);
        if (error == EventError.NONE) {
            setResult(response, DConnectMessage.RESULT_OK);
            displayTouchScreen(SWUtil.toHostAppPackageName(device.getName()), serviceId);
        } else if (error == EventError.INVALID_PARAMETER) {
            MessageUtils.setInvalidRequestParameterError(response);
        } else {
            MessageUtils.setUnknownError(response);
        }
        return true;
    }

    @Override
    protected boolean onPutOnTouchEnd(final Intent request, final Intent response, final String serviceId,
            final String sessionKey) {
        BluetoothDevice device = SWUtil.findSmartWatch(serviceId);
        if (device == null) {
            MessageUtils.setNotFoundServiceError(response, "No service is found: " + serviceId);
            return true;
        }
        EventError error = EventManager.INSTANCE.addEvent(request);
        if (error == EventError.NONE) {
            setResult(response, DConnectMessage.RESULT_OK);
            displayTouchScreen(SWUtil.toHostAppPackageName(device.getName()), serviceId);
        } else if (error == EventError.INVALID_PARAMETER) {
            MessageUtils.setInvalidRequestParameterError(response);
        } else {
            MessageUtils.setUnknownError(response);
        }
        return true;
    }

    @Override
    protected boolean onPutOnDoubleTap(final Intent request, final Intent response, final String serviceId,
            final String sessionKey) {
        BluetoothDevice device = SWUtil.findSmartWatch(serviceId);
        if (device == null) {
            MessageUtils.setNotFoundServiceError(response, "No service is found: " + serviceId);
            return true;
        }
        EventError error = EventManager.INSTANCE.addEvent(request);
        if (error == EventError.NONE) {
            setResult(response, DConnectMessage.RESULT_OK);
            displayTouchScreen(SWUtil.toHostAppPackageName(device.getName()), serviceId);
        } else if (error == EventError.INVALID_PARAMETER) {
            MessageUtils.setInvalidRequestParameterError(response);
        } else {
            MessageUtils.setUnknownError(response);
        }
        return true;
    }

    @Override
    protected boolean onPutOnTouchMove(final Intent request, final Intent response, final String serviceId,
            final String sessionKey) {
        // SW not support "TouchMove".
        MessageUtils.setNotSupportAttributeError(response);
        return true;
    }

    @Override
    protected boolean onPutOnTouchCancel(final Intent request, final Intent response, final String serviceId,
            final String sessionKey) {
        // SW not support "TouchCancel".
        MessageUtils.setNotSupportAttributeError(response);
        return true;
    }

    @Override
    protected boolean onDeleteOnTouch(final Intent request, final Intent response, final String serviceId,
            final String sessionKey) {
        BluetoothDevice device = SWUtil.findSmartWatch(serviceId);
        if (device == null) {
            MessageUtils.setNotFoundServiceError(response, "No service is found: " + serviceId);
            return true;
        }
        EventError error = EventManager.INSTANCE.removeEvent(request);
        if (error == EventError.NONE) {
            setResult(response, DConnectMessage.RESULT_OK);
            clearTouchScreen(SWUtil.toHostAppPackageName(device.getName()), serviceId);
        } else if (error == EventError.INVALID_PARAMETER) {
            MessageUtils.setInvalidRequestParameterError(response);
        } else {
            MessageUtils.setUnknownError(response);
        }
        return true;
    }

    @Override
    protected boolean onDeleteOnTouchStart(final Intent request, final Intent response, final String serviceId,
            final String sessionKey) {
        BluetoothDevice device = SWUtil.findSmartWatch(serviceId);
        if (device == null) {
            MessageUtils.setNotFoundServiceError(response, "No service is found: " + serviceId);
            return true;
        }
        EventError error = EventManager.INSTANCE.removeEvent(request);
        if (error == EventError.NONE) {
            setResult(response, DConnectMessage.RESULT_OK);
            clearTouchScreen(SWUtil.toHostAppPackageName(device.getName()), serviceId);
        } else if (error == EventError.INVALID_PARAMETER) {
            MessageUtils.setInvalidRequestParameterError(response);
        } else {
            MessageUtils.setUnknownError(response);
        }
        return true;
    }

    @Override
    protected boolean onDeleteOnTouchEnd(final Intent request, final Intent response, final String serviceId,
            final String sessionKey) {
        BluetoothDevice device = SWUtil.findSmartWatch(serviceId);
        if (device == null) {
            MessageUtils.setNotFoundServiceError(response, "No service is found: " + serviceId);
            return true;
        }
        EventError error = EventManager.INSTANCE.removeEvent(request);
        if (error == EventError.NONE) {
            setResult(response, DConnectMessage.RESULT_OK);
            clearTouchScreen(SWUtil.toHostAppPackageName(device.getName()), serviceId);
        } else if (error == EventError.INVALID_PARAMETER) {
            MessageUtils.setInvalidRequestParameterError(response);
        } else {
            MessageUtils.setUnknownError(response);
        }
        return true;
    }

    @Override
    protected boolean onDeleteOnDoubleTap(final Intent request, final Intent response, final String serviceId,
            final String sessionKey) {
        BluetoothDevice device = SWUtil.findSmartWatch(serviceId);
        if (device == null) {
            MessageUtils.setNotFoundServiceError(response, "No service is found: " + serviceId);
            return true;
        }
        EventError error = EventManager.INSTANCE.removeEvent(request);
        if (error == EventError.NONE) {
            setResult(response, DConnectMessage.RESULT_OK);
            clearTouchScreen(SWUtil.toHostAppPackageName(device.getName()), serviceId);
        } else if (error == EventError.INVALID_PARAMETER) {
            MessageUtils.setInvalidRequestParameterError(response);
        } else {
            MessageUtils.setUnknownError(response);
        }
        return true;
    }

    @Override
    protected boolean onDeleteOnTouchMove(final Intent request, final Intent response, final String serviceId,
            final String sessionKey) {
        // SW not support "TouchMove".
        MessageUtils.setNotSupportAttributeError(response);
        return true;
    }

    @Override
    protected boolean onDeleteOnTouchCancel(final Intent request, final Intent response, final String serviceId,
            final String sessionKey) {
        // SW not support "TouchCancel".
        MessageUtils.setNotSupportAttributeError(response);
        return true;
    }

    /**
     * Display Touch screen.
     * 
     * @param deviceName Device Name
     * @param serviceId serviceID
     */
    protected void displayTouchScreen(final String deviceName, final String serviceId) {
        Intent intent = new Intent(Control.Intents.CONTROL_PROCESS_LAYOUT_INTENT);
        if (SWConstants.PACKAGE_SMART_WATCH_2.equals(deviceName)) {
            intent.putExtra(Control.Intents.EXTRA_DATA_XML_LAYOUT, R.layout.touch_control_sw2);
        } else {
            return; // This function not implemented. Because SW could not redraw xml layout data.
        }
        sendToHostApp(intent, serviceId);
    }

    /**
     * Clear Touch screen.
     * 
     * @param deviceName Device Name
     * @param serviceId serviceID
     */
    protected void clearTouchScreen(final String deviceName, final String serviceId) {
        if (SWConstants.PACKAGE_SMART_WATCH_2.equals(deviceName)) {
            Intent intent = new Intent(Control.Intents.CONTROL_CLEAR_DISPLAY_INTENT);
            sendToHostApp(intent, serviceId);
            intent = new Intent(Control.Intents.CONTROL_PROCESS_LAYOUT_INTENT);
            intent.putExtra(Control.Intents.EXTRA_DATA_XML_LAYOUT, R.layout.touch_clear_control_sw2);
            sendToHostApp(intent, serviceId);
        } else  {
            return; // This function not implemented. Because SW could not redraw xml layout data.
        }
    }

    /**
     * Send intent to Host application.
     * 
     * @param intent Intent.
     * @param serviceId ServiceID
     */
    protected void sendToHostApp(final Intent intent, final String serviceId) {
        BluetoothDevice device = SWUtil.findSmartWatch(serviceId);
        String deviceName = device.getName();
        intent.putExtra(Control.Intents.EXTRA_AEA_PACKAGE_NAME, getContext().getPackageName());
        intent.setPackage(SWUtil.toHostAppPackageName(deviceName));
        getContext().sendBroadcast(intent, Registration.HOSTAPP_PERMISSION);
    }
}
