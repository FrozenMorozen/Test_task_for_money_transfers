package com.codecrafters.client;

import com.codecrafters.client.entities.Point;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;
import java.util.Set;

public class PointPanel extends Composite {

    interface PointViewUiBinder extends UiBinder<HTMLPanel, PointPanel> {}
    private static PointViewUiBinder uiBinder = GWT.create(PointViewUiBinder.class);

    private static final PointService pointService = GWT.create(PointService.class);

    @UiField
    FlowPanel pointsList;

    @UiField
    TextBox filterTextBox;

    @UiField
    Button getPointsButton;

    public PointPanel(){
        initWidget(uiBinder.createAndBindUi(this));

        getPointsButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final String filterText = filterTextBox.getText();
                if (!filterText.isEmpty()) {
                    getPoints(filterText);
                }
            }
        });

        filterTextBox.getElement().setAttribute("placeholder", "Search point");
    }

    private void getPoints(final String text) {
        pointService.getPoints(text, new MethodCallback<List<Point>>() {
            @Override
            public void onFailure(final Method method, final Throwable exception) {

            }

            @Override
            public void onSuccess(final Method method, final List<Point> response) {
                pointsList.clear();
                // Здесь наверное ещё отсортировать
                for (final Point point : response) {
                    final PointLabel pointLabel= new PointLabel(point);
                    pointsList.add(pointLabel);
                }
            }
        });
    }
}
