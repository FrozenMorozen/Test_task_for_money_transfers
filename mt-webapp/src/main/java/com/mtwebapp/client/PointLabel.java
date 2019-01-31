package com.mtwebapp.client;

import com.mtwebapp.client.entities.Point;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

import java.util.ArrayList;
import java.util.List;


class PointLabel extends Composite {

    interface PointLabelUiBinder extends UiBinder<Label, PointLabel> {}
    private static PointLabelUiBinder ourUiBinder = GWT.create(PointLabelUiBinder.class);

    public interface PointLabelClickHandler {
        void onClick(final Point point);
    }

    private final List<PointLabelClickHandler> clickHandlers;

    @UiField
    Label pointlabel;

    public PointLabel(final Point point) {
        initWidget(ourUiBinder.createAndBindUi(this));
        pointlabel.setText(point.toString());
        clickHandlers = new ArrayList<>();

        addDomHandler(event -> {
            for (final PointLabelClickHandler clickHandler : clickHandlers) {
                clickHandler.onClick(point);
            }
        }, ClickEvent.getType());
    }

    public void addClickHandler(final PointLabelClickHandler clickHandler) {
        clickHandlers.add(clickHandler);
    }
}