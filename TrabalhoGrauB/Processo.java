
public abstract class Processo {
    private int tipo;
    private int pid;

    /*
     * 1 = ComputingProcess
     * 2 = WritingProcess
     * 3 = ReadingProcess
     * 4 = PrintingProcess
     */

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
    public abstract void imprimeInfo(int pid);
}