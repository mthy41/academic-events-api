package com.academicevents.api.services;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

//Ballet-Regular - fonte necessaria instalada no sistema  -> google fonts
public class CertificateSend {
    private final String textoInicial = "Com prazer certificamos que";

    private final String texto2 = "participou do workshop";
    private final String texto3 = "e concluiu com sucesso todas as atividades realizadas. A presente certificação é concedida em reconhecimento ao comprometimento e à participação, totalizando uma carga horária de [20] horas.";

    public static BufferedImage generateCertificate(String name, String workshopName, String eventName) {


        try {
            CertificateSend certificate = new CertificateSend();

            // Carregar a imagem de fundo
            BufferedImage background = ImageIO.read(new File("src/main/resources/certificado.png"));

            // Obter o Graphics2D da imagem carregada
            Graphics2D g2d = background.createGraphics();

            // Configurar fontes e cores
            g2d.setColor(Color.BLACK);

            // Definir posição inicial
            int startY = 600; // Distância mínima do topo
            int maxWidth = 1050; // Largura máxima para os textos
            int centerX = (background.getWidth() - maxWidth) / 2;

            // Incrementar Y para o próximo texto
            startY += 60;

            // Texto inicial
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 38));
            drawCenteredText(g2d, certificate.textoInicial, centerX, startY, maxWidth);

            // Incrementar Y para o nome
            startY += 130;

            // Nome do participante (destaque)
            g2d.setFont(new Font("Ballet-Regular", Font.PLAIN, 100));
            drawCenteredText(g2d, name, centerX, startY, maxWidth);

            // Incrementar Y para o próximo texto
            startY += 110;

            // Texto secundário
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 38));
            String texto2 = certificate.texto2 + " " + workshopName;
            drawCenteredText(g2d, texto2, centerX, startY, maxWidth);

            // Incrementar Y para o texto final
            startY += 110;

            // Texto final
            drawCenteredText(g2d, certificate.texto3, centerX, startY, maxWidth);

            // Incrementar Y para a data
            startY += 260;

            // Data do certificado
            g2d.setFont(new Font("Ballet-Regular", Font.PLAIN, 20));
            String data = "Emitido em: " + java.time.LocalDate.now();
            drawCenteredText(g2d, data, centerX, startY, maxWidth);

            // Finalizar edição
            g2d.dispose();

            // Salvar a imagem final
//            ImageIO.write(background, "png", new File("src/main/resources/certificado_final.png"));
            System.out.println("Certificado gerado com sucesso!");
            return background;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Desenha um texto centralizado com quebra de linha se ultrapassar a largura máxima.
     */
    private static void drawCenteredText(Graphics2D g2d, String text, int x, int y, int maxWidth) {
        FontMetrics metrics = g2d.getFontMetrics();
        int lineHeight = metrics.getHeight();
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        int currentY = y;

        for (String word : words) {
            if (metrics.stringWidth(line + word) > maxWidth) {
                // Desenhar linha anterior e começar nova linha
                int lineX = x + (maxWidth - metrics.stringWidth(line.toString())) / 2;
                g2d.drawString(line.toString(), lineX, currentY);
                line = new StringBuilder(word + " ");
                currentY += lineHeight;
            } else {
                line.append(word).append(" ");
            }
        }

        // Desenhar última linha
        int lineX = x + (maxWidth - metrics.stringWidth(line.toString())) / 2;
        g2d.drawString(line.toString(), lineX, currentY);
    }
}