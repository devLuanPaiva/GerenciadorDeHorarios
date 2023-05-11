import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Professor professor = new Professor();
        Turma turma = new Turma();
        ComponenteCurricular componente = new ComponenteCurricular();
        List<Turma> turmas = new ArrayList<>();
        List<Professor> professoresDisponiveis = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        List<ComponenteCurricular> componentes = new ArrayList<>();

        int opcao;

        do {
            System.out.println("      GERENCIADOR DE HORARIOS");
            System.out.println("|============== MENU ==============|");
            System.out.println("|1 - Cadastrar professor           |");
            System.out.println("|2 - Editar professor              |");
            System.out.println("|3 - Ver dados de um professor     |");
            System.out.println("|4 - Listar Professores            |");
            System.out.println("|5 - Exluir Professor              |");
            System.out.println("|6 - Cadastrar Componente          |");
            System.out.println("|8 - Ver dados de um componente    |");
            System.out.println("|9 - Listar Componentes            |");
            System.out.println("|10- Excluir Componente            |");
            System.out.println("|11- Cadastrar Turma               |");
            System.out.println("|12- Editar Turma                  |");
            System.out.println("|14- Listar Turmas                 |");
            System.out.println("|15- Listar Turmas por semestre    |");
            System.out.println("|16- Listar Turmas por professor   |");
            System.out.println("|17- Excluir Turma                 |");
            System.out.println("|0 - Sair                          |");
            System.out.println("|__________________________________|");

            System.out.print("Opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            int idProf, idTut, idComp;
            switch (opcao) {
                case 1:
                    cadastrarProfessor(scanner, professor);
                    break;
                case 2:
                    System.out.print("Informe a Identificacao do professor: ");
                    idProf = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        professor.editarProfessor(idProf);
                    } catch (IOException e) {
                        System.out.println("Erro ao editar professor: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Informe a Identificacao do professor: ");
                    idProf = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        professor.verDadosDeUmProfessor(idProf);
                    } catch (IOException e) {
                        System.out.println("Erro ao editar professor: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        professor.listarProfessores();
                    } catch (IOException e) {
                        System.out.println("Erro ao listar os professores: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("Informe a Identificacao do professor: ");
                    idProf = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        professor.excluirProfessor(idProf);
                        System.out.println("Professor excluido com sucesso.");
                    } catch (IOException e) {
                        System.out.println("Ocorreu um erro ao excluir o professor: " + e.getMessage());
                    }
                    break;
                case 6:
                    cadastrarComponente(scanner, componente, componentes, turmas);
                    break;
                case 8:
                    System.out.print("Informe a Identificacao do componente: ");
                    idComp = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        componente.verDadosDeUmComponente(idComp);
                    } catch (IOException e) {
                        System.out.println("Erro ao editar componente: " + e.getMessage());
                    }
                    break;
                case 9:
                    try {
                        componente.listarCompoentes();
                    } catch (IOException e) {
                        System.out.println("Erro ao listar os componentes: " + e.getMessage());
                    }
                    break;
                case 10:
                    System.out.print("Informe a Identificacao do componente: ");
                    idComp = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        componente.excluirComponente(idComp);
                        System.out.println("Componente excluido com sucesso.");
                    } catch (IOException e) {
                        System.out.println("Ocorreu um erro ao excluir o Componente: " + e.getMessage());
                    }
                    break;
                case 11:
                    cadastrarTurma(scanner, turma, turmas, professoresDisponiveis);
                    break;
                case 12:
                    System.out.print("Informe a Identificacao da turma: ");
                    idTut = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        turma.editarTurma(idTut);
                    } catch (IOException e) {
                        System.out.println("Erro ao editar turma: " + e.getMessage());
                    }
                    break;
                case 14:
                    try {
                        turma.listarTurmas();
                    } catch (IOException e) {
                        System.out.println("Erro ao listar as turmas: " + e.getMessage());
                    }
                    break;
                case 15:
                    try {
                        System.out.print("Informe o semestre");
                        idProf = scanner.nextInt();
                        turma.listarTurmasPorProfessor(idProf);
                    } catch (IOException e) {
                        System.out.println("Erro ao listar as turmas do professor: " + e.getMessage());
                    }
                    break;
                case 16:
                    try {
                        System.out.print("Informe a identificacao do professor");
                        idProf = scanner.nextInt();
                        turma.listarTurmasPorProfessor(idProf);
                    } catch (IOException e) {
                        System.out.println("Erro ao listar as turmas do professor: " + e.getMessage());
                    }
                    break;
                case 17:
                    System.out.print("Informe a Identificacao da turma: ");
                    idTut = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        turma.excluirTurma(idTut);
                        System.out.println("Turma excluida com sucesso.");
                    } catch (IOException e) {
                        System.out.println("Ocorreu um erro ao excluir a turma: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    scanner.nextLine();
                    break;
            }

            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }

    public static void cadastrarProfessor(Scanner scanner, Professor professor) throws IOException {
        String nome, email, formacao;
        int identificacao;

        System.out.println("|   CADASTRO DE PROFESSOR   |");
        System.out.println("|===========================|");

        System.out.print("Nome: ");
        nome = scanner.nextLine();

        System.out.print("Identificacao: ");
        int numero = -1;
        while (numero < 0 || numero > 999) {
            System.out.print("Digite um numero de 3 digitos: ");
            numero = scanner.nextInt();
            if (numero < 0 || numero > 999) {
                System.out.println("O numero digitado deve ter 3 digitos.");
            }
        }
        identificacao = numero;
        scanner.nextLine();

        System.out.print("Email: ");
        email = scanner.nextLine();

        System.out.print("Formacao: ");
        formacao = scanner.nextLine();

        if (professor.buscarProfessor(identificacao) == null) {
            professor.setNome(nome);
            professor.setIdentificacao(identificacao);
            professor.setEmail(email);
            professor.setFormacao(formacao);

        } else {
            System.out.println("Ja existe professor com essa mesma identificacao, tente outra.");
        }

        try {
            professor.salvar();
            System.out.println("Professor cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar professor: " + e.getMessage());
        }
    }

    private static void cadastrarTurma(Scanner scanner, Turma turma, List<Turma> turmas,
            List<Professor> professoresDisponiveis) {
        try {
            int codTurma = 0, semestre = 0, diasAulaSemana = 0;
            List<String> horarios = new ArrayList<>();
            boolean validacaoHorario = false;
            System.out.println("|   CADASTRO DE TURMA   |");
            System.out.println("|=======================|");

            while (!validacaoHorario) {
                System.out.println("Digite o codigo da turma: ");
                int numero = -1;
                while (numero < 0 || numero > 99999) {
                    System.out.print("Digite um numero de 5 digitos: ");
                    numero = scanner.nextInt();
                    if (numero < 0 || numero > 99999) {
                        System.out.println("O numero digitado deve ter 5 digitos.");
                    }
                }
                codTurma = numero;
                System.out.println("Digite o semestre: ");
                semestre = scanner.nextInt();
                System.out.println("Digite o numero de dias de aula na semana: ");
                diasAulaSemana = scanner.nextInt();
                horarios = new ArrayList<>();
                scanner.nextLine(); // consumir a quebra de linha pendente
                for (int i = 1; i <= diasAulaSemana; i++) {
                    System.out.println("Digite o horario para o dia " + i + " (no formato HH:MM): ");
                    String horario = scanner.nextLine();
                    if (horarios.contains(horario)) {
                        System.out
                                .println("Horario ja cadastrado para outro dia da semana. Por favor, tente novamente.");
                        validacaoHorario = false;
                        break;
                    }
                    horarios.add(horario);
                    validacaoHorario = true;
                }
            }
            List<Professor> professores = new ArrayList<>();
            Professor professorSelecionado = null;
            boolean validacaoProfessores = false;
            while (!validacaoProfessores) {
                System.out.println("Lista de professores disponiveis:");
                Professor professorLista = new Professor();
                professorLista.listarProfessores();
                System.out.println("Digite o codigo do professor que deseja cadastrar na turma: ");
                int codigoProfessor = scanner.nextInt();
                professorSelecionado = professorLista.buscarProfessor(codigoProfessor);

                if (professorSelecionado == null) {
                    System.out.println("Codigo de professor invalido. Por favor, tente novamente.");
                    validacaoProfessores = false;
                } else {
                    professores.add(professorSelecionado);
                    validacaoProfessores = true;
                }
            }
            if (turma.buscarTurma(codTurma) == null) {
                turma.setCodTurma(codTurma);
                turma.setDiasAulaSemana(diasAulaSemana);
                turma.setHorarios(horarios);
                turma.setSemestre(semestre);
                turma.setProfessores(Arrays.asList(professorSelecionado));
                turmas.add(turma);
                turma.salvar();
                System.out.println("Turma cadastrada com sucesso");
            } else {
                System.out.println("Existe uma turma com o mesmo codigo, informe outro.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler ou escrever no arquivo: " + e.getMessage());
        }
    }

    public static void cadastrarComponente(Scanner scanner, ComponenteCurricular componente,
            List<ComponenteCurricular> componentes, List<Turma> turmas) {
        try {
            int codComponente = 0, semestre = 0, cargaHoraria = 0;
            String nome = "";
            boolean obrigatorio = false;
            List<Turma> turmas2 = new ArrayList<>();
            Turma turmaSelecionada = null;
            boolean validacaoTurma = false;
            System.out.println("|   CADASTRO DE COMPONENTE   |");
            System.out.println("|============================|");
            System.out.println();
            System.out.println("Informe o nome desse componente: ");
            nome = scanner.nextLine();
            System.out.println("Informe o codigo de identificacao: ");
            int numero = -1;
            while (numero < 0 || numero > 9999) {
                System.out.print("Digite um numero de 4 digitos: ");
                numero = scanner.nextInt();
                if (numero < 0 || numero > 9999) {
                    System.out.println("O numero digitado deve ter 4 digitos.");
                }
            }
            codComponente = numero;
            System.out.println("O componente e obrigatorio? ");
            System.out.println("1) SIM | 2 NAO");
            int codOBG = scanner.nextInt();
            if (codOBG == 1) {
                obrigatorio = true;
            } else if (codOBG == 2) {
                obrigatorio = false;
            }
            System.out.println("Informe o semestre do componente: ");
            semestre = scanner.nextInt();

            System.out.println("Informe a carga horaria: ");
            cargaHoraria = scanner.nextInt();
            while (!validacaoTurma) {
                System.out.println("Lista de turmas disponeiveis: ");
                Turma turmasLista = new Turma();
                turmasLista.listarTurmas();
                System.out.println("Digite o codigo da turma que deseja cadastrar no componente: ");
                int codTurma = scanner.nextInt();
                turmaSelecionada = turmasLista.buscarTurma(codTurma);

                if (turmaSelecionada == null) {
                    System.out.println("Codigo de turma invalido. Por favor, tente novamente.");
                    validacaoTurma = false;
                } else {
                    turmas2.add(turmaSelecionada);
                    validacaoTurma = true;
                }
            }

            componente.setCargaHoraria(cargaHoraria);
            componente.setCodComponente(codComponente);
            componente.setNome(nome);
            componente.setObrigatorio(obrigatorio);
            componente.setSemestre(semestre);
            componente.setTurmas(turmas2);
            componentes.add(componente);
            componente.salvar();
            System.out.println("Componente cadastrado com sucesso");
        } catch (IOException e) {
            System.out.println("Erro ao ler ou escrever no arquivo: " + e.getMessage());
        }
    }
}
