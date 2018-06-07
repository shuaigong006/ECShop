package com.example.core.util.callback;

import java.nio.file.FileAlreadyExistsException;
import java.util.WeakHashMap;

public class CallbackManager {

    private static final WeakHashMap<Object, IGlobalCallback> CALLBACKS = new WeakHashMap<>();

    private static class Holder {
        private static final CallbackManager INSTANCE = new CallbackManager();
    }

    public static CallbackManager getInstance() {
        return Holder.INSTANCE;
    }

    public final CallbackManager addCallback(Object tag, IGlobalCallback callback) {
        CALLBACKS.put(tag, callback);
        return this;
    }

    public final IGlobalCallback getCallback(Object tag) {
        return CALLBACKS.get(tag);
    }
}
