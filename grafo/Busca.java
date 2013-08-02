import java.util.ArrayList;
/**
 * Class que executa a busca
 * 
 * @author João Moreira Dias
 * @version 8 de Abril de 2013
 */
public class Busca{
    /**
     * Construtor para a class Busca, vazio, opcional.
     */
    public Busca()
    {
    }

    /**
     * Método de execução de busca
     * @param   nInicial    Nodo inicial do problema
     * @return  O poximo nodo/resultado a ser impresso
     */
    public Nodo exec(Nodo nInicial)
    {
        if (nInicial.isFinal())
        {
            return nInicial;
        }
        ArrayList<Nodo> fila = new ArrayList<Nodo>();
        ArrayList<Nodo> passados = new ArrayList<Nodo>();
        fila.add(nInicial);
        while (true)
        {
            if (fila.isEmpty())
            {
                return null;
            }
            Nodo nodo = fila.remove(0);
            passados.add(nodo);
            ArrayList<Nodo> sucessores = nodo.getSuc();
            for (Nodo filho : sucessores)
            {
                if (!passados.contains(filho) || !fila.contains(filho))
                {
                    if (filho.isFinal()) 
                    {
                        return filho;
                    }
                    fila.add(0, filho);
                }
            }
        }
    }
}