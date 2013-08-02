import java.util.Random;

public class Puzzle
{
    private int[] pecas = new int[9];
    private boolean curto = true;
    private int perfundidade;
    private int idPai =-1;

    public Puzzle()
    {
        init();
    }

    public Puzzle(int[] squares, boolean eCurto)
    {
        for (int i=0; i<9; i++)
        {
            pecas[i] = squares[i];
            setHeuristica(eCurto);
        }
    }

    public Puzzle(int[] quadrados, boolean curto, int perfundidade)
    {
        this.perfundidade = perfundidade;
        for (int i=0; i<9; i++)
        {
            pecas[i] = quadrados[i];
            setHeuristica(curto);
        }
    }

    public Puzzle(int[] squares, boolean curto, int perfundidade, int idPai)
    {
        this.perfundidade = perfundidade;
        this.idPai = idPai;
        for (int i=0; i<9; i++)
        {
            pecas[i] = squares[i];
            setHeuristica(curto);
        }
    }

    public Puzzle(Puzzle puzzle)
    {
        pecas = puzzle.paraArray();
        curto = puzzle.eCurto();
        perfundidade = puzzle.getPerfundidade();
        idPai = puzzle.getIdPai();
    }

    public void init()
    {
        pecas[0] = 2;
        pecas[1] = 3;
        pecas[2] = 7;
        pecas[3] = 1;
        pecas[4] = 4;
        pecas[5] = 8;
        pecas[6] = 0;
        pecas[7] = 5;
        pecas[8] = 6;
       /* // criar um puzzle já resolvido
        for (int i=0; i<9; i++)
        {
            pecas[i] = i;
        }
        // baralhar as pecas a sorte 25 vezes
        // nota, ja resolvi um puzzle em 0 movimentos
        // ele a sorte voltou a meter tudo na posição inicial
        // e o jogo acabou logo em 0 movimentos!!
        for (int j=0; j<mexer; j++)
        {
            Random rand = new Random();
            int choice = rand.nextInt(4)+1;
            switch (choice)
            {
                case 1:
                pecas = cima(pecas);
                break;

                case 2:
                pecas = baixo(pecas);
                break;

                case 3:
                pecas = esquerda(pecas);
                break;

                case 4:
                pecas = direita(pecas);
                break;
            }
        }*/
    }

    private int[] cima(int[] arrayPuzzle)
    {
        int guarda;
        int[] temp = new int[arrayPuzzle.length];
        int i;
        for (i=0; i<arrayPuzzle.length; i++)
            temp[i] = arrayPuzzle[i];
        int quadrado= 0;
        for (i=0; i<temp.length; i++)
        {
            if (temp[i] == 0)
                quadrado = i;
        }
        if (quadrado < 3)
            return temp;
        guarda = temp[quadrado];
        temp[quadrado] = temp[quadrado-3];
        temp[quadrado-3] = guarda;
        return temp;
    }

    private int[] baixo(int[] puzzleArray)
    {
        int guarda;
        int[] temp = new int[puzzleArray.length];
        for (int i=0; i<puzzleArray.length; i++)
            temp[i] = puzzleArray[i];
        int quadrado= 0;
        for (int i=0; i<temp.length; i++)
        {
            if (temp[i] ==0 )
                quadrado = i;
        }
        // validação
        if (quadrado > 5)
            return temp;
        guarda = temp[quadrado];
        temp[quadrado] = temp[quadrado+3];
        temp[quadrado+3] = guarda;
        return temp;
    }

    private int[] esquerda(int[] arrayPuzzle)
    {
        int guarda;
        int[] temp = new int[arrayPuzzle.length];
        for (int i=0; i<arrayPuzzle.length; i++)
        {
            temp[i] = arrayPuzzle[i];
        }
        int quadrado= 0;
        for (int i=0; i<temp.length; i++)
        {
            if (temp[i] ==0 )
                quadrado = i;
        }
        // valida
        if (quadrado%3 == 0)
        {
            return temp;
        }
        guarda = temp[quadrado];
        temp[quadrado] = temp[quadrado-1];
        temp[quadrado-1] = guarda;
        return temp;
    }

    private int[] direita(int[] arrayPuzzle)
    {
        int guarda;
        int[] temp = new int[arrayPuzzle.length];
        for (int i=0; i<arrayPuzzle.length; i++)
        {
            temp[i] = arrayPuzzle[i];
        }
        int quadrado= 0;
        for (int i=0; i<temp.length; i++)
        {
            if (temp[i] ==0 )
                quadrado = i;
        }
        // valida
        if (quadrado%3 == 2)
            return temp;
        guarda = temp[quadrado];
        temp[quadrado] = temp[quadrado+1];
        temp[quadrado+1] = guarda;
        return temp;
    }

    public String toString()
    {
        String s = "---------------\n";
        for (int i=1; i<=9; i++) {
            if ((i%3) == 1)
                s += " | ";
            if (pecas[i-1] == 0)
                s += "  | ";
            else
                s += pecas[i-1]+" | ";
            if ((i%3) == 0)
                s += "\n---------------\n";
        }
        return s;
    }

    public boolean isIgual(Puzzle p)
    {
        return (p.toString().equals(toString()));
    }

    public boolean eCurto()
    {
        return curto;
    }

    public int getPerfundidade()
    {
        return perfundidade;
    }

    public int getIdPai()
    {
        return idPai;
    }

    public void setHeuristica(boolean eCurto)
    {
        curto = eCurto;
    }
    // Get Heuristic based on type
    public int getHeuCurt()
    {
        if (curto)
        {
            return getCurtoHeuristica();
        }
        else
        {
            return getHeuNcur();
        }
    }

    private int getHeuNcur()
    {
        // contador de peças na pos errada
        int erradas = 0;
        for (int i=0; i<9; i++)
        {
            if (pecas[i] != i)
            {
                erradas++;
            }
        }
        return erradas;
    }

    // Set curto Heuristic
    private int getCurtoHeuristica()
    {
        int h = 0;
        int xs;
        int xg;
        int ys;
        int yg;
        for (int i=0; i<9; i++)
        {
            // meter valores em tabuleiro e medir distancias
            xg = i % 3;
            yg = i / 3;
            xs = pecas[i] % 3;
            ys = pecas[i] / 3;
            h += Math.abs(xs - xg) + Math.abs(ys - yg);
        }
        return h;
    }

    // Add to the cost
    public void contaJogada()
    {
        perfundidade++;
    }

    public int[] paraArray()
    {
        int[] temp = new int[9];
        for (int i=0; i<9; i++)
        {
            temp[i] = pecas[i];
        }
        return temp;
    }

    public int[] cima()
    {
        return cima(pecas);
    }

    public int[] baixo()
    {
        return baixo(pecas);
    }

    public int[] esquerda()
    {
        return esquerda(pecas);
    }

    public int[] direita()
    {
        return direita(pecas);
    }
}