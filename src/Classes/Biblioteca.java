package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Interface para empréstimos e devoluções
interface BibliotecaInterface {
    boolean fazerEmprestimo(Livro livro, MembroBiblioteca membro);
    boolean fazerDevolucao(Livro livro, MembroBiblioteca membro);
}

// Classe base para representar livros
class Livro {
    private String titulo;
    private String autor;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return "Livro: " + titulo + " (Autor: " + autor + ")";
    }
}

// Classe para representar livros físicos (herda de Livro)
class LivroFisico extends Livro {
    private int numeroPaginas;

    public LivroFisico(String titulo, String autor, int numeroPaginas) {
        super(titulo, autor);
        this.numeroPaginas = numeroPaginas;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    @Override
    public String toString() {
        return super.toString() + ", Livro Físico (Páginas: " + numeroPaginas + ")";
    }
}

// Classe para representar livros digitais (herda de Livro)
class LivroDigital extends Livro {
    private String formato;

    public LivroDigital(String titulo, String autor, String formato) {
        super(titulo, autor);
        this.formato = formato;
    }

    public String getFormato() {
        return formato;
    }

    @Override
    public String toString() {
        return super.toString() + ", Livro Digital (Formato: " + formato + ")";
    }
}

// Classe para representar membros da biblioteca
class MembroBiblioteca {
    private String nome;

    public MembroBiblioteca(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Membro da Biblioteca: " + nome;
    }
}

// Classe que implementa a interface BibliotecaInterface e armazena informações sobre os empréstimos
class Biblioteca implements BibliotecaInterface {
    private List<Livro> livrosDisponiveis;
    private List<MembroBiblioteca> membros;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.livrosDisponiveis = new ArrayList<>();
        this.membros = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public List<Livro> getLivrosDisponiveis() {
        return livrosDisponiveis;
    }
    
    public List<MembroBiblioteca> getMembros() {
        return membros;
    }
    
    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void adicionarLivro(Livro livro) {
        livrosDisponiveis.add(livro);
    }

    public void adicionarMembro(MembroBiblioteca membro) {
        membros.add(membro);
    }

    @Override
    public boolean fazerEmprestimo(Livro livro, MembroBiblioteca membro) {
        if (livrosDisponiveis.contains(livro) && membros.contains(membro)) {
            emprestimos.add(new Emprestimo(livro, membro));
            livrosDisponiveis.remove(livro);
            return true;
        }
        return false;
    }

    @Override
    public boolean fazerDevolucao(Livro livro, MembroBiblioteca membro) {
        Emprestimo emprestimo = new Emprestimo(livro, membro);
        if (emprestimos.contains(emprestimo)) {
            emprestimos.remove(emprestimo);
            livrosDisponiveis.add(livro);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Biblioteca: Livros disponíveis - " + livrosDisponiveis.size() +
               ", Membros - " + membros.size() + ", Empréstimos - " + emprestimos.size();
    }
}

// Classe para representar empréstimos
class Emprestimo {
    private Livro livro;
    private MembroBiblioteca membro;

    public Emprestimo(Livro livro, MembroBiblioteca membro) {
        this.livro = livro;
        this.membro = membro;
    }

    public Livro getLivro() {
        return livro;
    }

    public MembroBiblioteca getMembro() {
        return membro;
    }

