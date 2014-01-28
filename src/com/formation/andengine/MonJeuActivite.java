package com.formation.andengine;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.graphics.Color;
import android.graphics.Typeface;

public class MonJeuActivite extends BaseGameActivity {

	private Camera camera;
	private static final int CAMERA_LARGEUR = 480;
	private static final int CAMERA_HAUTEUR = 320;
	private Font font;
	private Font fontPerso;
	private ChangeableText changeableText;
	
	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Engine onLoadEngine() {
		camera = new Camera(0, 0, CAMERA_LARGEUR, CAMERA_HAUTEUR);
		return new Engine(new EngineOptions(true, 
				ScreenOrientation.LANDSCAPE, 
				new RatioResolutionPolicy(CAMERA_LARGEUR, CAMERA_HAUTEUR), 
				camera));
	}

	@Override
	public void onLoadResources() {
		FontFactory.setAssetBasePath("font/");
		
		final BitmapTextureAtlas fontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR);
		final BitmapTextureAtlas fontPersoTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR);
		
		font = new Font(fontTexture, Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), 24, true, Color.WHITE);
		fontPerso = FontFactory.createFromAsset(fontPersoTexture, this, "exo.ttf",24,true,Color.WHITE);
		
		this.getEngine().getTextureManager().loadTexture(fontTexture);
		this.getEngine().getTextureManager().loadTexture(fontPersoTexture);
		this.getEngine().getFontManager().loadFont(font);
		this.getEngine().getFontManager().loadFont(fontPerso);
		
	}

	@Override
	public Scene onLoadScene() {
		final Scene scene = new Scene();
		
		final Text monTexte = new Text(0, 0, font , "Hello world !");
		changeableText = new ChangeableText(0, 50, fontPerso, "Coucou tout le monde");
		changeableText.setText("Et puis non !");
		
		scene.attachChild(changeableText);
		
		scene.attachChild(monTexte);
		
		return scene;
	}

}