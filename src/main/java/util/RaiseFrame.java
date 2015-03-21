package util;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import net.miginfocom.swing.MigLayout;



public class RaiseFrame extends JFrame
{
        private JPanel container;
	private TexasPlayer player;
        private JLabel amount;
        private JTextField enter = new JTextField(40);
        private JButton commit;
        Font Italic = new Font("Serif", Font.ITALIC, 12);
        Font Bold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
	public RaiseFrame(TexasPlayer player) 
	{
	super("New book");
        this.player = player;
        try 
        {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
        {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        } 
        catch (Exception e) 
        {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        container = new JPanel(new MigLayout("wrap 2"));
        amount = new JLabel("Amount");
        amount.setFont(Bold);
        enter.setFont(Italic);
        container.add(amount, "span 1, align center");
        container.add(enter, "span 1, align center");
        commit = new JButton("Commit bet");
        commit.setFont(Bold);
        container.add(commit, "span 2, align center");
    
        commit.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
            	   addBet(enter.getText());
                   cleanUp();
         	   dispose();    
            }
        });
        add(container);
        pack();
        setLocationRelativeTo(null);    // centers on screen
        setVisible(true);
	}
        
        public void addBet(String val)
        {
            player.addBet(Integer.parseInt(val));
        }
        public void cleanUp()
        {
            player.cleanUp();
        }
}