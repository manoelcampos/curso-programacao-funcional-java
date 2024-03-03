import java.util.Comparator;

/**
 * Representa um curso de uma instituição de ensino.
 * Fazer a classe implementar {@Comparable} é opcional.
 * Isto permite definir uma ordenação padrão quando
 * for preciso ordenar uma coleção de cursos,
 * por exemplo usando {@link java.util.List#sort(Comparator)}
 * ou {@link java.util.stream.Stream#sorted(Comparator)}.
 *
 * @author Manoel Campos da Silva Filho
 */
public class Curso implements Comparable<Curso> {
    private int id;
    private String nome;
    private int semestres;
    private Campus campus;

    public Curso(){
    }

    public Curso(int id, String nome, int semestres, Campus campus){
        setId(id);
        setNome(nome);
        setSemestres(semestres);
        setCampus(campus);
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

    public int getSemestres() {
        return semestres;
    }

    public final void setSemestres(int semestres) {
        this.semestres = semestres;
    }

    public Campus getCampus() {
        return campus;
    }

    private void setCampus(Campus campus) {
        this.campus = campus;
    }

    @Override
    public String toString(){
        return String.format("Id: %6d Nome: %-30s Semestres: %4d", id, nome, semestres);
    }

    @Override
    public int compareTo(final Curso other) {
        //Se other é nulo, retorna um valor != de zero para indicar que não são iguais
        if(other == null)
            return 1;

        return this.nome.compareTo(other.nome);
    }
}
