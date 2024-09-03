package exame;

/**
 * Representa um curso de uma instituição de ensino.
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
    public int compareTo(Curso that) {
        return Long.compare(this.id, that.id);
    }
}
