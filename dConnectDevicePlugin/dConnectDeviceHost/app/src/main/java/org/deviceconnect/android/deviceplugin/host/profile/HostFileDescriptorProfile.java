/*
 HostFileDescriptorProfile.java
 Copyright (c) 2014 NTT DOCOMO,INC.
 Released under the MIT license
 http://opensource.org/licenses/mit-license.php
 */
package org.deviceconnect.android.deviceplugin.host.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import org.deviceconnect.android.deviceplugin.host.BuildConfig;
import org.deviceconnect.android.deviceplugin.host.HostDeviceService;
import org.deviceconnect.android.deviceplugin.host.file.FileData;
import org.deviceconnect.android.deviceplugin.host.file.FileDataManager;
import org.deviceconnect.android.deviceplugin.host.file.FileDataManager.FileModifiedListener;
import org.deviceconnect.android.event.Event;
import org.deviceconnect.android.event.EventError;
import org.deviceconnect.android.event.EventManager;
import org.deviceconnect.android.message.MessageUtils;
import org.deviceconnect.android.profile.FileDescriptorProfile;
import org.deviceconnect.android.profile.api.DConnectApi;
import org.deviceconnect.android.profile.api.DeleteApi;
import org.deviceconnect.android.profile.api.GetApi;
import org.deviceconnect.android.profile.api.PutApi;
import org.deviceconnect.message.DConnectMessage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * FileDescriptorプロファイル.
 * 
 * @author NTT DOCOMO, INC.
 */
public class HostFileDescriptorProfile extends FileDescriptorProfile {

    /** Error. */
    private static final int ERROR_VALUE_IS_NULL = 100;

    /** ファイルデータ管理クラス. */
    private FileDataManager mFileDataManager;

    /** 時刻フォーマット. */
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss+0900", Locale.getDefault());

    private final DConnectApi mGetOpenApi = new GetApi() {

        @Override
        public String getAttribute() {
            return ATTRIBUTE_OPEN;
        }

        @Override
        public boolean onRequest(final Intent request, final Intent response) {
            String path = getPath(request);
            Flag flag = getFlag(request);
            if (path == null) {
                MessageUtils.setInvalidRequestParameterError(response, "path is invalid.");
            } else if (path.trim().equals("") || path.trim().equals("/")) {
                MessageUtils.setInvalidRequestParameterError(response, "path is invalid.");
            } else if (flag == Flag.UNKNOWN) {
                MessageUtils.setInvalidRequestParameterError(response, "flag is invalid.");
            } else {
                FileDataManager mgr = getFileDataManager();
                FileData file = mgr.getFileData(path);
                if (file != null) {
                    MessageUtils.setIllegalDeviceStateError(response, "Already file is open.");
                } else {
                    try {
                        file = mgr.openFileData(path, flag);
                        if (BuildConfig.DEBUG) {
                            if (file != null) {
                                Log.e("Host", "file is opened.");
                            }
                        }
                        setResult(response, DConnectMessage.RESULT_OK);
                    } catch (IOException e) {
                        MessageUtils.setIllegalDeviceStateError(response, "file cannot open.");
                    } catch (IllegalStateException e) {
                        MessageUtils.setIllegalDeviceStateError(response, "file is already opened.");
                    }
                }
            }
            return true;
        }
    };

