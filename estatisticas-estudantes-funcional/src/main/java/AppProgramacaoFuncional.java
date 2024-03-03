import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


/**
 *
 * @author Manoel Campos da Silva Filho
 */
public class AppProgramacaoFuncional {
    private static final int TOTAL_STUDENTS = 1000;
    private final List<Estudante> estudantes;

    public AppProgramacaoFuncional(){
        estudantes = StudentGenerator.generate(TOTAL_STUDENTS);
        System.out.println();

        imprimeMaiorNota();
        imprimeMaiorNotaHomens();
        imprimeTotalHomens();
        imprimeTotalMulheres();
        imprimeMediaNotas();
        imprimeTotalEstudantesMatriculados();
        imprimeTotalCampiComAlunos();
        imprimeEstudantesAprovadosTSI();
        imprimeEstudantesAgrupandoPorCurso();
        imprimeEstudantesAprovadosPalmas();
        imprimeTotalEstudantesPorCurso();
        imprimeMediaNotasAgrupandoPorSexo();
        imprimeEstudantesPorSexo();
        imprimeCursosComEstudantesOrdenado();
    }

    private void imprimeMaiorNota() {
        final double maior =
                estudantes.stream()
                          .mapToDouble(Estudante::getNota)
                          .max()
                          .orElse(0);
        System.out.printf("Maior nota entre todos os Estudantes: %.2f\n", maior);
    }

    private void imprimeMaiorNotaHomens() {
        final double maior =
                estudantes.stream()
                          .filter((Estudante e) -> e.getSexo() == 'M')
                          .mapToDouble((Estudante e) -> e.getNota())
                          .max()
                          .orElse(0);
        System.out.printf("Maior nota entre todos os Estudantes: %.2f\n", maior);
    }

    private void imprimeTotalHomens() {
        final long totalHomens =
                estudantes.stream()
                          .filter(Estudante::isHomem)
                          .count();
        System.out.println("Total de Homens: " + totalHomens);
    }

    private void imprimeTotalMulheres() {
        final long totalMulheres =
                estudantes.stream()
                          .filter(Estudante::isMulher)
                          .count();
        System.out.println("Total de Mulheres: " + totalMulheres);
    }

    private void imprimeMediaNotas() {
        final double mediaNotas =
                estudantes.stream()
                          .mapToDouble(Estudante::getNota)
                          .average()
                          .orElse(0);
        System.out.println("Média das notas: " + mediaNotas);
    }

    private void imprimeTotalEstudantesMatriculados() {
        final long totalEstudantesMatriculados =
                estudantes.stream()
                          .map(Estudante::getCourse)
                          .filter(Objects::nonNull)
                          .count();
        System.out.printf("Total de Estudantes Matriculados: %d\n", totalEstudantesMatriculados);
    }

    private void imprimeEstudantesPorSexo() {
        final Map<Character, List<Estudante>> estudantesPorSexo =
                estudantes.stream()
                          .collect(groupingBy(Estudante::getSexo));
        System.out.println("\nEstudantes por sexo");
        estudantesPorSexo.forEach((gender, students) -> System.out.printf("\tSexo: %c | Estudantes: %s\n", gender, students));
    }

    private void imprimeTotalEstudantesPorCurso() {
        final Map<Curso, Long> totalEstudantesPorCurso =
                estudantes.stream()
                          .filter(s -> s.getCourse() != null)
                          .collect(groupingBy(Estudante::getCourse, counting()));
        System.out.println("\nTotal de Estudantes por Curso");
        totalEstudantesPorCurso.forEach(
            (curso, totalAlunos) -> System.out.printf("\tCurso: %-30s | Total de Estudantes: %d\n", curso.getNome(), totalAlunos)
        );
    }

    private void imprimeMediaNotasAgrupandoPorSexo() {
        Map<Character, Double> mapMediaPorSexo =
                estudantes.stream()
                          .collect(groupingBy(Estudante::getSexo, averagingDouble(Estudante::getNota)));
        mapMediaPorSexo.forEach((sexo, media) -> System.out.printf("%c: %.2f%n", sexo, media));
    }

    private void imprimeEstudantesAprovadosPalmas() {
        System.out.println();
        final String nomesEstudantesAprovados =
                estudantes.stream()
                          .filter(s -> s.getNota() > 9)
                          .filter(Estudante::temCurso)
                          .filter(s -> s.getCourse().getCampus().getId() == 1)
                          .map(Estudante::getNome)
                          .collect(Collectors.joining(", "));
        System.out.println("Nomes dos Estudantes Aprovados em Palmas com nota maior que 9: " + nomesEstudantesAprovados);
    }

    private void imprimeEstudantesAprovadosTSI() {
        final List<Estudante> alunosSistemasInternet =
                estudantes.stream()
                          .filter(Estudante::temCurso)
                          .filter(s -> s.getCourse().getId() == 1)
                          .filter(s -> s.getNota() >= 6)
                          .toList();
        System.out.println("\nEstudantes Aprovados em Tecnologia em Sistemas para Internet (TSI):");
        alunosSistemasInternet.forEach(s -> System.out.println("\t" + s));
        System.out.println("Total de Estudantes Aprovados em Sistemas para Internet: " + alunosSistemasInternet.size());
    }

    private void imprimeEstudantesAgrupandoPorCurso() {
        final Map<Curso, List<Estudante>> estudantesAgrupadosPorCurso =
                estudantes.stream()
                          .filter(Estudante::temCurso)
                          .collect(groupingBy(Estudante::getCourse));
        System.out.println("\nEstudantes por curso");
        estudantesAgrupadosPorCurso.forEach(
                (curso, students) -> System.out.printf("\t%-30s: Estudantes: %s\n", curso.getNome(), students)
        );
    }

    private void imprimeTotalCampiComAlunos() {
        final long totalCampiComAlunos =
                estudantes.stream()
                          .map(Estudante::getCourse)
                          .filter(Objects::nonNull)
                          .map(Curso::getCampus)
                          .distinct()
                          .count();
        System.out.printf("Campi em que há Estudantes: %d\n", totalCampiComAlunos);
    }

    private void imprimeCursosComEstudantesOrdenado() {
        var comparator = Comparator.comparingInt(Curso::getId)
                                   .thenComparing(Curso::getNome);

        var courses = estudantes.stream()
                                .map(Estudante::getCourse)
                                .filter(Objects::nonNull)
                                .distinct()
                                .sorted(comparator)
                                .toList();

        System.out.printf("%nCursos com estudantes matriculados (ordenado por id e nome)%n");
        courses.forEach(System.out::println);
    }

    public static void main(String[] args) {
        new AppProgramacaoFuncional();
    }
}

