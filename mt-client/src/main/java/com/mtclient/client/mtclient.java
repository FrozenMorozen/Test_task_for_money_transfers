package com.mtclient.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class mtclient implements EntryPoint {

    private final MultiWordSuggestOracle suggestOracle=new MultiWordSuggestOracle();
    private final SuggestBox suggestBox = new SuggestBox(suggestOracle);
    private static final ListBox listBox = new ListBox(true);

    public void onModuleLoad() {
        listBox.setVisible(false);
        final Button pointListButton = new Button("Points list");


        pointListButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (suggestBox.getText().length() >= 1) {
                    // Получить список пунктов денежных переводов
                    try {
                        mtclientService.App.getInstance().getPointsForCountryOrCity(suggestBox.getText(), new AsyncCallbackForListBox(listBox));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        suggestBox.addKeyUpHandler(new KeyUpHandler() {

            public void onKeyUp(KeyUpEvent event) {

                // Сделать запрос по первой букве suggestBox
                if (suggestBox.getText().length() == 1) {
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
        RootPanel.get("button").add(pointListButton);
        RootPanel.get("listBox").add(listBox);
    }


    private static class AsyncCallbackForSuggestions implements AsyncCallback<String[]>{
        private MultiWordSuggestOracle oracle;
        private SuggestBox suggestData;

        public AsyncCallbackForSuggestions(MultiWordSuggestOracle oracle, SuggestBox suggestData){
            this.oracle = oracle;
            this.suggestData = suggestData;
        }

        public void onFailure(Throwable caught) {
            oracle.clear();
            suggestData=null;
            showErrorMessage("Fail request for get contries/cities list");
        }


        public void onSuccess(String[] result) {
            oracle.clear();
            suggestData=null;
            List<String> suggestionList = new ArrayList<String>(Arrays.asList(result));
            for (String str : suggestionList) {
                oracle.add(str);
            }
            suggestData.refreshSuggestionList();
        }

    }

    // Показать сообщение об ошибке
    private static void showErrorMessage(String s) {
        final DialogBox errorDialogBox=new DialogBox();
        errorDialogBox.setGlassEnabled(true);
        errorDialogBox.setAnimationEnabled(true);

        // Create a table to layout the content
        VerticalPanel errorPanel = new VerticalPanel();
        errorPanel.setSpacing(4);
        errorDialogBox.setWidget(errorPanel);

        Label errorMessage=new Label(s);
        errorPanel.add(errorMessage);
        Button closeBox=new Button("Close", new ClickHandler() {
            public void onClick(ClickEvent event) {
                errorDialogBox.hide();
            }
        });
        closeBox.setFocus(true);
        errorPanel.add(closeBox);
        errorDialogBox.setPopupPosition(600,230);
        listBox.clear();
        errorDialogBox.show();
    }

    private static class AsyncCallbackForListBox implements AsyncCallback<String[]>{
        private ListBox listBox;

        public AsyncCallbackForListBox(ListBox listBox){
            this.listBox = listBox;
        }

        public void onFailure(Throwable caught) {
            listBox.clear();
            listBox.setVisible(false);
            showErrorMessage("Fail request for get points list");
        }

        public void onSuccess(String[] result) {
            if (result.length != 0) {
                listBox.clear();
                for (String pointInfo: result) {
                    listBox.addItem(pointInfo);
                }
                listBox.setVisible(true);
            } else {
                listBox.setVisible(false);
            }

        }

    }
}
