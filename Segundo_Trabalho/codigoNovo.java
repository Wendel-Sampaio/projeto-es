package Segundo_Trabalho;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class codigoNovo {

    static class Aluno {
        String nome; String id; String curso;
        List<Integer> provas = new ArrayList<>();
        List<Integer> quizzes = new ArrayList<>();
        List<Integer> tarefas = new ArrayList<>();
        Aluno(String n,String i,String c){nome=n; id=i; curso=c;}
    }
    public static double mediaFinal(String nome, String id, String curso,
                                    List<Integer> provas, List<Integer> quizzes, List<Integer>
                                            tarefas) {
        double mp = provas.stream().mapToInt(x->x).average().orElse(0);
        double mq = quizzes.stream().mapToInt(x->x).average().orElse(0);
        double mt = tarefas.stream().mapToInt(x->x).average().orElse(0);
        double score = mp * 0.6 + mq * 0.2 + mt * 0.2;
        if (curso != null && curso.toLowerCase().contains("eng")) score += 0.5;
        System.out.println(nome + " (" + id + ") - " + curso + " => " + score);
        return score;
    }
    public static void main(String[] args) {
        codigoAntigo.Aluno a = new codigoAntigo.Aluno("Ana","2023001","Engenharia de Software");
        a.provas.addAll(Arrays.asList(8,7,9));
        a.quizzes.addAll(Arrays.asList(10,8));
        a.tarefas.addAll(Arrays.asList(9,9,10));
        mediaFinal(a.nome, a.id, a.curso, a.provas, a.quizzes, a.tarefas);
    }
}
