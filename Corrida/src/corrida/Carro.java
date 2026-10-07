package corrida;

import java.util.Random;

public class Carro implements Runnable {
    private String nome;
    private double distanciaTotalCorrida;
    private double distanciaPercorrida;
    private Podio podio;
    private boolean jaFezPitStop;

    public Carro(String nome, double distanciaTotalCorrida, Podio podio) {
        this.nome = nome;
        this.distanciaTotalCorrida = distanciaTotalCorrida;
        this.distanciaPercorrida = 0.0;
        this.podio = podio;
        this.jaFezPitStop = false;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (distanciaPercorrida < distanciaTotalCorrida) {

            double avanco = Math.min(10 + (40 * random.nextDouble()),
                    distanciaTotalCorrida - distanciaPercorrida);
            distanciaPercorrida += avanco;

            if (distanciaPercorrida > distanciaTotalCorrida) {
                distanciaPercorrida = distanciaTotalCorrida;
            }

            System.out.printf("%s andou %.1f metros e já percorreu %.1f de %.1f metros.%n",
                    nome, avanco, distanciaPercorrida, distanciaTotalCorrida);

            if (!jaFezPitStop && distanciaPercorrida >= (distanciaTotalCorrida * 0.5)) {
                jaFezPitStop = true;
                System.out.printf(">>> [PIT STOP] %s parou nos boxes para trocar pneus!%n", nome);
                try {

                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            try {
                int tempoPausa = 100 + random.nextInt(401);
                Thread.sleep(tempoPausa);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        podio.registrarChegada(nome);
    }
}
