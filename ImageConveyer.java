import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ImageConveyer extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    private final Label enter = new Label("Enter Image Path to convey");
    private final TextField getPath = new TextField();
    private final Label store = new Label("Enter Image store path");
    private final TextField putPath = new TextField();
    private final Label imageN = new Label("Enter the new Image name");
    private final TextField imageName = new TextField();
    private final Button convey = new Button("Convey");

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(7);
        gridPane.setVgap(7);
        gridPane.add(enter, 0, 0);
        gridPane.add(getPath, 0, 1);
        gridPane.add(imageN, 0, 2);
        gridPane.add(imageName, 0, 3);
        gridPane.add(store, 0, 4);
        gridPane.add(putPath, 0, 5);
        gridPane.add(convey, 0, 6);
        gridPane.setAlignment(Pos.CENTER);
        getPath.setAlignment(Pos.BOTTOM_LEFT);
        GridPane.setHalignment(convey, HPos.CENTER);
        convey.setOnAction(e -> conveyer(getPath.getText()));

        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("ImageConveyer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void conveyer(String path){
        try {
            File input = new File(path);
            BufferedImage myImage = ImageIO.read(input);
            BufferedImage image2 = ImageIO.read(input);
            int height = myImage.getHeight();
            int width = myImage.getWidth();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (j != 0 && j != width - 1 && i != 0 && i != height - 1 && image2.getRGB(j - 1, i) == image2.getRGB(j + 1, i) && image2.getRGB(j, i - 1) == image2.getRGB(j, i + 1))
                        myImage.setRGB(j, i, -1);
                }
            }
            File file = new File(putPath.getText() + "/" + imageName.getText() + ".png");
            ImageIO.write(myImage, "png", file);
        }
        catch (Exception e){
            System.out.println("The path is not correct");
        }
        getPath.clear();
        putPath.clear();
        imageName.clear();
    }
}