    @Override
    public String toString() {
        return "Empréstimo: " + livro.toString() + " para " + membro.toString();
    }
}
class Status {
    public static void imprimirStatus(Biblioteca biblioteca) {
        System.out.println("===== Status da Biblioteca =====");
        System.out.println("Livros Disponíveis:");
        for (Livro livro : biblioteca.getLivrosDisponiveis()) {
            System.out.println("- " + livro.toString());
        }

        System.out.println("\nMembros da Biblioteca:");
        for (MembroBiblioteca membro : biblioteca.getMembros()) {
            System.out.println("- " + membro.toString());
        }

        System.out.println("\nLivros Emprestados:");
        for (Emprestimo emprestimo : biblioteca.getEmprestimos()) {
            System.out.println("- " + emprestimo.toString());
        }
        System.out.println("===============================");
    }
}

// Classe principal para o exemplo de uso
class Main {
    public static void main(String[] args) {
        // Inicialização da biblioteca
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo à Biblioteca!");

        // Criação dos objetos Livro, LivroFisico, LivroDigital e MembroBiblioteca
        Livro livro1 = new Livro("Java Programming", "John Doe");
        LivroFisico livro2 = new LivroFisico("Design Patterns", "Jane Smith", 300);
        LivroDigital livro3 = new LivroDigital("Data Structures", "Bob Johnson", "PDF");
        LivroFisico livro4 = new LivroFisico("Metamorfose", "Frsnz Kafka", 95);

        MembroBiblioteca membro1 = new MembroBiblioteca("Alice");
        MembroBiblioteca membro2 = new MembroBiblioteca("Bob");

        // Adicionando livros e membros à biblioteca
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.adicionarLivro(livro3);
        biblioteca.adicionarLivro(livro4);


        biblioteca.adicionarMembro(membro1);
        biblioteca.adicionarMembro(membro2);

        // Realizando empréstimos e devoluções
        biblioteca.fazerEmprestimo(livro1, membro1);
        biblioteca.fazerEmprestimo(livro2, membro2);

        biblioteca.fazerDevolucao(livro1, membro1);

        // Impressão das informações da biblioteca
        System.out.println(biblioteca.toString());
        Status.imprimirStatus(biblioteca);

        while (true) {
            exibirMenu();
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (escolha) {
                case 1:
                    realizarEmprestimo(biblioteca, scanner);
                    break;
                case 2:
                    realizarDevolucao(biblioteca, scanner);
                    break;
                case 3:
                    Status.imprimirStatus(biblioteca);
                    break;
                case 4:
                    System.out.println("Saindo do programa. Até mais!");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n===== Menu =====");
        System.out.println("1. Realizar Empréstimo");
        System.out.println("2. Realizar Devolução");
        System.out.println("3. Exibir Status da Biblioteca");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void realizarEmprestimo(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\n===== Realizar Empréstimo =====");

        System.out.print("Digite o nome do livro: ");
        String tituloLivro = scanner.nextLine();

        System.out.print("Digite o nome do membro da biblioteca: ");
        String nomeMembro = scanner.nextLine();

        Livro livro = buscarLivroPorTitulo(biblioteca, tituloLivro);
        MembroBiblioteca membro = buscarMembroPorNome(biblioteca, nomeMembro);

        if (livro != null && membro != null) {
            if (biblioteca.fazerEmprestimo(livro, membro)) {
                System.out.println("Empréstimo realizado com sucesso!");
            } else {
                System.out.println("Não foi possível realizar o empréstimo.");
            }
        } else {
            System.out.println("Livro ou membro não encontrado.");
        }
    }

    private static void realizarDevolucao(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\n===== Realizar Devolução =====");

        System.out.print("Digite o nome do livro a ser devolvido: ");
        String tituloLivro = scanner.nextLine();

        System.out.print("Digite o nome do membro da biblioteca que está devolvendo: ");
        String nomeMembro = scanner.nextLine();

        Livro livro = buscarLivroPorTitulo(biblioteca, tituloLivro);
        MembroBiblioteca membro = buscarMembroPorNome(biblioteca, nomeMembro);

        if (livro != null && membro != null) {
            if (biblioteca.fazerDevolucao(livro, membro)) {
                System.out.println("Devolução realizada com sucesso!");
            } else {
                System.out.println("Não foi possível realizar a devolução.");
            }
        } else {
            System.out.println("Livro ou membro não encontrado.");
        }
    }

    private static Livro buscarLivroPorTitulo(Biblioteca biblioteca, String titulo) {
        for (Livro livro : biblioteca.getLivrosDisponiveis()) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    private static MembroBiblioteca buscarMembroPorNome(Biblioteca biblioteca, String nome) {
        for (MembroBiblioteca membro : biblioteca.getMembros()) {
            if (membro.getNome().equalsIgnoreCase(nome)) {
                return membro;
            }
        }
        return null;
    }
    }
