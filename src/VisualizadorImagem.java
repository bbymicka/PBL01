import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class VisualizadorImagem extends JFrame {
    private final BufferedImage imagem;
    private final JPanel painelImagem;

    public VisualizadorImagem(BufferedImage imagem) {
        this.imagem = imagem;
        this.painelImagem = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(VisualizadorImagem.this.imagem, 0, 0, null);
            }
        };

        setTitle("Visualização do Flood Fill");
        painelImagem.setPreferredSize(new Dimension(imagem.getWidth(), imagem.getHeight()));
        setContentPane(painelImagem);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void atualizar() {
        painelImagem.repaint();
    }
}