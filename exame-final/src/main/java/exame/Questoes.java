package exame;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Manoel Campos
 */
public class Questoes {
    private static final List<Estudante> estudantes = new StudentGenerator().generate(100);

    public static void main(String[] args) {
        questao1();
        questao2();
        questao3();
        questao4();
        questao5();
    }

    private static void questao1() {
        double media = estudantes.stream()
                             .filter(e -> e.getCurso() != null)
                             .filter(e -> e.getCurso().getId() == 1)
                             .filter(e -> e.getNota() < 4)
                             .mapToDouble(Estudante::getNota)
                             .average()
                             .orElse(0);
        System.out.println("Média de estudantes matriculados no curso 1 e com nota menor que 4" + media);
        System.out.println();
    }

    private static void questao2() {
        System.out.println("Cursos com estudantes matriculados e aprovados");
        estudantes.stream()
                  .filter(e -> e.getCurso() != null)
                  .filter(e -> e.getNota() > 7)
                  .map(Estudante::getCurso)
                  .distinct()
                  .forEach(System.out::println);

        System.out.println();
    }

    private static void questao3() {
        var estudante = estudantes.stream()
                                  .filter(e -> e.getCurso() != null)
                                  .filter(e -> e.getCurso().getId() == 2)
                                  .reduce((e1, e2) -> e1.getNota() > e2.getNota() ? e1 : e2)
                                  .orElseThrow();
        System.out.println("Estudante com maior nota -> " + estudante);
        System.out.println();
    }

    private static void questao4() {
        System.out.println("Estudantes matriculados em cursos com 6 semestres, ordenados por nome (crescente) e nota (decrescente)");

        var notaComparator = Comparator.comparingDouble(Estudante::getNota).reversed();
        estudantes.stream()
                  .filter(e -> e.getCurso() != null)
                  .filter(e -> e.getCurso().getSemestres() == 6)
                  .sorted(Comparator.comparing(Estudante::getCurso).thenComparing(notaComparator))
                  .forEach(System.out::println);
        System.out.println();
    }

    private static void questao5() {
        imprimirMediaAlunosCurso(notas -> Arrays.stream(notas).average().orElse(0));
    }

    private static void imprimirMediaAlunosCurso(MediaNota calculaMedia){
        var vetorNotas = estudantes.stream()
                                   .filter(e -> e.getCurso() != null)
                                   .filter(e -> e.getCurso().getId() == 2)
                                   .mapToDouble(Estudante::getNota)
                                   .toArray();

        System.out.println("Média das notas dos alunos do curso 2: "+ calculaMedia.calcular(vetorNotas));
    }
}
