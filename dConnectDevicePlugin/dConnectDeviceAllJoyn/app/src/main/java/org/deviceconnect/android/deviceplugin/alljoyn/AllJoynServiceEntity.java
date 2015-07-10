package org.deviceconnect.android.deviceplugin.alljoyn;

import android.support.annotation.NonNull;
import android.util.Log;

import org.alljoyn.about.AboutKeys;
import org.alljoyn.bus.BusException;
import org.alljoyn.bus.Variant;
import org.alljoyn.services.common.BusObjectDescription;

import java.util.Map;

/**
 * AllJoynServiceEntitiy represents an AllJoyn service.
 * An AllJoynServiceEntity object is created from AllJoyn About announcement.
 *
 * @author NTT DOCOMO, INC.
 * @see <a href="https://allseenalliance.org/developers/learn/core/about-announcement/interface">
 * https://allseenalliance.org/developers/learn/core/about-announcement/interface</a>
 */
public class AllJoynServiceEntity {
    /**
     * Human-friendly service name.
     */
    public String serviceName;
    @NonNull
    public String busName;
    public short port;
    public Map<String, Variant> aboutData;

    // Flattened data from aboutData
    public byte[] appId;
    public String defaultLanguage;
    public String deviceName;
    public String deviceId;
    public String appName;
    public String manufacturer;
    public String modelNumber;
    public String[] supportedLanguages;
    public String description;
    public String dateOfManufacture;
    public String softwareVersion;
    public String ajSoftwareVersion;
    public String hardwareVersion;
    public String supportUrl;

    @NonNull
    public BusObjectDescription[] proxyObjects;
    //    public String objPath;

    public Integer sessionId;

    public AllJoynServiceEntity(@NonNull String busName, short port,
                                Map<String, Variant> aboutData,
                                @NonNull BusObjectDescription[] proxyObjects) {
        this.busName = busName;
        this.port = port;
        this.aboutData = aboutData;
        this.proxyObjects = proxyObjects;

        flattenAboutData();

        determineServiceName();
    }

    /**
     * Flatten <code>aboutData</code> to member fields.
     */
    private void flattenAboutData() {
        for (String key : aboutData.keySet()) {
            Variant val = aboutData.get(key);
            try {
                switch (key) {
                    case AboutKeys.ABOUT_APP_ID:
                        appId = val.getObject(byte[].class);
                        break;
                    case AboutKeys.ABOUT_DEFAULT_LANGUAGE:
                        // If default language is not specified, set it to English.
                        try {
                            defaultLanguage = val.getObject(String.class);
                        } catch (Exception ignore) {
                            defaultLanguage = "en";
                        }
                        if (defaultLanguage == null || defaultLanguage.length() == 0) {
                            defaultLanguage = "en";
                        }
                        break;
                    case AboutKeys.ABOUT_DEVICE_NAME:
                        deviceName = val.getObject(String.class);
                        break;
                    case AboutKeys.ABOUT_DEVICE_ID:
                        deviceId = val.getObject(String.class);
                        break;
                    case AboutKeys.ABOUT_APP_NAME:
                        appName = val.getObject(String.class);
                        break;
                    case AboutKeys.ABOUT_MANUFACTURER:
                        manufacturer = val.getObject(String.class);
                        break;
                    case AboutKeys.ABOUT_MODEL_NUMBER:
                        modelNumber = val.getObject(String.class);
                        break;
                    case AboutKeys.ABOUT_SUPPORTED_LANGUAGES:
                        supportedLanguages = val.getObject(String[].class);
                        break;
                    case AboutKeys.ABOUT_DESCRIPTION:
                        description = val.getObject(String.class);
                        break;
                    case AboutKeys.ABOUT_DATE_OF_MANUFACTURE:
                        dateOfManufacture = val.getObject(String.class);
                        break;
                    case AboutKeys.ABOUT_SOFTWARE_VERSION:
                        softwareVersion = val.getObject(String.class);
                        break;
                    case AboutKeys.ABOUT_AJ_SOFTWARE_VERSION:
                        ajSoftwareVersion = val.getObject(String.class);
                        break;
                    case AboutKeys.ABOUT_HARDWARE_VERSION:
                        hardwareVersion = val.getObject(String.class);
                        break;
                    case AboutKeys.ABOUT_SUPPORT_URL:
                        supportUrl = val.getObject(String.class);
                        break;
                }
            } catch (Exception e) {
                Log.w(getClass().getSimpleName(), "Failed to parse \"" + key + "\": "
                        + e.getLocalizedMessage());
            }
        }
    }

    /**
     * Determine a DeviceConnect service name.
     */
    private void determineServiceName() {
        if (aboutData.containsKey(AboutKeys.ABOUT_DEVICE_NAME)) {
            try {
                Variant val = aboutData.get(AboutKeys.ABOUT_DEVICE_NAME);
                serviceName = val.getObject(String.class);
            } catch (BusException e) {
                Log.w(getClass().getSimpleName(),
                        "Failed to parse about announcement (1).");
                serviceName = "Alljoyn service (" + busName.hashCode() + ")";
            }
        } else {
            serviceName = "Alljoyn service (" + busName.hashCode() + ")";
        }
    }
}
