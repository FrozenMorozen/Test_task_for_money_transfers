package com.mtwebapp.client;

import com.mtwebapp.client.entities.Point;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;


public class PointPanel extends Composite {

    interface PointViewUiBinder extends UiBinder<HTMLPanel, PointPanel> {
    }

    private static PointViewUiBinder uiBinder = GWT.create(PointViewUiBinder.class);

    private static final PointService pointService = GWT.create(PointService.class);

    @UiField
    FlowPanel pointsList;

    @UiField
    TextBox filterTextBox;

    @UiField
    Button getPointsButton;

    public PointPanel() {
        initWidget(uiBinder.createAndBindUi(this));

        getPointsButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                String filterText = filterTextBox.getText();
                if (!filterText.isEmpty()) {
                    getPoints(filterText);
                }
            }
        });

        filterTextBox.getElement().setAttribute("placeholder", "Country or city");
        filterTextBox.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    String filterText = filterTextBox.getText();
                    if (!filterText.isEmpty()) {
                        getPoints(filterText);
                    }
                }
            }
        });
    }

    private void getPoints(String text) {
        pointService.getPoints(text, new MethodCallback<List<Point>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                pointsList.clear();
                Label errorlabel = new Label("Ошибка: не удалось связаться с сервером.");
                pointsList.add(errorlabel);
            }

            @Override
            public void onSuccess(Method method, List<Point> response) {
                pointsList.clear();
                for (Point point : response) {
                    PointLabel pointLabel = new PointLabel(point);
                    pointsList.add(pointLabel);
                }
            }
        });
    }
}
