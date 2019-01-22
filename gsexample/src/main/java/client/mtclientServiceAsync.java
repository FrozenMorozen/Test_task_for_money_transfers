package client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface mtclientServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async) throws Exception;

    void getSuggestListByFilter(String filter, AsyncCallback<List<String>> async);
}
