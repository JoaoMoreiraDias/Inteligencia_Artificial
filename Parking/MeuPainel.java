import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MeuPainel extends JPanel
{
    private boolean cheio = false;

    public MeuPainel()
    {
        super();
    }

    public void setCor(boolean cor)
    {
        this.cheio=cor;
    }

    public void paintComponent(Graphics gd)
    {
        super.paintComponent(gd);
        int width = getWidth();
        int height = getHeight();
        if (width < 200)
            width = 200;
        if (height < 200)
            height = 200;
        Color verdeescuro = new Color(66,111,66);
        Color vermelhoescuro = new Color(128,0,0);
        gd.setColor( cheio ? Color.green : verdeescuro);
        gd.fillOval(width/2, (height/2)+60, 40, 40);
        gd.setColor( !cheio ? Color.red : vermelhoescuro);
        gd.fillOval(width/2, height/2, 40, 40);
    }
}