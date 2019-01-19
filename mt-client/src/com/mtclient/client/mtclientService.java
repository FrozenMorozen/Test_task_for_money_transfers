package com.mtclient.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("mtclientService")
public interface mtclientService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use mtclientService.App.getInstance() to access static instance of mtclientServiceAsync
     */
    public static class App {
        private static mtclientServiceAsync ourInstance = GWT.create(mtclientService.class);

        public static synchronized mtclientServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
