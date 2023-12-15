package models.figuras;

import java.awt.*;
import java.util.StringTokenizer;

public class Texto extends Figura {
    protected Ponto p;
    protected String texto;
    protected Font fonte;

    public Texto(int x, int y, String texto, Font fonte) {
        this(x, y, texto, fonte, Color.BLACK, Color.WHITE);
    }

    public Texto(int x, int y, String texto, Font fonte, Color cor, Color fill) {
        super(cor, fill);

        this.p = new Ponto(x, y);

        this.texto = texto;

        this.fonte = fonte;
    }

    public Texto(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");

        quebrador.nextToken();

        int x = Integer.parseInt(quebrador.nextToken());
        int y = Integer.parseInt(quebrador.nextToken());

        String texto = String.valueOf(quebrador.nextToken());

        Font fonte = new Font(String.valueOf(quebrador.nextToken()), // Família fonte
                Integer.parseInt(quebrador.nextToken()),  // Estilo
                Integer.parseInt(quebrador.nextToken())); // Tamanho


        Color cor = new Color(Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())); // B

        Color fill = new Color(Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())); // B

        this.p = new Ponto(x, y, cor, fill);
        this.texto = texto;
        this.fonte = fonte;
        this.cor = cor;
        this.fill = fill;
    }

    public void ficarVisivel(Graphics g) { //    Método que desenha o texto na posição usando a cor e a fonte configuradas.
        g.setColor(this.cor);
        g.setFont(this.fonte);
        g.drawString(this.texto, this.p.getX(), this.p.getY());
    }

    @Override
    public boolean equals(Object obj) { //    Método que compara se dois textos são iguais.
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        Texto t = (Texto) obj;

        if (!t.fonte.equals(this.fonte)) return false;
        if (!t.p.equals(this.p)) return false;
        if (!t.texto.equals(this.texto)) return false;

        if (t.cor.equals(this.cor)) return false;
        if (t.fill.equals(this.fill)) return false;

        return true;
    }

    @Override
    public int hashCode() { //    Método que gera um código hash para o texto.
        int ret = 1;

        ret = 7*ret + this.fonte.hashCode(); //Usa o hashcode da fonte para gerar o hashcode do texto.
        ret = 7*ret + this.p.hashCode();
        ret = 7*ret + this.texto.hashCode();

        ret = 7*ret + this.cor.hashCode();
        ret = 7*ret + this.fill.hashCode();

        if (ret < 0) ret = -ret;

        return ret;
    }

    private Texto(Texto t) throws Exception {
        if (t == null) throw new Exception("Modelo nulo"); //    Verifica se o modelo é nulo.

        this.fonte = t.fonte;
        this.p = (Ponto) t.p.clone();
        this.texto = t.texto;

        this.cor = t.cor;
        this.fill = t.fill;
    }

    @Override
    public Object clone() { //    Método que clona o texto.
        Texto ret = null;

        try {
            ret = new Texto(this);
        } catch (Exception e) {}

        return ret;
    }

    public String toString() { //    Método que retorna uma string com os dados do texto.
        return "t:" +
                this.p.getX() +
                ":" +
                this.p.getY() +
                ":" +
                this.texto +
                ":" +
                this.fonte.getFamily() +
                ":" +
                this.fonte.getStyle() +
                ":" +
                this.fonte.getSize() +
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
