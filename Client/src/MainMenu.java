import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import javax.swing.*;


public class MainMenu extends JFrame implements GameUtils {

    private JPanel contentPane;
    private class content extends JPanel{
        private BufferedImage iceClimber;
        public content(){
            try
            {
                iceClimber = ImageIO.read(new File(String.valueOf(Paths.get("Client/src/resources/logo.png").toAbsolutePath())));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error reading the image");
            }
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(iceClimber, 300, 50, this);
        }
    }
    public MainMenu(Client p_client)
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle(TITLE);
        setVisible(true);


        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.BLACK);

        paintButtons(p_client);
        contentPane.repaint();
    }

    public void openGame(Client p_client, int players)
    {
        this.setVisible(false);
        this.dispose();
        EventQueue.invokeLater(() -> {
            GameFrame gameFrame = new GameFrame(p_client,players);
            gameFrame.setVisible(true);
        });

    }
    public void paintButtons(Client p_client)
    {
        JButton play1Button = new JButton("1 PLAYER");
		play1Button.addActionListener(new ActionListener() 
        {
			public void actionPerformed(ActionEvent e) 
            {
				openGame(p_client, 1);
	        }

        });

        play1Button.setBackground(Color.BLACK);
		play1Button.setForeground(Color.WHITE);
		play1Button.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
		play1Button.setBounds((FRAME_WIDTH/2)-(BUTTON_WIDTH/2), 400, BUTTON_WIDTH, BUTTON_HEIGHT);
        contentPane.add(play1Button);
        
        JButton play2Button = new JButton("2 PLAYERS");
		play2Button.addActionListener(new ActionListener() 
        {
			public void actionPerformed(ActionEvent e) 
            {
				openGame(p_client, 2);
	        }

        });

        play2Button.setBackground(Color.BLACK);
		play2Button.setForeground(Color.WHITE);
		play2Button.setFont(new Font(FONT, Font.PLAIN, FONT_SIZE));
		play2Button.setBounds((FRAME_WIDTH/2)-(BUTTON_WIDTH/2), 475, BUTTON_WIDTH, BUTTON_HEIGHT);
		contentPane.add(play2Button);
    }
}
