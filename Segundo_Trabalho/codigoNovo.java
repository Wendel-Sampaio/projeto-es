package Segundo_Trabalho;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class codigoNovo {

    static class Aluno {
        String nome;
        String id;
        String curso;
        List<Integer> provas = new ArrayList<>();
        List<Integer> quizzes = new ArrayList<>();
        List<Integer> tarefas = new ArrayList<>();
        Aluno(String nome,String id,String curso)
        {this.nome=nome;
            this.id=id; this.curso=curso;}
    }
    public static double mediaFinalAluno(Aluno aluno) {
        double mediaProvas = aluno.provas.stream().mapToInt(x->x).average().orElse(0);
        double mediaQuizzes = aluno.quizzes.stream().mapToInt(x->x).average().orElse(0);
        double mediaTarefas = aluno.tarefas.stream().mapToInt(x->x).average().orElse(0);

        double pontuacao= mediaProvas * 0.6 + mediaQuizzes * 0.2 + mediaTarefas * 0.2;
        if (aluno.curso != null && aluno.curso.toLowerCase().contains("eng")) pontuacao += 0.5;

        return pontuacao;
    }

    public static String mediaFinalFormatada(Aluno aluno){
        return aluno.nome + " (" + aluno.id + ") - " + aluno.curso + " => " +  String.format("%.2f", mediaFinalAluno(aluno));
    }
    public static void main(String[] args) {
        Aluno alunoTeste = new Aluno("Ana","2023001","Engenharia de Software");
        alunoTeste.provas.addAll(Arrays.asList(8,7,9));
        alunoTeste.quizzes.addAll(Arrays.asList(10,8));
        alunoTeste.tarefas.addAll(Arrays.asList(9,9,10));

        System.out.println(mediaFinalFormatada(alunoTeste));

    }
}
