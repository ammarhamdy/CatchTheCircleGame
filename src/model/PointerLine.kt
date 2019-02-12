package model

import javafx.animation.ParallelTransition
import javafx.animation.PathTransition
import javafx.animation.RotateTransition
import javafx.animation.Timeline
import javafx.scene.shape.Line
import javafx.scene.paint.Color
import javafx.scene.shape.Shape
import javafx.scene.shape.StrokeLineCap
import javafx.util.Duration

class PointerLine
(private val length: Double, private val pathShape: Shape, secDuration: Double = 5.5) : Line() {

    private val duration = Duration.seconds(secDuration)
    private val parallelTransition: ParallelTransition

    init {
        stroke = Color.ORANGE
        strokeLineCap = StrokeLineCap.ROUND
        strokeWidth = 5.5
        //
        val pathTransition = PathTransition(duration, this.pathShape, this)
        val rotateTransition = RotateTransition(duration, this)
        rotateTransition.toAngle = 360.0
        parallelTransition = ParallelTransition(this, pathTransition, rotateTransition)
        parallelTransition.cycleCount = Timeline.INDEFINITE
    }

    fun getPathShape()=pathShape

    fun getLength() = length

    fun go(){parallelTransition.play()}

//    fun stop(){parallelTransition.pause()}

}