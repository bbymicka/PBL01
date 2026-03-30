import java.awt.image.BufferedImage;

public class PreenchedorImagem {

    private static final int PIXELS_POR_FRAME = 300;

    public MinhaArrayList<BufferedImage> floodFillAnimado(BufferedImage imagem,
                                                          int xInicial,
                                                          int yInicial,
                                                          int novaCor,
                                                          EstruturaLinear<Pixel> estrutura,
                                                          VisualizadorImagem visualizador) {
        MinhaArrayList<BufferedImage> frames = new MinhaArrayList<>();

        if (!dentroDosLimites(imagem, xInicial, yInicial)) {
            throw new IllegalArgumentException("Ponto inicial fora da imagem.");
        }

        int corOriginal = imagem.getRGB(xInicial, yInicial);

        if (corOriginal == novaCor) {
            frames.add(copiarImagem(imagem));
            return frames;
        }

        frames.add(copiarImagem(imagem));
        estrutura.inserir(new Pixel(xInicial, yInicial));
        int pixelsPintados = 0;

        while (!estrutura.estaVazia()) {
            Pixel atual = estrutura.remover();

            int x = atual.getX();
            int y = atual.getY();

            if (!dentroDosLimites(imagem, x, y)) {
                continue;
            }

            if (imagem.getRGB(x, y) != corOriginal) {
                continue;
            }

            imagem.setRGB(x, y, novaCor);
            pixelsPintados++;

            if (pixelsPintados % PIXELS_POR_FRAME == 0) {
                frames.add(copiarImagem(imagem));
                visualizador.atualizar();
            }

            estrutura.inserir(new Pixel(x + 1, y));
            estrutura.inserir(new Pixel(x - 1, y));
            estrutura.inserir(new Pixel(x, y + 1));
            estrutura.inserir(new Pixel(x, y - 1));
        }
        frames.add(copiarImagem(imagem));

        visualizador.atualizar();
        return frames;
    }

    private boolean dentroDosLimites(BufferedImage imagem, int x, int y) {
        return x >= 0 && x < imagem.getWidth() && y >= 0 && y < imagem.getHeight();
    }

    private BufferedImage copiarImagem(BufferedImage original) {
        BufferedImage copia = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
        copia.getGraphics().drawImage(original, 0, 0, null);
        return copia;
    }
}