public class PilhaArrayList<T> implements EstruturaLinear<T> {
    // A pilha será implementada usando a sua própria lista
    private MinhaArrayList<T> lista;

    // Construtor
    public PilhaArrayList() {
        lista = new MinhaArrayList<>();
    }

    // Insere sempre no final da pilha
    @Override
    public void inserir(T elemento) {
        lista.add(elemento);
    }

    // Remove sempre do final da pilha
    @Override
    public T remover() {
        if (estaVazia()) {
            throw new RuntimeException("Pilha vazia");
        }

        return lista.remove(lista.size() - 1);
    }

    // Retorna true se a pilha estiver vazia
    @Override
    public boolean estaVazia() {
        return lista.isEmpty();
    }
}