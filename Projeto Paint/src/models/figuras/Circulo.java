package models.figuras;

import java.awt.*;
import java.util.*;

public class Circulo extends Figura {
    protected Ponto centro;
    protected int raio;

    public Circulo(int x, int y, int r) {
        this(x, y, r, Color.BLACK, Color.WHITE);
    }

    public Circulo(int x, int y, int r, Color cor, Color fill) {
        super(cor, fill);

        this.centro = new Ponto(x, y);
        if (r < 0) r = -r;
        this.raio = r;
    }

    public Circulo(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");

        quebrador.nextToken();

        int x = Integer.parseInt(quebrador.nextToken());
        int y = Integer.parseInt(quebrador.nextToken());

        int r = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color(Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())); // B

        Color fill = new Color(Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())); // B

        this.centro = new Ponto(x, y, cor, fill);
        this.raio = r;
        this.cor = cor;
        this.fill = fill;
    }

    public void setCentro(int x, int y) {
        this.centro = new Ponto(x, y, this.getCor(), this.getFill());
    }

    public void setRaio(int r) {
        this.raio = r;
    }

    public Ponto getCentro() {
        return this.centro;
    }

    public int setRaio() {
        return this.raio;
    }

    public void ficarVisivel(Graphics g) {
        g.setColor(this.cor);
        g.drawOval(this.centro.getX() - raio, this.centro.getY() - raio, 2 * raio, 2 * raio);
        g.setColor(this.fill);
        g.fillOval(this.centro.getX() - raio, this.centro.getY() - raio, 2 * raio, 2 * raio);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        Circulo c = (Circulo) obj;

        if (c.centro != this.centro) return false;
        if (c.raio != this.raio) return false;

        if (c.cor.equals(this.cor)) return false;
        if (c.fill.equals(this.fill)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int ret = 1;

        ret = 7*ret + Integer.valueOf(this.raio).hashCode();
        ret = 7*ret + this.centro.hashCode();

        ret = 7*ret + this.cor.hashCode();
        ret = 7*ret + this.fill.hashCode();

        if (ret < 0) ret = -ret;

        return ret;
    }

    private Circulo(Circulo c) throws Exception {
        if (c == null) throw new Exception("Modelo nulo");

        this.raio = c.raio;
        this.centro = (Ponto) c.centro.clone();

        this.cor = c.cor;
        this.fill = c.fill;
    }

    @Override
    public Object clone() {
        Circulo ret = null;

        try {
            ret = new Circulo(this);
        } catch (Exception e) {}

        return ret;
    }

    public String toString() {
        return "c:" +
                this.centro.getX() +
                ":" +
                this.centro.getY() +
                ":" +
                this.raio +
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