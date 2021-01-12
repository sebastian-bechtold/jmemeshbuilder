package com.sebastianbechtold.jmeutil

import com.jme3.math.ColorRGBA
import com.jme3.math.Vector3f
import com.jme3.scene.Mesh
import com.jme3.scene.VertexBuffer
import java.util.*


class JmeMeshBuilder {

    var posBuffer = ArrayList<Float>()
    var normalsBuffer = ArrayList<Float>()
    var colorsBuffer = ArrayList<Float>()
    var texCoordsBuffer = ArrayList<Float>()

    var indices = ArrayList<Int>()



    var index = 0

    fun addVertex(pos: Vector3f, normal: Vector3f, tex: Vector3f = Vector3f(0f, 0f, 0f), color: ColorRGBA = ColorRGBA.White) {


        this.posBuffer.add(pos.x)
        this.posBuffer.add(pos.y)
        this.posBuffer.add(pos.z)

        normalsBuffer.add(normal.x)
        normalsBuffer.add(normal.y)
        normalsBuffer.add(normal.z)

        texCoordsBuffer.add(tex.x)
        texCoordsBuffer.add(tex.y)


        var rndcol = false

        if (!rndcol) {

            colorsBuffer.add(color.r)
            colorsBuffer.add(color.g)
            colorsBuffer.add(color.b)
            colorsBuffer.add(color.a)
        } else {
            var rnd = Random()
            colorsBuffer.add(rnd.nextFloat())
            colorsBuffer.add(rnd.nextFloat())
            colorsBuffer.add(rnd.nextFloat())
            colorsBuffer.add(1f)
        }

        indices.add(index)
        index++;
    }





    fun build(): Mesh {

        val mesh = Mesh()

        mesh.mode = Mesh.Mode.Triangles

        mesh.setBuffer(VertexBuffer.Type.Position, 3, posBuffer.toFloatArray())
        mesh.setBuffer(VertexBuffer.Type.Index, 3, indices.toIntArray());
        mesh.setBuffer(VertexBuffer.Type.Normal, 3, normalsBuffer.toFloatArray());
        mesh.setBuffer(VertexBuffer.Type.TexCoord, 2, texCoordsBuffer.toFloatArray());
        mesh.setBuffer(VertexBuffer.Type.Color, 4, colorsBuffer.toFloatArray())


        mesh.updateBound();

        return mesh;
    }


    fun reset() {
        index = 0
        indices.clear()
        posBuffer.clear()
        normalsBuffer.clear()
        colorsBuffer.clear()
        texCoordsBuffer.clear()
    }

}