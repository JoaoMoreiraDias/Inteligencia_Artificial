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
    private static final int n=0; //Representação de uma Nickel na array de inteiros
    private static final int d=1; //Representação de uma Dime na array de inteiros
    private static final int x=-1; //Representação de um espaço em vazio na array de inteiros
    private Estado estado; //Um Nodo tem um estado
    private Nodo nodoPai; //Um nodo tem um Nodo pai

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
    public boolean isFinal()
    {
        return estado.getValor(0)==d && estado.getValor(1)==d && estado.getValor(3)==n && estado.getValor(4)==n;
    }
    */

    /**
     * Cria a copia do nodo que vai ser alterado no futuro
     */
    public int[] copia(int[] original)
    {
        int[] novo=new int[5];
        for(int i=0; i<5; i++)
        {
            novo[i]=original[i];
        }
        return novo;
    }

    /**
     * Estado encontra o proximo sucesor valido
     * @return Lista de sucessores
     */
    /*
    public ArrayList<Nodo> getSuc()
    {
        ArrayList<Nodo> sucessores = new ArrayList<Nodo>();

        for(int i=0; i<4; i++)
        {
            int[] novo=copia(estado.getEstado());
            if(novo[i]==n && novo[i+1]==x)
            {
                novo[i+1]=n;
                novo[i]=x;
                adicionar(sucessores, new Nodo(new Estado(novo))); 
            }

            if(novo[i]==x && novo[i+1]==d)
            {
                novo[i+1]=x;
                novo[i]=d;
                adicionar(sucessores, new Nodo(new Estado(novo)));
            }
        }

        for(int i=0; i<3; i++)
        {
            int[] novo=copia(estado.getEstado());
            if(novo[i]==n && novo[i+1]==d && novo[i+2]==x)
            {
                novo[i]=x;
                novo[i+2]=n;
                adicionar(sucessores, new Nodo(new Estado(novo)));
            }

            if(novo[i]==x && novo[i+1]==n && novo[i+2]==d)
            {
                novo[i]=d;
                novo[i+2]=x;
                adicionar(sucessores, new Nodo(new Estado(novo)));
            }
        }
        return sucessores;
    }
    */

    /**
     * Adiciona um nodo filho a este nodo
     * @param sucessores    Lista de sucessores, onde o novo filho vai ser adicionado
     * @param novo  Nodo que vai ter este nodo como pai, filho deste nodo
     */
    /*
    private void adicionar(List<Nodo> sucessores, Nodo novo)
    {
        novo.setEstadoPai(this);
        sucessores.add(novo);
    }
    */

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
        int[] pos=estado.getEstado();
        String resultado=""; 
        for (int i=0; i<5; i++)
        {
            if(pos[i]==n)
            {
                resultado+="N";
            }
            else if(pos[i]==d)
            {
                resultado+="D";
            }
            else if(pos[i]==x)
            {
                resultado+="X";
            }
        }
        return resultado;
    }
}