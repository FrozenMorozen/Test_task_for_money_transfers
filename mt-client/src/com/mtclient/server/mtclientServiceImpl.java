package com.mtclient.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mtclient.client.mtclientService;

public class mtclientServiceImpl extends RemoteServiceServlet implements mtclientService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}