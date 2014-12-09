package com.eje.fallapart.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eje.fallapart.FallApartGame;
import com.eje.fallapart.game.Obstaculo;
import com.eje.fallapart.game.Pared;
import com.eje.fallapart.game.Player;
import com.eje.fallapart.services.BoundedCamera;
import com.eje.fallapart.services.FAContactListener;
import com.eje.fallapart.services.Inputs;
import com.eje.fallapart.services.InputsProcessor;
import com.eje.fallapart.services.MatyasMusic;
import com.eje.fallapart.services.MatyasSound;

public class GameScreen extends AbstractScreen{

	private World world;
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera b2dCam;
	private FAContactListener cl;
	private Body player;
	private Player playerT;
	private int direccion;
	private float time = 0;
	private Random randPos;
	private final int maxPos = 11;
	private final int minPos = 2;
	private final short Bit_Limit = 2;
	private final short Bit_Player = 4;
	private final short Bit_Enemy = 8;
	private Body bodyEnemy;
	private float velocidad;
	private Label scoreLbl;
	private float score;
	private SpriteBatch sb;
	protected BoundedCamera cam;
	private Sprite spriteL, spriteR;
	private Obstaculo obs;
	private Pared paredL1,paredL2,paredL3,paredL4;
	private Pared paredR1,paredR2,paredR3,paredR4;
	
	public GameScreen(FallApartGame game) {
		super(game);
		randPos = new Random();
		direccion = 1;
		velocidad = 5;
		cl = new FAContactListener();
		sb = new SpriteBatch();
		cam = new BoundedCamera();
		spriteL = new Sprite(new Texture("left.png"));
		spriteL.setSize(1,10);
		paredL1 = new Pared("left.png", -10, false);
		paredL2 = new Pared("left.png", 0, false);
		paredL3 = new Pared("left.png", 10, false);
		paredL4 = new Pared("left.png", 20, false);
		
		paredR1 = new Pared("right.png", -10, true);
		paredR2 = new Pared("right.png", 0, true);
		paredR3 = new Pared("right.png", 10, true);
		paredR4 = new Pared("right.png", 20, true);
	}

