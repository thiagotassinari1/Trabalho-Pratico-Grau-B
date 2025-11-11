
public abstract class Processo {
    private int tipo;
    private static int pid = 0;

    /*
     * 1 = ComputingProcess
     * 2 = WritingProcess
     * 3 = ReadingProcess
     * 4 = PrintingProcess
     */

    public Processo(int tipo) {
        this.tipo = tipo;
    }

    public Processo(int tipo, int pid) {
        this.tipo = 0;
        this.pid++;
    }

    public int getPid() {
        return this.pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    // Métodos que todas as outras classes DEVEM ter:
    public abstract void execute();

    // sobrescrever em cada classe pra poder fazer o PrintingProcess 
    public abstract void imprimeInfo(int pid);
}