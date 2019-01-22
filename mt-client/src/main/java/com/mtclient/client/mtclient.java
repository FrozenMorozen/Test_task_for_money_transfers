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

    final Tree pointsTree = new Tree();
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final Button button = new Button("Click me");
        final Label label = new Label();
        final Label label2 = new Label();

/*
        final MultiWordSuggestOracle suggestOracle = new MultiWordSuggestOracle();
        suggestOracle.add("A");
        suggestOracle.add("AB");
        suggestOracle.add("ABC");
        suggestOracle.add("ABCD");
        suggestOracle.add("B");
        suggestOracle.add("BC");
        suggestOracle.add("BCD");
        suggestOracle.add("BCDE");
        suggestOracle.add("C");
        suggestOracle.add("CD");
        suggestOracle.add("CDE");
        suggestOracle.add("CDEF");
        suggestOracle.add("D");
        suggestOracle.add("DE");
        suggestOracle.add("DEF");
        suggestOracle.add("DEFG");

        final SuggestBox suggestBox = new SuggestBox(suggestOracle);
*/
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
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
                } else {

                    label.setText("s");
                }
                suggestBox.showSuggestionList();
            }
        });


        //suggestBox.refreshSuggestionList();

        // Assume that the host HTML has elements defined whose
        // IDs are "slot1", "slot2".  In a real app, you probably would not want
        // to hard-code IDs.  Instead, you could, for example, search for all
        // elements with a particular CSS class and replace them with widgets.
        //
        RootPanel.get("slot1").add(suggestBox);
        RootPanel.get("slot2").add(button);
        RootPanel.get().add(label);
        RootPanel.get().add(label2);
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
        private MultiWordSuggestOracle oracle=new MultiWordSuggestOracle();
        private SuggestBox suggestData=new SuggestBox(oracle);

        public AsyncCallbackForSuggestions(MultiWordSuggestOracle oracle, SuggestBox suggestData){
            this.oracle=oracle;
            this.suggestData=suggestData;
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
            oracle.setDefaultSuggestionsFromText(suggestionList);
            //oracle.setSuggestAllMatchingWords(true);
            suggestData.refreshSuggestionList();
        }

    }
}
