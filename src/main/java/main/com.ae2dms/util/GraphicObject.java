package main.com.ae2dms.util;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import main.com.ae2dms.enums.ImageFactory;


/**
 * Draw game graphic
 */
public class GraphicObject extends ImageView {
    Image image;

    /**
     * Assign unique image to each game object
     *
     * @param obj       A game object
     */
    public GraphicObject(GameObject obj) {

        switch (obj) {
            case WALL:
                image = ImageFactory.wall;
                break;

            case CRATE:
                image = ImageFactory.box;
                break;

            case DOOR:
                image = ImageFactory.door;
                break;

            case ICE:
                image = ImageFactory.ice;
                break;

            case DIAMOND:
                image = ImageFactory.diamond;

                if (GameEngine.isDebugActive()) {
                    FadeTransition ft = new FadeTransition(Duration.millis(1000), this);
                    ft.setFromValue(1.0);
                    ft.setToValue(0.2);
                    ft.setCycleCount(Timeline.INDEFINITE);
                    ft.setAutoReverse(true);
                    ft.play();
                }

                break;

            case KEEPER:
                image = ImageFactory.keeper;
                break;

            case KEEPERRED:
                image = ImageFactory.keeperRed;
                break;

            case FLOOR:
                image = ImageFactory.floor;
                break;

            case CRATE_ON_DIAMOND:
                image = ImageFactory.arrive;
                break;

            default:
                String message = "Error in Level constructor. Object not recognized.";
                GameEngine.logger.severe(message);
                throw new AssertionError(message);
        }

        this.setImage(image);
        this.setFitWidth(30);
        this.setFitHeight(30);

        if (obj != GameObject.WALL) {
            this.setFitWidth(30);
            this.setFitHeight(30);
        }
    }
}
