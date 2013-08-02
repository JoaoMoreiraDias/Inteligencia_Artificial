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
    private Estado estado; //Estado do nodo
    private Nodo paiNodo; //Estado pai deste estado

    /**
     * Construtor para a class Nodo
     * @param estado  Estado que vai pretencer ao nodo
     */
    public Nodo(Estado estado)
    {
        this.estado=estado;
    }

    /**
     * Verifica se o estado é final
     */
    /*
    public boolean isFinal(){
    return estado.getcanibalEsq() == 0 && estado.getmissionarioEsq() == 0;
    }
     */

    /**
     * Nodo encontra o proximo sucesor valido
     * @return Lista de sucessores
     */
    /*public List<Nodo> getSuc(){
    List<Nodo> sucessores = new ArrayList<Nodo>();
    if (estado.getposBarco() == false){
    adicionar(sucessores, new Nodo(new Estado(estado.getcanibalEsq(), estado.getmissionarioEsq() - 2, true, estado.getcanibalDir(), estado.getmissionarioDir() + 2)));
    adicionar(sucessores, new Nodo(new Estado(estado.getcanibalEsq() - 2, estado.getmissionarioEsq(), true, estado.getcanibalDir() + 2, estado.getmissionarioDir())));
    adicionar(sucessores, new Nodo(new Estado(estado.getcanibalEsq() - 1, estado.getmissionarioEsq() - 1, true, estado.getcanibalDir() + 1, estado.getmissionarioDir() + 1)));
    adicionar(sucessores, new Nodo(new Estado(estado.getcanibalEsq(), estado.getmissionarioEsq() - 1, true, estado.getcanibalDir(), estado.getmissionarioDir() + 1)));
    adicionar(sucessores, new Nodo(new Estado(estado.getcanibalEsq() - 1, estado.getmissionarioEsq(), true, estado.getcanibalDir() + 1, estado.getmissionarioDir())));
    }
    else{
    adicionar(sucessores, new Nodo(new Estado(estado.getcanibalEsq(), estado.getmissionarioEsq() + 2, false, estado.getcanibalDir(), estado.getmissionarioDir() - 2)));
    adicionar(sucessores, new Nodo(new Estado(estado.getcanibalEsq() + 2, estado.getmissionarioEsq(), false, estado.getcanibalDir() - 2, estado.getmissionarioDir())));
    adicionar(sucessores, new Nodo(new Estado(estado.getcanibalEsq() + 1, estado.getmissionarioEsq() + 1, false, estado.getcanibalDir() - 1, estado.getmissionarioDir() - 1)));
    adicionar(sucessores, new Nodo(new Estado(estado.getcanibalEsq(), estado.getmissionarioEsq() + 1, false, estado.getcanibalDir(), estado.getmissionarioDir() - 1)));
    adicionar(sucessores, new Nodo(new Estado(estado.getcanibalEsq() + 1, estado.getmissionarioEsq(), false, estado.getcanibalDir() - 1, estado.getmissionarioDir())));
    }
    return sucessores;
    }
     */
    public Nodo getPaiNodo(){
        return paiNodo;
    }

    public void setpaiNodo(Nodo paiNodo){
        this.paiNodo = paiNodo;
    }

    /**
     * Adiciona o estado a lista de sucesores se for válido
     * @param   sucessores   lista de sucessores a que vai ser acrescentado o novo estado
     * @param   eNovo   Estado novo
     */
    /*
    private void adicionar(List<Nodo> sucessores, Nodo nNovo){
    if (nNovo.estado.isValido()) {
    nNovo.setpaiNodo(this);
    sucessores.add(nNovo);
    }
    }
     */

    /**
     * Override do toString
     * Apresenta de forma clara o estado
     */
    public String toString(){
        if (estado.getposBarco() == false){
            return "(" + estado.getcanibalEsq() + "," + estado.getmissionarioEsq() + ",E," + estado.getcanibalDir() + "," + estado.getmissionarioDir() + ")";
        }
        else{
            return "(" + estado.getcanibalEsq() + "," + estado.getmissionarioEsq() + ",D," + estado.getcanibalDir() + "," + estado.getmissionarioDir() + ")";
        }
    }
}