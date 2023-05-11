import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ComponenteCurricular {
    private boolean obrigatorio;
    private int semestre;
    private int cargaHoraria;
    private String nome;
    private int codComponente;
    private List<Turma> turmas = new ArrayList<>();

    public ComponenteCurricular(boolean obrigatorio, int semestre, int cargaHoraria, String nome, int codComponente,
            List<Turma> turmas) {
        this.obrigatorio = obrigatorio;
        this.semestre = semestre;
        this.cargaHoraria = cargaHoraria;
        this.nome = nome;
        this.codComponente = codComponente;
        this.turmas = turmas;
    }

    public ComponenteCurricular() {
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodComponente() {
        return codComponente;
    }

    public void setCodComponente(int codComponente) {
        this.codComponente = codComponente;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    @Override
    public String toString() {
        return "ComponenteCurricular [E obrigatorio? " + obrigatorio + " | semestre: " + semestre + " | carga horaria: "
                + cargaHoraria + " | nome: " + nome + " | codigo componente: " + codComponente + " | turmas: " + turmas
                + "]";
    }

    public void salvar() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("componentes.txt", true));
        writer.write(codComponente + ";" + nome + ";" + obrigatorio + ";" + semestre + ";" + cargaHoraria + ";" + turmas
                + ";");
        writer.newLine();
        writer.close();
    }

    public ComponenteCurricular buscarComponente(int codComponente) throws IOException {
        File arquivo = new File("componentes.txt");
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] campos = linha.split(";");
            if (Integer.parseInt(campos[0]) == codComponente) {
                List<Turma> turmas = new ArrayList<>();
                return new ComponenteCurricular(Boolean.parseBoolean(campos[2]), Integer.parseInt(campos[3]),
                        Integer.parseInt(campos[4]), campos[1], Integer.parseInt(campos[0]), turmas);

            }
        }
        reader.close();
        return null;// Componente nao encontrada

    }

    public boolean editarComponente(int codComponente) throws IOException {
        ComponenteCurricular componente = buscarComponente(codComponente);
        if (componente == null) {
            System.out.println("Componente nao encontrado.");
            return false;

        }
        System.out.println("Escolha qual atributo deseja editar:");
        System.out.println("1. Codigo");
        System.out.println("2. Semestre");
        System.out.println("3. Carga Horaria");
        System.out.println("4. Nome");
        System.out.println("5. A obrigatoriedade");
        System.out.println("6. A turma");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opcao = Integer.parseInt(reader.readLine());
        String novoValor;
        int novoV;
        boolean nov;

        switch (opcao) {
            case 1:
                System.out.println("Digite o novo Codigo:");
                novoV = Integer.parseInt(reader.readLine());
                ComponenteCurricular codComp = buscarComponente(novoV);
                if (codComp == null) {
                    componente.setCodComponente(novoV);
                } else {
                    System.out.println("Ja existe outro componente com esse codigo");
                }
                break;
            case 2:
                System.out.println("Digite o novo semestre:");
                novoV = Integer.parseInt(reader.readLine());
                componente.setSemestre(novoV);
                break;
            case 3:
                System.out.println("Digite a nova carga horaria:");
                novoV = Integer.parseInt(reader.readLine());
                componente.setCargaHoraria(novoV);
                break;
            case 4:
                System.out.println("Digite o novo nome:");
                novoValor = reader.readLine();
                componente.setNome(novoValor);
                break;
            case 5:
                nov = componente.isObrigatorio();
                nov = true ? false : true;
                componente.setObrigatorio(nov);
                System.out.println("Seu componente agora e: " + ((nov) ? "Obrigatorio" : "Optativo"));
            case 6:
                boolean validacaoTurmas = false;
                while (!validacaoTurmas) {
                    System.out.println("Lista de turmas disponíveis:");
                    Turma turmasLista = new Turma();
                    turmasLista.listarTurmas();
                    System.out.println("Digite o codigo da nova turma: ");
                    novoV = Integer.parseInt(reader.readLine());
                    Turma novaTurma = turmasLista.buscarTurma(novoV);
                    if (novaTurma == null) {
                        System.out.println("Código de turma invalido. Por favor, tente novamente.");
                        validacaoTurmas = false;
                    } else {
                        System.out.print("Voce deseja remover o antigo?");
                        System.out.println("1 - SIM | 2 - NAO: ");
                        int escolha = Integer.parseInt(reader.readLine());
                        if(escolha == 1){
                            componente.getTurmas().clear();
                        }
                        componente.getTurmas().add(novaTurma);
                        validacaoTurmas = true;
                    }
                }
                break;
            default:
                System.out.println("Opcao invalida.");
                break;
        }
        // Lê todo o conteúdo do arquivo para a memória
        BufferedReader reader2 = new BufferedReader(new FileReader("componentes.txt"));
        StringBuilder sb = new StringBuilder();
        String linha;
        while ((linha = reader2.readLine()) != null) {
            String[] campos = linha.split(";");
            if (Integer.parseInt(campos[0]) == codComponente) {
                // Atualiza a linha do professor que foi editado
                linha = componente.getCodComponente() + ";" + componente.getNome() + ";" + componente.isObrigatorio() + ";"
                        + componente.getSemestre() + ";" + componente.getCargaHoraria() + ";" + componente.getTurmas() + ";";
            }
            sb.append(linha).append("\n");
        }
        reader2.close();
        // Sobrescreve o arquivo original com o conteúdo atualizado
        BufferedWriter writer = new BufferedWriter(new FileWriter("componentes.txt"));
        writer.write(sb.toString());
        writer.close();
        return true;
    }

    public void verDadosDeUmComponente(int codComponente) throws IOException {
        ComponenteCurricular componente = buscarComponente(codComponente);
        if (componente == null) {
            System.out.println("Componente nao encontrado.");
        } else {
            System.out.println(componente.toString());
        }
    }

    public void listarCompoentes() throws IOException {
        File arquivo = new File("componentes.txt");
        if (arquivo.exists() && arquivo.length() > 0) {
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");
                ComponenteCurricular componente = new ComponenteCurricular(Boolean.parseBoolean(campos[2]),
                        Integer.parseInt(campos[3]),
                        Integer.parseInt(campos[4]), campos[1], Integer.parseInt(campos[0]), turmas);
                System.out.println(componente.toString());
            }
            reader.close();
        } else {
            System.out.println("Nao tem componentes no banco.");
        }
    }

    public boolean excluirComponente(int codComponente) throws IOException {
        boolean componenteEncontrado = false;

        // Lê todo o conteúdo do arquivo para a memória
        BufferedReader reader = new BufferedReader(new FileReader("componentes.txt"));
        StringBuilder sb = new StringBuilder();
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] campos = linha.split(";");
            if (Integer.parseInt(campos[0]) == codComponente) {
                componenteEncontrado = true;
                continue; // Pula a linha da turma que será excluída
            }
            sb.append(linha).append("\n");
        }
        reader.close();
        if (!componenteEncontrado) {
            System.out.println("Componente nao encontrado.");
            return false;
        }
        // Sobrescreve o arquivo original com o conteúdo atualizado
        BufferedWriter writer = new BufferedWriter(new FileWriter("componentes.txt"));
        writer.write(sb.toString());
        writer.close();

        return true;
    }
}
