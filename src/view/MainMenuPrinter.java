package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainMenuPrinter {

    private ImageView background;
    private Group panel;

    public MainMenuPrinter()
    {
        background = new ImageView(new Image("file:assets/image/menu-1024.jpg"));
        panel = new Group();
        panel.getChildren().add(background);
    }

    public Node getPanel()
    {
        return panel;
    }

}
