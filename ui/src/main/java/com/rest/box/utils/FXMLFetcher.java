package com.rest.box.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FXMLFetcher {

    public static Pane getAsPane(final String pathInResourcesFolder) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMLFetcher.class.getResource(pathInResourcesFolder));
            return loader.load();
        } catch (final Exception exception) {
            //TODO
        }
        return null;
    }
}
