/**
 * Representa um aluno em uma instituição de ensino.
 * Classe baseada no exemplo do <a href="https://apexapps.oracle.com/pls/apex/f?p=44785:145:0::::P145_EVENT_ID,P145_PREV_PAGE:4887,143">Curso JDK 8 MOOC: Lambdas and Streams Introduction</a>.
 *
 * @author Manoel Campos da Silva Filho
 */
public class Estudante {
    private int id;
    private String nome;
    private char sexo;
    private double nota;
    private int anoGraduacao;
    private Curso curso;

    public Estudante(){
    }

    /**
     * Cria um estudante.
     * Este construtor possui uma quantidade mais do que recomendável de parâmetros (3).
     * O mesmo é usado apenas para facilitar a geração de dados aleatórios,
     * mas é uma péssima prática em projetos reais.
     * No entanto, observe que o construtor não é público (é package),
     * não podendo ser usado fora do pacote.
     *
     * @param id matrícula do estudante
     * @param nome nome do estudante
     * @param sexo sexo do estudante
     * @param nota nota do estudante
     * @param anoGraduacao ano de graduação
     * @param curso curso matriculado
     */
    Estudante(int id, String nome, char sexo, double nota, int anoGraduacao, Curso curso){
        setId(id);
        setNome(nome);
        setSexo(sexo);
        setNota(nota);
        setAnoGraduacao(anoGraduacao);
        setCourse(curso);
    }

    public int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public final void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public final void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public double getNota() {
        return nota;
    }

    public final void setNota(double nota) {
        this.nota = nota;
    }

    public int getAnoGraduacao() {
        return anoGraduacao;
    }

    public final void setAnoGraduacao(int anoGraduacao) {
        this.anoGraduacao = anoGraduacao;
    }

    public Curso getCourse() {
        return curso;
    }

    public final void setCourse(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString(){
        final String courseName = String.format("%-35s", curso == null ? "" : "Curso: " + curso.getNome());
        return String.format(
                "Id: %6d Nome: %-30s Sexo: %c Nota: %5.2f Ano Grad: %4d %s",
                id, nome, sexo, nota, anoGraduacao, courseName);
    }
}
