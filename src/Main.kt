import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

class Main: Application() {

    override fun start(primaryStage: Stage?) {
        val scene: Scene = FXMLLoader.load(Main::class.java.getResource("/view/startScene.fxml"))
        primaryStage?.scene = scene
        primaryStage?.isFullScreen = true
        primaryStage?.title = "Catch the circle"
        primaryStage?.icons?.add(Image("/View/Image/logo.png"))
        primaryStage?.show()
    }

}

fun main(args: Array<String>){
    Application.launch(Main::class.java, *args)
}
