public class Pixel {
    // Coordenada X do pixel na imagem
    private int x;

    // Coordenada Y do pixel na imagem
    private int y;

    // Construtor que recebe as duas coordenadas do pixel
    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Retorna o valor de X
    public int getX() {
        return x;
    }

    // Retorna o valor de Y
    public int getY() {
        return y;
    }

    // Retorna uma representação em texto do objeto
    @Override
    public String toString() {
        return "Pixel{x=" + x + ", y=" + y + "}";
    }
}