import database.dbUtils.DbManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DocAsByte;
import utils.FxmlPath;
import utils.FxmlUtils;
import utils.UtilsProject;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage stage) {
        DbManager.initDatabase(false);
//        DocAsByte.generateTxtWithByteDoc();
        Scene scene = new Scene(Objects.requireNonNull(FxmlUtils.fxmlLoader(FxmlPath.MAIN_FRAME_FXML)));
        stage.setTitle("System fakturowania");
        UtilsProject.setStageSize(stage, 1121, 740);
        stage.setScene(scene);
        stage.show();
    }
}
