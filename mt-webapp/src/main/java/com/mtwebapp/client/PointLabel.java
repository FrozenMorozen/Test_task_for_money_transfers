package com.mtwebapp.client;

import com.mtwebapp.client.entities.Point;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;


class PointLabel extends Composite {

    interface PointLabelUiBinder extends UiBinder<Label, PointLabel> {}
    private static PointLabelUiBinder ourUiBinder = GWT.create(PointLabelUiBinder.class);

    @UiField
    Label pointlabel;


    public PointLabel(Point point) {
        initWidget(ourUiBinder.createAndBindUi(this));
        pointlabel.setText(point.toString());
    }
}