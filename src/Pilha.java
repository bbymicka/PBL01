public class Pilha<T> implements EstruturaLinear<T> {
    Node<T> topo;
    int tamanho;

    // Construtor
    public Pilha() {
        topo = null;
        tamanho = 0;
    }

    // Insere sempre no final da pilha
    @Override
    public void inserir(T elemento) {
        Node<T> novoNode = new Node<>(elemento);
        novoNode.anterior = topo;
        topo = novoNode;
        tamanho++;
    }

    // Remove sempre do final da pilha
    @Override
    public T remover() {
        if (estaVazia()) {
            throw new RuntimeException("Pilha vazia");
        }

        T removido = topo.elemento;
        topo = topo.anterior;
        tamanho--;
        return removido;
    }

    // Retorna true se a pilha estiver vazia
    @Override
    public boolean estaVazia() {
        return topo == null;
    }

    private static class Node<T> {
        T elemento;
        Node<T> anterior;

        public Node(T elemento) {
            this.elemento = elemento;
        }
    }
}