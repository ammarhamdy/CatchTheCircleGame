package model

import javafx.scene.shape.Circle
import javafx.scene.shape.Shape

class Setting(private val shape: Shape, private val pointer: PointerLine){

    val pointerCoordinates = arrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    val shapeCenter = arrayOf(0.0, 0.0)
    val groundSize = arrayOf(0.0, 0.0)
    val targetCoordinates = arrayOf(0.0, 0.0, 0.0)
    val targetArea: Array<ClosedFloatingPointRange<Double>> = arrayOf(0.0..0.0, 0.0..0.0)
    var changeTarget: ()->Unit = fun(){}

    init {
        val index: String? = shape.javaClass.kotlin.simpleName
         // initializing settings depend on type of shape.
        val circleBaseSettings= fun(){
            val radius : Double = (shape as Circle).radius
            val rotation : Double = shape.rotate
            shape.strokeWidth = pointer.getLength()
            //  init shape (circle) size, width = height.
            val shapeSize = radius * 2 + shape.strokeWidth * 2
            // init the background rectangle width, height, x, y.
            groundSize[0] = pointer.getLength() + shapeSize
            groundSize[1] = pointer.getLength() + shapeSize
            // init shape coordinates.
            shapeCenter[0] = groundSize[0] / 2
            shapeCenter[1] = groundSize[1] / 2
            // init the pointer(line), {startX, startY, enbX, endY, centerX, CenterY}.
            val cosRotation = Math.cos(Math.toRadians(rotation))
            val sinRotation = Math.sin(Math.toRadians(rotation))
            val lengthCosRotation = pointer.getLength() * cosRotation
            val lengthSinRotation = pointer.getLength() * sinRotation
            pointerCoordinates[0] = shapeCenter[0] + radius * cosRotation - lengthCosRotation / 2
            pointerCoordinates[1] = shapeCenter[1] + radius * sinRotation - lengthSinRotation / 2
            pointerCoordinates[2] = pointerCoordinates[0] + lengthCosRotation
            pointerCoordinates[3] = pointerCoordinates[1] + lengthSinRotation
            pointerCoordinates[4] = pointerCoordinates[0] + lengthCosRotation / 2
            pointerCoordinates[5] = pointerCoordinates[1] + lengthSinRotation / 2
            // init the target small circle initial position, radius, and random positions.
            targetCoordinates[2] = shape.strokeWidth/2
            changeTarget = fun(){
                val angle = Math.random()*360
                targetCoordinates[0] = Math.cos(Math.toRadians(angle))*radius + shapeCenter[0]
                targetCoordinates[1] = Math.sin(Math.toRadians(angle))*radius + shapeCenter[1]
                // target area(x1 to x2, y1 to y2)
                val tolerant = targetCoordinates[2] + radius/100
                targetArea[0] = (targetCoordinates[0]-tolerant).rangeTo(targetCoordinates[0]+tolerant)
                targetArea[1] = (targetCoordinates[1]-tolerant).rangeTo(targetCoordinates[1]+tolerant)
            }
            changeTarget()
        }
        /*val recAttribute = fun(){
            val width = (shape as Rectangle).width
            val height = shape.height
            //
            width = width + shape.strokeWidth *2
            height = height + shape.strokeWidth *2
            //
            changeTarget = fun(){
                val angle = Math.random()*360
                targetX =Math.cos(Math.toRadians(angle))*width
                targetY = Math.cos(Math.toRadians(angle))*height
            }
        }*/
        mapOf("Circle" to circleBaseSettings/*, "Rectangle" to recAttribute*/)[index]?.invoke()
    }

}
