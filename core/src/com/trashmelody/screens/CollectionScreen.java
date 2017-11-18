package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.managers.Assets;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.utils.Debugger;
import com.trashmelody.TrashMelody;

import static com.trashmelody.managers.Assets.*;
import static com.trashmelody.utils.RenderingUtils.*;

@Singleton
public class CollectionScreen extends LazyScreen {
    private TrashMelody game;
    private ScreenProvider screens;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private BitmapFont font,font2,font3,font4;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();
    private int count = 1;
    private Texture bg,footer,header,pack,l,r,lh,rh,back;
    //Dangerous Trashes
    private Texture cigar,spray,can,bag,thinner;
    //Recycle Trashes
    private Texture  cardboard,glass,note,paper,plastic;
    //Wet Trashes
    private Texture curry,donut,icecream,matcha,popcorn;
    //Bin
    private Texture dangerBin,recycleBin,wetBin,idkBin;
    //Temporary Current Stage
    private int currentStage = 1;

    @Inject
    CollectionScreen(TrashMelody game, OrthographicCamera camera, ScreenProvider screens, Viewport viewport,SpriteBatch batch) {
        this.game = game;
        this.screens = screens;
        this.camera = camera;
        this.viewport = new ScalingViewport(Scaling.fit, vw, vh, camera);
        this.batch = batch;
    }

