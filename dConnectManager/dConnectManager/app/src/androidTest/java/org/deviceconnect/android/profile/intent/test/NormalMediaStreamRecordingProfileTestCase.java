/*
 NormalMediaStreamRecordingProfileTestCase.java
 Copyright (c) 2014 NTT DOCOMO,INC.
 Released under the MIT license
 http://opensource.org/licenses/mit-license.php
 */
package org.deviceconnect.android.profile.intent.test;

import android.content.Intent;
import android.support.test.runner.AndroidJUnit4;

import org.deviceconnect.android.test.plugin.profile.TestMediaStreamRecordingProfileConstants;
import org.deviceconnect.message.DConnectMessage;
import org.deviceconnect.message.intent.message.IntentDConnectMessage;
import org.deviceconnect.profile.MediaStreamRecordingProfileConstants;
import org.junit.Test;
import org.junit.runner.RunWith;



/**
 * MediaStreamRecordingプロファイルの正常系テスト.
 * @author NTT DOCOMO, INC.
 */
@RunWith(AndroidJUnit4.class)
public class NormalMediaStreamRecordingProfileTestCase extends IntentDConnectTestCase
    implements TestMediaStreamRecordingProfileConstants {

    /**
     * 指定したスマートデバイス上で使用可能なカメラ情報を取得するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: GET
     * Extra:
     *     profile=mediastream_recording
     *     attribute=mediarecorder
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * ・recordersに長さ1のBundle配列が格納されていること。
     * ・recorder[0].idが"test_camera_0"であること。
     * ・recorder[0].stateが"inactive"であること。
     * ・recorder[0].imageWidthが1920であること。
     * ・recorder[0].imageHeightが1080であること。
     * ・recorder[0].previewWidthが640であること。
     * ・recorder[0].previewHeightが480であること。
     * ・recorder[0].previewMaxFrameRateが30.0であること。
     * ・recorder[0].mimeTypeが"video/mp4"であること。
     * ・recorder[0].configが"test_config"であること。
     * </pre>
     */
    @Test
    public void testGetMediaRecorder() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_GET);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_MEDIARECORDER);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスに対して写真撮影依頼を送信するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: POST
     * Extra:
     *     profile=mediastream_recording
     *     attribute=takephoto
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * ・uriが"test.mp4"であること。
     * </pre>
     */
    @Test
    public void testTakePhoto001() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_POST);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_TAKE_PHOTO);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスに対して写真撮影依頼を送信するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: POST
     * Extra:
     *     profile=mediastream_recording
     *     attribute=mediarecorder
     *     target=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * ・uriが"test.mp4"であること。
     * </pre>
     */
    @Test
    public void testTakePhoto002() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_POST);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_TAKE_PHOTO);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_TARGET,
                TestMediaStreamRecordingProfileConstants.ID);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスに対して動画撮影または音声録音の開始依頼を送信するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: POST
     * Extra:
     *     profile=mediastream_recording
     *     attribute=record
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * ・uriが"test.mp4"であること。
     * </pre>
     */
    @Test
    public void testRecord001() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_POST);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_RECORD);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスに対して動画撮影または音声録音の開始依頼を送信するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: POST
     * Extra:
     *     profile=mediastream_recording
     *     attribute=record
     *     target=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * ・uriが"test.mp4"であること。
     * </pre>
     */
    @Test
    public void testRecord002() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_POST);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_RECORD);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_TARGET,
                TestMediaStreamRecordingProfileConstants.ID);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスに対して動画撮影または音声録音の開始依頼を送信するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: POST
     * Extra:
     *     profile=mediastream_recording
     *     attribute=record
     *     timeslice=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * ・uriが"test.mp4"であること。
     * </pre>
     */
    @Test
    public void testRecord003() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_POST);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_RECORD);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_TIME_SLICE, TIME_SLICE);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスに対して動画撮影または音声録音の開始依頼を送信するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: POST
     * Extra:
     *     profile=mediastream_recording
     *     attribute=record
     *     target=xxxx
     *     timeslice=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * ・uriが"test.mp4"であること。
     * </pre>
     */
    @Test
    public void testRecord004() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_POST);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_RECORD);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_TARGET,
                TestMediaStreamRecordingProfileConstants.ID);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_TIME_SLICE, TIME_SLICE);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスに対して動画撮影または音声録音の一時停止依頼を送信するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: PUT
     * Extra:
     *     profile=mediastream_recording
     *     attribute=pause
     *     mediaId=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testPause001() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_PUT);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_PAUSE);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_PATH,
            TestMediaStreamRecordingProfileConstants.PATH);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスに対して動画撮影または音声録音の一時停止依頼を送信するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: PUT
     * Extra:
     *     profile=mediastream_recording
     *     attribute=pause
     *     mediaId=xxxx
     *     target=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testPause002() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_PUT);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_PAUSE);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_PATH,
                TestMediaStreamRecordingProfileConstants.PATH);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_TARGET,
                TestMediaStreamRecordingProfileConstants.ID);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスに対して動画撮影または音声録音の再開依頼を送信するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: PUT
     * Extra:
     *     profile=mediastream_recording
     *     attribute=resume
     *     mediaId=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testResume001() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_PUT);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_RESUME);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_PATH,
                TestMediaStreamRecordingProfileConstants.PATH);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスに対して動画撮影または音声録音の再開依頼を送信するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: PUT
     * Extra:
     *     profile=mediastream_recording
     *     attribute=resume
     *     mediaId=xxxx
     *     target=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testResume002() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_PUT);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_RESUME);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_PATH,
                TestMediaStreamRecordingProfileConstants.PATH);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_TARGET,
                TestMediaStreamRecordingProfileConstants.ID);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスに対して動画撮影または音声録音の停止依頼を送信するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: PUT
     * Extra:
     *     profile=mediastream_recording
     *     attribute=stop
     *     mediaId=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testStop001() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_PUT);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_STOP);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_PATH,
                TestMediaStreamRecordingProfileConstants.PATH);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスに対して動画撮影または音声録音の停止依頼を送信するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: PUT
     * Extra:
     *     profile=mediastream_recording
     *     attribute=stop
     *     mediaId=xxxx
     *     target=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testStop002() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_PUT);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_STOP);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_PATH,
                TestMediaStreamRecordingProfileConstants.PATH);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_TARGET,
                TestMediaStreamRecordingProfileConstants.ID);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスに対して動画撮影または音声録音のミュート依頼を送信するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: PUT
     * Extra:
     *     profile=mediastream_recording
     *     attribute=mutetrack
     *     mediaId=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testMuteTrack001() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_PUT);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_MUTETRACK);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_PATH,
                TestMediaStreamRecordingProfileConstants.PATH);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスに対して動画撮影または音声録音のミュート依頼を送信するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: PUT
     * Extra:
     *     profile=mediastream_recording
     *     attribute=mutetrack
     *     mediaId=xxxx
     *     target=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testMuteTrack002() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_PUT);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_MUTETRACK);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_PATH,
                TestMediaStreamRecordingProfileConstants.PATH);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_TARGET,
                TestMediaStreamRecordingProfileConstants.ID);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスに対して動画撮影または音声録音のミュート解除依頼を送信するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: PUT
     * Extra:
     *     profile=mediastream_recording
     *     attribute=unmutetrack
     *     mediaId=xxxx
     *     target=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testUnmuteTrack001() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_PUT);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_UNMUTETRACK);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_PATH,
                TestMediaStreamRecordingProfileConstants.PATH);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスに対して動画撮影または音声録音のミュート解除依頼を送信するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: PUT
     * Extra:
     *     profile=mediastream_recording
     *     attribute=unmutetrack
     *     mediaId=xxxx
     *     target=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testUnmuteTrack002() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_PUT);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_UNMUTETRACK);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_PATH,
                TestMediaStreamRecordingProfileConstants.PATH);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_TARGET,
                TestMediaStreamRecordingProfileConstants.ID);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスのカメラのサポートするオプションの一覧を取得するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: GET
     * Extra:
     *     profile=mediastream_recording
     *     attribute=options
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testGetOptions001() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_GET);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_OPTIONS);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスのカメラのサポートするオプションの一覧を取得するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: GET
     * Extra:
     *     profile=mediastream_recording
     *     attribute=options
     *     target=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testGetOptions002() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_GET);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_OPTIONS);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_TARGET,
            TestMediaStreamRecordingProfileConstants.ID);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスのカメラのオプションを設定するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: PUT
     * Extra:
     *     profile=mediastream_recording
     *     attribute=options
     *     imageWidth=xxxx
     *     imageHeight=xxxx
     *     mimeType=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testPutOptions001() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_PUT);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_OPTIONS);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_TARGET,
                TestMediaStreamRecordingProfileConstants.ID);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_IMAGE_WIDTH,
            TestMediaStreamRecordingProfileConstants.IMAGE_WIDTH);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_IMAGE_HEIGHT,
            TestMediaStreamRecordingProfileConstants.IMAGE_HEIGHT);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_PREVIEW_WIDTH,
            TestMediaStreamRecordingProfileConstants.PREVIEW_WIDTH);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_PREVIEW_HEIGHT,
            TestMediaStreamRecordingProfileConstants.PREVIEW_HEIGHT);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_PREVIEW_MAX_FRAME_RATE,
            TestMediaStreamRecordingProfileConstants.PREVIEW_MAX_FRAME_RATE);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_MIME_TYPE,
            TestMediaStreamRecordingProfileConstants.MIME_TYPE);
        Intent response = sendRequest(request);

        assertResultOK(response);
    }

    /**
     * 指定したスマートデバイスのカメラのオプションを設定するテストを行う.
     * <pre>
     * 【Intent通信】
     * Action: PUT
     * Extra:
     *     profile=mediastream_recording
     *     attribute=options
     *     target=xxxx
     *     imageWidth=xxxx
     *     imageHeight=xxxx
     *     mimeType=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testPutOptions002() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_PUT);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_OPTIONS);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_TARGET,
                TestMediaStreamRecordingProfileConstants.ID);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_IMAGE_WIDTH,
                TestMediaStreamRecordingProfileConstants.IMAGE_WIDTH);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_IMAGE_HEIGHT,
                TestMediaStreamRecordingProfileConstants.IMAGE_HEIGHT);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_PREVIEW_WIDTH,
            TestMediaStreamRecordingProfileConstants.PREVIEW_WIDTH);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_PREVIEW_HEIGHT,
            TestMediaStreamRecordingProfileConstants.PREVIEW_HEIGHT);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_PREVIEW_MAX_FRAME_RATE,
            TestMediaStreamRecordingProfileConstants.PREVIEW_MAX_FRAME_RATE);
        request.putExtra(MediaStreamRecordingProfileConstants.PARAM_MIME_TYPE,
                TestMediaStreamRecordingProfileConstants.MIME_TYPE);
        Intent response = sendRequest(request);

        assertResultOK(response);
    }

    /**
     * 写真撮影イベントのコールバック登録テストを行う.
     * <pre>
     * 【Intent通信】
     * Action: PUT
     * Extra:
     *     profile=mediastream_recording
     *     attribute=onphoto
     *     sessionKey=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * ・コールバック登録後にイベントを受信すること。
     * </pre>
     */
    @Test
    public void testOnPhoto01() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_PUT);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_ON_PHOTO);

        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 写真撮影イベントのコールバック解除テストを行う.
     * <pre>
     * 【Intent通信】
     * Action: DELETE
     * Extra:
     *     profile=mediastream_recording
     *     attribute=onphoto
     *     sessionKey=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testOnPhoto02() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_DELETE);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE, MediaStreamRecordingProfileConstants.ATTRIBUTE_ON_PHOTO);

        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 動画撮影または音声録音開始イベントのコールバック登録テストを行う.
     * <pre>
     * 【Intent通信】
     * Action: PUT
     * Extra:
     *     profile=mediastream_recording
     *     attribute=onrecordingchange
     *     sessionKey=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * ・コールバック登録後にイベントを受信すること。
     * </pre>
     */
    @Test
    public void testOnRecordingChange01() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_PUT);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE,
                MediaStreamRecordingProfileConstants.ATTRIBUTE_ON_RECORDING_CHANGE);

        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * 動画撮影または音声録音開始イベントのコールバック解除テストを行う.
     * <pre>
     * 【Intent通信】
     * Action: DELETE
     * Extra:
     *     profile=mediastream_recording
     *     attribute=onrecording
     *     sessionKey=xxxx
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testOnRecordingChange02() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_DELETE);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE,
            MediaStreamRecordingProfileConstants.ATTRIBUTE_ON_RECORDING_CHANGE);

        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * プレビュー開始テストを行う.
     * <pre>
     * 【Intent通信】
     * Action: PUT
     * Extra:
     *     profile=mediastream_recording
     *     attribute=preview
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testPutPreview() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_PUT);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE,
            MediaStreamRecordingProfileConstants.ATTRIBUTE_PREVIEW);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }

    /**
     * プレビュー停止テストを行う.
     * <pre>
     * 【Intent通信】
     * Action: DELETE
     * Extra:
     *     profile=mediastream_recording
     *     attribute=preview
     * </pre>
     * <pre>
     * 【期待する動作】
     * ・resultに0が返ってくること。
     * </pre>
     */
    @Test
    public void testDeletePreview() {
        Intent request = new Intent(IntentDConnectMessage.ACTION_DELETE);
        request.putExtra(DConnectMessage.EXTRA_SERVICE_ID, getServiceId());
        request.putExtra(DConnectMessage.EXTRA_PROFILE, MediaStreamRecordingProfileConstants.PROFILE_NAME);
        request.putExtra(DConnectMessage.EXTRA_ATTRIBUTE,
            MediaStreamRecordingProfileConstants.ATTRIBUTE_PREVIEW);
        Intent response = sendRequest(request);
        assertResultOK(response);
    }
}
