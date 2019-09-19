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
        System.out.printf(
                "Total de estudantes: %d Campi em que há alunos: %d\n",
                students.size(),
                students.stream().map(Student::getCourse).filter(Objects::nonNull).map(Course::getCampus).distinct().count());;

        System.out.printf(
                "Total de estudantes: %d Matriculados: %d\n",
                students.size(),
                students.stream().map(Student::getCourse).filter(Objects::nonNull).count());;

        List<Student> alunosSistemasInternet = students.stream().filter(s -> s.getCourse() != null).filter(s -> s.getCourse().getId() == 1).collect(Collectors.toList());
        System.out.println("\nAlunos de Sistemas para Internet:");
        alunosSistemasInternet.forEach(s -> System.out.println("\t" + s));
        System.out.println("Total: " + alunosSistemasInternet.size());

        System.out.println("\nEstudantes por curso");
        Map<Course, List<Student>> groups1 = students.stream().filter(s -> s.getCourse() != null).collect(Collectors.groupingBy(Student::getCourse));
        groups1.forEach((course, students) -> System.out.printf("Curso: %s Estudantes: %s\n", course.getName(), students.size()));

        System.out.println();
        String names = students.stream().map(Student::getName).collect(Collectors.joining(", "));
        System.out.println("Estudantes: " + names);

        Map<Course, Long> groups2 = students.stream().filter(s -> s.getCourse() != null).collect(Collectors.groupingBy(Student::getCourse, Collectors.counting()));
    }

    public static void main(String[] args) {
        new AppProgramacaoFuncional();
    }

}