    @Override
    public void render(float delta) {
        clearScreen();

        game.batch.begin();

        //Draw Background
        game.batch.draw(bg,0,0,vw,vh);
        game.batch.draw(footer, 0, 0, vw, vh / 12);
        game.batch.draw(back, vw / 1.13F, 0, vw / 10, vh / 16);
        game.batch.draw(pack, vw / 4, vw/ 6.5F, vw / 1.8F, vh / 2);
        game.batch.draw(header, vw/128, vh / 1.25F, vw / 2.5F, vh / 5);
        game.batch.draw(l, vw/5, vh / 2, vw / 45, vh / 24);
        game.batch.draw(r, vw/1.2F, vh / 2, vw / 45, vh / 24);
        font3.draw(batch, "TYPE", vw / 2.2F, vw / 24);

        //Trash on Stage 1
        if (count < 1) count = 1;
        if(count > 3 * currentStage) count = 3 * currentStage;
        if(count==1) {
            game.batch.draw(bag, vw / 2.35F, vh / 3.1F, vw / 5, vh / 2.2F);
            font.draw(batch, "Immortal bag", vw / 2.8F, vw / 6);
            font2.draw(batch, "She was born 700 years ago. And as her name says"
                    + "\n" + "she is a plastic bag that could live through centuries.", vw / 2.9F, vw / 8);
        }
        if(count==2) {
            game.batch.draw(paper, vw / 2.35F, vh / 3.1F, vw / 5, vh / 2.2F);
            font.draw(batch, "The Trio", vw / 2.35F, vw / 6);
            font2.draw(batch, "The trio of derpy paper friends that"
                    + "\n" + "a famous artist has thrown into the bin.", vw / 2.5F, vw / 8);
        }
        if(count==3) {
            game.batch.draw(popcorn, vw / 2.35F, vh / 3.1F, vw / 5, vh / 2.2F);
            font.draw(batch, "Popu-san", vw / 2.35F, vw / 6);
            font2.draw(batch, "He is the son of comedy director M-san.Popu-san is a popular actor." + "\n"
                            + "   He has starred in many popular films such as Trash 1997 and" + "\n"
                            + "        earned Oscar nominations for Get Trash 2008."
                    , vw / 3.25F, vw / 8);
        }
        if(count==4) {
            game.batch.draw(spray, vw / 2.35F, vh / 3.1F, vw / 5, vh / 2.2F);
            font.draw(batch, "Hairspray chan", vw / 2.95F, vw / 6);
            font2.draw(batch, "Fired from a beauty salon being accused of causing global warming, " + "\n"
                            + "       she then determined to founding her own salon " + "\n"
                            +"       with Wax-kung and Gel-kung to take revenge."
                    , vw / 3.25F, vw / 8);
        }
        if(count==5) {
            game.batch.draw(note, vw / 2.35F, vh / 3.1F, vw / 5, vh / 2.2F);
            font.draw(batch, "Pep", vw / 2.05F, vw / 6);
            font2.draw(batch, "   The lost piece of Pep Guardiola’s note, " + "\n"
                            + "so the name \"Pep\" literally comes from his owner."
                    , vw / 2.7F, vw / 8);
        }
        if(count==6) {
            game.batch.draw(donut, vw / 2.35F, vh / 3.1F, vw / 5, vh / 2.2F);
            font.draw(batch, "Dono-chan", vw / 2.45F, vw / 6);
            font2.draw(batch, "  Dono-Chan is a teacher of Circle Dance and Sing Academy." +"\n"
                            +"       Although she is fat. she can dance very well. " +"\n"
                            +"         She and everyone is jealous of her talent."
                    , vw / 3.25F, vw / 8);
        }
        if(count==7) {
            game.batch.draw(cigar, vw / 2.35F, vh / 3.1F, vw / 5, vh / 2.2F);
            font.draw(batch, "Cigar", vw / 2.2F, vw / 6);
            font2.draw(batch, "A friend of every man *Cough*. But his health *Cough* " +"\n"+
                            "    is not very well lately *Cough* due to *Cough* " +"\n"+
                            "  his oral cavity, larynx, esophagus, and lung cancer."
                    , vw / 2.9F, vw / 8);
        }
        if(count==8) {
            game.batch.draw(plastic, vw / 2.35F, vh / 3.1F, vw / 5, vh / 2.2F);
            font.draw(batch, "SaiSai", vw / 2.2F, vw / 6);
            font2.draw(batch, "A plastic box that wants to find new friends " +"\n"+
                            "          and go explore the world."
                    , vw / 2.7F, vw / 8);
        }
        if(count==9) {
            game.batch.draw(curry, vw / 2.35F, vh / 3.1F, vw / 5, vh / 2.2F);
            font.draw(batch, "Keri-a", vw / 2.2F, vw / 6);
            font2.draw(batch, "     Keri-a is the hottest girl in Trash World. " +"\n"+
                            "  She had her red lips cosmetically enhanced. " +"\n"+
                            "She is the Miss Popular Vote in the Trash World."
                    , vw / 2.8F, vw / 8);
        }
        if(count==10) {
            game.batch.draw(thinner, vw / 2.35F, vh / 3.1F, vw / 5, vh / 2.2F);
            font.draw(batch, "Thinner the Carpenter", vw / 4.05F, vw / 6);
            font2.draw(batch, "A hot and flammable guy. His smell can cause pleasant " +"\n"+
                            "           hallucinations to everyone near him."
                    , vw / 3.1F, vw / 8);
        }
        if(count==11) {
            game.batch.draw(glass, vw / 2.35F, vh / 3.1F, vw / 5, vh / 2.2F);
            font.draw(batch, "MookMook", vw / 2.5F, vw / 6);
            font2.draw(batch, "An empty plastic glass from a bubble milk tea shop. " +"\n"+
                            "         He is finding a way back to the shop."
                    , vw / 2.9F, vw / 8);
        }
        if(count==12) {
            game.batch.draw(matcha, vw / 2.35F, vh / 3.1F, vw / 5, vh / 2.2F);
            font.draw(batch, "Matty", vw / 2.3F, vw / 6);
            font2.draw(batch, "   His full name is Matcha-sama. He has dark-green eyes, " +"\n"+
                            "and he has a lot of success with ladies. He has been voted " +"\n"+
                            "        the sexiest man in Trash World many times."
                    , vw / 3.2F, vw / 8);
        }
        if(count==13) {
            game.batch.draw(can, vw / 2.35F, vh / 3.1F, vw / 5, vh / 2.2F);
            font.draw(batch, "Oily Oiler", vw / 2.5F, vw / 6);
            font2.draw(batch, "                Oily Oiler is an oil can from the suburb. " +"\n"+
                            "After he had been emptied petrol, he got thrown away without care."
                    , vw / 3.45F, vw / 8);
        }
        if(count==14) {
            game.batch.draw(cardboard, vw / 2.35F, vh / 3.1F, vw / 5, vh / 2.2F);
            font.draw(batch, "Bokk Kung", vw / 2.5F, vw / 6);
            font2.draw(batch, "          A cardboard box that used to contain a dog. " +"\n"+
                            "He hopes to find a new dog and he’d bark \"Box-Box\" like a dog."
                    , vw / 3.3F, vw / 8);
        }
        if(count==15) {
            game.batch.draw(icecream, vw / 2.35F, vh / 3.1F, vw / 5, vh / 2.2F);
            font.draw(batch, "Izu-chan", vw / 2.3F, vw / 6);
            font2.draw(batch, "    Her full name is Izu - Pink Cremu. She is a sweetened frozen girl " +"\n"+
                            "you'll want to eat if you see one. Her cheek is pink and her hair is white."
                    , vw / 3.45F, vw / 8);
        }
        if(count==1||count==4||count==7||count==10||count==13){
            game.batch.draw(dangerBin, vw / 1.75F, vh / 3.2F, vw / 19.5F, vh / 14);
            font4.draw(batch, ": DANGER", vw / 1.9F, vw / 26);
        }
        if(count==2||count==5||count==8||count==11||count==14){
            game.batch.draw(recycleBin, vw / 1.75F, vh / 3.2F, vw / 19, vh / 14);
            font4.draw(batch, ": RECYCLE", vw / 1.9F, vw / 26);
        }
        if(count==3||count==6||count==9||count==12||count==15){
            game.batch.draw(wetBin, vw / 1.75F, vh / 3.2F, vw / 19, vh / 14);
            font4.draw(batch, ": WET", vw / 1.9F, vw / 26);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_RIGHT)){
            game.batch.draw(rh, vw/1.2F, vh / 2, vw / 45, vh / 24);
            count ++;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_LEFT)){
            game.batch.draw(lh, vw/5, vh / 2, vw / 45, vh / 24);
            count--;}
        if (Gdx.input.isKeyJustPressed(Input.Keys.O))currentStage++;
        if (Gdx.input.isKeyJustPressed(Input.Keys.I))currentStage--;
        if(currentStage>5)currentStage=5;
        if(currentStage<1)currentStage=1;

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setLazyScreen(screens.get(MenuScreen.class));
        }



        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Collection Screen");
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
        assets.load(COLLECTION_BG, TEXTURE);
        assets.load(COLLECTION_HEADER, TEXTURE);
        assets.load(COLLECTION_PACK, TEXTURE);
        assets.load(COLLECTION_LEFT, TEXTURE);
        assets.load(COLLECTION_RIGHT, TEXTURE);
        assets.load(COLLECTION_LEFT_H, TEXTURE);
        assets.load(COLLECTION_RIGHT_H, TEXTURE);
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
        assets.load(GLOBAL_ICON_BACK, TEXTURE);
        assets.load(GLOBAL_FOOTER_BAR, TEXTURE);
        //Bin
        assets.load(GAME_BIN_01, TEXTURE);
        assets.load(GAME_BIN_02, TEXTURE);
        assets.load(GAME_BIN_03, TEXTURE);
        assets.load(GAME_BIN_04, TEXTURE);
    }

    @Override
    public void afterLoad(Assets assets) {
        this.bg         = assets.get(COLLECTION_BG, TEXTURE);
        this.footer     = assets.get(GLOBAL_FOOTER_BAR, TEXTURE);
        this.header     = assets.get(COLLECTION_HEADER, TEXTURE);
        this.pack       = assets.get(COLLECTION_PACK, TEXTURE);
        this.l          = assets.get(COLLECTION_LEFT, TEXTURE);
        this.r          = assets.get(COLLECTION_RIGHT, TEXTURE);
        this.lh         = assets.get(COLLECTION_LEFT_H, TEXTURE);
        this.rh         = assets.get(COLLECTION_RIGHT_H, TEXTURE);
        this.back       = assets.get(GLOBAL_ICON_BACK, TEXTURE);
        //Danger Trashes
        this.bag        = assets.get(COLLECTION_DANGER_1, TEXTURE);
        this.spray      = assets.get(COLLECTION_DANGER_2, TEXTURE);
        this.cigar      = assets.get(COLLECTION_DANGER_3, TEXTURE);
        this.thinner    = assets.get(COLLECTION_DANGER_4, TEXTURE);
        this.can        = assets.get(COLLECTION_DANGER_5, TEXTURE);
        //Recycle Trashes
        this.paper      = assets.get(COLLECTION_RECYCLE_1, TEXTURE);
        this.note       = assets.get(COLLECTION_RECYCLE_2, TEXTURE);
        this.plastic    = assets.get(COLLECTION_RECYCLE_3, TEXTURE);
        this.glass      = assets.get(COLLECTION_RECYCLE_4, TEXTURE);
        this.cardboard  = assets.get(COLLECTION_RECYCLE_5, TEXTURE);
        //Wet Trashes
        this.popcorn    = assets.get(COLLECTION_WET_1, TEXTURE);
        this.donut      = assets.get(COLLECTION_WET_2, TEXTURE);
        this.curry      = assets.get(COLLECTION_WET_3, TEXTURE);
        this.matcha     = assets.get(COLLECTION_WET_4, TEXTURE);
        this.icecream   = assets.get(COLLECTION_WET_5, TEXTURE);
        //Bin
        this.dangerBin  = assets.get(GAME_BIN_01, TEXTURE);
        this.recycleBin = assets.get(GAME_BIN_02, TEXTURE);
        this.wetBin     = assets.get(GAME_BIN_03, TEXTURE);
        this.idkBin     = assets.get(GAME_BIN_04, TEXTURE);

        this.font       = assets.get8bitFont(40, Color.WHITE);
        this.font2      = assets.getSuperSpaceFont(24, Color.WHITE);
        this.font3       = assets.get8bitFont(24, Color.WHITE);
        this.font4       = assets.getSuperSpaceFont(24, Color.WHITE);
    }
}
