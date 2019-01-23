package com.mtclient.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;

import java.util.List;

public interface mtclientServiceAsync {

    void getSuggestListByFilter(String filter, AsyncCallback<String[]> async);

    void getPointsForCountryOrCity(String countryOrCityName, AsyncCallback<String[]> async);
}
