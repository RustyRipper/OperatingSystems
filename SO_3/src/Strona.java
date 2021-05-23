import java.util.Objects;

public class Strona {
    public int nr;

    public Strona(int nr) {
        this.nr=nr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Strona)) return false;
        Strona strona = (Strona) o;
        return nr == strona.nr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr);
    }
}
