package br.unifil.dc.sisop;

import java.io.File;
import java.util.Optional;


/**
 * Write a description of class ComandosInternos here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class ComandosInternos {

    public static int exibirRelogio() {
        System.out.println(java.time.LocalDate.now());
        System.out.println(java.time.LocalTime.now());
        return (1);
    }

    public static int escreverListaArquivos(Optional<String> nomeDir) {
        File file = new File(nomeDir.get());
        File afile[] = file.listFiles();
        for (int i = 0; i < afile.length; i++) {
            System.out.println(afile[i].getName());
        }
        return (1);
    }

    public static int criarNovoDiretorio(String nomeDir, String nomePasta) {
        File file = new File(nomeDir + "/" + nomePasta);
        if (!file.exists()) {
            file.mkdirs();
        }
        return (1);
    }

    public static int apagarDiretorio(String nomeDir, String nomePasta) {
        File file = new File(nomeDir + "/" + nomePasta);
        if ((file.exists()) && (file.isDirectory())) {
            file.delete();
        }
        return (1);
    }

    public static int mudarDiretorioTrabalho(String nomeDir) {
        throw new RuntimeException("Método ainda não implementado");
    }

    /**
     * Essa classe não deve ser instanciada.
     */
    private ComandosInternos() {
    }
}
