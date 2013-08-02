import java.util.ArrayList;
import java.util.List;
/**
 * Class Main, contem o main.
 * 
 * @author João Moreira Dias
 * @version 8 de Abril de 2013
 */
public class Main{
    /**
     * Método main desta aplicação
     */
    public static void main(String[] args){
        System.out.println("\nCanibais e Missionarios");
        Nodo nInicial = new Nodo(new Estado (3, 3, false, 0, 0));
        resolver(nInicial);
    }

    /**
     * Método de resolução do problema
     */
    private static void resolver(Nodo nInicial){
        Busca search = new Busca();
        Nodo solution = search.exec(nInicial);
        imprimSol(solution);
    }

    /**
     * Método de impressão da solução
     */
    private static void imprimSol(Nodo solution){
        System.out.println("\nLegenda:\n(canibalEsq,missionaryEsq,barco,canibalDir,missionaryDir)\n\n--Inicio--");
        List<Nodo> caminho = new ArrayList<Nodo>();
        Nodo nodo = solution;
        while(null!=nodo){
            caminho.add(nodo);
            nodo = nodo.getPaiNodo();
        }

        int perfundidade = caminho.size() - 1;
        for (int i = perfundidade; i >= 0; i--){
            nodo = caminho.get(i);
            if (nodo.isFinal()){
                System.out.print(nodo.toString() + "\n----FIM----");
            }
            else{
                System.out.print(nodo.toString() + "\n");
            }
        }
    }
}