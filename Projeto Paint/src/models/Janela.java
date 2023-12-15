package models;

import models.figuras.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Janela extends JFrame { // Classe para representar a janela do programa
    protected static final long serialVersionUID = 1L; // Serialização da classe Janela

    protected JButton btnAbrir = new JButton("Abrir"), // Botões da janela
            btnSalvar = new JButton("Salvar"),
            btnPonto = new JButton("Ponto"),
            btnLinha = new JButton("Linha"),
            btnQuadrado = new JButton("Quadrado"),
            btnRetangulo = new JButton("Retangulo"),
            btnCirculo = new JButton("Circulo"),
            btnElipse = new JButton("Elipse"),
            btnTexto = new JButton("Texto"),
            btnCores1 = new JButton("Cor Principal"),
            btnCores2 = new JButton("Cor Secundária"),
            btnApagar = new JButton("Apagar"),
            btnSair = new JButton("Sair");
    protected Painel pnlDesenho = new Painel();

    protected JLabel statusBar1 = new JLabel("Mensagem:"),
            statusBar2 = new JLabel("Localização do ponteiro:");

    protected boolean esperaPonto = false, // Variáveis booleanas para indicar o estado do programa
            esperaInicioLinha = false,
            esperaFimLinha = false,
            esperaInicioQuadrado = false,
            esperaFimQuadrado = false,
            esperaInicioRetangulo = false,
            esperaFimRetangulo = false,
            esperaInicioCirculo = false,
            esperaFimCirculo = false,
            esperaInicioElipse = false,
            esperaFimElipse = false,
            esperaTexto = false;

    protected Color corAtual = Color.BLACK;
    protected Color corFill = new Color(238, 238, 238); // 0 0 0 - branco
    protected Ponto p1;

    protected Vector<Figura> figuras = new Vector<>();

    public Janela() { // Construtor da classe Janela
        super("Editor Gráfico");

        carregarBotoesDaImg();

        btnAbrir.addActionListener(new AbrirArquivo());
        btnSalvar.addActionListener(new SalvarArquivo());
        btnPonto.addActionListener(new DesenhoDePonto());
        btnLinha.addActionListener(new DesenhoDeLinha());
        btnQuadrado.addActionListener(new DesenhoDeQuadrado());
        btnRetangulo.addActionListener(new DesenhoDeRetangulo());
        btnCirculo.addActionListener(new DesenhoDeCirculo());
        btnElipse.addActionListener(new DesenhoDeElipse());
        btnTexto.addActionListener(new AdicionarTexto());
        btnCores1.addActionListener(new EscolherCor1());
        btnCores2.addActionListener(new EscolherCor2());
        btnApagar.addActionListener(new ApagarPainel());
        btnSair.addActionListener(new SairPrograma());

        JPanel pnlBotoes = new JPanel();
        FlowLayout flwBotoes = new FlowLayout();
        pnlBotoes.setLayout(flwBotoes);

        pnlBotoes.add(btnAbrir);
        pnlBotoes.add(btnSalvar);
        pnlBotoes.add(btnPonto);
        pnlBotoes.add(btnLinha);
        pnlBotoes.add(btnQuadrado);
        pnlBotoes.add(btnRetangulo);
        pnlBotoes.add(btnCirculo);
        pnlBotoes.add(btnElipse);
        pnlBotoes.add(btnTexto);
        pnlBotoes.add(btnCores1);
        pnlBotoes.add(btnCores2);
        pnlBotoes.add(btnApagar);
        pnlBotoes.add(btnSair);

        JPanel pnlStatus = new JPanel();
        GridLayout grdStatus = new GridLayout(1, 2);
        pnlStatus.setLayout(grdStatus);

        pnlStatus.add(statusBar1);
        pnlStatus.add(statusBar2);

        Container cntForm = this.getContentPane(); // Container da janela
        cntForm.setLayout(new BorderLayout());
        cntForm.add(pnlBotoes, BorderLayout.NORTH);
        cntForm.add(pnlDesenho, BorderLayout.CENTER);
        cntForm.add(pnlStatus, BorderLayout.SOUTH);

        this.addWindowListener(new FechamentoDeJanela()); // Adiciona o listener para o fechamento da janela

        this.setSize(1380, 800);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void carregarBotoesDaImg() { // Método para carregar as imagens dos botões
        try {
            Image btnAbrirImg = ImageIO.read(getClass().getResource("../resources/icons/abrir.jpg"));
            btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo abrir.jpg não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnSalvarImg = ImageIO.read(getClass().getResource("../resources/icons/salvar.jpg"));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo salvar.jpg não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnPontoImg = ImageIO.read(getClass().getResource("../resources/icons/ponto.jpg"));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo ponto.jpg não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnLinhaImg = ImageIO.read(getClass().getResource("../resources/icons/linha.jpg"));
            btnLinha.setIcon(new ImageIcon(btnLinhaImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo linha.jpg não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnQuadradoImg = ImageIO.read(getClass().getResource("../resources/icons/quadrado.jpg"));
            btnQuadrado.setIcon(new ImageIcon(btnQuadradoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo quadrado.jpg não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnRetanguloImg = ImageIO.read(getClass().getResource("../resources/icons/retangulo.jpg"));
            btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo retangulo.jpg não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnCirculoImg = ImageIO.read(getClass().getResource("../resources/icons/circulo.jpg"));
            btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo circulo.jpg não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnElipseImg = ImageIO.read(getClass().getResource("../resources/icons/elipse.jpg"));
            btnElipse.setIcon(new ImageIcon(btnElipseImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo elipse.jpg não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnTextoImg = ImageIO.read(getClass().getResource("../resources/icons/texto.jpg"));
            btnTexto.setIcon(new ImageIcon(btnTextoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo elipse.jpg não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnCoresImg = ImageIO.read(getClass().getResource("../resources/icons/cores.jpg"));
            btnCores1.setIcon(new ImageIcon(btnCoresImg));
            btnCores2.setIcon(new ImageIcon(btnCoresImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo cores.jpg não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnApagarImg = ImageIO.read(getClass().getResource("../resources/icons/apagar.jpg"));
            btnApagar.setIcon(new ImageIcon(btnApagarImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo apagar.jpg não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnSairImg = ImageIO.read(getClass().getResource("../resources/icons/sair.jpg"));
            btnSair.setIcon(new ImageIcon(btnSairImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo sair.jpg não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private int calcularRaio(int centroX, int centroY, int x, int y) {
        int tempX = x - centroX;
        int tempY = y - centroY;
        return (int) Math.pow((Math.pow(tempX, 2) + Math.pow(tempY, 2)), 0.5);
    }

    protected class Painel extends JPanel implements MouseListener, MouseMotionListener {
        public Painel() {
            super();

            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }

        public void paint(Graphics g) { // Método para desenhar as figuras
            for (int i = 0; i < figuras.size(); i++)
                figuras.get(i).ficarVisivel(g);
        }

        @Override
        public void mousePressed(MouseEvent e) { // Método para manipular os eventos do mouse
            if (esperaPonto) {
                figuras.add(new Ponto(e.getX(), e.getY(), corAtual, corFill));
                figuras.get(figuras.size() - 1).ficarVisivel(pnlDesenho.getGraphics());
                esperaPonto = false;
                statusBar1.setText("Mensagem:");
            } else if (esperaInicioLinha) {
                p1 = new Ponto(e.getX(), e.getY(), corAtual, corFill);
                esperaInicioLinha = false;
                esperaFimLinha = true;
                statusBar1.setText("Mensagem: clique o ponto final da linha");
            } else if (esperaFimLinha) {
                esperaFimLinha = false;
                figuras.add(new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual, corFill));
                figuras.get(figuras.size() - 1).ficarVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            } else if (esperaInicioQuadrado) {
                p1 = new Ponto(e.getX(), e.getY(), corAtual, corFill);
                esperaInicioQuadrado = false;
                esperaFimQuadrado = true;
                statusBar1.setText("Mensagem: clique o ponto final do quadrado");
            } else if (esperaFimQuadrado) {
                esperaFimQuadrado = false;
                if (e.getX() - p1.getX() > e.getY() - p1.getY())
                    figuras.add(new Quadrado(p1.getX(), p1.getY(), e.getX() - p1.getX(), corAtual, corFill));
                else
                    figuras.add(new Quadrado(p1.getX(), p1.getY(), e.getY() - p1.getY(), corAtual, corFill));
                figuras.get(figuras.size() - 1).ficarVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            } else if (esperaInicioRetangulo) {
                p1 = new Ponto(e.getX(), e.getY(), corAtual, corFill);
                esperaInicioRetangulo = false;
                esperaFimRetangulo = true;
                statusBar1.setText("Mensagem: clique o ponto final do retângulo");
            } else if (esperaFimRetangulo) {
                esperaFimRetangulo = false;
                figuras.add(new Retangulo(p1.getX(), p1.getY(), e.getX() - p1.getX(), e.getY() - p1.getY(), corAtual, corFill));
                figuras.get(figuras.size() - 1).ficarVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            } else if (esperaInicioCirculo) {
                p1 = new Ponto(e.getX(), e.getY(), corAtual, corFill);
                esperaInicioCirculo = false;
                esperaFimCirculo = true;
                statusBar1.setText("Mensagem: clique o raio do círculo");
            } else if (esperaFimCirculo) {
                esperaFimCirculo = false;
                figuras.add(new Circulo(p1.getX(), p1.getY(), calcularRaio(p1.getX(), p1.getY(), e.getX(), e.getY()), corAtual, corFill));
                figuras.get(figuras.size() - 1).ficarVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            } else if (esperaInicioElipse) {
                p1 = new Ponto(e.getX(), e.getY(), corAtual, corFill);
                esperaInicioElipse = false;
                esperaFimElipse = true;
                statusBar1.setText("Mensagem: clique o primeiro raio da elipse");
            } else if (esperaFimElipse) {
                esperaFimElipse = false;
                figuras.add(new Elipse(p1.getX(), p1.getY(), calcularRaio(p1.getX(), p1.getY(), e.getX(), e.getY()), e.getY() - p1.getY(), corAtual, corFill));
                figuras.get(figuras.size() - 1).ficarVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            } else if (esperaTexto) {
                esperaTexto = false;
                new JanelaTexto(this, e.getX(), e.getY());
                statusBar1.setText("Mensagem:");

            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) { // Método para mostrar a posição do mouse
            statusBar2.setText("Localização do ponteiro: " + e.getX() + "," + e.getY());
            if (esperaFimLinha) {
                pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
                new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual, corFill).ficarVisivel(pnlDesenho.getGraphics());
                pnlDesenho.paint(pnlDesenho.getGraphics());
            } else if (esperaFimQuadrado) {
                pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
                if (e.getX() - p1.getX() > e.getY() - p1.getY())
                    new Quadrado(p1.getX(), p1.getY(), e.getX() - p1.getX(), corAtual, corFill).ficarVisivel(pnlDesenho.getGraphics());
                else
                    new Quadrado(p1.getX(), p1.getY(), e.getY() - p1.getY(), corAtual, corFill).ficarVisivel(pnlDesenho.getGraphics());
                pnlDesenho.paint(pnlDesenho.getGraphics());
            } else if (esperaFimRetangulo) {
                pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
                new Retangulo(p1.getX(), p1.getY(), e.getX() - p1.getX(), e.getY() - p1.getY(), corAtual, corFill).ficarVisivel(pnlDesenho.getGraphics());
                pnlDesenho.paint(pnlDesenho.getGraphics());
            } else if (esperaFimCirculo) {
                pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
                new Circulo(p1.getX(), p1.getY(), calcularRaio(p1.getX(), p1.getY(), e.getX(), e.getY()), corAtual, corFill).ficarVisivel(pnlDesenho.getGraphics());
                pnlDesenho.paint(pnlDesenho.getGraphics());
            } else if (esperaFimElipse) {
                pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
                new Elipse(p1.getX(), p1.getY(), calcularRaio(p1.getX(), p1.getY(), e.getX(), e.getY()), e.getY() - p1.getY(), corAtual, corFill).ficarVisivel(pnlDesenho.getGraphics());
                pnlDesenho.paint(pnlDesenho.getGraphics());
            }
        }

        public void adicionarTexto(int x, int y, String texto, Font fonte) { // Método para adicionar texto
            figuras.add(new Texto(x, y, texto, fonte, corAtual, corFill));
            figuras.get(figuras.size() - 1).ficarVisivel(pnlDesenho.getGraphics());
        }
    }

    protected class AbrirArquivo implements ActionListener { // Classe para abrir um arquivo
        @Override
        public void actionPerformed(ActionEvent e) {
            pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
            figuras.clear();

            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir") + "\\src\\resources\\files");
            fileChooser.setDialogTitle("Escolha quais figuras quer abrir:");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Paint file", "pnt"));

            int userSelection = fileChooser.showOpenDialog(pnlDesenho);

            if (userSelection == JFileChooser.APPROVE_OPTION) { // Se o usuário escolheu um arquivo
                try {
                    File arq = fileChooser.getSelectedFile();
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(arq)); // Leitura do arquivo
                    String line = bufferedReader.readLine();
                    while (line != null) { // Enquanto não chegou ao fim do arquivo
                        char[] tipo = new char[1];
                        line.getChars(0, 1, tipo, 0);
                        switch (tipo[0]) {
                            case 'p':
                                figuras.add(new Ponto(line));
                                break;
                            case 'l':
                                figuras.add(new Linha(line));
                                break;
                            case 'q':
                                figuras.add(new Quadrado(line));
                                break;
                            case 'r':
                                figuras.add(new Retangulo(line));
                                break;
                            case 'c':
                                figuras.add(new Circulo(line));
                                break;
                            case 'e':
                                figuras.add(new Elipse(line));
                                break;
                            case 't':
                                figuras.add(new Texto(line));
                                break;
                        }
                        line = bufferedReader.readLine();
                    }
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                pnlDesenho.paint(pnlDesenho.getGraphics());
            }
        }
    }

    protected class SalvarArquivo implements ActionListener { // Classe para salvar um arquivo
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir") + "\\src\\resources\\files");
            fileChooser.setDialogTitle("Escolha aonde deseja salvar as figuras:");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Paint file", "pnt"));

            int userSelection = fileChooser.showSaveDialog(pnlDesenho);

            if (userSelection == JFileChooser.APPROVE_OPTION) { // Se o usuário escolheu um arquivo
                try {
                    String nomedoarquivo = fileChooser.getSelectedFile().toString(); // Escrita do arquivo
                    if (!nomedoarquivo.endsWith(".pnt")) nomedoarquivo += ".pnt";
                    File arq = new File(nomedoarquivo); // Criação do arquivo
                    FileWriter mudarArq = new FileWriter(arq);
                    for (int i = 0; i < figuras.size(); i++)
                        mudarArq.write(figuras.get(i) + "\n"); // Escrita das figuras no arquivo
                    mudarArq.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    protected class DesenhoDePonto implements ActionListener { // Classe para desenhar um ponto
        @Override
        public void actionPerformed(ActionEvent e) {
            esperaPonto = true;

            statusBar1.setText("Mensagem: clique no local desejado para inserir o ponto");
        }
    }

    protected class DesenhoDeLinha implements ActionListener { // Classe para desenhar uma linha
        @Override
        public void actionPerformed(ActionEvent e) {
            esperaInicioLinha = true;

            statusBar1.setText("Mensagem: clique o ponto inicial da linha");
        }
    }

    protected class DesenhoDeQuadrado implements ActionListener { // Classe para desenhar um quadrado
        @Override
        public void actionPerformed(ActionEvent e) {
            esperaInicioQuadrado = true;

            statusBar1.setText("Mensagem: clique o ponto inicial do quadrado");
        }
    }

    protected class DesenhoDeRetangulo implements ActionListener { // Classe para desenhar um retângulo
        @Override
        public void actionPerformed(ActionEvent e) {
            esperaInicioRetangulo = true;

            statusBar1.setText("Mensagem: clique o ponto inicial do retângulo");
        }
    }

    protected class DesenhoDeCirculo implements ActionListener { // Classe para desenhar um círculo
        @Override
        public void actionPerformed(ActionEvent e) {
            esperaInicioCirculo = true;

            statusBar1.setText("Mensagem: clique o centro do círculo");
        }
    }

    protected class DesenhoDeElipse implements ActionListener { // Classe para desenhar uma elipse
        @Override
        public void actionPerformed(ActionEvent e) {
            esperaInicioElipse = true;

            statusBar1.setText("Mensagem: clique o centro da elipse");
        }
    }

    protected class AdicionarTexto implements ActionListener { // Classe para adicionar texto
        @Override
        public void actionPerformed(ActionEvent e) {
            esperaTexto = true;

            statusBar1.setText("Mensagem: clique no local a ser inserido o texto");
        }
    }

    protected class EscolherCor1 implements ActionListener { // Classe para escolher a cor principal
        @Override
        public void actionPerformed(ActionEvent e) {
            corAtual = JColorChooser.showDialog(pnlDesenho, "Escolha uma cor:", Color.BLACK);
        }
    }

    protected class EscolherCor2 implements ActionListener { // Classe para escolher a cor secundária
        @Override
        public void actionPerformed(ActionEvent e) {
            corFill = JColorChooser.showDialog(pnlDesenho, "Escolha uma cor:", Color.WHITE);
        }
    }

    protected class ApagarPainel implements ActionListener { // Classe para apagar o painel
        @Override
        public void actionPerformed(ActionEvent e) {
            pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
            figuras.clear();
        }
    }

    protected class SairPrograma implements ActionListener { // Classe para sair do programa
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    protected class FechamentoDeJanela extends WindowAdapter { // Classe para fechar a janela
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}
