/**
 * Class Estado
 * 
 * @author João Moreira Dias
 * @version 8 de Abril de 2013
 */
public class Estado{
    private int[] posicoes=new int[5]; //Posição das moedas, "o estado"

    /**
     * Construtor para objectos da class Estado
     * @param novasPosicoes Posição novas das moedas no estado
     */
    public Estado(int[] novasPosicoes)
    {
        posicoes=novasPosicoes;
    }

    /**
     * Construtor para objectos da class Estado
     * @param pos   Posicao de que se requesita o valor
     * @retun Valor da posicao passada por parametro
     */
    public int getValor(int pos)
    {
        return posicoes[pos];
    }

    /**
     * Construtor para objectos da class Estado
     * @return Retorna as posiçõens das moedas
     */
    public int[] getEstado()
    {
        return posicoes;
    }
    
    /**
     * Verifica se o estado é final
     */
    public boolean isFinal()
    {
        return estado.getValor(0)==d && estado.getValor(1)==d && estado.getValor(3)==n && estado.getValor(4)==n;
    }
    
    /**
     * Adiciona um nodo filho a este nodo
     * @param sucessores    Lista de sucessores, onde o novo filho vai ser adicionado
     * @param novo  Nodo que vai ter este nodo como pai, filho deste nodo
     */
    private void adicionar(List<Nodo> sucessores, Nodo novo)
    {
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
}