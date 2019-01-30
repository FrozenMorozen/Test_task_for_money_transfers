package com.codecrafters.client;

import com.codecrafters.client.entities.Point;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to represent a {@link Point} in a panel.
 *
 * @author Fabian Dietenberger
 */
class PointLabel extends Composite {

    interface PointLabelUiBinder extends UiBinder<Label, PointLabel> {}
    private static PointLabelUiBinder ourUiBinder = GWT.create(PointLabelUiBinder.class);

    public interface PointLabelClickHandler {
        void onClick(final Point point);
    }

    private final List<PointLabelClickHandler> clickHandlers;

    @UiField
    Label label;

    public PointLabel(final Point point) {
        initWidget(ourUiBinder.createAndBindUi(this));
        label.setText(point.toString());
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