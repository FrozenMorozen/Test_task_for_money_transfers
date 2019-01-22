package com.mtclient.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class mtclient implements EntryPoint {

    private final MultiWordSuggestOracle suggestOracle=new MultiWordSuggestOracle();
    private final SuggestBox suggestBox = new SuggestBox(suggestOracle);

    private final ListBox listBox = new ListBox(true);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        listBox.setVisible(false);
        final Button button = new Button("Список пунктов");
        final Label label = new Label();

        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                try {
                    mtclientService.App.getInstance().getPointsForCountryOrCity(suggestBox.getText(), new AsyncCallbackForListBox(listBox));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (label.getText().equals("")) {
                    try {
                        mtclientService.App.getInstance().getMessage("Hello, World!", new MyAsyncCallback(label));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    label.setText("");
                }
            }
        });


        suggestBox.addKeyUpHandler(new KeyUpHandler() {

            public void onKeyUp(KeyUpEvent event) {
                if (suggestBox.getText().length() == 1) {
                    // Сделать запрос в БД по первой букве и положить в MultiWordSuggestOracle для suggestBox
                    try {
                        mtclientService.App.getInstance().getSuggestListByFilter(suggestBox.getText(),new AsyncCallbackForSuggestions(suggestOracle, suggestBox));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (suggestBox.getText().length() == 0){
                    suggestOracle.clear();
                }
                suggestBox.showSuggestionList();
            }
        });

        RootPanel.get("suggestBox").add(suggestBox);
        RootPanel.get("button").add(button);
        RootPanel.get("listBox").add(listBox);
        RootPanel.get().add(label);
    }

    private void suggectBoxChangeHadler() {

    }


    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }

    private static class AsyncCallbackForSuggestions implements AsyncCallback<String[]>{
        private MultiWordSuggestOracle oracle;
        private SuggestBox suggestData;

        public AsyncCallbackForSuggestions(MultiWordSuggestOracle oracle, SuggestBox suggestData){
            this.oracle = oracle;
            this.suggestData = suggestData;
        }

        public void onFailure(Throwable caught) {

        }

        public void onSuccess(String[] result) {
            oracle.clear();
            suggestData=null;
            List<String> suggestionList = new ArrayList<String>(Arrays.asList(result));
            for (String str : suggestionList) {
                oracle.add(str);
            }
            //oracle.setDefaultSuggestionsFromText(suggestionList);
            suggestData.refreshSuggestionList();
        }

    }

    private static class AsyncCallbackForListBox implements AsyncCallback<String[]>{
        private ListBox listBox;

        public AsyncCallbackForListBox(ListBox listBox){
            this.listBox = listBox;
        }

        public void onFailure(Throwable caught) {

        }

        public void onSuccess(String[] result) {
            if (result.length != 0) {
                for (String pointInfo: result) {
                    listBox.addItem(pointInfo);
                }
                listBox.setVisible(true);
            }

        }

    }
}
