import java.util.List;
import java.util.function.Predicate;;

/**
 * Aplicação de exemplo de princípios de programação funcional em Java,
 * Expressões Lambda e API de Streams do Java 8.
 *
 * Para aprofundar nestes assuntos, veja os links abaixo:
 *
 * <ul>
 * <li><a href=
 * "https://www.oracle.com/technetwork/pt/articles/java/streams-api-java-8-3410098-ptb.html">Curso
 * Streams e Expressões Lambda do Java 8</a></li>
 * <li><a href= "http://bit.ly/2I2U5bU">Curso JDK 8 MOOC: Lambdas and Streams
 * Introduction</a></li>
 * </ul>
 *
 * @author Manoel Campos da Silva Filho
 */
public class AppProgramacaoFuncional {
    private static final int TOTAL_STUDENTS = 1000;
    private final List<Student> students;

    public AppProgramacaoFuncional(){
        students = StudentGenerator.generate(TOTAL_STUDENTS);
    }

    public static void main(String[] args) {
        AppProgramacaoFuncional app = new AppProgramacaoFuncional();
        app.printStudents();
        System.out.println("Maior nota dos alunos de 2011: " + app.maxScore());
        System.out.println("Média de notas dos alunos de 2011: " + app.averageScore());
    }

    /**
     * Imprime a lista original de estudantes gerados aleatoriamente.
     * Veja que a classe {@link Student} possui um método toString()
     * que devemo qual será o conteúdo apresentado quando 
     * mandarmos imprimir um objeto Student.
     */
    private void printStudents(){
        System.out.println("Lista de todos os " + TOTAL_STUDENTS + " gerados aleatoriamente.");
        students.forEach(student -> System.out.println(student));
        System.out.println();
    }

    /**
     * Obtém a maior nota dos estudantes de 2011.
     */
    private double maxScore(){
        return students.stream()
                .filter(student -> filterStudents(student))
                .mapToDouble(student -> student.getScore())
                .max()
                .orElse(0.0);
    }

    /**
     * Obtém a média de notas dos estudantes de 2011.
     */
    private double averageScore(){
        return students.stream()
                .filter(student -> filterStudents(student))
                .mapToDouble(student -> student.getScore())
                .average()
                .orElse(0.0);
    }

    /**
     * Aplica um filtro sobre um estudante.
     * Assim, ao filtrar uma lista de estudantes,
     * se um estudante não atender às condições definidas neste método
     * de filtragem, que chamamos de "predicado" ({@link Predicate}),
     * o estudante não será incluído nos resultados.
     * 
     * Neste exemplo, o filtro define que desejamos apenas estudantes
     * que finalizaram o curso em 2011. Se o estudante não atender a este critério,
     * não será selecionado ao aplicarmos tal filtro.
     * 
     * @return true se o estudante atendeu às condições do filtro, false
     *         caso não atenda
     * @see #maxScore()
     * @see #averageScore()
     */
    private boolean filterStudents(Student student) {
        return student.getGradYear() == 2011;
    }
}

