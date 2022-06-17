/**
 * 
 */
package it.aren.common;

import java.awt.image.BufferedImage;

import it.aren.file.ImageLoader;
import it.aren.file.SettingsLoader;

/**
 *
 *
 */
public enum BaseActionEnum {
    /**
     * 
     */
    PLAY("play.png"),
    /**
    * 
    */
    SETTINGS("settings.png");
    private final BufferedImage texture;
    BaseActionEnum(final String fileName) {
        this.texture = ImageLoader.loadImage(fileName, SettingsLoader.loadSettings().scale());
    }
    public BufferedImage getTexture() {
        return texture;
    }
}