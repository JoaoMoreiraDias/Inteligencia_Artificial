import java.util.Random;

public class MiniMax
{
    public static boolean parar = false;

    private int profMax = Integer.MAX_VALUE;
    private Random random = new Random();
    int visitados = 0;

    public MiniMax(int prof)
    {
        profMax = prof;
    }

    public double max(Estado estadoCorrente)
    {
        visitados = 0;
        return max(estadoCorrente, 1);
    }

    public double max(Estado estadoCorrente, int p)
    {
        if (p > profMax || estadoCorrente.isFinal() || parar)
        {
            return estadoCorrente.calcUtil();
        }

        double maximo = -99999;

        for (Estado estado : estadoCorrente.getSuc())
        {
            visitados++;
            Estado sucessor = estado;
            double minSucessor = min(sucessor, p + 1);

            if (minSucessor > maximo || (minSucessor == maximo && random.nextBoolean()))
            {
                maximo = minSucessor;
                estadoCorrente.setUtilidade(maximo);
                estadoCorrente.setProxJog(sucessor);
            }
        }
        return maximo;
    }

    public double min(Estado estAtu, int p)
    {
        if (p > profMax || estAtu.isFinal() || parar)
        {
            return estAtu.calcUtil();
        }

        double minimo = 99999;
        for (Estado estado : estAtu.getSuc()) {
            visitados++;
            Estado sec = estado;
            double maxSuc = max(sec, p + 1);

            if (maxSuc < minimo || (maxSuc == minimo && random.nextBoolean())) {
                minimo = maxSuc;
                estAtu.setUtilidade(minimo);
                estAtu.setProxJog(sec);
            }
        }
        return minimo;
    }
}