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
public class Course implements Comparable<Course> {
    private int id;
    private String name;
    private int semesters;
    private Campus campus;

    public Course(){
    }

    public Course(int id, String name, int semesters, Campus campus){
        setId(id);
        setName(name);
        setSemesters(semesters);
        setCampus(campus);
    }

    public int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public int getSemesters() {
        return semesters;
    }

    public final void setSemesters(int semesters) {
        this.semesters = semesters;
    }

    public Campus getCampus() {
        return campus;
    }

    private void setCampus(Campus campus) {
        this.campus = campus;
    }

    @Override
    public String toString(){
        return String.format("Id: %6d Nome: %-30s Semestres: %4d", id, name, semesters);
    }

    @Override
    public int compareTo(final Course other) {
        //Se other é nulo, retorna um valor != de zero para indicar que não são iguais
        if(other == null)
            return 1;

        return this.name.compareTo(other.name);
    }
}
