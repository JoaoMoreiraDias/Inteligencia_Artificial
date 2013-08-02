import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
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
    public Busca(){
    }

    /**
     * Método de execução de busca
     * 
     * @param   nInicial    Nodo inicial do problema
     */
    public Nodo exec(Nodo nInicial){
        if (nInicial.isFinal()){
            return nInicial;
        }
        Queue<Nodo> fila = new LinkedList<Nodo>();
        Set<Nodo> passados = new HashSet<Nodo>();
        fila.add(nInicial);
        while (true){
            if (fila.isEmpty()){
                return null;
            }
            Nodo nodo = fila.poll();
            passados.add(nodo);
            List<Nodo> sucesores = nodo.getSuc();
            for (Nodo filho : sucesores){
                if (!passados.contains(filho) || !fila.contains(filho)){
                    if (filho.isFinal()) {
                        return filho;
                    }
                    fila.add(filho);
                }
            }
        }
    }
}