    private final DConnectApi mGetReadApi = new GetApi() {

        @Override
        public String getAttribute() {
            return ATTRIBUTE_READ;
        }

        @Override
        public boolean onRequest(final Intent request, final Intent response) {
            String path = getPath(request);
            Long length = getLength(request);
            Long position = getPosition(request);
            if (path == null) {
                MessageUtils.setInvalidRequestParameterError(response, "path is invalid.");
            } else if (length == null || length < 0) {
                MessageUtils.setInvalidRequestParameterError(response, "length is invalid.");
            } else if (position != null && position < 0) {
                MessageUtils.setInvalidRequestParameterError(response, "position is invalid.");
            } else {
                long pos = 0;
                if (position != null) {
                    pos = position;
                }

                FileDataManager mgr = getFileDataManager();
                FileData file = mgr.getFileData(path);
                if (file == null) {
                    MessageUtils.setInvalidRequestParameterError(response, "file is not opened.");
                } else {
                    mgr.readFile(file, (int) pos, length.intValue(), new FileDataManager.ReadFileCallback() {
                        @Override
                        public void onSuccess(@NonNull final String data) {
                            setResult(response, DConnectMessage.RESULT_OK);
                            setSize(response, data.length());
                            setFileData(response, data);
                            sendResponse(response);
                        }

                        @Override
                        public void onFail() {
                            MessageUtils.setIllegalDeviceStateError(response, "file cannot be read.");
                            sendResponse(response);
                        }
                    });
                }
                return false;
            }
            return true;
        }
    };

    private final DConnectApi mPutCloseApi = new PutApi() {

        @Override
        public String getAttribute() {
            return ATTRIBUTE_CLOSE;
        }

        @Override
        public boolean onRequest(final Intent request, final Intent response) {
            String path = getPath(request);
            if (path == null) {
                MessageUtils.setInvalidRequestParameterError(response, "path is invalid.");
            } else {
                FileDataManager mgr = getFileDataManager();
                FileData file = mgr.getFileData(path);
                if (file != null) {
                    boolean isSuccess = mgr.closeFileData(path);
                    if (isSuccess) {
                        setResult(response, DConnectMessage.RESULT_OK);
                    } else {
                        MessageUtils.setIllegalServerStateError(response, "file is not opened.");
                    }
                } else {
                    MessageUtils.setIllegalServerStateError(response, "file is not opened.");
                }
            }
            return true;
        }
    };

    private final DConnectApi mPutWriteApi = new PutApi() {

        @Override
        public String getAttribute() {
            return ATTRIBUTE_WRITE;
        }

        @Override
        public boolean onRequest(final Intent request, final Intent response) {
            String path = getPath(request);
            byte[] data = getContentData(getUri(request));
            Long position = getPosition(request);
            if (path == null) {
                MessageUtils.setInvalidRequestParameterError(response, "path is invalid.");
            } else if (data == null) {
                MessageUtils.setInvalidRequestParameterError(response, "data is invalid.");
            } else if (position != null && position < 0) {
                MessageUtils.setInvalidRequestParameterError(response, "position is invalid.");
            } else {
                int pos = 0;
                if (position != null) {
                    pos = position.intValue();
                }

                FileDataManager mgr = getFileDataManager();
                FileData file = mgr.getFileData(path);
                if (file == null) {
                    MessageUtils.setInvalidRequestParameterError(response, "file is not opened.");
                } else if (file.getFlag() != Flag.RW) {
                    MessageUtils.setInvalidRequestParameterError(response, "file must be opened with Read & Write mode.");
                } else {
                    if (pos < 0 || data.length < pos) {
                        MessageUtils.setInvalidRequestParameterError(response, "position is invalid.");
                    } else {
                        mgr.writeFile(file, data, pos, new FileDataManager.WriteFileCallback() {
                            @Override
                            public void onSuccess() {
                                setResult(response, DConnectMessage.RESULT_OK);
                                sendResponse(response);
                            }

                            @Override
                            public void onFail() {
                                MessageUtils.setUnknownError(response, "file cannot write.");
                                sendResponse(response);
                            }
                        });
                        return false;
                    }
                }
            }
            return true;
        }
    };

    private final DConnectApi mGetOnWatchFileApi = new GetApi() {

        @Override
        public String getAttribute() {
            return ATTRIBUTE_ON_WATCH_FILE;
        }

        @Override
        public boolean onRequest(final Intent request, final Intent response) {
            FileDataManager mgr = getFileDataManager();
            mgr.getUpdatedFiles(new FileDataManager.CheckUpdatedFilesCallback() {
                @Override
                public void onSuccess(@NonNull List<File> files) {
                    if (files.size() > 0) {
                        Bundle f = createFile(files.get(0));
                        if (f != null) {
                            setFile(response, f);
                        }
                    }
                    setResult(response, DConnectMessage.RESULT_OK);
                    sendResponse(response);
                }

                @Override
                public void onFail() {
                    MessageUtils.setIllegalServerStateError(response,
                        "READ_EXTERNAL_STORAGE permission not granted.");
                    sendResponse(response);
                }
            });
            return false;
        }
    };

