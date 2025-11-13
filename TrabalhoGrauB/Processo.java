
public abstract class Processo {
    private int tipo;
    private int pid;

    /*
     * 1 = ComputingProcess
     * 2 = WritingProcess
     * 3 = ReadingProcess
     * 4 = PrintingProcess
     */

    // construtor pra quando ler uma fila salva no arquivo fila.txt
    // o pid é estatico, então se o ultimo item da fila for pid 14, ele define o pid da classe inteira como 14, ai os próximos que criar (novos, alem da fila) vai ser 15, 16, 17... pq o fato do pid ser static, quando mexe nele em algum lugar, mexe pra classe inteira
    // construtor sobrecarregado pra quando for criado o processo pela leitura do arquivo fila.txt
    public Processo(int pid, int tipo) {
        this.pid = pid;
        this.tipo = tipo;
    }

    public int getPid() {
        return this.pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getTipo() {
        return this.tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    // Métodos que todas as outras classes DEVEM ter:
    public abstract void execute();

    // sobrescrever em cada classe pra poder fazer o PrintingProcess 
    public abstract void imprimeInfo(int pid);
}