public class FilaArrayList<T> implements EstruturaLinear<T> {
    // A fila será implementada usando a sua própria lista
    private MinhaArrayList<T> lista;

    // Construtor
    public FilaArrayList() {
        lista = new MinhaArrayList<>();
    }

    // Insere sempre no final da fila
    @Override
    public void inserir(T elemento) {
        lista.add(elemento);
    }

    // Remove sempre do começo da fila
    @Override
    public T remover() {
        if (estaVazia()) {
            throw new RuntimeException("Fila vazia");
        }

        return lista.remove(0);
    }

    // Retorna true se a fila estiver vazia
    @Override
    public boolean estaVazia() {
        return lista.isEmpty();
    }
}