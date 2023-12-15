package models.figuras;

import models.figuras.Figura;

import java.awt.*;
import java.util.*;

public class Ponto extends Figura {
    protected int x, y;

    public Ponto(int x, int y) {
        this(x, y, Color.BLACK, Color.WHITE);
    }

    public Ponto(int x, int y, Color cor, Color fill) {
        super(cor, fill);

        this.x = x;
        this.y = y;
    }

    public Ponto(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");

        quebrador.nextToken();

        this.x = Integer.parseInt(quebrador.nextToken());
        this.y = Integer.parseInt(quebrador.nextToken());

        this.cor = new Color(Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())); // B

        this.fill = new Color(Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())); // B
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void ficarVisivel(Graphics g) {
        g.setColor(this.cor);
        g.drawLine(this.x, this.y, this.x, this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        Ponto p = (Ponto) obj;

        if (p.x != this.x) return false;
        if (p.y != this.y) return false;

        if (p.cor.equals(this.cor)) return false;
        if (p.fill.equals(this.fill)) return false;

        return true;
    }

    @Override
    public int hashCode() { //    Retorna um inteiro representando o objeto.
        int ret = 1;

        ret = 7*ret + Integer.valueOf(this.x).hashCode();
        ret = 7*ret + Integer.valueOf(this.y).hashCode();

        ret = 7*ret + this.cor.hashCode();
        ret = 7*ret + this.fill.hashCode();

        if (ret < 0) ret = -ret;

        return ret;
    }

    private Ponto(Ponto p) throws Exception {
        if (p == null) throw new Exception("Modelo nulo");

        this.x = p.x;
        this.y = p.y;

        this.cor = p.cor;
        this.fill = p.fill;
    }

    @Override
    public Object clone() { //    Retorna um clone do objeto.
        Ponto ret = null;

        try {
            ret = new Ponto(this);
        } catch (Exception e) {}

        return ret;
    }

    public String toString() {
        return "p:" +
                this.x +
                ":" +
                this.y +
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