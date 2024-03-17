package com.rest.box.controller;

import com.rest.box.constants.Identifiers;
import com.rest.box.constants.Scenes;
import com.rest.box.decorators.ButtonDecorator;
import com.rest.box.listeners.OnKeyValueChangeListener;
import com.rest.box.utils.FXMLFetcher;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class KeyValuePairBaseController {

    private final String tableId;

    private final OnKeyValueChangeListener onKeyValueChangeListener;

    private final ListView<Pane> itemListView;

    private final Button addButton;

    private final Button deleteAllButton;

    public KeyValuePairBaseController(
            OnKeyValueChangeListener onKeyValueChangeListener,
            String tableId,
            ListView<Pane> itemListView,
            Button addButton,
            Button deleteAllRequestHeadersButton
    ) {
        this.onKeyValueChangeListener = onKeyValueChangeListener;
        this.tableId = tableId;
        this.itemListView = itemListView;
        this.addButton = addButton;
        this.deleteAllButton = deleteAllRequestHeadersButton;
        initActionEvents();
    }

    public void addKeyValue(final String key, final String value) {
        Pane keyValueInputPane = FXMLFetcher.getAsPane(Scenes.keyValueInputItem);

        TextField keyInput = (TextField) findNode(keyValueInputPane, Identifiers.keyTextField);
        TextField valueInput = (TextField) findNode(keyValueInputPane, Identifiers.valueTextField);
        Button deleteButton = (Button) findNode(keyValueInputPane, Identifiers.deleteButton);

        if (keyInput != null) {
            keyInput.setText(key);
            keyInput.textProperty().addListener((observable, oldValue, newValue) -> {
                if (onKeyValueChangeListener != null) {
                    onKeyValueChangeListener.onChange(tableId);
                }
            });
        }

        if (valueInput != null) {
            valueInput.setText(value);
            valueInput.textProperty().addListener((observable, oldValue, newValue) -> {
                if (onKeyValueChangeListener != null) {
                    onKeyValueChangeListener.onChange(tableId);
                }
            });
        }

        if (deleteButton != null) {
            ButtonDecorator.deleteButton(this, deleteButton);
            deleteButton.setOnAction(actionEvent1 -> {
                itemListView.getItems().remove(keyValueInputPane);
                if (onKeyValueChangeListener != null) {
                    onKeyValueChangeListener.onChange(tableId);
                }
            });
        }

        itemListView.getItems().add(keyValueInputPane);
    }

    public Map<String, String> readKeyValueMap() {
        Map<String, String> map = new HashMap<>();
        if (itemListView != null && itemListView.getItems() != null) {
            for (Pane onePairPane : itemListView.getItems()) {
                if (onePairPane != null) {
                    TextField keyInput = (TextField) findNode(onePairPane, Identifiers.keyTextField);
                    TextField valueInput = (TextField) findNode(onePairPane, Identifiers.valueTextField);
                    if (keyInput != null && valueInput != null) {
                        String key = keyInput.getText();
                        String value = valueInput.getText();
                        if (key != null
                                && value != null
                                && !key.trim().isEmpty()
                                && !value.trim().isEmpty()) {
                            map.put(key, value);
                        }
                    }
                }
            }
        }
        return map;
    }

    private void initActionEvents() {
        this.addButton.setOnAction(this::addQueryParamButtonAction);
        this.deleteAllButton.setOnAction(this::deleteAllQueryParamButtonAction);
    }

    private void addQueryParamButtonAction(ActionEvent actionEvent) {
        addKeyValue(null, null);
        if (onKeyValueChangeListener != null) {
            onKeyValueChangeListener.onChange(tableId);
        }
    }

    private void deleteAllQueryParamButtonAction(ActionEvent actionEvent) {
        itemListView.getItems().clear();
        if (onKeyValueChangeListener != null) {
            onKeyValueChangeListener.onChange(tableId);
        }
    }

    private Node findNode(Pane pane, String identifier) {
        if (pane != null
                && identifier != null
                && !identifier.trim().isEmpty()) {
            for (Node node : pane.getChildren()) {
                if (node != null
                        && node.getId() != null
                        && !node.getId().trim().isEmpty()
                        && node.getId().trim().equalsIgnoreCase(identifier.trim())) {
                    return node;
                }
            }
        }
        return null;
    }
}
