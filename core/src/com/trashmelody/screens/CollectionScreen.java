package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.managers.Assets;
import com.trashmelody.managers.MusicManager;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.TrashMelody;
import com.trashmelody.utils.GifDecoder;

import static com.trashmelody.managers.Assets.*;
import static com.trashmelody.utils.RenderingUtils.*;

@Singleton
public class CollectionScreen extends LazyScreen {
    private TrashMelody game;
    private ScreenProvider screens;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private MusicManager musicManager;
    private Music bgMusic2;
    private BitmapFont fontTitle, fontDesc, fontTrashType;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();
    private int count = 1;
    private Animation<TextureRegion> bg;
    private Texture footer, header, pack, l, r, lh, rh, btnBack, storyBG;
    //Dangerous Trashes
    private Texture cigar, spray, can, thinner;
    //Recycle Trashes
    private Texture cardboard, glass, note, paper, plastic, bottle;
    //Wet Trashes
    private Texture curry, donut, icecream, matcha, popcorn;
    //General Trashes
    private Texture bag, plate, tooth, cloth, pencil;
    //Bin
    private Texture dangerBin, recycleBin, wetBin, idkBin;
    //Temporary Current Stage
    private int currentUnlock = 3;
    float elapsed;
    private GlyphLayout layoutTitle = new GlyphLayout();
    private GlyphLayout layoutDesc = new GlyphLayout();
    private GlyphLayout layoutTrashType = new GlyphLayout();

    @Inject
    CollectionScreen(TrashMelody game, OrthographicCamera camera, ScreenProvider screens, Viewport viewport, SpriteBatch batch, MusicManager musicManager) {
        this.game = game;
        this.screens = screens;
        this.camera = camera;
        this.viewport = new ScalingViewport(Scaling.fit, vw, vh, camera);
        this.batch = batch;
        this.musicManager = musicManager;
    }

    @Override
    public void show(){
        musicManager.setDefault(MUSIC_BG2);
        musicManager.playMusic();
        musicManager.setMusicLoopStatus(true);
    }

