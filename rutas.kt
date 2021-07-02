class Grafos(var cantMax: Int = 100){
    var tamaño:Int = 0
    var vertices:Array<Int> = Array(cantMax,{0})
    var adyacentes:Array<MutableList<Int>> = Array(cantMax,{mutableListOf()})
    var pesos:Array<MutableList<Int>> = Array(cantMax,{mutableListOf()})

    fun es_vacio():Boolean{
        return tamaño==0
    }

    fun busca(v:Int):Int{
        for (i in 0..tamaño-1){
            if (v == vertices[i]){
                return i
            }
        }
        return -1
    }

    fun añade_ver(v:Int){
        var aux = busca(v)
        if (aux<0){
            check(tamaño < vertices.size){"----Espacio insuficiente------"}
            vertices[tamaño] = v
            tamaño += 1 
        }
    }

    fun añ_arista(v:Int,w:Int,p:Int){
        var p1 = busca(v)
        var p2 = busca(w)
        check(p1>=0 && p2 >=0){"-----vertices desconocidos-----"}
        if(w !in adyacentes[p1] ){
            adyacentes[p1].add(w)
            pesos[p1].add(p)
            adyacentes[p2].add(v)
            pesos[p2].add(p)
        }
    }

    fun esta_vertice(v:Int):Boolean{
        val aux = busca(v)
        return aux >= 0 
    }

    fun esta_arista(v:Int,w:Int):Boolean{
        var p1 = busca(v)
        return (p1 >=0 && w in adyacentes[p1])
    }

    fun elim_arista(v:Int,w:Int){
        var p = busca(v)
        var q = busca(w)
        if (p>=0 && q>=0){
            var j = adyacentes[p].indexOf(w)
            adyacentes[p].remove(w)
            pesos[p].removeAt(j)
            var k = adyacentes[q].indexOf(v)
            adyacentes[q].remove(v)
            pesos[q].removeAt(k)
        }
    }

    fun elim_vertice(v:Int){
        var p = busca(v)
        if(p>=0){//mover a la izquierda
            for (i in p+1..tamaño-1){
                vertices[i-1] = vertices[i]
                adyacentes[i-1] = adyacentes[i]
                pesos[i-1] = pesos[i]
            }
            tamaño -= 1
            for (i in 0..tamaño-1){
                if(v in adyacentes[i]){
                    var j = adyacentes[i].indexOf(v)
                    adyacentes[i].remove(v)
                    pesos[i].removeAt(j)
                }
            }
        }
    }

    fun adyacentes(v:Int){
        var p = busca(v)
        if(p>=0){
            println("Los adyacentes son: ${adyacentes[p]} con pesos: ${pesos[p]}")}
        else{
            println("Vertice desconocido")}
    }

    fun verificaRuta(w:Int,p:Int,list: MutableList<Int>,camino: MutableList<Int>):Boolean{
        var aux:Boolean
        if(adyacentes[p].isEmpty()){
            return false
        }
        if(w in adyacentes[p]){
            return true
        }
        else{
            var tam = adyacentes[p].size
            for (i in 0..tam-1){
                var num = adyacentes[p].get(i)
                if(num !in list){
                    var q = busca(num)
                    list.add(num)
                    camino.add(num)
                    aux = verificaRuta(w,q,list,camino)
                    if(aux){
                        return true
                    }
                    else{
                        camino.remove(num)
                    }
                }
            }
        }
        return false
    }

    fun existeRuta(v:Int,w:Int){
        var p = busca(v)
        var list = mutableListOf<Int>()
        var camino = mutableListOf<Int>()
        var suma = 0
        list.add(v)
        camino.add(v)
        var aux = verificaRuta(w,p,list,camino)
        if(aux){
            camino.add(w)
            println("----------------------")
            println("Si hay una ruta de $v a $w")
            println("La ruta que se seguira es:")
            for(i in 0..camino.size-1){
                print(" ${camino[i]} ")
                if(i != camino.size-1){
                    print("-> ")
                }
                if(i >0){
                    var pos = busca(camino[i-1])
                    var ind = adyacentes[pos].indexOf(camino[i])
                    suma += pesos[pos].get(ind)
                }
            }
            println(" ")
            println("Los kilometros a recorrer en total son: $suma")
            println("----------------------")
        }
        else{
            println("----------------------")
            println("No hay una ruta de $v a $w")
            println("----------------------")
        }
    }

}

fun main() {
    var myGraph = Grafos(10) // Crear un grafo para 10 elementos
    myGraph.añade_ver(0)//añade un vértice 0 
    myGraph.añade_ver(1)//añade un vértice 1
    myGraph.añade_ver(2)//añade un vértice 2
    myGraph.añade_ver(3)//añade un vértice 3
    myGraph.añade_ver(4)//añade un vértice 4
    myGraph.añade_ver(5)//añade un vértice 5
    myGraph.añade_ver(6)
    myGraph.añ_arista(0,1,3) // añade una arista de 0 a 1 
    myGraph.añ_arista(0,3,5) // añade una arista de 0 a 3 
    //myGraph.añ_arista(1,3,7) // añade una arista de 1 a 3 
    //myGraph.añ_arista(1,4,2) // añade una arista de 1 a 4 
    myGraph.añ_arista(2,4,6) // añade una arista de 2 a 4 
    //myGraph.añ_arista(3,2,1) // añade una arista de 3 a 2 
    myGraph.añ_arista(5,1,4) // añade una arista de 5 a 1
    //myGraph.añ_arista(5,3,8) // añade una arista de 5 a 3  
    myGraph.añ_arista(3,4,1)
    while(true){
        print("Ingrese el punto inicial: (-1 para terminar):  ")
        var ini = readLine()!!.toInt()
        if(ini == -1){
            break
        }
        print("Ingrese el punto final: (-1 para terminar):  ")
        var fin = readLine()!!.toInt()
        if(fin == -1){
            break
        }
        myGraph.existeRuta(ini,fin)
    }
    }

