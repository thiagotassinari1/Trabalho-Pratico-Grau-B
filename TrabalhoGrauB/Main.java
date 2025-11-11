import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void criarProcesso() {

    }

    public static void executaProximo() {

    }

    public static void executaEspecifico() {

    }

    public static void salvarFila() {

    }

    public static void carregarFila() {
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Processo> filaProcessos = new ArrayList<>();

        int escolha = -1;
        while (escolha != 0) {
            System.out.println("\nMenu de opções:");
            System.out.println("1 - Criar processo;");
            System.out.println("2 - Executar próximo;");
            System.out.println("3 - Executar processo específico;");
            System.out.println("4 - Salvar a fila de processos;");
            System.out.println("5 - Carregar fila de processos do arquivo;");
            System.out.println("0 - Sair;");
            System.out.print("\nEscolha uma opção: ");
            escolha = sc.nextInt();

            switch (escolha) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    salvarFila();
                    break;
                case 5:
                    
                    break;
                case 0:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.print("Opção desconhecida.");
                    break;
            }
        }

        sc.close();
    }
}
