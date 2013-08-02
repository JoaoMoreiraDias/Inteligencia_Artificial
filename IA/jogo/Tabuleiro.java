public class Tabuleiro
{
    private int[][] tabuleiro;
    private int nLin;
    private int nCol;

    public Tabuleiro (int linhas, int colunas, int[][] novo)
    {
        nLin =linhas;
        nCol =colunas;
        tabuleiro = novo;
    }

    public void fazJog(int cI, int lI, int cF, int lF, int nj)
    {
        tabuleiro[lI][cI]=0;
        tabuleiro[lF][cF]=nj;
    }

    public Tabuleiro copia()
    {
        int[][] resultado=new int[nLin][nCol];

        for(int a=0; a< nLin; a++)
        {
            for(int b=0; b< nCol; b++)
            {
                resultado[a][b]=tabuleiro[a][b];
            }
        }
        return new Tabuleiro(nLin, nCol,resultado);
    }

    public int[] contPecas()
    {
        int totalJogador1=0;
        int totalJogador2=0;
        int[] resultado=new int[3];
        for(int l=0; l< nLin; ++l)
        {
            for(int c=0; c< nCol; ++c)
            {
                if(tabuleiro[l][c]==1)
                {
                    totalJogador1++;
                }
                if(tabuleiro[l][c]==2)
                {
                    totalJogador2++;
                }
            }
        }
        resultado[1]=totalJogador1;
        resultado[2]=totalJogador2;
        return resultado;
    }

    public int getValor(int linha, int coluna)
    {
        return tabuleiro[linha][coluna];
    }

    public void setValor(int linha, int coluna, int cor)
    {
        tabuleiro[linha][coluna]=cor;
    }
}