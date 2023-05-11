import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Professor {
    private String nome;
    private int identificacao;
    private String email;
    private String formacao;

    public Professor(String nome, int identificacao, String email, String formacao) {
        this.nome = nome;
        this.identificacao = identificacao;
        this.email = email;
        this.formacao = formacao;
    }

    public Professor() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(int identificacao) {
        this.identificacao = identificacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    @Override
    public String toString() {
        return "Professor [Nome: " + nome + " | Identificacao: " + identificacao + " | Email: " + email + " | Formacao: "
                + formacao + "]";
    }

    public void salvar() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("professores.txt", true));
        writer.write(nome + ";" + identificacao + ";" + email + ";" + formacao);
        writer.newLine();
        writer.close();
    }

    public Professor buscarProfessor(int identificacao) throws IOException {
        File arquivo = new File("professores.txt");
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] campos = linha.split(";");
            if (Integer.parseInt(campos[1]) == identificacao) {
                return new Professor(campos[0], Integer.parseInt(campos[1]), campos[2], campos[3]);
            }
        }
        reader.close();
        return null; // Professor não encontrado
    }
    

    public boolean editarProfessor(int identificacao) throws IOException {
        Professor professor = buscarProfessor(identificacao);
        if (professor == null) {
            System.out.println("Professor nao encontrado.");
            return false;
        }
    
        System.out.println("Escolha qual atributo deseja editar:");
        System.out.println("1. Nome");
        System.out.println("2. Identificacao");
        System.out.println("3. Email");
        System.out.println("4. Formacao");
    
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opcao = Integer.parseInt(reader.readLine());
    
        String novoValor;
        switch (opcao) {
            case 1:
                System.out.println("Digite o novo nome:");
                novoValor = reader.readLine();
                professor.setNome(novoValor);
                break;
            case 2:
                System.out.println("Digite a nova identificacao:");
                novoValor = reader.readLine();
                professor.setIdentificacao(Integer.parseInt(novoValor));
                break;
            case 3:
                System.out.println("Digite o novo email:");
                novoValor = reader.readLine();
                professor.setEmail(novoValor);
                break;
            case 4:
                System.out.println("Digite a nova formacao:");
                novoValor = reader.readLine();
                professor.setFormacao(novoValor);
                break;
            default:
                System.out.println("Opção invalida.");
                return false;
        }
    
        // Lê todo o conteúdo do arquivo para a memória
        BufferedReader reader2 = new BufferedReader(new FileReader("professores.txt"));
        StringBuilder sb = new StringBuilder();
        String linha;
        while ((linha = reader2.readLine()) != null) {
            String[] campos = linha.split(";");
            if (Integer.parseInt(campos[1]) == identificacao) {
                // Atualiza a linha do professor que foi editado
                linha = professor.getNome() + ";" + professor.getIdentificacao() + ";" + professor.getEmail() + ";" + professor.getFormacao();
            }
            sb.append(linha).append("\n");
        }
        reader2.close();
    
        // Sobrescreve o arquivo original com o conteúdo atualizado
        BufferedWriter writer = new BufferedWriter(new FileWriter("professores.txt"));
        writer.write(sb.toString());
        writer.close();
    
        return true;
    }
    

    public void verDadosDeUmProfessor(int identificacao) throws IOException {
        Professor professor = buscarProfessor(identificacao);
        if (professor == null) {
            System.out.println("Professor não encontrado.");
        } else {
            System.out.println(professor.toString());
        }
    }
    public void listarProfessores() throws IOException {
        File arquivo = new File("professores.txt");
        if (arquivo.exists() && arquivo.length() > 0) {
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");
                Professor professor = new Professor(campos[0], Integer.parseInt(campos[1]), campos[2], campos[3]);
                System.out.println(professor.toString());
            }
            reader.close();
        } else {
           System.out.println("Nao tem professores no banco.");
        }
    }
    public boolean excluirProfessor(int identificacao) throws IOException {
        boolean professorEncontrado = false;
    
        // Lê todo o conteúdo do arquivo para a memória
        BufferedReader reader = new BufferedReader(new FileReader("professores.txt"));
        StringBuilder sb = new StringBuilder();
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] campos = linha.split(";");
            if (Integer.parseInt(campos[1]) == identificacao) {
                professorEncontrado = true;
                continue; // Pula a linha do professor que será excluído
            }
            sb.append(linha).append("\n");
        }
        reader.close();  
        if (!professorEncontrado) {
            System.out.println("Professor nao encontrado.");
            return false;
        } 
        // Sobrescreve o arquivo original com o conteúdo atualizado
        BufferedWriter writer = new BufferedWriter(new FileWriter("professores.txt"));
        writer.write(sb.toString());
        writer.close();
    
        return true;
    }        
}