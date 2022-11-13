import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* GUI
* Clase que crea la interfaz gráfica donde ocurre la comunicacion clientes-servidor
*/

public class GUI extends JFrame{
    
    public JPanel pane;
    public JButton btnSend;
    private Client client;

    public GUI(Client p_client)
    {
        this.client = p_client;

        setTitle("Ice Climbers");
        setVisible(true);
        setSize(800, 800);
        setBackground(Color.getColor("white"));
        setResizable(false);
        
        pane = new JPanel();
        this.getContentPane().add(pane);
        pane.setLayout(null);
        pane.setBackground(Color.getColor("gray"));

        btnSend = new JButton("Enviar");
        btnSend.setSize(150,50);
        btnSend.setLocation(65, 200);
        pane.add(btnSend);
        
        pane.repaint();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