    private final DConnectApi mPutOnWatchFileApi = new PutApi() {

        @Override
        public String getAttribute() {
            return ATTRIBUTE_ON_WATCH_FILE;
        }

        @Override
        public boolean onRequest(final Intent request, final Intent response) {
            EventError error = EventManager.INSTANCE.addEvent(request);
            if (error == EventError.NONE) {
                List<Event> events = EventManager.INSTANCE.getEventList(request);
                if (events.size() == 1) {
                    mFileDataManager.startTimer();
                }
                setResult(response, DConnectMessage.RESULT_OK);
            } else {
                MessageUtils.setError(response, ERROR_VALUE_IS_NULL, "Can not register event.");
            }
            return true;
        }
    };

    private final DConnectApi mDeleteOnWatchFileApi = new DeleteApi() {

        @Override
        public String getAttribute() {
            return ATTRIBUTE_ON_WATCH_FILE;
        }

        @Override
        public boolean onRequest(final Intent request, final Intent response) {
            EventError error = EventManager.INSTANCE.removeEvent(request);
            if (error == EventError.NONE) {
                List<Event> events = EventManager.INSTANCE.getEventList(request);
                if (events.size() == 0) {
                    mFileDataManager.stopTimer();
                }
                setResult(response, DConnectMessage.RESULT_OK);
            } else {
                MessageUtils.setError(response, ERROR_VALUE_IS_NULL, "Can not unregister event.");
            }
            return true;
        }
    };

    /**
     * コンストラクタ.
     * 
     * @param mgr ファイルデータ管理クラス.
     */
    public HostFileDescriptorProfile(final FileDataManager mgr) {
        mFileDataManager = mgr;
        mFileDataManager.setFileModifiedListener(new FileModifiedListener() {
            @Override
            public void onWatchFile(final List<File> files) {
                sendWatchFileEvent(files);
            }
        });
        addApi(mGetOpenApi);
        addApi(mGetReadApi);
        addApi(mPutCloseApi);
        addApi(mPutWriteApi);
        addApi(mGetOnWatchFileApi);
        addApi(mPutOnWatchFileApi);
        addApi(mDeleteOnWatchFileApi);
    }

    /**
     * onwatchイベントを送信する.
     * 
     * @param files 変更のあったファイル一覧
     */
    private void sendWatchFileEvent(final List<File> files) {
        HostDeviceService service = (HostDeviceService) getContext();

        List<Event> events = EventManager.INSTANCE.getEventList(HostDeviceService.SERVICE_ID, PROFILE_NAME,
                null, ATTRIBUTE_ON_WATCH_FILE);
        // TODO:Eventが無くなっていたらlistener 解除を行うように実装。
        synchronized (events) {
            for (File f : files) {
                Bundle file = createFile(f);
                if (file != null) {
                    for (Event event : events) {
                        Intent intent = EventManager.createEventMessage(event);
                        setFile(intent, file);
                        service.sendEvent(intent, event.getAccessToken());
                    }
                }
            }
        }
    }

    /**
     * Fileデータを作成する.
     * 
     * @param f ファイル
     * @return Fileデータ
     */
    private Bundle createFile(final File f) {
        String path = mFileDataManager.getPath(f);
        if (path == null) {
            return null;
        }

        Bundle file = new Bundle();
        setPath(file, path);
        setCurr(file, mDateFormat.format(new Date(f.lastModified())));
        return file;
    }

    /**
     * ファイル管理クラスを取得する.
     * 
     * @return ファイル管理クラス
     */
    private FileDataManager getFileDataManager() {
        return mFileDataManager;
    }
}
