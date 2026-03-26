import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class GeradorGif {

    private GeradorGif() {
    }

    public static void salvarGifAnimado(MinhaArrayList<BufferedImage> frames,
                                        String caminhoSaida,
                                        int delayMs,
                                        boolean repetir) throws IOException {
        if (frames == null || frames.isEmpty()) {
            throw new IllegalArgumentException("A lista de frames nao pode estar vazia.");
        }

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("gif");
        if (!writers.hasNext()) {
            throw new IllegalStateException("Nao foi encontrado ImageWriter para GIF.");
        }

        ImageWriter writer = writers.next();
        try (ImageOutputStream output = new FileImageOutputStream(new File(caminhoSaida))) {
            writer.setOutput(output);
            writer.prepareWriteSequence(null);

            for (int i = 0; i < frames.size(); i++) {
                var frame = frames.get(i);
                ImageWriteParam params = writer.getDefaultWriteParam();
                ImageTypeSpecifier type = ImageTypeSpecifier.createFromBufferedImageType(frame.getType());
                IIOMetadata metadata = writer.getDefaultImageMetadata(type, params);
                configurarMetadados(metadata, delayMs, repetir);

                IIOImage imagem = new IIOImage(frame, null, metadata);
                writer.writeToSequence(imagem, params);
            }

            writer.endWriteSequence();
        } finally {
            writer.dispose();
        }
    }

    private static void configurarMetadados(IIOMetadata metadata, int delayMs, boolean repetir) throws IOException {
        String formato = metadata.getNativeMetadataFormatName();
        IIOMetadataNode raiz = (IIOMetadataNode) metadata.getAsTree(formato);

        IIOMetadataNode gce = obterOuCriar(raiz, "GraphicControlExtension");
        gce.setAttribute("disposalMethod", "none");
        gce.setAttribute("userInputFlag", "FALSE");
        gce.setAttribute("transparentColorFlag", "FALSE");
        gce.setAttribute("delayTime", Integer.toString(Math.max(1, delayMs / 10)));
        gce.setAttribute("transparentColorIndex", "0");

        IIOMetadataNode appExtensions = obterOuCriar(raiz, "ApplicationExtensions");
        IIOMetadataNode appNode = new IIOMetadataNode("ApplicationExtension");
        appNode.setAttribute("applicationID", "NETSCAPE");
        appNode.setAttribute("authenticationCode", "2.0");
        byte loop = (byte) (repetir ? 0 : 1);
        appNode.setUserObject(new byte[]{0x1, loop, 0x0});
        appExtensions.appendChild(appNode);

        metadata.setFromTree(formato, raiz);
    }

    private static IIOMetadataNode obterOuCriar(IIOMetadataNode raiz, String nomeNo) {
        for (int i = 0; i < raiz.getLength(); i++) {
            if (raiz.item(i).getNodeName().equalsIgnoreCase(nomeNo)) {
                return (IIOMetadataNode) raiz.item(i);
            }
        }

        IIOMetadataNode novoNo = new IIOMetadataNode(nomeNo);
        raiz.appendChild(novoNo);
        return novoNo;
    }
}

