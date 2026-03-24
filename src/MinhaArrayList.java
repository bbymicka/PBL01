public class MinhaArrayList<T> {
    // Array interno que vai armazenar os elementos
    private Object[] elementos;

    // Quantidade real de elementos armazenados
    private int tamanho;

    // Capacidade inicial padrão
    private static final int CAPACIDADE_INICIAL = 10;

    // Construtor da lista
    public MinhaArrayList() {
        elementos = new Object[CAPACIDADE_INICIAL];
        tamanho = 0;
    }

    // Adiciona um elemento no final da lista
    public void add(T elemento) {
        garantirCapacidade();
        elementos[tamanho] = elemento;
        tamanho++;
    }

    // Retorna o elemento do índice informado
    @SuppressWarnings("unchecked")
    public T get(int indice) {
        validarIndice(indice);
        return (T) elementos[indice];
    }

    // Remove e retorna o elemento do índice informado
    @SuppressWarnings("unchecked")
    public T remove(int indice) {
        validarIndice(indice);

        T removido = (T) elementos[indice];

        // Desloca todos os elementos à esquerda para "fechar o buraco"
        for (int i = indice; i < tamanho - 1; i++) {
            elementos[i] = elementos[i + 1];
        }

        // Limpa a última posição, que ficou duplicada após o deslocamento
        elementos[tamanho - 1] = null;
        tamanho--;

        return removido;
    }

    // Retorna a quantidade de elementos da lista
    public int size() {
        return tamanho;
    }

    // Retorna true se a lista estiver vazia
    public boolean isEmpty() {
        return tamanho == 0;
    }

    // Garante espaço suficiente no array interno
    private void garantirCapacidade() {
        if (tamanho == elementos.length) {
            Object[] novoArray = new Object[elementos.length * 2];

            for (int i = 0; i < elementos.length; i++) {
                novoArray[i] = elementos[i];
            }

            elementos = novoArray;
        }
    }

    // Valida se o índice existe na lista
    private void validarIndice(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice inválido: " + indice);
        }
    }
}