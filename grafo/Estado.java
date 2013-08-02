import java.util.ArrayList;
/**
 * Class Estado
 * 
 * @author João Moreira Dias
 * @version 8 de Abril de 2013
 */
public class Estado{
    private int numero; //Numbero do estado, de 1 a 12
    
    //"Mapa" de todos os caminhos e os custos respectivos de cada caminho, quando tem -1 não há ligação
    //Vai mais tarde ser usado na busca de succesores
    private static final int[][] matrix ={
            {-1, 2, 8, 6, -1, -1, -1, -1, -1, -1, -1, -1},
            {2, -1, 3, -1, 3, -1, -1, -1, -1, -1, -1, -1},
            {8, 3, -1, 1, -1, 4, -1, -1, -1, -1, -1, -1},
            {6, -1, 1, -1, -1, -1, 9, 8, -1, -1, -1, -1},
            {-1, 3, -1, -1, -1, 9, -1, -1, 11, -1, -1, -1},
            {-1, -1, 4, -1, 9, -1, -1, -1, 1, -1, -1, -1},
            {-1, -1, -1, 9, -1, -1, -1, 6, 2, 4, -1, -1},
            {-1, -1, -1, 8, -1, -1, 6, -1, -1, 7, -1, -1},
            {-1, -1, -1, -1, 11, 1, 2, -1, -1, 12, 2, -1},
            {-1, -1, -1, -1, -1, -1, 4, 7, 12, -1, -1, 5},
            {-1, -1, -1, -1, -1, -1, -1, -1, 2, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, 5, -1, -1}
        };

    /**
     * Construtor de um Estado
     * @param nNumero Novo numero que o estado vai ter.
     */
    public Estado(int nNumero)
    {
        numero=nNumero;
    }

    /**
     * Override do toString desta class.
     */
    public String toString()
    {
        return Integer.toString(numero); //Deixa imprimir um Int como String
    }

    /**
     * Verifica se o estado é final
     */
    public boolean isFinal()
    {
        return numero==12; //12 é o ultimo nodo
    }

    /**
     * Estado encontra o proximo sucesor valido
     * @return Lista de sucessores
     */
    public ArrayList<Grafo> getSuc()
    {
        ArrayList<Grafo> resultado=new ArrayList<Grafo>();
        for (int i=0; i<matrix.length; i++)
        {
            if((matrix[numero-1][i])>0)
            {
                Grafo temp =new Grafo(new Estado(i+1), matrix[numero-1][i]);
                resultado.add(temp);
            }
        }
        return resultado;
    }
}