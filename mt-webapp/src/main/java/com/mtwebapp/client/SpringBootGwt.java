package com.mtwebapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import org.fusesource.restygwt.client.Defaults;


public class SpringBootGwt implements EntryPoint {

    public void onModuleLoad() {
        Defaults.setServiceRoot("http://localhost:8080");
        RootPanel.get().add(new PointPanel());
    }
}