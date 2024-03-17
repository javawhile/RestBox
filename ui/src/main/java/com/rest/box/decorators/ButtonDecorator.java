package com.rest.box.decorators;

import com.rest.box.constants.Icons;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class ButtonDecorator {

    public static void deleteButton(Object context, Button button) {
        InputStream crossIconResource = context.getClass().getResourceAsStream(Icons.crossIcon);
        if (crossIconResource != null) {
            Image image = new Image(crossIconResource);
            ImageView imageView = new ImageView(image);
            button.setGraphic(imageView);
            button.setStyle("-fx-background-color:transparent;");
            button.setOnMouseEntered(t -> button.setStyle("-fx-background-color:#dae7f3;"));
            button.setOnMouseExited(t -> button.setStyle("-fx-background-color:transparent;"));
        }
    }

}
