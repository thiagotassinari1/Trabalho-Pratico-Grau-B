import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WritingProcess extends Processo {
    private String expressao;


    public WritingProcess(String expressao) {
        super(2);
        this.expressao = expressao;
    }

    @Override
    public void execute() {
        try {
            // append ativado(true) para não sobrescrever o que já tiver no arquivo;
            FileWriter writer = new FileWriter("computation.txt", true);
            BufferedWriter buffer = new BufferedWriter(writer);

            String linha = String.format("%s", expressao);

            buffer.write(linha);
            buffer.write("\n");

            System.out.println("Expressões salvas com sucesso!");
            buffer.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar as expressões: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void imprimeInfo(int pid) {
        String infos = String.format("Pid: %d - Tipo: Writing Process | Expressão: %s", pid, expressao);
        System.out.println(infos);
    }
}