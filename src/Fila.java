public class Fila<T> implements EstruturaLinear<T> {
    Node<T> primeiro;
    Node<T> ultimo;
    int tamanho;

    // Construtor
    public Fila() {
        primeiro = null;
        ultimo = null;
        tamanho = 0;
    }

    // Insere sempre no final da fila
    @Override
    public void inserir(T elemento) {
        Node<T> novo = new Node<>(elemento);
        if (tamanho == 0) {
            primeiro = novo;
        }
        else {
            ultimo.proximo = novo;
        }
        ultimo = novo;
        tamanho++;
    }

    // Remove sempre do começo da fila
    @Override
    public T remover() {
        if (estaVazia()) {
            throw new RuntimeException("Fila vazia");
        }
        T removido = primeiro.elemento;

        if (tamanho == 1) {
            primeiro = null;
            ultimo = null;
        }
        else {
            primeiro = primeiro.proximo;
        }
        tamanho--;
        return removido;
    }

    // Retorna true se a fila estiver vazia
    @Override
    public boolean estaVazia() {
        return tamanho == 0;
    }

    private static class Node<T> {
        T elemento;
        Node<T> proximo;

        public Node(T elemento) {
            this.elemento = elemento;
        }
    }

}