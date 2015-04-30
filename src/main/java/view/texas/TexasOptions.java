package view.texas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;

/**
 *This class is a frame for connecting to a running server and start a chat.
 * @author kim
 */
public class TexasOptions extends JFrame
{
    JPanel container;
    JLabel txt;
    JButton btn;
    JTextField Cash;
    JTextField Blind;
    TexasGui gui;
    private Font Italic = new Font("Serif", Font.ITALIC, 12);
    private Font Plain = new Font("Serif", Font.PLAIN, 12);
    private Font IBold = Italic.deriveFont(Italic.getStyle() | Font.BOLD);
    private Font PBold = Plain.deriveFont(Plain.getStyle() | Font.BOLD);
    public TexasOptions(TexasGui gui)
    {
        super("Texas Options");
        try 
        {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
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
        this.setLayout(new MigLayout());
        this.gui = gui;
        startup();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) 
            {
               
            }
        });
        pack();
        setLocationRelativeTo(null);    // centers on screen
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void startup()
    {
        container = new JPanel(new MigLayout("wrap 2"));
        txt = new JLabel("Please choose options for the texas game");
        txt.setFont(Plain);
        container.add(txt, "span 2, align center");
        txt = new JLabel("Starting cash: ");
        txt.setFont(Plain);
        container.add(txt, "span 1");
        Cash = new JTextField(30);
        Cash.setFont(Plain);
        container.add(Cash, "span 1");
        txt = new JLabel("Minimum blind");
        txt.setFont(Plain);
        container.add(txt, "span 1");
        Blind = new JTextField(30);
        Blind.setFont(Plain);
        container.add(Blind, "span 1");
        btn = new JButton("Start");
        btn.setFont(PBold);
        btn.addActionListener(new ActionListener() 
        {
	    public void actionPerformed(ActionEvent arg0) 
                {   
                    gui.tf = new TexasFrame(gui, Integer.parseInt(Cash.getText()));
                    dispose();
	        }
	});
        container.add(btn, "span 2");
        txt = new JLabel("Copyright \u00a9 Kim Hammar all rights reserved");
        txt.setFont(Plain);
        container.add(txt, "span 2, gaptop 20");
        add(container, BorderLayout.CENTER);
    }
}