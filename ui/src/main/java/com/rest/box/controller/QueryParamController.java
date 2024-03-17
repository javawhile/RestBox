package com.rest.box.controller;

import com.rest.box.listeners.OnKeyValueChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class QueryParamController extends KeyValuePairBaseController {

    public static final String IDENTIFIER = "QueryParams";

    public QueryParamController (
            OnKeyValueChangeListener onKeyValueChangeListener,
            ListView<Pane> queryParamsListView,
            Button addQueryParamsButton,
            Button deleteAllQueryParamsButton
    ) {
        super(
                onKeyValueChangeListener,
                IDENTIFIER,
                queryParamsListView,
                addQueryParamsButton,
                deleteAllQueryParamsButton
        );
    }
}
