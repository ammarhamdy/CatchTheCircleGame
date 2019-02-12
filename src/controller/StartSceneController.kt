package controller

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.text.Text
import model.PointerLine
import model.PlayGround
import model.Setting
import java.net.URL
import java.util.*

class StartSceneController: Initializable {

    private  val shape = Circle(200.0, Color.CORNFLOWERBLUE)
    private val pointer = PointerLine(40.0, pathShape = shape, secDuration = 10.0)
//    private val pointer2 = PointerLine(30.0, pathShape = shape, secDuration = 10.0)
    private val targetCircle = Circle()
    private lateinit var playGround : PlayGround
    private lateinit  var setting: Setting
    //
    @FXML
    lateinit var playGroundContainer: StackPane
    @FXML
    lateinit var counterText: Text

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        // init the circle color and rotation.
        shape.stroke = Color.BLUEVIOLET
        shape.rotate = 270.0
        // init target circle color.
        targetCircle.fill = Color.DEEPSKYBLUE
        // init play ground after set all shape, and line attributes.
        playGround = PlayGround(pointer = pointer, target = targetCircle)
        setting = playGround.getSetting()
        playGround.draw()
        // put the circle, line, target circle.
        playGroundContainer.children.add(playGround)
    }

     private fun check(){
         val pointerX = pointer.translateX + setting.pointerCoordinates[4]
         val pointerY = pointer.translateY + setting.pointerCoordinates[5]
         if(pointerX in setting.targetArea[0] && pointerY in setting.targetArea[1]){
             counterText.text = (counterText.text.toByte()+1).toString()
             shape.rotate = 50.0
             playGround.changeTarget()
         }
         val color = Color.rgb((Math.random()*256).toInt(),(Math.random()*256).toInt(), (Math.random()*256).toInt(), .5)
         playGround.children.add(Circle(pointerX, pointerY, 3.0, color))
    }

    fun start(){
        pointer.go()
        playGroundContainer.setOnMousePressed { check() }
    }
}