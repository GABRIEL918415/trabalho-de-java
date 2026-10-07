package corrida;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Podio {

    private final List<String> classificacao = new ArrayList<>();

    public synchronized void registrarChegada(String nomeCarro) {

        if (nomeCarro == null || nomeCarro.trim().isEmpty()) {
            System.out.println("Erro: nome do carro inválido.");
            return;
        }

        classificacao.add(nomeCarro);
        System.out.printf("%n[CHEGADA] O %s cruzou a linha de chegada!%n", nomeCarro);

        int posicao = classificacao.size();

        System.out.printf(
                "---> %s ficou em %dº LUGAR!%n",
                nomeCarro,
                posicao
        );
    }

    public synchronized void exibirPodio() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("       RESULTADO FINAL DA CORRIDA     ");
        System.out.println("======================================");

        if (classificacao.isEmpty()) {
            System.out.println("Nenhum carro terminou a corrida.");
        } else {
            for (int i = 0; i < classificacao.size(); i++) {
                System.out.printf(
                        "%dº Lugar: %s%n",
                        i + 1,
                        classificacao.get(i)
                );
            }
        }

        System.out.println("======================================");
    }

    public synchronized List<String> getClassificacao() {
        return Collections.unmodifiableList(
                new ArrayList<>(classificacao)
        );
    }
}
