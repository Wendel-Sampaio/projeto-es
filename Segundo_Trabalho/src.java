package Segundo_Trabalho;

import java.util.*;
 
 public class Grupo2_StudentAggregatorRef {
 
 	public static class Student {
     	private final String nome;
     	private final String id;
     	private final String curso;
     	private final List<Integer> provas;
     	private final List<Integer> quizzes;
     	private final List<Integer> tarefas;
 
     	public Student(String nome, String id, String curso,
                    	List<Integer> provas, List<Integer> quizzes, List<Integer> tarefas) {
         	this.nome = nome; this.id = id; this.curso = curso;
         	this.provas = new ArrayList<>(provas);
         	this.quizzes = new ArrayList<>(quizzes);
         	this.tarefas = new ArrayList<>(tarefas);
     	}
 
     	public String getNome() { return nome; }
     	public String getId() { return id; }
     	public String getCurso() { return curso; }
     	public List<Integer> getProvas() { return provas; }
     	public List<Integer> getQuizzes() { return quizzes; }
     	public List<Integer> getTarefas() { return tarefas; }
 	}
 
 	public static class GradeService {
   	  private static final double WEIGHT_PROVAS = 0.6;
     	private static final double WEIGHT_QUIZZES = 0.2;
     	private static final double WEIGHT_TAREFAS = 0.2;
     	private static final double ENGENHARIA_BONUS = 0.5;
 
     	public double calcularMediaFinal(Student s) {
         	double mp = average(s.getProvas());
         	double mq = average(s.getQuizzes());
         	double mt = average(s.getTarefas());
         	double score = mp * WEIGHT_PROVAS + mq * WEIGHT_QUIZZES + mt * WEIGHT_TAREFAS;
         	if (s.getCurso() != null && s.getCurso().toLowerCase().contains("eng")) {
             	score += ENGENHARIA_BONUS;
         	}
         	return Math.round(score * 100.0) / 100.0;
     	}
 
     	private double average(List<Integer> l) {
         	return l.stream().mapToInt(Integer::intValue).average().orElse(0.0);
     	}
 	}
 
 	public static void main(String[] args) {
     	Student a = new Student("Ana","2023001","Engenharia de Software",
             	Arrays.asList(8,7,9), Arrays.asList(10,8), Arrays.asList(9,9,10));
     	GradeService gs = new GradeService();
     	double media = gs.calcularMediaFinal(a);
     	System.out.println(a.getNome() + " (" + a.getId() + ") - " + a.getCurso() + " => " + media);
 	}
 }
 