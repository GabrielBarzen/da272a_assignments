package com.eudycontreras.othello.application;

import javafx.scene.image.Image;

import java.util.Objects;

/**
 * <H2>Created by</h2> Eudy Contreras
 * <h4> Mozilla Public License 2.0 </h4>
 * Licensed under the Mozilla Public License 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <a href="https://www.mozilla.org/en-US/MPL/2.0/">visit Mozilla Public Lincense Version 2.0</a>
 * <H2>Class description</H2>
 * 
 * @author Eudy Contreras
 */
public class OthelloResources {

	public static final String IMAGE_SOURCE_DIRECTORY = "res"; //TODO correct path
	
	public static final Image LOGO = new Image(loadResource("res/logo.png"));
	
	public static final Image BOARD_TEXTURE_1 = new Image(loadResource("res/board_texture_1.jpg"));
	
	public static final Image BOARD_TEXTURE_2 = new Image(loadResource("res/board_texture_2.jpg"));
	
	public static final Image CREATOR_PROFILE = new Image(loadResource("res/eudy_contreras.jpg"));
	
	public static final Image TEXTURE[] = new Image[]{
			new Image(loadResource("res/texture_16.jpg")),
			new Image(loadResource("res/texture_0.jpg")),
			new Image(loadResource("res/texture_1.jpg")),
			new Image(loadResource("res/texture_2.jpg")),
			new Image(loadResource("res/texture_3.jpg")),
			new Image(loadResource("res/texture_4.jpg")),
			new Image(loadResource("res/texture_5.jpg")),
			new Image(loadResource("res/texture_6.jpg")),
			new Image(loadResource("res/texture_7.jpg")),
			new Image(loadResource("res/texture_8.jpg")),
			new Image(loadResource("res/texture_10.jpg")),
			new Image(loadResource("res/texture_11.jpg")),
			new Image(loadResource("res/texture_12.jpg")),
			new Image(loadResource("res/texture_13.jpg")),
	};
	

	public static String loadResource(String image) {
		String url = IMAGE_SOURCE_DIRECTORY + image;
		return url;
	}
}
