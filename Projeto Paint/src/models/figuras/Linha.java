package models.figuras;

import java.awt.*;
import java.util.*;

public class Linha extends Figura { //    Representa uma linha.
    protected Ponto p1, p2;

    public Linha(int x1, int y1, int x2, int y2) {
        this(x1, y1, x2, y2, Color.BLACK, Color.WHITE);
    }

    public Linha(int x1, int y1, int x2, int y2, Color cor, Color fill) {
        super(cor, fill);

        this.p1 = new Ponto(x1, y1, cor, fill);
        this.p2 = new Ponto(x2, y2, cor, fill);
    }

    public Linha(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":"); // quebrando a string recebida

        quebrador.nextToken(); // pula o tipo

        int x1 = Integer.parseInt(quebrador.nextToken()); // pegando o valor do x1
        int y1 = Integer.parseInt(quebrador.nextToken());// pegando o valor do y1

        int x2 = Integer.parseInt(quebrador.nextToken());
        int y2 = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color(Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())); // B

        Color fill = new Color(Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())); // B

        this.p1 = new Ponto(x1, y1, cor, fill);
        this.p2 = new Ponto(x2, y2, cor, fill);
        this.cor = cor;
        this.fill = fill;
    }

    public void setP1(int x, int y) {
        this.p1 = new Ponto(x, y, this.getCor(), this.getFill());
    }

    public void setP2(int x, int y) {
        this.p2 = new Ponto(x, y, this.getCor(), this.getFill());
    }

    public Ponto getP1() {
        return this.p1;
    }

    public Ponto getP2() {
        return this.p2;
    }

    public void ficarVisivel(Graphics g) {
        g.setColor(this.cor);
        g.drawLine(this.p1.getX(), this.p1.getY(),   // ponto inicial
                this.p2.getX(), this.p2.getY());  // ponto final
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        Linha l = (Linha) obj;

        if (!l.p1.equals(this.p1)) return false;
        if (!l.p2.equals(this.p2)) return false;

        if (l.cor.equals(this.cor)) return false;
        if (l.fill.equals(this.fill)) return false;

        return true;
    }

    @Override
    public int hashCode() { //    Retorna um inteiro representando o objeto.
        int ret = 1;

        ret = 7*ret + this.p1.hashCode();
        ret = 7*ret + this.p2.hashCode();

        ret = 7*ret + this.cor.hashCode();
        ret = 7*ret + this.fill.hashCode();

        if (ret < 0) ret = -ret;

        return ret;
    }

    private Linha(Linha l) throws Exception {
        if (l == null) throw new Exception("Modelo nulo");

        this.p1 = (Ponto) l.p1.clone();
        this.p2 = (Ponto) l.p2.clone();

        this.cor = l.cor;
        this.fill = l.fill;
    }

    @Override
    public Object clone() { //    Retorna um clone do objeto.
        Linha ret = null;

        try {
            ret = new Linha(this);
        } catch (Exception e) {}

        return ret;
    }

    public String toString() {
        return "l:" +
                this.p1.getX() +
                ":" +
                this.p1.getY() +
                ":" +
                this.p2.getX() +
                ":" +
                this.p2.getY() +
                ":" +
                this.getCor().getRed() +
                ":" +
                this.getCor().getGreen() +
                ":" +
                this.getCor().getBlue() +
                ":" +
                this.getFill().getRed() +
                ":" +
                this.getFill().getGreen() +
                ":" +
                this.getFill().getBlue();
    }
}