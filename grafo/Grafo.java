/**
 * Class Grafo
 * 
 * @author João Moreira Dias
 * @version 8 de Abril de 2013
 */
public class Grafo
{
    private Estado estado; //Estado que o grafo vai ter
    private double custo; //Custo que a viagem, ou grafo, tem

    /**
     * Construtor da class Grafo
     * @param novoEstado    Estado novo a que este grafo vai pretençer
     * @param novoCusto Custo que o grafo vai ter
     */
    public Grafo(Estado novoEstado, double novoCusto)
    {
        estado=novoEstado;
        custo=novoCusto;
    }

    /**
     * GETTER para retornar o estado deste grafo
     * @return  O estado do grafo
     */
    public Estado getEstado()
    {
        return estado;
    }

    /**
     * GETTER do custo da viagem, ou grafo
     * @return  o custo da viagem, ou grafo
     */
    public double getCusto()
    {
        return custo;
    }
}