	@Override
	public void show(){
		super.show();
		scoreLbl = new Label("Puntaje", getSkin());
		scoreLbl.setFontScale(2);
		
		game.getMusicManager().play(MatyasMusic.MENU_MUSIC);
		
		
		Table table = super.getTable();
		table.clear();
		table.setBounds(0, 0, AbstractScreen.VIEWPORT_WIDTH, AbstractScreen.VIEWPORT_HEIGHT);
		Image image = new Image(getSkin(), "bg_main");
		

		
		//table.setBackground(image.getDrawable());
		
		Button pauseButton = new Button( getSkin(),"btnPausa");
		pauseButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				stage.addAction(Actions.sequence(Actions.delay(0.25f),
						Actions.fadeOut(0.75f),
						new Action(){
							public boolean act(float delta){
								//game.getMusicManager().stop();
								game.getSoundManager().play(MatyasSound.CLICK);
								game.setScreen(new IntroScreen(game));
								return true;
							}
						}));
			}
		});
		pauseButton.setScale(0.15f);
		pauseButton.addAction(Actions.scaleTo(1f,1f,1f));
		stage.addActor(pauseButton);
		//table.row();
		table.add(pauseButton).padBottom(0);
		table.add().size(100, 10);
		table.add(scoreLbl);
		
		table.row();
		table.add().size(100, 1000);
		world = new World(new Vector2(0,7.81f),true);
		world.setContactListener(cl);
		
		b2dr = new Box2DDebugRenderer();
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false,VIEWPORT_WIDTH*2/PPM,VIEWPORT_HEIGHT*2/PPM);
		
		//Dynamic
		BodyDef bdef = new BodyDef();
		bdef.type = BodyType.DynamicBody;
		bdef.position.set(720 / PPM, 1600 / PPM);
		bdef.fixedRotation = false;
		player = world.createBody(bdef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(100/PPM, 100/PPM);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.restitution = 0.3f;
		fdef.friction = 0.6f;
		fdef.filter.categoryBits = Bit_Player;
		fdef.filter.maskBits = Bit_Limit | Bit_Enemy;
		player.createFixture(fdef).setUserData("player");
		
		playerT = new Player(player);
		player.setUserData(playerT);
		
		//Platforms
		bdef.position.set(600/PPM,1800/PPM);
		bdef.type = BodyType.StaticBody;
		Body body2 = world.createBody(bdef);
		
		shape.setAsBox(700/PPM, 100/PPM);
		fdef.shape = shape;
		fdef.restitution = 0f;
		fdef.friction = 0f;
		fdef.filter.categoryBits = Bit_Limit;
		fdef.filter.maskBits = Bit_Player;
		body2.createFixture(fdef);
		
		
		bdef.position.set(0/PPM,2000/PPM);
		body2 = world.createBody(bdef);
		shape.setAsBox(100/PPM, 1000/PPM);
		fdef.shape = shape;
		body2.createFixture(fdef);
		
		bdef.position.set(1400/PPM,2000/PPM);
		body2 = world.createBody(bdef);
		shape.setAsBox(100/PPM, 1000/PPM);
		fdef.shape = shape;
		body2.createFixture(fdef);
		
		bdef.position.set(600/PPM,800/PPM);
		body2 = world.createBody(bdef);
		shape.setAsBox(700/PPM, 100/PPM);
		fdef.shape = shape;
		body2.createFixture(fdef);
		
		createEnemy();
	}
	
	private void playerMove() {
		direccion *= -1;
		player.setLinearVelocity(0, 0);
		player.applyForceToCenter(400*direccion, -60, true);

	}
	
	public void createEnemy(){
		BodyDef bdef = new BodyDef();
		bdef.type = BodyType.DynamicBody;
		int randomNum = randPos.nextInt((maxPos - minPos) + 1) + minPos;
		randomNum *= PPM;
		bdef.position.set(randomNum/PPM,0);
		bdef.gravityScale = 0;
		
		bodyEnemy = world.createBody(bdef);
		bodyEnemy.setLinearVelocity(0,velocidad);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(200/PPM, 100/PPM);
		/*
		CircleShape shape = new CircleShape();
		shape.setRadius(200/PPM);
		*/
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.filter.categoryBits = Bit_Enemy;
		fdef.filter.maskBits = Bit_Player;
		bodyEnemy.createFixture(fdef).setUserData("enemy");
		
		obs = new Obstaculo(bodyEnemy);
	}
	@Override	
	public void render(float delta){
		super.render(delta);
		time += delta;
		score += delta;
		scoreLbl.setText("Puntos: " + (int)score);
		
		// camera follow player
		cam.setToOrtho(false, AbstractScreen.VIEWPORT_WIDTH*2, AbstractScreen.VIEWPORT_HEIGHT*2);
		
		cam.update();
				
				
		//clear screen
		//Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		//Draw world
		//b2dCam.update();
		//b2dr.render(world, b2dCam.combined);
		//Gdx.app.log(FallApartGame.LOG, "Body " + body.getPosition());
		world.step(STEP, 1, 1);
		
		if (Gdx.input.justTouched()) {
		    playerMove();
		    Gdx.app.log(FallApartGame.LOG, "Touch ");
		}
		sb.setProjectionMatrix(b2dCam.combined);
		playerT.update(delta);
		
		playerT.render(sb);
		/*
		sb.begin();
		spriteL.draw(sb);
		sb.end();
		*/
		if(time>2){
			time = 0;
			velocidad += 0.2f;
		}
		
		if(cl.isPlayerDead()){
			game.getPreferencesManager().setScore((int)score);
			game.setScreen(new IntroScreen(game));
		}
		
		obs.render(sb);
		obs.update(delta,velocidad);
		
		paredL1.update(delta, velocidad);
		paredL1.render(sb);
		
		paredL2.update(delta, velocidad);
		paredL2.render(sb);
		
		paredL3.update(delta, velocidad);
		paredL3.render(sb);
		
		paredL4.update(delta, velocidad);
		paredL4.render(sb);
		
		paredR1.update(delta, velocidad);
		paredR1.render(sb);
		
		paredR2.update(delta, velocidad);
		paredR2.render(sb);
		
		paredR3.update(delta, velocidad);
		paredR3.render(sb);
		
		paredR4.update(delta, velocidad);
		paredR4.render(sb);
	}
}
