package com.mtclient.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface mtclientServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
