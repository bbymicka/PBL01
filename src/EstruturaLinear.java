public interface EstruturaLinear<T> {
    // Insere um elemento na estrutura
    void inserir(T elemento);

    // Remove e retorna um elemento da estrutura
    T remover();

    // Verifica se a estrutura está vazia
    boolean estaVazia();
}