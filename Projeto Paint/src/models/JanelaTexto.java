package models;

import models.figuras.Texto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaTexto extends JFrame implements ActionListener { //    Classe que representa uma janela para entrada de texto.
    protected JTextArea textArea; //Declaração de diversos componentes gráficos e variáveis que serão utilizados na construção da interface.
    protected JScrollPane scrollPane;
    protected JLabel fontLabel;
    protected JLabel styleLabel;
    protected JSpinner fontSizeSpinner;
    protected SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 2, 1);
    protected JSpinner fontStyleSpinner;
    protected JComboBox<String> fontBox;
    protected JButton concluir;
    protected String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();//    Array de strings com as fontes disponíveis no sistema.
    private String selectedFontFamily = "Arial";
    private int selectedStyle = 0;
    private int selectedSize = 20;
    protected Font selectedFont;
    protected Janela.Painel pai;
    protected int x, y;

    public JanelaTexto(Janela.Painel pai, int x, int y) { //Início do construtor da classe JanelaTexto. Recebe como parâmetros uma instância de Janela.Painel e as coordenadas x e y.
        this.setTitle("Digite o texto:");
        this.setSize(600, 600);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null); //    Configurações iniciais da janela, como título, tamanho, layout e posição centralizada.

        textArea = new JTextArea(); //    Configuração do componente JTextArea para entrada de texto, com quebra de linha automática e estilo de fonte padrão.
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font(selectedFontFamily, selectedStyle, selectedSize));

        scrollPane = new JScrollPane(textArea); //    Configuração de um painel de rolagem (JScrollPane) para a área de texto.
        scrollPane.setPreferredSize(new Dimension(450, 450));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        fontLabel = new JLabel("Fonte: "); //    Criação de um rótulo para indicar a escolha da fonte.

        fontSizeSpinner = new JSpinner(); //    Configuração de um seletor numérico para escolher o tamanho da fonte.
        fontSizeSpinner.setPreferredSize(new Dimension(50, 25));
        fontSizeSpinner.setValue(20);
        fontSizeSpinner.addChangeListener((e) -> {
            selectedSize = (int) fontSizeSpinner.getValue();
            textArea.setFont(new Font(selectedFontFamily, selectedStyle, selectedSize));
        });

        styleLabel = new JLabel("Estilo: ");

        fontStyleSpinner = new JSpinner(spinnerModel); //    Configuração de um seletor numérico para escolher o tamanho da fonte.
        fontStyleSpinner.setPreferredSize(new Dimension(50, 25));
        fontStyleSpinner.setValue(0);
        fontStyleSpinner.addChangeListener((e) -> {
            selectedStyle = (int) fontSizeSpinner.getValue();
            switch (selectedStyle) {
                case 0:
                    textArea.setFont(new Font(selectedFontFamily, Font.PLAIN, selectedSize));
                    break;
                case 1:
                    textArea.setFont(new Font(selectedFontFamily, Font.BOLD, selectedSize));
                    break;
                case 2:
                    textArea.setFont(new Font(selectedFontFamily, Font.ITALIC, selectedSize));
                    break;
            }
        });

        fontBox = new JComboBox<>(fonts); //     Configuração de um botão para concluir a entrada de texto, com ação associada para adicionar o texto ao painel pai e fechar a janela.
        fontBox.addActionListener(this);
        fontBox.setSelectedItem(selectedFontFamily);

        concluir = new JButton("Concluir");
        concluir.addActionListener((e) -> {
            Font f = new Font(selectedFontFamily, Font.PLAIN, selectedSize);
            switch (selectedStyle) {
                case 0:
                    f = new Font(selectedFontFamily, Font.PLAIN, selectedSize);
                    break;
                case 1:
                    f = new Font(selectedFontFamily, Font.BOLD, selectedSize);
                    break;
                case 2:
                    f = new Font(selectedFontFamily, Font.ITALIC, selectedSize);
                    break;
            }
            this.pai.adicionarTexto(this.x, this.y, this.textArea.getText(), f);
            this.dispose();
        });

        this.add(fontLabel); //    Adição dos componentes gráficos à janela.
        this.add(fontSizeSpinner);
        this.add(styleLabel);
        this.add(fontStyleSpinner);
        this.add(fontBox);
        this.add(concluir);
        this.add(scrollPane);
        this.setVisible(true);

        this.pai = pai; //    Atribuição dos parâmetros aos atributos da classe.
        this.x = x;
        this.y = y;
    }

    @Override
    public void actionPerformed(ActionEvent e) { //    Método para alterar a fonte do texto de acordo com a escolha do usuário.
        if (e.getSource() == fontBox) {
            selectedFontFamily = (String) fontBox.getSelectedItem();
            selectedStyle = (int) fontStyleSpinner.getValue();
            selectedSize = (int) fontSizeSpinner.getValue();
            textArea.setFont(new Font(selectedFontFamily, selectedStyle, selectedSize));
        }
    }
}
