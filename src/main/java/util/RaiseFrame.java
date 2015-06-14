package util;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;



public class RaiseFrame extends JFrame
{
	private JPanel container;
	private TexasPlayer player;
	private JLabel amount;
	private JTextField enter = new JTextField(40);
	private JButton commit;
	private Font Title = new Font("Serif", Font.PLAIN, 20);
	private Font Italic = new Font("Serif", Font.ITALIC, 12);
	private Font Plain = new Font("Serif", Font.PLAIN, 12);
	private Font IBold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
	private Font PBold = Plain.deriveFont(Plain.getStyle() | Font.BOLD);
	private Font TBold = Title.deriveFont(Title.getStyle() | Font.BOLD);
	public RaiseFrame(TexasPlayer player) 
	{
	super("Raise bet");
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
        JLabel txt = new JLabel("Maximum amount to raise with (All in): " + Integer.toString(player.getCash() - 
                (player.bets.getCallAmount() - player.getBet())));
        txt.setFont(PBold);
        container.add(txt, "span 2, align center");
        amount = new JLabel("Amount");
        amount.setFont(PBold);
        enter.setFont(Italic);
        container.add(amount, "span 1, align center");
        container.add(enter, "span 1, align center");
        commit = new JButton("Commit bet");
        commit.setFont(PBold);
        container.add(commit, "span 2, align center");
    
        commit.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
				if(raise(enter.getText()))
				dispose();
                                else
                                {
                                    enter.setText("Invalid raise amount");
                                }
            }
        });
        txt = new JLabel("Copyright \u00a9 Kim Hammar all rights reserved");
        txt.setFont(Plain);
        container.add(txt, "span 1, gaptop 20");
        add(container);
        pack();
        setLocationRelativeTo(null);    // centers on screen
        setVisible(true);
	}
        
        public boolean raise(String val)
        {
			return player.raise(val);
        }
        
}
