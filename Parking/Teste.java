import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Teste extends JFrame
{
    private int contCarros = 0;
    MeuPainel mp;

    public Teste()
    {
        JPanel pp = new JPanel();
        pp.setPreferredSize (new Dimension(300,200));
        pp.setLayout(new BorderLayout());
        JButton jbA = new JButton("Adicionar Carro");
        JButton jbB = new JButton("Remover Carro");
        jbA.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ev){
                    if (contCarros == 4){
                        contCarros = contCarros + 1;
                        mp.setCor(false);
                    }else
                    if (contCarros<4){
                        contCarros = contCarros + 1;
                    }else
                    if (contCarros == 5){
                       JOptionPane.showMessageDialog(null, "Cheio - Nao posso por mais");
                    }
                    repaint();
                }
            });
        jbB.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ev){
                    if (contCarros == 5){
                        contCarros = contCarros - 1;
                        mp.setCor(true);
                    }else
                    if (contCarros>0){
                        contCarros = contCarros - 1;
                    }else
                    if (contCarros == 0){
                       JOptionPane.showMessageDialog(null, "Vazio - Nao posso tirar mais");
                    }
                    repaint();
                }
            });
        pp.add(jbA, BorderLayout.WEST);
        pp.add(jbB, BorderLayout.EAST);
        mp = new MeuPainel();
        mp.setSize(new Dimension(200,200));
        mp.setCor(true);
        pp.add(mp,BorderLayout.CENTER);
        add(pp);
    }

    public static void main(String[] args)
    {
        Teste t = new Teste();
        t.pack();
        t.setVisible(true);
    }
}