import java.util.ArrayList;
import java.util.List;
/**
 * Class Nodo
 * 
 * @author João Moreira Dias
 * @version 8 de Abril de 2013
 */
public class Nodo
{
    private Estado estado; //Um Nodo tem um estado
    private Nodo nodoPai; //Um nodo tem um Nodo pai
    private double custo; //Custo do caminho

    /**
     * Construtor para a class Nodo
     * @param estado  Estado que vai pretencer ao nodo
     * @param custo Custo que a viagem tem de um Nodo para outro
     */
    public Nodo(Estado estado, double custo)
    {
        this.estado=estado;
        this.custo=custo;
    }

    /**
     * Adiciona um nodo filho a este nodo
     * @param sucessores    Lista de sucessores, onde o novo filho vai ser adicionado
     * @param nEstado  Nodo que vai ter este nodo como pai, filho deste nodo
     * @param nCusto   Novo cusro do caminho de um Nodo para outro
     */
    private void adicionar(List<Nodo> sucessores, Estado nEstado, double nCusto)
    {
        Nodo novo=new Nodo(nEstado, custo + nCusto);
        novo.setEstadoPai(this);
        sucessores.add(novo);
    }

    /**
     * Estado encontra o proximo sucesor valido
     * @return Lista de sucessores
     */
    public ArrayList<Nodo> getSuc()
    {
        ArrayList<Nodo> sucessores = new ArrayList<Nodo>();
        ArrayList<Grafo> ramosSucessores=estado.getSuc();
        for(Grafo cada : ramosSucessores)
        {
            adicionar(sucessores, cada.getEstado(), cada.getCusto());
        }
        return sucessores;
    }

    /**
     * Pede para verifica se o estado é final
     */
    public boolean isFinal()
    {
        return estado.isFinal();
    }

    /**
     * Estado encontra o proximo sucesor valido
     * @return Pai deste Nodo
     */
    public Nodo getNodoPai()
    {
        return nodoPai;
    }

    /**
     * Estado encontra o proximo sucesor valido
     * @return Lista de sucessores
     */
    public void setEstadoPai(Nodo novoNodo)
    {
        nodoPai = novoNodo;
    }

    /**
     * Override do toString
     * Apresenta de forma clara o estado
     */
    public String toString()
    {
        return estado.toString()+"    -  "+custo;
    }
}