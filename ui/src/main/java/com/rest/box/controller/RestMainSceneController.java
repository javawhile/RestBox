package com.rest.box.controller;

import com.rest.box.client.RestClient;
import com.rest.box.model.enums.HttpMethod;
import com.rest.box.listeners.OnKeyValueChangeListener;
import com.rest.box.model.Response;
import com.rest.box.model.enums.RequestBodyType;
import com.rest.box.utils.UrlUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import lombok.Getter;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class RestMainSceneController implements Initializable, OnKeyValueChangeListener {

    private final RestClient restClient = new RestClient();

    @FXML
    private TextField urlTextField;

    @FXML
    private ComboBox<String> httpMethodsComboBox;

    @FXML
    private Button sendButton;

    @FXML
    private TextArea responseTextArea;

    //QUERY PARAMS TAB

    @FXML
    private ListView<Pane> queryParamsListView;

    @FXML
    private Button addQueryParamButton;

    @FXML
    private Button deleteAllQueryParamButton;

    @Getter
    private QueryParamController queryParamController;

    //REQUEST HEADERS TAB

    @FXML
    private ListView<Pane> requestHeadersListView;

    @FXML
    private Button addRequestHeadersButton;

    @FXML
    private Button deleteAllRequestHeadersButton;

    @Getter
    private RequestHeadersController requestHeadersController;

    //BODY TAB

    @FXML
    private ComboBox<String> bodyTypeComboBox;

    @FXML
    private TextArea requestBodyTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initHttpMethodsComboBox();
        initBodyTypeComboBox();
        initControllers();
        initSendButton();
        initDefaultRequestHeaders();
    }

    private void initHttpMethodsComboBox() {
        httpMethodsComboBox.getItems().clear();
        for (HttpMethod httpMethod : HttpMethod.values()) {
            httpMethodsComboBox.getItems().add(httpMethod.name());
        }
        httpMethodsComboBox.getSelectionModel().selectFirst();
    }

    private void initBodyTypeComboBox() {
        bodyTypeComboBox.getItems().clear();
        for (RequestBodyType requestBodyType : RequestBodyType.values()) {
            bodyTypeComboBox.getItems().add(requestBodyType.name());
        }
        bodyTypeComboBox.getSelectionModel().selectFirst();
    }

    private void initControllers() {
        queryParamController = new QueryParamController(
                this, queryParamsListView, addQueryParamButton, deleteAllQueryParamButton
        );

        requestHeadersController = new RequestHeadersController(
                this, requestHeadersListView, addRequestHeadersButton, deleteAllRequestHeadersButton
        );
    }

    private void initSendButton() {
        sendButton.setOnAction(actionEvent -> {

            //GET REQUEST DATA
            String httpMethod = httpMethodsComboBox.getSelectionModel().getSelectedItem();
            String fullUrl = urlTextField.getText();
            String requestBody = requestBodyTextArea.getText();
            Map<String, String> queryParams = queryParamController.readKeyValueMap();
            Map<String, String> requestHeaders = requestHeadersController.readKeyValueMap();

            //CALL
            Response restResponse = restClient.invoke(httpMethod, fullUrl, requestBody, queryParams, requestHeaders);

            //SHOW RESPONSE
            responseTextArea.setText(restResponse.getBody());
        });
    }

    private void initDefaultRequestHeaders() {
        requestHeadersController.addKeyValue("Accept", "text/html,application/xhtml+xml,application/xml");
        requestHeadersController.addKeyValue("Accept-Encoding", "gzip, deflate, br, zstd");
        requestHeadersController.addKeyValue("Accept-Language", "en-US,en");
        requestHeadersController.addKeyValue("User-Agent", "Mozilla/5.0");
    }

    @Override
    public void onChange(String tableId) {
        if (tableId.equals(QueryParamController.IDENTIFIER)) {
            try {
                Map<String, String> queryParams = queryParamController.readKeyValueMap();
                String finalUrl = UrlUtils.addQueryParamToUrl(urlTextField.getText(), queryParams);
                if (finalUrl != null && !finalUrl.trim().isEmpty()) {
                    urlTextField.setText(finalUrl);
                }
            } catch (final URISyntaxException exception) {
                System.err.println("Exception while parsing Query Params: " + exception.getMessage());
            }
        }
    }
}
