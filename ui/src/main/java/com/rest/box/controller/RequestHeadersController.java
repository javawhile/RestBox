package com.rest.box.controller;

import com.rest.box.listeners.OnKeyValueChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class RequestHeadersController extends KeyValuePairBaseController {

    public static final String IDENTIFIER = "RequestHeaders";

    public RequestHeadersController(
            OnKeyValueChangeListener onKeyValueChangeListener,
            ListView<Pane> requestHeadersListView,
            Button addRequestHeadersButton,
            Button deleteAllRequestHeadersButton
    ) {
        super(
                onKeyValueChangeListener,
                IDENTIFIER,
                requestHeadersListView,
                addRequestHeadersButton,
                deleteAllRequestHeadersButton
        );
    }

}
