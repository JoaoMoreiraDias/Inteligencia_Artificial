import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class Estado here.
 * 
 * @author João Moreira Dias
 * @version 8 de Abril de 2013
 */
public class Estado
{
    private int canibalEsq; //Numero de canibais da margem esquerda
    private int missionarioEsq; //Numero de missionarios na margem esquerda
    private int canibalDir; //Numero de canibais na maregem direita
    private int missionarioDir; // Numero de missionario na margem esquerda
    private boolean posBarco; //Em que margem é que o barco esta: esquerda = false, direita = true

    /**
     * Construtor para objectos da class Estado
     */
    public Estado(int canibalEsq, int missionarioEsq, boolean posBarco, int canibalDir, int missionarioDir)
    {
        this.canibalEsq = canibalEsq;
        this.missionarioEsq = missionarioEsq;
        this.posBarco = posBarco;
        this.canibalDir = canibalDir;
        this.missionarioDir = missionarioDir;
    }

    /**
     * Verifica se o estado é valido/possivel, pois há regras para "perder"/estados illegais
     */
    public boolean isValido(){
        if (getmissionarioEsq() >= 0 && getmissionarioDir() >= 0 && getcanibalEsq() >= 0 && getcanibalDir() >= 0 && (getmissionarioEsq() == 0 || getmissionarioEsq() >= getcanibalEsq()) && (getmissionarioDir() == 0 || getmissionarioDir() >= getcanibalDir())){
            return true;
        }
        return false;
    }

    //Principio de Getters e Setters de um estado
    public int getcanibalEsq(){
        return canibalEsq;
    }

    public void setcanibalEsq(int canibalEsq){
        this.canibalEsq = canibalEsq;
    }

    public int getmissionarioEsq(){
        return missionarioEsq;
    }

    public void setmissionarioEsq(int missionarioEsq){
        this.missionarioEsq = missionarioEsq;
    }

    public int getcanibalDir(){
        return canibalDir;
    }

    public void setcanibalDir(int canibalDir){
        this.canibalDir = canibalDir;
    }

    public int getmissionarioDir(){
        return missionarioDir;
    }

    public void setmissionarioDir(int missionarioDir){
        this.missionarioDir = missionarioDir;
    }

    public boolean getposBarco(){
        return posBarco;
    }
    // Fim de Getters e Setters

    /**
     * Mover o barco para a esquerda
     */
    public void moverEsq(){
        posBarco = false;
    }

    /**
     * Mover o barco para a direita
     */
    public void moverDir(){
        posBarco = true;
    }

    /**
     * Verifica se o barco esta na esquerda
     * O barco esta na esquerda?
     */
    public boolean isOnEsq(){
        return posBarco == false;
    }

    /**
     * Verifica se o barco esta na direita
     * O barco esta na direita?
     */
    public boolean isOnRigth(){
        return posBarco == true;
    }

    /**
     * Adiciona o estado a lista de sucesores se for válido
     * @param   sucessores   lista de sucessores a que vai ser acrescentado o novo estado
     * @param   eNovo   Estado novo
     */
    private void adicionar(List<Nodo> sucessores, Nodo nNovo){
        if (nNovo.estado.isValido()) {
            nNovo.setpaiNodo(this);
            sucessores.add(nNovo);
        }
    }

    /**
     * Verifica se o estado é final
     */
    public boolean isFinal(){
        return estado.getcanibalEsq() == 0 && estado.getmissionarioEsq() == 0;
    }

    /**
     * Nodo encontra o proximo sucesor valido
     * @return Lista de sucessores
     */
    public List<> getSuc(){
        List<> sucessores = new ArrayList<>();
        if (getposBarco() == false){
            adicionar(sucessores, new Nodo(new Estado(getcanibalEsq(), getmissionarioEsq() - 2, true, getcanibalDir(), getmissionarioDir() + 2)));
            adicionar(sucessores, new Nodo(new Estado(getcanibalEsq() - 2, getmissionarioEsq(), true, getcanibalDir() + 2, getmissionarioDir())));
            adicionar(sucessores, new Nodo(new Estado(getcanibalEsq() - 1, getmissionarioEsq() - 1, true, getcanibalDir() + 1, getmissionarioDir() + 1)));
            adicionar(sucessores, new Nodo(new Estado(getcanibalEsq(), getmissionarioEsq() - 1, true, getcanibalDir(), getmissionarioDir() + 1)));
            adicionar(sucessores, new Nodo(new Estado(getcanibalEsq() - 1, getmissionarioEsq(), true, getcanibalDir() + 1, getmissionarioDir())));
        }
        else{
            adicionar(sucessores, new Nodo(new Estado(getcanibalEsq(), getmissionarioEsq() + 2, false, getcanibalDir(), getmissionarioDir() - 2)));
            adicionar(sucessores, new Nodo(new Estado(getcanibalEsq() + 2, getmissionarioEsq(), false, getcanibalDir() - 2, getmissionarioDir())));
            adicionar(sucessores, new Nodo(new Estado(getcanibalEsq() + 1, getmissionarioEsq() + 1, false, getcanibalDir() - 1, getmissionarioDir() - 1)));
            adicionar(sucessores, new Nodo(new Estado(getcanibalEsq(), getmissionarioEsq() + 1, false, getcanibalDir(), getmissionarioDir() - 1)));
            adicionar(sucessores, new Nodo(new Estado(getcanibalEsq() + 1, getmissionarioEsq(), false, getcanibalDir() - 1, .getmissionarioDir())));
        }
        return sucessores;
    }
}