    @Override
    public void render(float delta) {
        elapsed += delta;
        clearScreen();

        Texture cardToDraw;
        String nameToDraw = "";
        String descToDraw = "";
        String typeToDraw = "";

        game.batch.begin();

        // Draw Background
        if (TrashMelody.enableAnimation) {
            game.batch.draw(bg.getKeyFrame(elapsed), 0, 0, findRatio(16, 9, vh, 'w'), vh);
        } else {
            game.batch.draw(bg.getKeyFrame(0), 0, 0, findRatio(16, 9, vh, 'w'), vh);
        }

        game.batch.draw(header, vw / 128, vh / 1.25F, vw / 2.5F, vh / 5);

        if (vw < 1500) {
            game.batch.draw(storyBG, (vw / 2) - ((vw / 1.3F) / 2), 0, vw / 1.3F, vh / 3.32F);
            game.batch.draw(pack, vw / 4F, vw / 5F, vw / 2F, findRatio(1200, 627, vw / 2, 'h'));
        } else {
            game.batch.draw(storyBG, (vw / 2) - ((vw / 2F) / 2), 0, vw / 2F, findRatio(991, 359, vw / 2F, 'h'));
            game.batch.draw(pack, vw / 4F, vw / 5.85F, vw / 2F, findRatio(1200, 627, vw / 2, 'h'));
        }

        game.batch.draw(footer, 0, 0, vw, findRatio(1920, 80, vw, 'h'));
        game.batch.draw(btnBack, vw / 64, 0, findRatio(180, 54, vh / 16F, 'w'), vh / 16);
        game.batch.draw(l, vw / 6, vh / 1.9F, vw / 45, vh / 24);
        game.batch.draw(r, vw / 1.23F, vh / 1.9F, vw / 45, vh / 24);

        // Trash on Stage 1
        if (count < 1) count = 1;
        if (count > 20) count = 20;

        switch (count) {
            case 1:
                cardToDraw = thinner;
                nameToDraw = "Thinner the Carpenter";
                descToDraw = "A hot and flammable guy. His smell can cause pleasant "
                        + "hallucinations to everyone near him.";
                break;
            case 2:
                cardToDraw = cardboard;
                nameToDraw = "Bokk Kung";
                descToDraw = "A cardboard box that used to contain a dog. "
                        + "He hopes to find a new dog and he’d bark \"Box-Box\" like a dog.";
                break;
            case 3:
                cardToDraw = donut;
                nameToDraw = "Dono-chan";
                descToDraw = "Dono-Chan is a teacher of Circle Dance and Sing Academy. "
                        + "Although she is fat, she can dance very well. "
                        + "She and everyone is jealous of her talent.";
                break;
            case 4:
                cardToDraw = pencil;
                nameToDraw = "Pencie";
                descToDraw = "a nerd in school on trash world, " +
                        "he is very smart every one can depends on him when struggle on something";
                break;
            case 5:
                cardToDraw = bottle;
                nameToDraw = "Botty";
                descToDraw = "Good Bottle Guys that want to be a hero";
                break;
            case 6:
                cardToDraw = note;
                nameToDraw = "Pep";
                descToDraw = "The lost piece of Pep Guardiola’s note, "
                        + "so the name \"Pep\" literally comes from his owner.";
                break;
            case 7:
                cardToDraw = popcorn;
                nameToDraw = "Popu-san";
                descToDraw = "He is the son of comedy director M-san.Popu-san is a popular actor. "
                        + "He has starred in many popular films such as Trash 1997 and "
                        + "earned Oscar nominations for Get Trash 2008.";
                break;
            case 8:
                cardToDraw = plate;
                nameToDraw = "dish - dash";
                descToDraw = "the most friendly girl on trash world she always positive and friendly on everything";
                break;
            case 9:
                cardToDraw = cigar;
                nameToDraw = "Cigar";
                descToDraw = "A friend of every man *Cough*. But his health *Cough* "
                        + "is not very well lately *Cough* due to *Cough* "
                        + "his oral cavity, larynx, esophagus, and lung cancer.";
                break;
            case 10:
                cardToDraw = plastic;
                nameToDraw = "SaiSai";
                descToDraw = "A plastic box that wants to find new friends and go explore the world.";
                break;
            case 11:
                cardToDraw = curry;
                nameToDraw = "Keri-a";
                descToDraw = "Keri-a is the hottest girl in Trash World. "
                        + "  She had her red lips cosmetically enhanced. "
                        + "She is the Miss Popular Vote in the Trash World.";
                break;
            case 12:
                cardToDraw = tooth;
                nameToDraw = "toothpast";
                descToDraw = "Pencie’s closefriends .He is smart and clean guy so " +
                        "he always clean every tooth of everybody in trash world";
                break;
            case 13:
                cardToDraw = spray;
                nameToDraw = "Hairspray chan";
                descToDraw = "Fired from a beauty salon being accused of causing global warming, "
                        + "she then determined to founding her own salon "
                        + "with Wax-kung and Gel-kung to take revenge.";
                break;
            case 14:
                cardToDraw = glass;
                nameToDraw = "MookMook";
                descToDraw = "An empty plastic glass from a bubble milk tea shop. "
                        + "He is finding a way back to the shop.";
                break;
            case 15:
                cardToDraw = matcha;
                nameToDraw = "Matty";
                descToDraw = "His full name is Matcha-sama. He has dark-green eyes, "
                        + "and he has a lot of success with ladies. He has been voted "
                        + "the sexiest man in Trash World many times.";
                break;
            case 16:
                cardToDraw = cloth;
                nameToDraw = "Raggy";
                descToDraw = "a slave in the cafe every one always use and insult him " +
                        "on one decide him as friend except dish-dash";
                break;
            case 17:
                cardToDraw = can;
                nameToDraw = "Oily Oiler";
                descToDraw = "Oily Oiler is an oil can from the suburb. "
                        + "After he had been emptied petrol, he got thrown away without care.";
                break;
            case 18:
                cardToDraw = paper;
                nameToDraw = "The Trio";
                descToDraw = "The trio of derpy paper friends that "
                        + "a famous artist has thrown into the bin.";
                break;
            case 19:
                cardToDraw = icecream;
                nameToDraw = "Izu-chan";
                descToDraw = "Her full name is Izu - Pink Cremu. She is a sweetened frozen girl "
                        + "you'll want to eat if you see one. Her cheek is pink and her hair is white.";
                break;
            case 20:
                cardToDraw = bag;
                nameToDraw = "Immortal bag";
                descToDraw = "She was born 700 years ago. And as her name says; "
                        + "she is a plastic bag that could live through centuries.";
                break;
            default:
                cardToDraw = spray;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_RIGHT)) {
            game.batch.draw(rh, vw / 1.23F, vh / 1.9F, vw / 45, vh / 24);
            count++;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_LEFT)) {
            game.batch.draw(lh, vw / 6, vh / 1.9F, vw / 45, vh / 24);
            count--;
        }
        if (vw < 1500) {
            game.batch.draw(cardToDraw, vw / 2.52F, vh / 3.1F, vw / 5, vh / 2.2F);
        } else {
            game.batch.draw(cardToDraw, vw / 2.52F, vh / 3F, vw / 5, vh / 2.2F);
        }

        // Set fontTitle properties and draw
        layoutTitle.setText(fontTitle, nameToDraw, Color.WHITE, vw, Align.center, true);
        fontTitle.draw(batch, layoutTitle, 0, vw / 6.2F);

        // Set fontDesc properties and draw
        float descWidth;
        if (vw < 1500) descWidth = vw / 1.5F;
        else descWidth = vw / 2.5F;
        layoutDesc.setText(fontDesc, descToDraw, Color.WHITE, descWidth, Align.center, true);
        fontDesc.draw(batch, layoutDesc, (vw / 2) - (descWidth / 2F), vw / 8);

        // Set fontTrashType properties and draw
        if (count == 1 || count == 9 || count == 13 || count == 17) {
            typeToDraw = "Type * Dangerous";
            game.batch.draw(dangerBin, vw / 1.75F, vh / 3.2F, vw / 19.5F, vh / 14);
        } else if (count == 2 || count == 5 || count == 6 || count == 10 || count == 14 || count == 18) {
            typeToDraw = "Type * Recycle";
            game.batch.draw(recycleBin, vw / 1.75F, vh / 3.2F, vw / 19, vh / 14);
        } else if (count == 3 || count == 7 || count == 11 || count == 15 || count == 19) {
            typeToDraw = "Type * Wet";
            game.batch.draw(wetBin, vw / 1.75F, vh / 3.2F, vw / 19, vh / 14);
        } else if (count == 4 || count == 8 || count == 12 || count == 16 || count == 20) {
            typeToDraw = "Type * General";
            game.batch.draw(idkBin, vw / 1.75F, vh / 3.2F, vw / 19, vh / 14);
        }
        layoutTrashType.setText(fontTrashType, typeToDraw, Color.WHITE, vw, Align.center, true);
        fontTrashType.draw(batch, layoutTrashType, 0, vw / 24);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setLazyScreen(screens.get(MenuScreen.class));
        }

        // Debug zone
