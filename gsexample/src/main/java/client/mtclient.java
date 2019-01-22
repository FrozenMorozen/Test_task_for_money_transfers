package client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import java.util.List;


/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class mtclient implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final Button button = new Button("Click me");
        final Label label = new Label();
        final Label label2 = new Label();


        final MultiWordSuggestOracle suggestOracle = new MultiWordSuggestOracle();
        /*suggestOracle.add("A");
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
        suggestOracle.add("DEFG");*/

        final SuggestBox suggestBox = new SuggestBox(suggestOracle);

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

        /*
        suggestBox.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if (suggestBox.getText().equals("")) {

                    label2.setText("empty");

                } else {
                    String filter = suggestBox.getText();
                    List<String> suggestions = new ArrayList<>();
                    mtclientService.App.getInstance().getSuggestListByFilter(filter, new MyAsyncCallback_2(suggestions));

                    label2.setText(label2.getText()+" ][ onKeyPress - "+suggestBox.getText());
                    label.setText(suggestions.get(0));
                    suggestOracle.clear();
                    suggestOracle.addAll(suggestions);
                }
            }
        });*/

        suggestBox.addKeyUpHandler(new KeyUpHandler() {

            public void onKeyUp(KeyUpEvent event) {
                if (suggestBox.getText().length() == 1) {
                    // Сделать запрос в БД по первой букве и положить в MultiWordSuggestOracle для suggestBox
                    label.setText(String.valueOf(suggestBox.getText().length()));

                } else {
                    label.setText("");
                }

            }
        });
/*
        suggestBox.addKeyboardListener(new KeyboardListener() {
            @Override
            public void onKeyDown(Widget sender, char keyCode, int modifiers) {
                if (suggestBox.getText().equals("")) {

                    label.setText("empty");

                } else {
                    String filter = suggestBox.getText();
                    List<String> suggestions = new ArrayList<>();
                    mtclientService.App.getInstance().getSuggestListByFilter(filter, new MyAsyncCallback_2(suggestions));

                    label.setText(label.getText()+" ][ onKeyDown - "+suggestBox.getText());

                    suggestOracle.clear();
                    suggestOracle.addAll(suggestions);
                }
            }

            @Override
            public void onKeyPress(Widget sender, char keyCode, int modifiers) {
                if (suggestBox.getText().equals("")) {

                    label.setText("empty");

                } else {
                    String filter = suggestBox.getText();
                    List<String> suggestions = new ArrayList<>();
                    mtclientService.App.getInstance().getSuggestListByFilter(filter, new MyAsyncCallback_2(suggestions));

                    label.setText(label.getText()+" ][ onKeyPress - "+suggestBox.getText());

                    suggestOracle.clear();
                    suggestOracle.addAll(suggestions);
                }
            }

            @Override
            public void onKeyUp(Widget sender, char keyCode, int modifiers) {
                if (suggestBox.getText().equals("")) {

                    label.setText("empty");

                } else {
                    String filter = suggestBox.getText();
                    List<String> suggestions = new ArrayList<>();
                    mtclientService.App.getInstance().getSuggestListByFilter(filter, new MyAsyncCallback_2(suggestions));

                    label.setText(label.getText()+" ][ onKeyUp - "+suggestBox.getText());

                    suggestOracle.clear();
                    suggestOracle.addAll(suggestions);
                }
            }
        });*/

        //suggestBox.refreshSuggestionList();

        // Assume that the host HTML has elements defined whose
        // IDs are "slot1", "slot2".  In a real app, you probably would not want
        // to hard-code IDs.  Instead, you could, for example, search for all
        // elements with a particular CSS class and replace them with widgets.
        //
        RootPanel.get("slot1").add(suggestBox);
        RootPanel.get("slot2").add(label);
        RootPanel.get().add(button);
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

    private static class MyAsyncCallback_2 implements AsyncCallback<List<String>>{
        private List<String> list;

        public MyAsyncCallback_2(List<String> list) {
            this.list = list;
        }

        @Override
        public void onFailure(Throwable caught) {

        }

        @Override
        public void onSuccess(List<String> result) {

        }
    }
}
