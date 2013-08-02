import java.util.Vector;

public class Resolve
{
    private Vector usados;
    private Vector lista;
    private Puzzle p;

    public Resolve(int depth)
    {
        usados = new Vector();
        lista = new Vector();
        p = new Puzzle(depth);
        lista.add(p);
    }

    public Resolve(Puzzle puzzle)
    {
        usados = new Vector();
        lista = new Vector();
        p = new Puzzle(puzzle);
        lista.add(p);
    }

    public void resolver()
    {
        while (expandiNos(p)) {
            p= encontrarMelhNodo();
        }
    }

    public int getCusto()
    {
        return (usados.size() + lista.size());
    }

    private Puzzle encontrarMelhNodo()
    {
        Puzzle best;
        Puzzle test;
        if (lista.size()==0)
            best = (Puzzle) usados.elementAt(0);
        else
            best = (Puzzle) lista.elementAt(0);
        for (int i=1; i< lista.size();i++)
        {
            test = (Puzzle) lista.get(i);
            int bestH = best.getPerfundidade() + best.getHeuCurt();
            int testH = test.getPerfundidade() + test.getHeuCurt();
            // quanto mais pequeno melhor
            if (testH < bestH)
                best = test;
        }
        return best;
    }

    private boolean expandiNos(Puzzle p)
    {
        int[] test;
        Puzzle temp;
        lista.remove(p);
        int parentID = usados.size();
        usados.add(p);
        // se heuristica = 0, esta no obejectivo
        if (p.getHeuCurt() == 0)
            return false;
        test = p.cima();
        temp = new Puzzle(test, p.eCurto(), p.getPerfundidade(), parentID);
        adicinarLista(temp);
        test = p.baixo();
        temp = new Puzzle(test, p.eCurto(), p.getPerfundidade(), parentID);
        adicinarLista(temp);
        test = p.esquerda();
        temp = new Puzzle(test, p.eCurto(), p.getPerfundidade(), parentID);
        adicinarLista(temp);
        test = p.direita();
        temp = new Puzzle(test, p.eCurto(), p.getPerfundidade(), parentID);
        adicinarLista(temp);
        return true;
    }

    private void adicinarLista(Puzzle p)
    {
        if (!repetido(p))
        {
            p.contaJogada();
            lista.add(p);
        }
    }

    private boolean repetido(Puzzle no)
    {
        for (int i=0; i< usados.size(); i++)
        {
            if (no.isIgual((Puzzle) usados.get(i)))
                return true;
        }
        return false;
    }

    public void imprimir()
    {
        Vector list = new Vector();
        Puzzle no = getSolucaoPuzzle();
        // adicionar a lista de add to answer list
        list.add(no);
        // encontrar no pai
        int idPai = no.getIdPai();
        while (idPai != -1)
        {
            no = (Puzzle) usados.elementAt(idPai);
            list.add(no);
            idPai = no.getIdPai();
        }

        for (int i = 1; i <= list.size();  i++)
        {
            if (i ==1)
            {
                System.out.println("Inicial:");
                System.out.println(list.elementAt(list.size()-i));
            }
            else
            {
                System.out.println("Movimento nº " + ( i -1)+ ":");
                System.out.println(list.elementAt(list.size()-i));
            }
        }
        System.out.println("FIM!");
    }

    public Puzzle getSolucaoPuzzle()
    {
        return (Puzzle) usados.lastElement();
    }
} 