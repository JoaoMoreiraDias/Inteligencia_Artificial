import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class MeuPainel extends JPanel {
    private boolean cor = true;

    public MeuPainel() {
        super();
    }
    
    public void setCor( boolean cor) {
        this.cor = cor;
    }
    
    public void paintComponent( Graphics gd) {
        super.paintComponent(gd);
        gd.setColor( cor ? Color.green : Color.red);
        int width = getWidth();
        int height = getHeight();
        if (width < 200)
            width = 200;
        if (height < 200)
            height = 200;
        gd.fillOval(width/2, height/2, 40, 40);
    }
}
