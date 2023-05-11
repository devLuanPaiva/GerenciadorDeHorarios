import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Turma {
    private int codTurma;
    private int semestre;
    private int diasAulaSemana;
    private List<String> horarios;
    private List<Professor> professores = new ArrayList<>();

    public Turma(int codTurma, int semestre, int diasAulaSemana, List<String> horarios, List<Professor> professores) {
        this.codTurma = codTurma;
        this.semestre = semestre;
        this.diasAulaSemana = diasAulaSemana;
        this.horarios = horarios;
        this.professores = professores;
    }

    public Turma() {
    }

    public int getCodTurma() {
        return codTurma;
    }

    public void setCodTurma(int codTurma) {
        this.codTurma = codTurma;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getDiasAulaSemana() {
        return diasAulaSemana;
    }

    public void setDiasAulaSemana(int diasAulaSemana) {
        this.diasAulaSemana = diasAulaSemana;
    }

    public List<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    @Override
    public String toString() {
        return "Turma [codigo da Turma: " + codTurma + " | semestre: " + semestre + " | diasAulaSemana: "
                + diasAulaSemana
                + " | horarios: " + horarios + " | professores: " + professores + "]";
    }

    public void salvar() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("turmas.txt", true));
        writer.write(codTurma + ";" + semestre + ";" + diasAulaSemana + ";" + horarios + ";" + professores);
        writer.newLine();
        writer.close();
    }

    public Turma buscarTurma(int codTutma) throws IOException {
        File arquivo = new File("turmas.txt");
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] campos = linha.split(";");
            if (Integer.parseInt(campos[0]) == codTutma) {
                List<String> horarios = Arrays.asList(campos[4].split(", "));
                List<Professor> professores = new ArrayList<>();
                Turma turma = new Turma(Integer.parseInt(campos[0]), Integer.parseInt(campos[1]),
                        Integer.parseInt(campos[2]), horarios, professores);
                return turma;
            }
        }
        reader.close();
        return null;// Turma nao encontrada

    }

    public boolean editarTurma(int codTurma) throws IOException {
        Turma turma = buscarTurma(codTurma);
        if (turma == null) {
            System.out.println("Turma nao encontrada.");
            return false;
        }
        System.out.println("Escolha qual atributo deseja editar:");
        System.out.println("1. Codigo");
        System.out.println("2. Semestre");
        System.out.println("3. Dias de Aula");
        System.out.println("4. Horarios");
        System.out.println("5. Professor");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opcao = Integer.parseInt(reader.readLine());
        String novoValor;
        int novoV;
        switch (opcao) {
            case 1:
                System.out.println("Digite o novo Codigo:");
                novoV = Integer.parseInt(reader.readLine());
                Turma codTur = buscarTurma(novoV);
                if (codTur == null) {
                    turma.setCodTurma(novoV);
                } else {
                    System.out.println("Ja existe outra turma com esse codigo");
                }
                break;
            case 2:
                System.out.println("Digite o novo semestre:");
                novoV = Integer.parseInt(reader.readLine());
                turma.setSemestre(novoV);
                break;
            case 3:
                System.out.println("Digite o novo número de dias de aula na semana:");
                novoV = Integer.parseInt(reader.readLine());
                turma.setDiasAulaSemana(novoV);
                turma.setHorarios(new ArrayList<String>());
                for (int i = 1; i <= diasAulaSemana; i++) {
                    System.out.println("Digite o novo horario para o dia " + i + " (no formato HH:MM):");
                    novoValor = reader.readLine();
                    if (turma.getHorarios().contains(novoValor)) {
                        System.out
                                .println("Horario ja cadastrado para outro dia da semana. Por favor, tente novamente.");
                        i--;
                        continue;
                    }
                    turma.getHorarios().add(novoValor);
                }
                break;
            case 4:
                turma.setHorarios(new ArrayList<String>());
                int diasAulaSemana2 = turma.getDiasAulaSemana();
                for (int i = 1; i <= diasAulaSemana2; i++) {
                    System.out.println("Digite o novo horario para o dia " + i + " (no formato HH:MM):");
                    novoValor = reader.readLine();
                    if (turma.getHorarios().contains(novoValor)) {
                        System.out
                                .println("Horario ja cadastrado para outro dia da semana. Por favor, tente novamente.");
                        i--;
                        continue;
                    }
                    turma.getHorarios().add(novoValor);
                }
                break;
            case 5:
                boolean validacaoProfessores = false;
                while (!validacaoProfessores) {
                    System.out.println("Lista de professores disponíveis:");
                    Professor professorLista = new Professor();
                    professorLista.listarProfessores();
                    System.out.println("Digite o codigo do novo professor para a turma: ");
                    novoV = Integer.parseInt(reader.readLine());

                    Professor novoProfessor = professorLista.buscarProfessor(novoV);

                    if (novoProfessor == null) {
                        System.out.println("Codigo de professor invalido. Por favor, tente novamente.");
                        validacaoProfessores = false;
                    } else {
                        System.out.print("Voce deseja remover o antigo?");
                        System.out.println("1 - SIM | 2 - NAO: ");
                        int escolha = Integer.parseInt(reader.readLine());
                        if(escolha == 1){
                            turma.getProfessores().clear();
                        }    
                        turma.getProfessores().add(novoProfessor);
                        validacaoProfessores = true;
                    }
                }
                break;
            default:
                System.out.println("Opcao invalida.");
                break;
        }
        // Lê todo o conteúdo do arquivo para a memória
        BufferedReader reader2 = new BufferedReader(new FileReader("turmas.txt"));
        StringBuilder sb = new StringBuilder();
        String linha;
        while ((linha = reader2.readLine()) != null) {
            String[] campos = linha.split(";");
            if (Integer.parseInt(campos[0]) == codTurma) {
                // Atualiza a linha do professor que foi editado
                linha = turma.getCodTurma() + ";" + turma.getSemestre() + ";" + turma.getDiasAulaSemana() + ";"
                        + turma.getHorarios() + ";" + turma.getProfessores() + ";";
            }
            sb.append(linha).append("\n");
        }
        reader2.close();
        // Sobrescreve o arquivo original com o conteúdo atualizado
        BufferedWriter writer = new BufferedWriter(new FileWriter("turmas.txt"));
        writer.write(sb.toString());
        writer.close();

        return true;
    }

    public void listarTurmas() throws IOException {
        File arquivo = new File("turmas.txt");
        if (arquivo.exists() && arquivo.length() > 0) {
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");
                Turma turma = new Turma(Integer.parseInt(campos[0]), Integer.parseInt(campos[1]),
                        Integer.parseInt(campos[2]), horarios, professores);
                System.out.println(turma.toString());
            }
            reader.close();
        } else {
            System.out.println("Nao tem turmas no banco.");
        }
    }
    public void listarTurmasPorProfessor(int identificacao) throws IOException {
        File arquivo = new File("turmas.txt");
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;
    
        // Imprime o cabeçalho da tabela
        System.out.printf("%-10s%-30s%-20s%-30s\n", "Codigo", "Horarios", "Semestre", "Professores");
    
        while ((linha = reader.readLine()) != null) {
            String[] campos = linha.split(";");
    
            // Verifica se a turma possui o professor buscado
            boolean possuiProfessor = false;
            for (Professor professor : professores) {
                if (professor.getIdentificacao() == identificacao) {
                    possuiProfessor = true;
                    break;
                }
            }
    
            if (possuiProfessor) {
                // Monta a string com os horários da turma
                String horarios = "";
                for (String horario : Arrays.asList(campos[3].split(","))) {
                    horarios += horario.trim() + "; ";
                }
                horarios = horarios.substring(0, horarios.length() - 2); // remove o último "; "
    
                // Monta a string com os professores da turma
                String professoresStr = "";
                for (Professor professor : professores) {
                    professoresStr += professor.getNome() + "; ";
                }
                professoresStr = professoresStr.substring(0, professoresStr.length() - 2); // remove o último "; "
    
                // Imprime as informações da turma na tabela
                System.out.printf("%-10s%-30s%-20s%-30s\n", campos[0], horarios, campos[1], professoresStr);
            }
        }
        reader.close();
    }
    public void listarTurmasPorSemestre(int semestre) throws IOException {
        List<Turma> turmasSemestre = new ArrayList<>();
        File arquivo = new File("turmas.txt");
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] campos = linha.split(";");
            if (Integer.parseInt(campos[1]) == semestre) {
                List<String> horarios = Arrays.asList(campos[3].split(","));
                List<Professor> professores = new ArrayList<>();
                Turma turma = new Turma(Integer.parseInt(campos[0]), Integer.parseInt(campos[1]),
                        Integer.parseInt(campos[2]), horarios, professores);
                turmasSemestre.add(turma);
            }
        }
        reader.close();
    
        // Imprime as informações das turmas em formato de tabela
        System.out.printf("%-15s %-10s %-20s %-30s %-30s%n", "Codigo Turma", "Semestre", "Dias de Aula na Semana", "Horarios", "Professores");
        for (Turma turma : turmasSemestre) {
            String horarios = String.join(", ", turma.getHorarios());
            String professores = turma.getProfessores().isEmpty() ? "Nenhum professor alocado" : turma.getProfessores().toString();
            System.out.printf("%-15d %-10d %-20d %-30s %-30s%n", turma.getCodTurma(), turma.getSemestre(), turma.getDiasAulaSemana(), horarios, professores);
        }
    }
    public boolean excluirTurma(int codTurma) throws IOException {
        boolean turmaEncontrada = false;
    
        // Lê todo o conteúdo do arquivo para a memória
        BufferedReader reader = new BufferedReader(new FileReader("turmas.txt"));
        StringBuilder sb = new StringBuilder();
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] campos = linha.split(";");
            if (Integer.parseInt(campos[0]) == codTurma) {
                turmaEncontrada = true;
                continue; // Pula a linha da turma que será excluída
            }
            sb.append(linha).append("\n");
        }
        reader.close();  
        if (!turmaEncontrada) {
            System.out.println("Turma nao encontrada.");
            return false;
        } 
        // Sobrescreve o arquivo original com o conteúdo atualizado
        BufferedWriter writer = new BufferedWriter(new FileWriter("turmas.txt"));
        writer.write(sb.toString());
        writer.close();
    
        return true;
    }   
}
