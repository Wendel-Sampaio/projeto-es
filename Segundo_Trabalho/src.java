package Segundo_Trabalho;
import java.util.*;

// A classe principal agora é um contêiner para as classes que de fato fazem o trabalho.
public class Grupo2_StudentAggregatorRef {

    /**
     * REFATORAÇÃO: Extract Class / Introduce Parameter Object
     *
     * A classe 'Aluno' foi renomeada para 'Student' para usar uma convenção mais comum em inglês.
     * Além disso, foi refatorada para encapsular os dados do aluno. Isso resolve os problemas de
     * 'Primitive Obsession' e 'Data Clumps' do código original, garantindo que as informações
     * relacionadas a um aluno estejam sempre juntas.
     * Os campos agora são 'private' e 'final' para garantir a imutabilidade dos dados
     * e evitar modificações acidentais, seguindo boas práticas de encapsulamento.
     */
    public static class Student {
        private final String nome;
        private final String id;
        private final String curso;
        private final List<Integer> provas;
        private final List<Integer> quizzes;
        private final List<Integer> tarefas;

        // Construtor completo para criar um objeto Student.
        // A lista de parâmetros Longa do método 'mediaFinal' do código antigo
        // foi substituída por um único objeto 'Student', uma aplicação da
        // refatoração 'Introduce Parameter Object'.
        public Student(String nome, String id, String curso,
                       List<Integer> provas, List<Integer> quizzes, List<Integer> tarefas) {
            this.nome = nome; this.id = id; this.curso = curso;
            // Criação de cópias das listas para garantir o encapsulamento.
            this.provas = new ArrayList<>(provas);
            this.quizzes = new ArrayList<>(quizzes);
            this.tarefas = new ArrayList<>(tarefas);
        }

        // Métodos 'getters' para acesso seguro aos dados encapsulados.
        public String getNome() { return nome; }
        public String getId() { return id; }
        public String getCurso() { return curso; }
        public List<Integer> getProvas() { return provas; }
        public List<Integer> getQuizzes() { return quizzes; }
        public List<Integer> getTarefas() { return tarefas; }
    }

    /**
     * REFATORAÇÃO: Move Method / Extract Class
     *
     * A lógica de cálculo da média final foi movida para uma nova classe, 'GradeService'.
     * Isso separa a responsabilidade de 'conter' dados (a classe Student) da responsabilidade
     * de 'operar' sobre esses dados (a classe GradeService), seguindo o
     * Princípio da Responsabilidade Única (SRP).
     */
    public static class GradeService {
        // Constantes estáticas para os pesos. Isso torna o código mais legível
        // e fácil de manter caso os pesos precisem ser alterados.
        private static final double WEIGHT_PROVAS = 0.6;
        private static final double WEIGHT_QUIZZES = 0.2;
        private static final double WEIGHT_TAREFAS = 0.2;
        private static final double ENGENHARIA_BONUS = 0.5;

        // O método 'mediaFinal' foi renomeado para 'calcularMediaFinal' e agora
        // recebe um único objeto 'Student', substituindo a lista de parâmetros longa.
        public double calcularMediaFinal(Student s) {
            // Chamada ao método privado 'average' para evitar duplicação de código.
            double mp = average(s.getProvas());
            double mq = average(s.getQuizzes());
            double mt = average(s.getTarefas());

            // Uso de variáveis explicativas (as constantes) para tornar o cálculo
            // da média mais claro.
            double score = mp * WEIGHT_PROVAS + mq * WEIGHT_QUIZZES + mt * WEIGHT_TAREFAS;

            if (s.getCurso() != null && s.getCurso().toLowerCase().contains("eng")) {
                score += ENGENHARIA_BONUS;
            }
            // Arredonda a nota para duas casas decimais para maior precisão.
            return Math.round(score * 100.0) / 100.0;
        }

        /**
         * REFATORAÇÃO: Extract Method
         *
         * Este método privado foi extraído da lógica principal para remover a duplicação de código.
         * A lógica para calcular a média de uma lista de notas agora está centralizada aqui.
         * Isso torna o código mais limpo e a lógica de média mais fácil de ser reutilizada e testada.
         */
        private double average(List<Integer> l) {
            return l.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        }
    }

    // Método principal para demonstração.
    public static void main(String[] args) {
        // Criação de um objeto 'Student' com todos os dados encapsulados.
        Student a = new Student("Ana","2023001","Engenharia de Software",
                Arrays.asList(8,7,9), Arrays.asList(10,8), Arrays.asList(9,9,10));

        // Criação de um objeto 'GradeService' para realizar o cálculo.
        GradeService gs = new GradeService();
        double media = gs.calcularMediaFinal(a);

        // Exibição do resultado.
        System.out.println(a.getNome() + " (" + a.getId() + ") - " + a.getCurso() + " => " + media);
    }
}