//        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font, "Collection Screen");
        // Debug zone

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        viewport.update(width, height);
    }

    @Override
    public void loadAssets(Assets assets) {
        assets.load(MUSIC_BG2,MUSIC);
        assets.load(COLLECTION_BG, TEXTURE);
        assets.load(COLLECTION_HEADER, TEXTURE);
        assets.load(COLLECTION_PACK, TEXTURE);
        assets.load(COLLECTION_LEFT, TEXTURE);
        assets.load(COLLECTION_RIGHT, TEXTURE);
        assets.load(COLLECTION_LEFT_H, TEXTURE);
        assets.load(COLLECTION_RIGHT_H, TEXTURE);
        assets.load(COLLECTION_STORY_BG, TEXTURE);
        assets.load(GLOBAL_ICON_BACK, TEXTURE);
        assets.load(GLOBAL_FOOTER_METAL_BAR, TEXTURE);
        //Trashes
        assets.load(COLLECTION_DANGER_1, TEXTURE);
        assets.load(COLLECTION_DANGER_2, TEXTURE);
        assets.load(COLLECTION_DANGER_3, TEXTURE);
        assets.load(COLLECTION_DANGER_4, TEXTURE);
        assets.load(COLLECTION_DANGER_5, TEXTURE);
        assets.load(COLLECTION_RECYCLE_1, TEXTURE);
        assets.load(COLLECTION_RECYCLE_2, TEXTURE);
        assets.load(COLLECTION_RECYCLE_3, TEXTURE);
        assets.load(COLLECTION_RECYCLE_4, TEXTURE);
        assets.load(COLLECTION_RECYCLE_5, TEXTURE);
        assets.load(COLLECTION_WET_1, TEXTURE);
        assets.load(COLLECTION_WET_2, TEXTURE);
        assets.load(COLLECTION_WET_3, TEXTURE);
        assets.load(COLLECTION_WET_4, TEXTURE);
        assets.load(COLLECTION_WET_5, TEXTURE);
        assets.load(COLLECTION_GENERAL_1, TEXTURE);
        assets.load(COLLECTION_GENERAL_2, TEXTURE);
        assets.load(COLLECTION_GENERAL_3, TEXTURE);
        assets.load(COLLECTION_GENERAL_4, TEXTURE);
        assets.load(COLLECTION_GENERAL_5, TEXTURE);
        //Bin
        assets.load(GAME_BIN_01, TEXTURE);
        assets.load(GAME_BIN_02, TEXTURE);
        assets.load(GAME_BIN_03, TEXTURE);
        assets.load(GAME_BIN_04, TEXTURE);

        this.bg = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal(COLLECTION_BG).read());
    }

    @Override
    public void afterLoad(Assets assets) {
        this.bgMusic2 = assets.get(MUSIC_BG2, MUSIC);
        this.footer = assets.get(GLOBAL_FOOTER_METAL_BAR, TEXTURE);
        this.header = assets.get(COLLECTION_HEADER, TEXTURE);
        this.pack = assets.get(COLLECTION_PACK, TEXTURE);
        this.l = assets.get(COLLECTION_LEFT, TEXTURE);
        this.r = assets.get(COLLECTION_RIGHT, TEXTURE);
        this.lh = assets.get(COLLECTION_LEFT_H, TEXTURE);
        this.rh = assets.get(COLLECTION_RIGHT_H, TEXTURE);
        this.btnBack = assets.get(GLOBAL_ICON_BACK, TEXTURE);
        this.storyBG = assets.get(COLLECTION_STORY_BG);
        //Danger Trashes
        this.spray = assets.get(COLLECTION_DANGER_2, TEXTURE);
        this.cigar = assets.get(COLLECTION_DANGER_3, TEXTURE);
        this.thinner = assets.get(COLLECTION_DANGER_4, TEXTURE);
        this.can = assets.get(COLLECTION_DANGER_5, TEXTURE);
        //Recycle Trashes
        this.paper = assets.get(COLLECTION_RECYCLE_1, TEXTURE);
        this.note = assets.get(COLLECTION_RECYCLE_2, TEXTURE);
        this.plastic = assets.get(COLLECTION_RECYCLE_3, TEXTURE);
        this.glass = assets.get(COLLECTION_RECYCLE_4, TEXTURE);
        this.cardboard = assets.get(COLLECTION_RECYCLE_5, TEXTURE);
        this.bottle = assets.get(COLLECTION_GENERAL_1, TEXTURE);
        //Wet Trashes
        this.popcorn = assets.get(COLLECTION_WET_1, TEXTURE);
        this.donut = assets.get(COLLECTION_WET_2, TEXTURE);
        this.curry = assets.get(COLLECTION_WET_3, TEXTURE);
        this.matcha = assets.get(COLLECTION_WET_4, TEXTURE);
        this.icecream = assets.get(COLLECTION_WET_5, TEXTURE);
        //General Trashes
        this.bag = assets.get(COLLECTION_DANGER_1, TEXTURE);
        this.plate = assets.get(COLLECTION_GENERAL_2, TEXTURE);
        this.tooth = assets.get(COLLECTION_GENERAL_3, TEXTURE);
        this.cloth = assets.get(COLLECTION_GENERAL_4, TEXTURE);
        this.pencil = assets.get(COLLECTION_GENERAL_5, TEXTURE);
        //Bin
        this.dangerBin = assets.get(GAME_BIN_01, TEXTURE);
        this.recycleBin = assets.get(GAME_BIN_02, TEXTURE);
        this.wetBin = assets.get(GAME_BIN_03, TEXTURE);
        this.idkBin = assets.get(GAME_BIN_04, TEXTURE);
        // Fonts
        this.fontTitle = assets.get8bitFont(40, Color.WHITE);
        this.fontDesc = assets.getSuperSpaceFont(31, Color.WHITE);
        this.fontTrashType = assets.get8bitFont(24, Color.WHITE);
    }
}
