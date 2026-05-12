import java.util.*;

public class Salones<T>{
    // Se tiene un conjunto de salas comunicadas entre sí a través de puertas que se abren solamente en
    //un sentido. Una de las salas se denomina entrada y la otra salida. Construir un algoritmo que
    //permita ir desde la entrada a la salida atravesando la máxima cantidad de salas. Idea: podría
    //representar el problema mediante un grafo dirigido, donde cada nodo es una habitación, y cada
    //puerta es un arco dirigido hacia otra habitación
        HashMap<Integer,Integer> salones;
    public ArrayList<Integer> encontrarCamino(Grafo<T> grafo, int entrada, int salida) {
        ArrayList<Integer> caminoActual = new ArrayList<>();
        ArrayList<Integer> caminoMaximo = new ArrayList<>();

        recorrer(grafo, entrada, salida, caminoActual, caminoMaximo);

        return caminoMaximo;
    }

    private void recorrer(Grafo<T> grafo, int actual, int salida,
                          ArrayList<Integer> caminoActual,
                          ArrayList<Integer> caminoMaximo) {

        // PODA: si el vértice ya fue visitado no continuar
        if (caminoActual.contains(actual)) return;

        // agrego al camino actual
        caminoActual.add(actual);

        // llegué a la salida, comparo con el máximo
        if (actual == salida) {
            if (caminoActual.size() > caminoMaximo.size()) {
                caminoMaximo.clear();
                caminoMaximo.addAll(caminoActual);

            }
        } else {
            // sigo explorando adyacentes
            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual);
            while (adyacentes.hasNext()) {
                int vecino =  adyacentes.next();
                if (!caminoActual.contains(vecino)) {
                    recorrer(grafo,vecino, salida, caminoActual, caminoMaximo);
                }
            }
        }

        // backtracking: saco el vértice al retroceder
        caminoActual.remove(caminoActual.size() - 1);
    }

    //Suma de subconjuntos. Dados n números positivos distintos, se desea encontrar todas las
    //combinaciones de esos números tal que la suma sea igual a M.


    public ArrayList<Integer>sumaConvinaciones(ArrayList<Integer>n , int m, ArrayList<Integer> combinacionActual, ArrayList<Integer> cumplen){
        ArrayList<Integer> cumplen = new ArrayList<>();
        ArrayList<Integer> conbinacionActual = new ArrayList<>();

        sumaSubConjunto(n,m,0,0,conbinacionActual,cumplen);
        return cumplen;
    }

    private void sumaSubConjunto(ArrayList<Integer> n, int m, int pos,int suma,ArrayList<Integer> combinacionActual,
                                 ArrayList<Integer> cumplen) {
        for (int num : combinacionActual) {
            suma += num;
        }
        if (suma == m) {
            cumplen.add(new ArrayList<>(combinacionActual));
            return;
        }

        for (int i = pos; i < n.size(); i++) {
            if (suma + n.get(i) <= m) {
                combinacionActual.add(n.get(i));
                sumaSubConjunto(n, m, i + 1, suma, combinacionActual, cumplen);
                combinacionActual.remove(combinacionActual.size() - 1);
            }

        }
    }

            //Partición de conjunto. Dado un conjunto de n enteros se desea encontrar, si existe, una partición en
            //dos subconjuntos disjuntos, tal que la suma de sus elementos sea la misma.

    public ArrayList<ArrayList<Integer>> encuntraConjunto(ArrayList<Integer>cc) {
        ArrayList<ArrayList<Integer>> resultado = new ArrayList<>();
            sumaconjunto(cc,0,0,resultado);
            return resultado;
    }

    private ArrayList<Integer> sumaconjunto(ArrayList<Integer> cc,int pos, int total, ArrayList<ArrayList<Integer>> resultado) {
       ArrayList<ArrayList<Integer>> subconjuntoB = new ArrayList<>();
        ArrayList<ArrayList<Integer>> subconjuntoA = new ArrayList<>();

        int mitad;

        if (Espar(cc)){

            for (Integer i:cc){
                total+=cc.get(i);
            }

            mitad = total/2;
            for (int i = pos; i < cc.size(); i++) {

                int suma=0;
                if (suma<=mitad){
                    subconjuntoA.add(cc.get(i));
                    sumaconjunto(cc,pos+1,total,subconjuntoA);
                    subconjuntoA.remove(subconjuntoA.size() - 1);
                } else if (suma>mitad) {
                    subconjuntoB.add(cc.get(i));
                    sumaconjunto(cc,pos+1,total,subconjuntoA);
                    subconjuntoA.remove(subconjuntoA.size() - 1);
                }

            }

        }
        if (subconjuntoA.isEmpty())
            return resultado;
        else {
            resultado.addAll(subconjuntoA);
            resultado.addAll(subconjuntoB);
        }
        return resultado;
    }

