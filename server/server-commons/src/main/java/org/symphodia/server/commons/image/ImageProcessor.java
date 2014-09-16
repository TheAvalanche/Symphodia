package org.symphodia.server.commons.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageProcessor {

    BufferedImage image;

    public ImageProcessor(String path) throws IOException {
        image = ImageIO.read(new File(path));
    }

    public ImageProcessor(InputStream stream) throws IOException {
        image = ImageIO.read(stream);
    }

    public InputStream toInputStream() throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        return new ByteArrayInputStream(os.toByteArray());
    }

    public void resize(int width, int height) {
        int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
        image = resizeImage(width, height, type);
    }

    public void resizeProportionally(int minSize) {
        int width = minSize;
        int height = minSize;
        if (image.getWidth() < image.getHeight()) {
            height = image.getHeight() / (image.getWidth() / minSize);
        } else if (image.getHeight() < image.getWidth()) {
            width = image.getWidth() / (image.getHeight() / minSize);
        }
        int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
        image = resizeImage(width, height, type);
    }

    public void cropToSquare(int size) {
        int x = (image.getWidth() - size) / 2;
        int y = (image.getHeight() - size) / 2;
        image = image.getSubimage(x, y, size, size);
    }

    private BufferedImage resizeImage(int width, int height, int type) {

        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }
}
