import java.util.*;

/**
 * Uma versão estruturada do aplicativo de estatísticas
 * de estudantes, para compararação com a versão funcional.
 *
 * @author Manoel Campos da Silva Filho
 */
public class AppProgramacaoEstruturada {
    private static final int TOTAL_STUDENTS = 1000;
    private final List<Estudante> estudantes;

    public AppProgramacaoEstruturada(){
        estudantes = StudentGenerator.generate(TOTAL_STUDENTS);

        imprimeMaiorNota();
        imprimeMaiorHomens();
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
        final Map<Character, List<Estudante>> estudantesPorSexo = new HashMap<>();
        for (Estudante estudante : estudantes) {
            estudantesPorSexo.computeIfAbsent(estudante.getSexo(), k -> new ArrayList<>()).add(estudante);
        }

        System.out.println("\nEstudantes por sexo");
        estudantesPorSexo.forEach(
            (gender, students) -> System.out.printf("\tSexo: %c | Estudantes: %s\n", gender, students)
        );
    }

    private void imprimeTotalEstudantesPorCurso() {
        final Map<Curso, Long> totalEstudantesPorCurso = new HashMap<>();
        for (Estudante s : estudantes) {
            if (s.getCourse() != null) {
                totalEstudantesPorCurso.merge(s.getCourse(), 1L, Long::sum);
            }
        }

        System.out.println("\nTotal de Estudantes por Curso");
        totalEstudantesPorCurso.forEach(
            (curso, totalAlunos) -> {
                System.out.printf("\tCurso: %-30s | Total de Estudantes: %d\n", curso.getNome(), totalAlunos);
            }
        );
    }

    private void imprimeEstudantesAprovadosPalmas() {
        System.out.println();
        StringJoiner joiner = new StringJoiner(", ");
        for (Estudante s : estudantes) {
            if (s.getNota() > 9) {
                if (s.getCourse() != null) {
                    if (s.getCourse().getCampus().getId() == 1) {
                        String name = s.getNome();
                        joiner.add(name);
                    }
                }
            }
        }

        final String nomesEstudantesAprovados = joiner.toString();
        System.out.println("Nomes dos Estudantes Aprovados em Palmas com nota maior que 9: " + nomesEstudantesAprovados);
    }

    private void imprimeEstudantesPorCurso() {
        final Map<Curso, List<Estudante>> estudantesAgrupadosPorCurso = new HashMap<>();
        for (Estudante s : estudantes) {
            if (s.getCourse() != null) {
                estudantesAgrupadosPorCurso.computeIfAbsent(s.getCourse(), k -> new ArrayList<>()).add(s);
            }
        }

        System.out.println("\nEstudantes por curso");
        estudantesAgrupadosPorCurso.forEach(
                (curso, students) -> System.out.printf("\t%-30s | Estudantes: %s\n", curso.getNome(), students)
        );
    }

    private void imprimeEstudantesAprovadosTSI() {
        final List<Estudante> alunosSistemasInternet = new ArrayList<>();
        for (Estudante estudante : estudantes) {
            if (estudante.getCourse() != null) {
                if (estudante.getCourse().getId() == 1) {
                    if (estudante.getNota() >= 6) {
                        alunosSistemasInternet.add(estudante);
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
        for (Estudante estudante : estudantes) {
            Curso curso = estudante.getCourse();
            if (curso != null) {
                totalEstudantesMatriculados++;
            }
        }
        System.out.printf("Total de Estudantes Matriculados: %d\n", totalEstudantesMatriculados);
    }

    private void imprimeTotalCampiComAlunos() {
        long totalCampiComAlunos = 0;
        Set<Campus> campiSet = new HashSet<>();
        for (Estudante estudante : estudantes) {
            Curso curso = estudante.getCourse();
            if (curso != null) {
                Campus campus = curso.getCampus();
                if (campiSet.add(campus)) {
                    totalCampiComAlunos++;
                }
            }
        }
        System.out.printf("Campi em que há Estudantes: %d\n", totalCampiComAlunos);
    }

    private void imprimeTotalMulheres() {
        long totalMulheres = 0;
        for (Estudante s : estudantes) {
            if (s.getSexo() == 'F') {
                totalMulheres++;
            }
        }
        System.out.println("Total de Mulheres: " + totalMulheres);
    }

    private void imprimeTotalHomens() {
        long totalHomens = 0;
        for (Estudante s : estudantes) {
            if (s.getSexo() == 'M') {
                totalHomens++;
            }
        }
        System.out.println("Total de Homens: " + totalHomens);
    }

    private void imprimeMaiorNota() {
        double maiorNota = 0;
        for (Estudante estudante : estudantes) {
            if (estudante.getNota() > maiorNota) {
                maiorNota = estudante.getNota();
            }
        }
        System.out.printf("Maior nota entre todos os Estudantes: %.2f\n", maiorNota);
    }

    private void imprimeMaiorHomens() {
        double maiorNota = 0;
        for (Estudante estudante : estudantes) {
            if (estudante.getSexo() == 'M' && estudante.getNota() > maiorNota) {
                maiorNota = estudante.getNota();
            }
        }
        System.out.printf("Maior nota entre homens: %.2f\n", maiorNota);
    }

    public static void main(String[] args) {
        new AppProgramacaoEstruturada();
    }
}
