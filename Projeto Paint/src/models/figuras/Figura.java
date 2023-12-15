package models.figuras;

import java.awt.*;

public abstract class Figura {
    protected Color cor;
    protected Color fill;

    protected Figura() {
        this(Color.BLACK, Color.WHITE);
    }

    protected Figura(Color cor, Color fill) {
        this.cor = cor;
        this.fill = fill;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public Color getCor() {
        return this.cor;
    }

    public void setFill(Color fill) {
        this.fill = fill;
    }

    public Color getFill() {
        return this.fill;
    }

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public abstract Object clone();

    public abstract String toString();

    public abstract void ficarVisivel(Graphics g);
}