import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Teste extends JFrame {

	private boolean sw;
	MeuPainel mp;
	
	public Teste() {
		sw = false;
		JPanel pp = new JPanel();
		pp.setPreferredSize( new Dimension(300,200));
		pp.setLayout( new BorderLayout());
		JButton jb = new JButton(" botao ");
		jb.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent ev) {
				sw = !sw;
				mp.setCor( sw);
				repaint();
			}
		});
		pp.add( jb, BorderLayout.EAST);
		mp = new MeuPainel();
		mp.setSize( new Dimension(200,200));
		mp.add(new JLabel("Exemplo"));
		mp.setCor( sw);
		pp.add(mp,BorderLayout.CENTER);

		add( pp);
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Teste t = new Teste();
		t.pack();
		t.setVisible(true);
		JOptionPane.showMessageDialog(null, "exemplo");
	}

}
