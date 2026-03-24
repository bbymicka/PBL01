import java.awt.image.BufferedImage;

public class PreenchedorImagem {

    public void floodFillAnimado(BufferedImage imagem,
                                 int xInicial,
                                 int yInicial,
                                 int novaCor,
                                 EstruturaLinear<Pixel> estrutura,
                                 VisualizadorImagem visualizador) {
        if (!dentroDosLimites(imagem, xInicial, yInicial)) {
            throw new IllegalArgumentException("Ponto inicial fora da imagem.");
        }

        int corOriginal = imagem.getRGB(xInicial, yInicial);

        if (corOriginal == novaCor) {
            return;
        }

        estrutura.inserir(new Pixel(xInicial, yInicial));

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
            visualizador.atualizar();

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            estrutura.inserir(new Pixel(x + 1, y));
            estrutura.inserir(new Pixel(x - 1, y));
            estrutura.inserir(new Pixel(x, y + 1));
            estrutura.inserir(new Pixel(x, y - 1));
        }
    }

    private boolean dentroDosLimites(BufferedImage imagem, int x, int y) {
        return x >= 0 && x < imagem.getWidth() && y >= 0 && y < imagem.getHeight();
    }
}