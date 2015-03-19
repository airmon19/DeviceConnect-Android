/*
 ChromeCastNotificationProfile.java
 Copyright (c) 2014 NTT DOCOMO,INC.
 Released under the MIT license
 http://opensource.org/licenses/mit-license.php
 */
package org.deviceconnect.android.deviceplugin.chromecast.profile;

import org.deviceconnect.android.deviceplugin.chromecast.ChromeCastService;
import org.deviceconnect.android.deviceplugin.chromecast.core.ChromeCastMessage;
import org.deviceconnect.android.message.MessageUtils;
import org.deviceconnect.android.profile.NotificationProfile;
import org.deviceconnect.message.DConnectMessage;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

/**
 * Notification プロファイル (Chromecast).
 * <p>
 * Chromecastのノーティフィケーションの操作機能を提供する
 * </p>
 * @author NTT DOCOMO, INC.
 */
public class ChromeCastNotificationProfile extends NotificationProfile implements ChromeCastConstants {
    /** Chromecastが無効になっているときのエラーメッセージ. */
    private static final String ERROR_MESSAGE_DEVICE_NOT_ENABLED = "Chromecast is not enabled.";

    /**
     * デバイスが有効か否かを返す<br/>.
     * デバイスが無効の場合、レスポンスにエラーを設定する
     * 
     * @param   response    レスポンス
     * @param   app         ChromeCastMediaPlayer
     * @return  デバイスが有効か否か（有効: true, 無効: false）
     */
    private boolean isDeviceEnable(final Intent response, final ChromeCastMessage app) {
        if (!app.isDeviceEnable()) {
            MessageUtils.setIllegalDeviceStateError(response, ERROR_MESSAGE_DEVICE_NOT_ENABLED);
            setResult(response, DConnectMessage.RESULT_ERROR);
            return false;
        }
        return true;
    }

    @Override
    protected boolean onPostNotify(final Intent request, final Intent response,
            final String serviceId, final NotificationType type, final Direction dir,
            final String lang, final String body, final String tag,
            final byte[] iconData) {
        ChromeCastMessage app = ((ChromeCastService) getContext()).getChromeCastMessage();
        if (body == null) {
            MessageUtils.setInvalidRequestParameterError(response, "body is null");
            response.putExtra(DConnectMessage.EXTRA_RESULT, DConnectMessage.RESULT_ERROR);
            return true;
        }
        switch (type) {
        case PHONE: break;
        case MAIL:  break;
        case SMS:   break;
        case EVENT: break;
        default:
            MessageUtils.setInvalidRequestParameterError(response, "type is null or invalid");
            response.putExtra(DConnectMessage.EXTRA_RESULT, DConnectMessage.RESULT_ERROR);
            return true;
        }

        if (!isDeviceEnable(response, app)) {
            return true;
        }
        try {
            JSONObject json = new JSONObject();
            json.put(KEY_FUNCTION, FUNCTION_POST_NOTIFICATION);
            json.put(KEY_TYPE, type.getValue());
            json.put(KEY_MESSAGE, body);
            app.sendMessage(response, json.toString());
            return false;
        } catch (JSONException e) {
            MessageUtils.setUnknownError(response);
            return true;
        }
    }

    @Override
    protected boolean onDeleteNotify(final Intent request,
            final Intent response, final String serviceId,
            final String notificationId) {
        ChromeCastMessage app = ((ChromeCastService) getContext()).getChromeCastMessage();
        if (!isDeviceEnable(response, app)) {
            return true;
        }
        try {
            JSONObject json = new JSONObject();
            json.put(KEY_FUNCTION, FUNCTION_DELETE_NOTIFICATION);
            app.sendMessage(response, json.toString());
            return false;
        } catch (JSONException e) {
            MessageUtils.setUnknownError(response);
            return true;
        }
    }

}
