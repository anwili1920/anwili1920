data class vertice<T>(val indice: Int,val data: T)
data class arista<T>(
    val origen: vertice<T>,
    val destino: vertice<T>,
    val distancia: Double?=null
)
interface graphos<T>{
    fun crearvertice(dato: T): vertice<T>
    fun agregar(origen: vertice<T>,destino: vertice<T>,distancia: Double?)
    fun aristas(destino: vertice<T>): ArrayList<arista<T>>
    fun distancia(
        origen: vertice<T>,
        destino: vertice<T>): Double?
}

class listaAdyacente<T>: graphos<T>{
    private val adyacentes: HashMap< vertice<T>,ArrayList<arista<T>> > =HashMap()
    override fun crearvertice(dato: T):vertice<T> {
        val ver= vertice( adyacentes.count(), dato)
        adyacentes[ver]= ArrayList()
        return ver
    }
    fun añadir(origen:vertice<T>,destino:vertice<T>,distancia:Double?){
        val a= arista(origen,destino,distancia)
        adyacentes[origen]?.add(a)
    }
    override fun aristas(origen: vertice<T>)=adyacentes[origen] ?:arrayListOf()
    override fun toString(): String{
        return buildString{
            adyacentes.forEach{(vertice,arista)->//2
            val edgeString=aristas.joinToString
            { it.destino.dato.toString()}//3
            append("${vertice.data}-->[ $edgeString ]\n")
            }
        }
    }
    override fun toString(): String {
return buildString { // 1
adjacencies.forEach { (vertex, edges) -> // 2
val edgeString = edges.joinToString
{ it.destination.data.toString() } // 3
append("${vertex.data} ---> [ $edgeString ]\n") // 4
}
}
}
} 
fun main(){
 // creando los vertices de los lugares
 val graph=listaAdyacente<String>()
 val hospital=graph.crearvertice("hospital")
 val casa=graph.crearvertice("casa")
 val farmacia=graph.crearvertice("farmacia")
 val laboratorio=graph.crearvertice("laboratorio")
 val radiografia=graph.crearvertice("radiografia")
 val banco=graph.crearvertice("banco")
 val paradero=graph.crearvertice("paradero")
 graph.añadir(casa,hospital,3000.58)
 graph.añadir(hospital,farmacia,2500.58)
 graph.añadir(casa,farmacia,5000.49)
 graph.añadir(farmacia,laboratorio,450.58)
 graph.añadir(laboratorio,radiografia,500.58)
 graph.añadir(radiografia,banco,292.58)
 graph.añadir(banco,farmacia,300.49)
 
 println("Todo funciona correctamente")
 //val hospital=graph.crearvertice("")
}  
