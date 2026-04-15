package com.horcrux.svg.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.imagehelper.ImageSource;
import com.google.firebase.messaging.Constants;

/* loaded from: classes3.dex */
public class SvgLoadEvent extends Event<SvgLoadEvent> {
    public static final String EVENT_NAME = "topLoad";
    private final float height;
    private final String uri;
    private final float width;

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) 0;
    }

    public SvgLoadEvent(int i, int i2, ReactContext reactContext, String str, float f, float f2) {
        super(i, i2);
        this.uri = new ImageSource(reactContext, str).getSource();
        this.width = f;
        this.height = f2;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), getEventData());
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("width", this.width);
        createMap.putDouble("height", this.height);
        createMap.putString("uri", this.uri);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putMap(Constants.ScionAnalytics.PARAM_SOURCE, createMap);
        return createMap2;
    }
}
