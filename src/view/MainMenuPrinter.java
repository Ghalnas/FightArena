package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainMenuPrinter {

    private ImageView background;

    public MainMenuPrinter()
    {
        background = new ImageView(new Image("file:assets/image/menu-1280.png"));
    }

    public Node getPanel()
    {
        Group panel = new Group();
        panel.getChildren().add(background);
        return panel;
    }

}
