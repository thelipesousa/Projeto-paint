package models.figuras;

import java.awt.*;
import java.util.*;

public class Elipse extends Figura {
    protected Ponto centro;

    protected int raio1, raio2;

    public Elipse(int x, int y, int r1, int r2) {
        this(x, y, r1, r2, Color.BLACK, Color.WHITE);
    }

    public Elipse(int x, int y, int r1, int r2, Color cor, Color fill) {
        super(cor, fill);

        this.centro = new Ponto(x, y);

        if (r1 < 0) r1 = -r1;
        this.raio1 = r1;
        if (r2 < 0) r2 = -r2;
        this.raio2 = r2;
    }

    public Elipse(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");

        quebrador.nextToken();

        int x = Integer.parseInt(quebrador.nextToken());
        int y = Integer.parseInt(quebrador.nextToken());

        int r1 = Integer.parseInt(quebrador.nextToken());
        int r2 = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color(Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())); // B

        Color fill = new Color(Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())); // B

        this.centro = new Ponto(x, y, cor, fill);
        this.raio1 = r1;
        this.raio2 = r2;
        this.cor = cor;
        this.fill = fill;
    }

    public void setCentro(int x, int y) {
        this.centro = new Ponto(x, y, this.getCor(), this.getFill());
    }

    public void setRaio1(int r1) {
        this.raio1 = r1;
    }

    public void setRaio2(int r2) {
        this.raio2 = r2;
    }

    public Ponto getCentro() {
        return this.centro;
    }

    public int setRaio1() {
        return this.raio1;
    }

    public int setRaio2() {
        return this.raio2;
    }

    public void ficarVisivel(Graphics g) {
        g.setColor(this.cor);
        g.drawOval(this.centro.getX() - raio1, this.centro.getY() - raio2, 2 * raio1, 2 * raio2);
        g.setColor(this.fill);
        g.fillOval(this.centro.getX() - raio1, this.centro.getY() - raio2, 2 * raio1, 2 * raio2);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        Elipse e = (Elipse) obj;

        if (e.centro != this.centro) return false;
        if (e.raio1 != this.raio1) return false;
        if (e.raio2 != this.raio2) return false;

        if (e.cor.equals(this.cor)) return false;
        if (e.fill.equals(this.fill)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int ret = 1;

        ret = 7*ret + Integer.valueOf(this.raio1).hashCode();
        ret = 7*ret + Integer.valueOf(this.raio2).hashCode();
        ret = 7*ret + this.centro.hashCode();

        ret = 7*ret + this.cor.hashCode();
        ret = 7*ret + this.fill.hashCode();

        if (ret < 0) ret = -ret;

        return ret;
    }

    private Elipse(Elipse e) throws Exception {
        if (e == null) throw new Exception("Modelo nulo");

        this.raio1 = e.raio1;
        this.raio2 = e.raio2;
        this.centro = (Ponto) e.centro.clone();

        this.cor = e.cor;
        this.fill = e.fill;
    }

    @Override
    public Object clone() {
        Elipse ret = null;

        try {
            ret = new Elipse(this);
        } catch (Exception e) {}

        return ret;
    }

    public String toString() {
        return "e:" +
                this.centro.getX() +
                ":" +
                this.centro.getY() +
                ":" +
                this.raio1 +
                ":" +
                this.raio2 +
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