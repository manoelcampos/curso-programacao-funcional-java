import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


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

        final double maiorNota =
                students.stream()
                        .mapToDouble(Student::getScore)
                        .max()
                        .orElse(0);
        System.out.printf("Maior nota entre todos os Estudantes: %.2f\n", maiorNota);

        final long totalHomens =
            students.stream()
                    .filter(s -> s.getGender() == 'M')
                    .count();
        System.out.println("Total de Homens: " + totalHomens);

        final long totalMulheres =
                students.stream()
                        .filter(s -> s.getGender() == 'F')
                        .count();
        System.out.println("Total de Mulheres: " + totalMulheres);

        final long totalCampiComAlunos =
                students.stream()
                        .map(Student::getCourse)
                        .filter(Objects::nonNull)
                        .map(Course::getCampus)
                        .distinct()
                        .count();
        System.out.printf("Campi em que há Estudantes: %d\n", totalCampiComAlunos);

        final long totalEstudantesMatriculados =
                students.stream()
                        .map(Student::getCourse)
                        .filter(Objects::nonNull)
                        .count();
        System.out.printf("Total de Estudantes Matriculados: %d\n", totalEstudantesMatriculados);

        final List<Student> alunosSistemasInternet =
                students.stream()
                        .filter(s -> s.getCourse() != null)
                        .filter(s -> s.getCourse().getId() == 1)
                        .filter(s -> s.getScore() >= 6)
                        .collect(Collectors.toList());
        System.out.println("\nEstudantes Aprovados em Sistemas para Internet:");
        alunosSistemasInternet.forEach(s -> System.out.println("\t" + s));
        System.out.println("Total de Estudantes Aprovados em Sistemas para Internet: " + alunosSistemasInternet.size());

        final Map<Course, List<Student>> estudantesAgrupadosPorCurso =
                students.stream()
                        .filter(s -> s.getCourse() != null)
                        .collect(Collectors.groupingBy(Student::getCourse));
        System.out.println("\nEstudantes por curso");
        estudantesAgrupadosPorCurso.forEach((course, students) -> System.out.printf("\tCurso: %-30s | Estudantes: %s\n", course.getName(), students));

        System.out.println();
        final String nomesEstudantesAprovados =
                students.stream()
                        .filter(s -> s.getScore() > 9)
                        .filter(s -> s.getCourse() != null)
                        .filter(s -> s.getCourse().getCampus().getId() == 1)
                        .map(Student::getName)
                        .collect(Collectors.joining(", "));
        System.out.println("Nomes dos Estudantes Aprovados em Palmas com nota maior que 9: " + nomesEstudantesAprovados);

        final Map<Course, Long> totalEstudantesPorCurso =
                students.stream()
                        .filter(s -> s.getCourse() != null)
                        .collect(Collectors.groupingBy(Student::getCourse, Collectors.counting()));
        System.out.println("\nTotal de Estudantes por Curso");
        totalEstudantesPorCurso.forEach((course, totalAlunos) -> System.out.printf("\tCurso: %-30s | Total de Estudantes: %d\n", course.getName(), totalAlunos));

        final Map<Character, List<Student>> estudantesPorSexo =
                students.stream()
                        .collect(Collectors.groupingBy(Student::getGender));
        System.out.println("\nEstudantes por sexo");
        estudantesPorSexo.forEach((gender, students) -> System.out.printf("\tSexo: %c | Estudantes: %s\n", gender, students));
    }

    public static void main(String[] args) {
        new AppProgramacaoFuncional();
    }

}

