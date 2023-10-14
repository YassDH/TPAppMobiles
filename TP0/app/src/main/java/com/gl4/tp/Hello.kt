package com.gl4.tp

import java.lang.Math.abs

fun main(argv : Array<String>){
    // QUESTION 1
    println("Hello !")
    // QUESTION 2
    data class Point(var x: Int, var y: Int)
    // QUESTION 3
    fun distance(a : Point, b : Point): Int {
        return abs(a.x - b.x) + abs(a.y - b.y);
    }

    var a : Point = Point(1,3)
    var b : Point = Point(2,4)

    println(distance(a,b));
    // QUESTION 5


    class Rectangle(p : Point = Point(0,0) , q: Point = Point(1,1)){
        val p : Point = p
        val q : Point = q
        override fun toString() : String{
            return "p=$p q=$q"
        }

        fun surface() : Int {
            return abs(p.x-q.x) * abs(p.y-q.y)
        }
    }
    val rectangles = arrayOf(Rectangle(), Rectangle(p=a), Rectangle(q=b), Rectangle(a, b))
    for (rectangle in rectangles){
        print("Surface of "+rectangle.toString()+" is : ")
        println(rectangle.surface())
    }


}