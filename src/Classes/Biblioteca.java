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

// Classe principal para o exemplo de uso
class Main {
    public static void main(String[] args) {
        // Inicialização da biblioteca
        Biblioteca biblioteca = new Biblioteca();

        // Entrada de dados do usuário usando Scanner
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
    }
}
