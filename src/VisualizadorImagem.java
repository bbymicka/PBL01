import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class VisualizadorImagem extends JFrame {
    private BufferedImage imagem;

    public VisualizadorImagem(BufferedImage imagem) {
        this.imagem = imagem;

        setTitle("Visualização do Flood Fill");
        setSize(imagem.getWidth() + 20, imagem.getHeight() + 40);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void atualizar() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imagem, 10, 30, null);
    }
}