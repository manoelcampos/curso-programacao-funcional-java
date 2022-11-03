import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;


/**
 *
 * @author Manoel Campos da Silva Filho
 */
public class AppProgramacaoFuncional {
    private static final int TOTAL_STUDENTS = 1000;
    private final List<Student> students;

    public AppProgramacaoFuncional(){
        students = StudentGenerator.generate(TOTAL_STUDENTS);
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
    }

    private void imprimeMaiorNota() {
        final double maior =
                students.stream()
                        .mapToDouble(Student::getScore)
                        .max()
                        .orElse(0);
        System.out.printf("Maior nota entre todos os Estudantes: %.2f\n", maior);
    }

    private void imprimeMaiorNotaHomens() {
        final double maior =
                students.stream()
                        .filter(s -> s.getGender() == 'M')
                        .mapToDouble(Student::getScore)
                        .max()
                        .orElse(0);
        System.out.printf("Maior nota entre todos os Estudantes: %.2f\n", maior);
    }

    private void imprimeTotalHomens() {
        final long totalHomens =
                students.stream()
                        .filter(s -> s.getGender() == 'M')
                        .count();
        System.out.println("Total de Homens: " + totalHomens);
    }

    private void imprimeTotalMulheres() {
        final long totalMulheres =
                students.stream()
                        .filter(s -> s.getGender() == 'F')
                        .count();
        System.out.println("Total de Mulheres: " + totalMulheres);
    }

    private void imprimeMediaNotas() {
        final double mediaNotas =
                students.stream()
                        .mapToDouble(Student::getScore)
                        .average()
                        .orElse(0);
        System.out.println("Média das notas: " + mediaNotas);
    }

    private void imprimeTotalEstudantesMatriculados() {
        final long totalEstudantesMatriculados =
                students.stream()
                        .map(Student::getCourse)
                        .filter(Objects::nonNull)
                        .count();
        System.out.printf("Total de Estudantes Matriculados: %d\n", totalEstudantesMatriculados);
    }

    private void imprimeEstudantesPorSexo() {
        final Map<Character, List<Student>> estudantesPorSexo =
                students.stream()
                        .collect(groupingBy(Student::getGender));
        System.out.println("\nEstudantes por sexo");
        estudantesPorSexo.forEach((gender, students) -> System.out.printf("\tSexo: %c | Estudantes: %s\n", gender, students));
    }

    private void imprimeTotalEstudantesPorCurso() {
        final Map<Course, Long> totalEstudantesPorCurso =
                students.stream()
                        .filter(s -> s.getCourse() != null)
                        .collect(groupingBy(Student::getCourse, Collectors.counting()));
        System.out.println("\nTotal de Estudantes por Curso");
        totalEstudantesPorCurso.forEach(
            (course, totalAlunos) -> System.out.printf("\tCurso: %-30s | Total de Estudantes: %d\n", course.getName(), totalAlunos)
        );
    }

    private void imprimeMediaNotasAgrupandoPorSexo() {
        Map<Character, Double> mapMediaPorSexo =
                students.stream()
                        .collect(groupingBy(Student::getGender, averagingDouble(Student::getScore)));
        mapMediaPorSexo.forEach((sexo, media) -> System.out.printf("%c: %.2f%n", sexo, media));
    }

    private void imprimeEstudantesAprovadosPalmas() {
        System.out.println();
        final String nomesEstudantesAprovados =
                students.stream()
                        .filter(s -> s.getScore() > 9)
                        .filter(Student::hasCourse)
                        .filter(s -> s.getCourse().getCampus().getId() == 1)
                        .map(Student::getName)
                        .collect(Collectors.joining(", "));
        System.out.println("Nomes dos Estudantes Aprovados em Palmas com nota maior que 9: " + nomesEstudantesAprovados);
    }

    private void imprimeEstudantesAprovadosTSI() {
        final List<Student> alunosSistemasInternet =
                students.stream()
                        .filter(Student::hasCourse)
                        .filter(s -> s.getCourse().getId() == 1)
                        .filter(s -> s.getScore() >= 6)
                        .toList();
        System.out.println("\nEstudantes Aprovados em Tecnologia em Sistemas para Internet (TSI):");
        alunosSistemasInternet.forEach(s -> System.out.println("\t" + s));
        System.out.println("Total de Estudantes Aprovados em Sistemas para Internet: " + alunosSistemasInternet.size());
    }

    private void imprimeEstudantesAgrupandoPorCurso() {
        final Map<Course, List<Student>> estudantesAgrupadosPorCurso =
                students.stream()
                        .filter(Student::hasCourse)
                        .collect(groupingBy(Student::getCourse));
        System.out.println("\nEstudantes por curso");
        estudantesAgrupadosPorCurso.forEach(
                (course, students) -> System.out.printf("\t%-30s: Estudantes: %s\n", course.getName(), students)
        );
    }

    private void imprimeTotalCampiComAlunos() {
        final long totalCampiComAlunos =
                students.stream()
                        .map(Student::getCourse)
                        .filter(Objects::nonNull)
                        .map(Course::getCampus)
                        .distinct()
                        .count();
        System.out.printf("Campi em que há Estudantes: %d\n", totalCampiComAlunos);
    }

    public static void main(String[] args) {
        new AppProgramacaoFuncional();
    }
}

