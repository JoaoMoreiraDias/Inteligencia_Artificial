import java.util.ArrayList;

public class Estado
{
    public static int col = 4;
    public static int lin = 6;
    public static final int j1 = 1;
    public static final int j2 = 2;

    private int ultJog = 0;
    private int avd = 0;
    private double utilidade = 0;
    private Estado proxJog = null;
    private Tabuleiro tabuleiro;

    public Estado(Estado est, int jog)
    {
        tabuleiro=new Tabuleiro(lin, col, new int[lin][col]);
        for (int l=0;l< lin;l++) {
            for (int c=0;c< col;c++) {
                tabuleiro.setValor(l, c, est.getValor(l, c));
            }
        }
        if(jog==1)
        {
            avd =2;
        }
        else
        {
            avd =1;
        }
        ultJog =jog;
    }

    public Estado(String novo, int jogador)
    {
        String[] posicoesTabuleiro=novo.split(" ");
        tabuleiro=new Tabuleiro(lin, col, new int[lin][col]);
        int total=0;
        for (int l=0;l< lin;l++)
        {
            for (int c=0;c< col;c++)
            {
                tabuleiro.setValor(l,c,Integer.parseInt(posicoesTabuleiro[total]));
                total++;
            }
        }
        if(jogador==1)
        {
            avd =2;
        }
        else
        {
            avd =1;
        }
        ultJog =jogador;
    }

    public void setProxJog(Estado j)
    {
        proxJog = j;
    }

    public void setJogador(int j) {
        ultJog = j;
    }

    public void setTabuleiro(Tabuleiro n)
    {
        tabuleiro=n;
    }

    public Estado getProxJog()
    {
        return proxJog;
    }

    public int getJogador()
    {
        return ultJog;
    }

    public int getValor(int linha, int coluna)
    {
        return tabuleiro.getValor(linha, coluna);
    }

    public void joga(int linhaInicial, int colunaInicial, int linha, int coluna, int jogador)
    {
        if(isDentro(linha, coluna))
        {
            tabuleiro.fazJog(colunaInicial, linhaInicial, coluna, linha, jogador);
        }
    }

    public boolean livre(int l, int c)
    {
        return tabuleiro.getValor(l,c) == 0;
    }

    private boolean isDentro(int novaLinha, int novaColuna)
    {
        return novaLinha>=0 && novaLinha< lin && novaColuna>=0 && novaColuna< col;
    }

    public ArrayList<Estado> getSuc() {
        ArrayList<Estado> suc = new ArrayList<Estado>();

        int seraJogadaDe=0;

        if (this.ultJog == j1) {
            seraJogadaDe = j2;
        } else {
            seraJogadaDe = j1;
        }

        if (seraJogadaDe==1)
        {
            Tabuleiro tabuleiroTemp;
            Estado temp;
            for(int col=0; col< Estado.col; ++col)
            {
                for(int lin=0; lin< Estado.lin; ++lin)
                {
                    if (tabuleiro.getValor(lin,col)==1)
                    {
                        int c=col;
                        int l=lin+1;
                        if (isDentro(l, c) && tabuleiro.getValor(l,c)==0)
                        {
                            tabuleiroTemp=tabuleiro.copia();
                            tabuleiroTemp.fazJog(col, lin, c, l, seraJogadaDe);
                            temp=new Estado(this, seraJogadaDe);
                            temp.setTabuleiro(tabuleiroTemp);
                            suc.add(temp);
                        }
                        c=col-1;
                        if (isDentro(l, c) && tabuleiro.getValor(l,c)!=1)
                        {
                            tabuleiroTemp=tabuleiro.copia();
                            tabuleiroTemp.fazJog(col, lin, c, l, seraJogadaDe);
                            temp=new Estado(this, seraJogadaDe);
                            temp.setTabuleiro(tabuleiroTemp);
                            suc.add(temp);
                        }
                        c=col+1;
                        if (isDentro(l, c) && tabuleiro.getValor(l,c)!=1)
                        {
                            tabuleiroTemp=tabuleiro.copia();
                            tabuleiroTemp.fazJog(col, lin, c, l, seraJogadaDe);
                            temp=new Estado(this, seraJogadaDe);
                            temp.setTabuleiro(tabuleiroTemp);
                            suc.add(temp);
                        }
                    }
                }
            }
        }
        else
        {
            Tabuleiro tabuleiroTemp;
            Estado temp;
            for (int col=0; col< Estado.col; ++col)
            {
                for (int lin=0; lin< Estado.lin; ++lin)
                {
                    if (tabuleiro.getValor(lin,col)==2)
                    {
                        int c=col;
                        int l=lin-1;
                        if (isDentro(l, c) && tabuleiro.getValor(l,c)==0)
                        {
                            tabuleiroTemp=tabuleiro.copia();
                            tabuleiroTemp.fazJog(col, lin, c, l, seraJogadaDe);
                            temp=new Estado(this, seraJogadaDe);
                            temp.setTabuleiro(tabuleiroTemp);
                            suc.add(temp);
                        }
                        c=col-1;
                        if (isDentro(l, c) && tabuleiro.getValor(l,c)!=2)
                        {
                            tabuleiroTemp=tabuleiro.copia();
                            tabuleiroTemp.fazJog(col, lin, c, l, seraJogadaDe);
                            temp=new Estado(this, seraJogadaDe);
                            temp.setTabuleiro(tabuleiroTemp);
                            suc.add(temp);
                        }
                        c=col+1;
                        if (isDentro(l, c) && tabuleiro.getValor(l,c)!=2)
                        {
                            tabuleiroTemp=tabuleiro.copia();
                            tabuleiroTemp.fazJog(col, lin, c, l, seraJogadaDe);
                            temp=new Estado(this, seraJogadaDe);
                            temp.setTabuleiro(tabuleiroTemp);
                            suc.add(temp);
                        }
                    }
                }
            }
        }
        return suc;
    }

    public void setUtilidade(double n)
    {
        utilidade = n;
    }

    public double calcUtil()
    {
        if(ganha(ultJog))
        {
            utilidade+=999999;
        }
        if(ganha(avd))
        {
            utilidade-=999999;
        }
        int[] pecas=tabuleiro.contPecas();
        if(pecas[ultJog]<pecas[avd])
        {
            utilidade+=20*pecas[ultJog];
        }
        else
        if(pecas[ultJog]>pecas[avd])
        {
            utilidade+=10*pecas[ultJog];
        }
        else if(pecas[ultJog]==pecas[avd])
        {
            utilidade+=10*pecas[ultJog];
        }
        return utilidade;
    }

    public boolean isFinal()
    {
        return ganha(j1) || ganha(j2);
    }

    private boolean ganha(int jogador) {
        if(jogador==1)
        {
            for(int col=0; col< Estado.col; ++col)
            {
                if(tabuleiro.getValor(lin -1, col)==1)
                {
                    return true;
                }
            }
        }
        else if(jogador==2)
        {
            for(int col=0; col< Estado.col; ++col)
            {
                if(tabuleiro.getValor(0, col)==0)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString()
    {
        StringBuffer r = new StringBuffer("\n");
        for (int i=0;i< lin;i++)
        {
            for (int j=0;j< col;j++)
            {
                r.append(tabuleiro.getValor(i,j));
                if (j+1< col)
                {
                    r.append("|");
                }
            }
            if (i+1< lin)
            {
                r.append("\n-------\n");
            }
        }
        return ""+r;
    }
}