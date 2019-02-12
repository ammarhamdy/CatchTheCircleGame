package model

import javafx.scene.Group
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle

class PlayGround(private val pointer: PointerLine, private val target: Circle):Group(){

    private val shape = pointer.getPathShape()
    private val setting = Setting(shape, pointer)
    private val backgroundRec : Rectangle
    private val padding = 5

    init {
        // init background: size, color
        backgroundRec = Rectangle(
                0.0,0.0,
                setting.groundSize[0] + padding,
                setting.groundSize[1] + padding )
        backgroundRec.fill = Color.TRANSPARENT
        // add shapes to play ground.
        children.addAll(backgroundRec)
    }

    fun getSetting()=setting

    fun draw(){
        // the path (big circle) center x, y
        shape.layoutX = setting.shapeCenter[0]
        shape.layoutY = setting.shapeCenter[1]
        // put line in right position.
        pointer.startX = setting.pointerCoordinates[0]
        pointer.startY = setting.pointerCoordinates[1]
        pointer.endX = setting.pointerCoordinates[2]
        pointer.endY = setting.pointerCoordinates[3]
        // target circle.
        target.radius = setting.targetCoordinates[2]
        target.layoutX = setting.targetCoordinates[0]
        target.layoutY = setting.targetCoordinates[1]
        children.addAll(shape, target, pointer)
    }

    fun changeTarget(){
        setting.changeTarget()
        target.layoutX = setting.targetCoordinates[0]
        target.layoutY = setting.targetCoordinates[1]
    }

}