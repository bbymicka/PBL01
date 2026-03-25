import java.awt.Color;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        try {
            String caminhoEntrada = "src/FormaGeometricaDemas.png";
            String caminhoSaida = "src/saida.png";

            BufferedImage imagem = GerenciadorImagem.carregarImagem(caminhoEntrada);

            ConsoleUI ui = new ConsoleUI();
            int forma = ui.escolherForma();
            int estruturaEscolhida = ui.escolherEstrutura();

            int xInicial = 0;
            int yInicial = 0;

            switch (forma) {
                case 1:
                    xInicial = 100;
                    yInicial = 100;
                    break;
                case 2:
                    xInicial = 390;
                    yInicial = 130;
                    break;
                case 3:
                    xInicial = 120;
                    yInicial = 385;
                    break;
                case 4:
                    xInicial = 395;
                    yInicial = 385;
                    break;
                default:
                    System.out.println("Forma inválida.");
                    return;
            }

            EstruturaLinear<Pixel> estrutura;

            if (estruturaEscolhida == 1) {
                estrutura = new Fila<>();
                System.out.println("Modo escolhido: FIFO");
            } else if (estruturaEscolhida == 2) {
                estrutura = new Pilha<>();
                System.out.println("Modo escolhido: LIFO");
            } else {
                System.out.println("Estrutura inválida.");
                return;
            }

            int novaCor = Color.RED.getRGB();

            VisualizadorImagem visualizador = new VisualizadorImagem(imagem);
            PreenchedorImagem preenchedor = new PreenchedorImagem();

            preenchedor.floodFillAnimado(imagem, xInicial, yInicial, novaCor, estrutura, visualizador);

            GerenciadorImagem.salvarImagem(imagem, "png", caminhoSaida);

            System.out.println("Imagem salva com sucesso em: " + caminhoSaida);

        } catch (Exception e) {
            System.out.println("Erro ao executar o programa: " + e.getMessage());
            e.printStackTrace();
        }
    }
}