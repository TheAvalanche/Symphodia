package org.symphodia.server.commons.image;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageProcessorTest {

    @Test
    public void testConstructor() throws Exception {
        ImageProcessor processor = new ImageProcessor(this.getClass().getResource("/test.png").getPath());

        BufferedImage image = ImageIO.read(processor.toInputStream());

        Assert.assertNotNull(image);
        Assert.assertEquals(image.getHeight(), 325);
        Assert.assertEquals(image.getWidth(), 325);
    }

    @Test
    public void testResize() throws Exception {
        ImageProcessor processor = new ImageProcessor(this.getClass().getResource("/test.png").getPath());
        processor.resize(100, 50);
        BufferedImage image = ImageIO.read(processor.toInputStream());

        Assert.assertNotNull(image);
        Assert.assertEquals(image.getWidth(), 100);
        Assert.assertEquals(image.getHeight(), 50);

        processor.resize(500, 250);

        image = ImageIO.read(processor.toInputStream());

        Assert.assertNotNull(image);
        Assert.assertEquals(image.getWidth(), 500);
        Assert.assertEquals(image.getHeight(), 250);
    }

    @Test
    public void testResizeProportionally() throws Exception {
        ImageProcessor processor = new ImageProcessor(this.getClass().getResource("/test.png").getPath());
        processor.resizeProportionally(100);
        BufferedImage image = ImageIO.read(processor.toInputStream());

        Assert.assertNotNull(image);
        Assert.assertEquals(image.getWidth(), 100);
        Assert.assertEquals(image.getHeight(), 100);

        processor.resizeProportionally(500);

        image = ImageIO.read(processor.toInputStream());

        Assert.assertNotNull(image);
        Assert.assertEquals(image.getWidth(), 500);
        Assert.assertEquals(image.getHeight(), 500);
    }

    @Test
    public void testCropToSquare() throws Exception {
        ImageProcessor processor = new ImageProcessor(this.getClass().getResource("/test.png").getPath());
        processor.cropToSquare(100);
        BufferedImage image = ImageIO.read(processor.toInputStream());

        Assert.assertNotNull(image);
        Assert.assertEquals(image.getWidth(), 100);
        Assert.assertEquals(image.getHeight(), 100);
    }
}
