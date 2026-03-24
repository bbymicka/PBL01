import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GerenciadorImagem {

    // Carrega uma imagem a partir de um caminho no computador
    public static BufferedImage carregarImagem(String caminho) throws IOException {
        return ImageIO.read(new File(caminho));
    }

    // Salva uma imagem no formato desejado em um caminho no computador
    public static void salvarImagem(BufferedImage imagem, String formato, String caminho) throws IOException {
        ImageIO.write(imagem, formato, new File(caminho));
    }
}