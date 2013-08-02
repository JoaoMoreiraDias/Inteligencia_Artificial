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
        System.out.println("\nNickel and Dime");
        int[] pos = new int[5];
        pos[0] = 0;
        pos[1] = 0;
        pos[2] = -1;
        pos[3] = 1;
        pos[4] = 1;
        Nodo nInicial = new Nodo(new Estado(pos));
        resolver(nInicial);
    }

    /**
     * Método de resolução do problema
     * @param nInicial   Nodo em que o problema começa
     */
    private static void resolver(Nodo nInicial)
    {
        Busca busca = new Busca();
        Nodo novo = busca.exec(nInicial);
        imprimSol(novo);
    }

    /**
     * Método de impressão da solução
     * @param novo  Nodo novo que vai se impresso
     */
    private static void imprimSol(Nodo novo)
    {
        System.out.println("\nLegenda:\nN=Nickel\nD=Dime\nX=Vazio\n\n--Inicio--");
        ArrayList<Nodo> caminho = new ArrayList<Nodo>();
        Nodo estado = novo;
        while(null!=estado)
        {
            caminho.add(estado);
            estado = estado.getNodoPai();
        }
        int perfundidade = caminho.size() - 1;
        for (int i = perfundidade; i >= 0; i--)
        {
            estado = caminho.get(i);
            if (estado.isFinal())
            {
                System.out.print(estado.toString() + "\n----FIM----");
            }
            else
            {
                System.out.print(estado.toString() + "\n");
            }
        }
    }
}