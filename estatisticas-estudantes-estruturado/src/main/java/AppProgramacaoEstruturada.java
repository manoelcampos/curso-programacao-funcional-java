import java.util.*;

/**
 * Uma versão estruturada do aplicativo de estatísticas
 * de estudantes, para compararação com a versão funcional.
 *
 * @author Manoel Campos da Silva Filho
 */
public class AppProgramacaoEstruturada {
    private static final int TOTAL_STUDENTS = 1000;
    private final List<Student> students;

    public AppProgramacaoEstruturada(){
        students = StudentGenerator.generate(TOTAL_STUDENTS);

        imprimeMaiorNota();
        imprimeTotalHomens();
        imprimeTotalMulheres();
        imprimeTotalCampiComAlunos();
        imprimeTotalEstudantesMatriculados();
        imprimeEstudantesAprovadosTSI();
        imprimeEstudantesPorCurso();
        imprimeEstudantesAprovadosPalmas();
        imprimeTotalEstudantesPorCurso();
        imprimeEstudantesPorSexo();
    }

    private void imprimeEstudantesPorSexo() {
        final Map<Character, List<Student>> estudantesPorSexo = new HashMap<>();
        for (Student student : students) {
            estudantesPorSexo.computeIfAbsent(student.getGender(), k -> new ArrayList<>()).add(student);
        }

        System.out.println("\nEstudantes por sexo");
        estudantesPorSexo.forEach(
            (gender, students) -> System.out.printf("\tSexo: %c | Estudantes: %s\n", gender, students)
        );
    }

    private void imprimeTotalEstudantesPorCurso() {
        final Map<Course, Long> totalEstudantesPorCurso = new HashMap<>();
        for (Student s : students) {
            if (s.getCourse() != null) {
                totalEstudantesPorCurso.merge(s.getCourse(), 1L, Long::sum);
            }
        }

        System.out.println("\nTotal de Estudantes por Curso");
        totalEstudantesPorCurso.forEach(
            (course, totalAlunos) -> {
                System.out.printf("\tCurso: %-30s | Total de Estudantes: %d\n", course.getName(), totalAlunos);
            }
        );
    }

    private void imprimeEstudantesAprovadosPalmas() {
        System.out.println();
        StringJoiner joiner = new StringJoiner(", ");
        for (Student s : students) {
            if (s.getScore() > 9) {
                if (s.getCourse() != null) {
                    if (s.getCourse().getCampus().getId() == 1) {
                        String name = s.getName();
                        joiner.add(name);
                    }
                }
            }
        }

        final String nomesEstudantesAprovados = joiner.toString();
        System.out.println("Nomes dos Estudantes Aprovados em Palmas com nota maior que 9: " + nomesEstudantesAprovados);
    }

    private void imprimeEstudantesPorCurso() {
        final Map<Course, List<Student>> estudantesAgrupadosPorCurso = new HashMap<>();
        for (Student s : students) {
            if (s.getCourse() != null) {
                estudantesAgrupadosPorCurso.computeIfAbsent(s.getCourse(), k -> new ArrayList<>()).add(s);
            }
        }

        System.out.println("\nEstudantes por curso");
        estudantesAgrupadosPorCurso.forEach(
                (course, students) -> System.out.printf("\tCurso: %-30s | Estudantes: %s\n", course.getName(), students)
        );
    }

    private void imprimeEstudantesAprovadosTSI() {
        final List<Student> alunosSistemasInternet = new ArrayList<>();
        for (Student student : students) {
            if (student.getCourse() != null) {
                if (student.getCourse().getId() == 1) {
                    if (student.getScore() >= 6) {
                        alunosSistemasInternet.add(student);
                    }
                }
            }
        }

        System.out.println("\nEstudantes Aprovados em Tecnologia em Sistemas para Internet (TSI):");
        alunosSistemasInternet.forEach(s -> System.out.println("\t" + s));
        System.out.println("Total de Estudantes Aprovados em Sistemas para Internet: " + alunosSistemasInternet.size());
    }

    private void imprimeTotalEstudantesMatriculados() {
        long totalEstudantesMatriculados = 0;
        for (Student student : students) {
            Course course = student.getCourse();
            if (course != null) {
                totalEstudantesMatriculados++;
            }
        }
        System.out.printf("Total de Estudantes Matriculados: %d\n", totalEstudantesMatriculados);
    }

    private void imprimeTotalCampiComAlunos() {
        long totalCampiComAlunos = 0;
        Set<Campus> campiSet = new HashSet<>();
        for (Student student : students) {
            Course course = student.getCourse();
            if (course != null) {
                Campus campus = course.getCampus();
                if (campiSet.add(campus)) {
                    totalCampiComAlunos++;
                }
            }
        }
        System.out.printf("Campi em que há Estudantes: %d\n", totalCampiComAlunos);
    }

    private void imprimeTotalMulheres() {
        long totalMulheres = 0;
        for (Student s : students) {
            if (s.getGender() == 'F') {
                totalMulheres++;
            }
        }
        System.out.println("Total de Mulheres: " + totalMulheres);
    }

    private void imprimeTotalHomens() {
        long totalHomens = 0;
        for (Student s : students) {
            if (s.getGender() == 'M') {
                totalHomens++;
            }
        }
        System.out.println("Total de Homens: " + totalHomens);
    }

    private void imprimeMaiorNota() {
        double maiorNota = 0;
        for (Student student : students) {
            if (student.getScore() > maiorNota) {
                maiorNota = student.getScore();
            }
        }
        System.out.printf("Maior nota entre todos os Estudantes: %.2f\n", maiorNota);
    }

    public static void main(String[] args) {
        new AppProgramacaoEstruturada();
    }
}