private boolean Espar(ArrayList<Integer> cc) {
     int suma=0;
        for (int i = 0; i < cc.size(); i++) {
            suma+= cc.get(i);

        }
        if (suma/2==0) return true;
        return false;
    }

    public ArrayList<ArrayList<Integer>> asignarTareaProcesadores(int[] procesadores, int[] tareas) {
        ArrayList<ArrayList<Integer>> mejorCombinacion = new ArrayList<>();

        // inicializo una lista por cada procesador
        for (int i = 0; i < procesadores.length; i++) {
            mejorCombinacion.add(new ArrayList<>());
        }

        ArrayList<ArrayList<Integer>> combinacionActual = new ArrayList<>();
        for (int i = 0; i < procesadores.length; i++) {
            combinacionActual.add(new ArrayList<>());
        }

        int[] tiempoPorProcesador = new int[procesadores.length];
        int[] mejorTiempo = {Integer.MAX_VALUE};

        asignarTareaPro(procesadores, tareas, combinacionActual, mejorCombinacion, tiempoPorProcesador, mejorTiempo, 0);

        return mejorCombinacion;
    }

    private void asignarTareaPro(int[] procesadores, int[] tareas,
                                 ArrayList<ArrayList<Integer>> combinacionActual,
                                 ArrayList<ArrayList<Integer>> mejorCombinacion,
                                 int[] tiempoPorProcesador, int[] mejorTiempo, int pos) {

        // condicion de corte: asigné todas las tareas
        if (pos == tareas.length) {
            // calculo el tiempo del procesador más lento
            int tiempoActual = 0;
            for (int t : tiempoPorProcesador) {
                tiempoActual = Math.max(tiempoActual, t);
            }
            // si es mejor que el actual lo guardo
            if (tiempoActual < mejorTiempo[0]) {
                mejorTiempo[0] = tiempoActual;
                mejorCombinacion.clear();
                for (ArrayList<Integer> lista : combinacionActual) {
                    mejorCombinacion.add(new ArrayList<>(lista));
                }
            }
            return;
        }

        // pruebo asignar la tarea pos a cada procesador
        for (int p = 0; p < procesadores.length; p++) {
            // asigno tarea al procesador p
            combinacionActual.get(p).add(tareas[pos]);
            tiempoPorProcesador[p] += tareas[pos];

            // sigo con la siguiente tarea
            asignarTareaPro(procesadores, tareas, combinacionActual, mejorCombinacion, tiempoPorProcesador, mejorTiempo, pos + 1);

            // backtracking
            combinacionActual.get(p).remove(combinacionActual.get(p).size() - 1);
            tiempoPorProcesador[p] -= tareas[pos];
        }
    }

    //Caballo de Atila. Por donde pisa el caballo de Atila jamás vuelve a crecer el pasto. El caballo fue
    //directamente hacia el jardín de n x n casillas. Empezó su paseo por una casilla cualquiera y volvió a
    //ella, es decir hizo un recorrido cerrado. No visitó dos veces una misma casilla, se movió de una
    //casilla a otra vecina en forma horizontal o vertical, pero nunca en diagonal. Por donde pisó el
    //caballo, el pasto jamás volvió a crecer. Luego de terminado el recorrido en algunas casillas todavía
    //había pasto (señal de que en ellas no había estado el caballo). Escriba un algoritmo que deduzca el
    //recorrido completo que hizo el caballo.


    public void caballoAtila(int [][] jardin){
            ArrayList<int[]>recorrido = new ArrayList<>();
        ArrayList<int[]>mejorRecorrido = new ArrayList<>();

        for (int i = 0; i < jardin.length; i++) {
            for (int j = 0; j < jardin[i].length; j++) {
                if (jardin[i][j]==1) {
                    recorrido.add(new int[]{i, j});
                    atila(jardin,i,j,i,j, recorrido,mejorRecorrido);
            }
        }

    }









}

    private void atila(int[][] jardin, int filaActual, int colActual, int filaInicio, int colInicio,
                       ArrayList<int[]> recorrido, ArrayList<int[]> mejorRecorrido)
    {
        if (filaActual < 0 || filaActual >= jardin.length ||
                colActual < 0 || colActual >= jardin[0].length) return;

        // PODA: casilla no pisada o ya visitada
        if (jardin[filaActual][colActual] != 1) return;

        // marco como visitada
        jardin[filaActual][colActual] = 2;
        recorrido.add(new int[]{filaActual, colActual});

        // verifico si es solución: volvió al inicio y pisó todas las casillas
        boolean esAdyacenteOrigen = (Math.abs(filaActual - filaInicio) +
                Math.abs(colActual - colInicio)) == 1;
        if (esAdyacenteOrigen && recorrido.size() > mejorRecorrido.size()) {
            mejorRecorrido.clear();
            mejorRecorrido.addAll(recorrido);
        }

        // exploro los 4 movimientos posibles
        atila(jardin, filaActual - 1, colActual, filaInicio, colInicio, recorrido, mejorRecorrido); // arriba
        atila(jardin, filaActual + 1, colActual, filaInicio, colInicio, recorrido, mejorRecorrido); // abajo
        atila(jardin, filaActual, colActual - 1, filaInicio, colInicio, recorrido, mejorRecorrido); // izquierda
        atila(jardin, filaActual, colActual + 1, filaInicio, colInicio, recorrido, mejorRecorrido); // derecha

        // backtracking
        jardin[filaActual][colActual] = 1;
        recorrido.remove(recorrido.size() - 1);
    }

    //7 Tablero mágico. Dado un tablero de tamaño n x n, construir un algoritmo que ubique (si es posible)
    //n*n números naturales diferentes, entre 1 y un cierto k (con k>n*n), de manera tal que la suma de las
    //columnas y de las filas sea igual a S.

    public boolean esIgualS(int n, int s, int k) {
        int[][] tablero = new int[n][n];
        int[] sumaFila = new int[n];
        int[] sumaCol = new int[n];
        HashSet<Integer> usados = new HashSet<>();
        return encuentraSumaIgual(tablero, n, 0, s, k, sumaFila, sumaCol, usados);
    }

    private boolean encuentraSumaIgual(int[][] tablero, int n, int pos, int s, int k,
                                       int[] sumaFila, int[] sumaCol,
                                       HashSet<Integer> usados) {

        // condicion de corte: llené todo el tablero
        if (pos == n * n) {
            // verifico que todas las columnas sumen S
            for (int col = 0; col < n; col++) {
                if (sumaCol[col] != s) return false;
            }
            return true;
        }

        int fila = pos / n;
        int col = pos % n;

        // pruebo cada número del 1 al k
        for (int num = 1; num <= k; num++) {

            // PODA: número ya usado
            if (usados.contains(num)) continue;

            // PODA: suma de fila se pasa de S
            if (sumaFila[fila] + num > s) continue;

            // PODA: suma de columna se pasa de S
            if (sumaCol[col] + num > s) continue;

            // coloco el número
            tablero[fila][col] = num;
            sumaFila[fila] += num;
            sumaCol[col] += num;
            usados.add(num);

            // sigo con la siguiente casilla
            if (encuentraSumaIgual(tablero, n, pos + 1, s, k, sumaFila, sumaCol, usados)) {
                return true;
            }

            // backtracking
            tablero[fila][col] = 0;
            sumaFila[fila] -= num;
            sumaCol[col] -= num;
            usados.remove(num);
        }

        return false;
    }

}

