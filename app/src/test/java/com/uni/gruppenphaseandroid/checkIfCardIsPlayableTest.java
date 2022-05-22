package com.uni.gruppenphaseandroid;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.ImageView;

import com.uni.gruppenphaseandroid.Cards.Card;
import com.uni.gruppenphaseandroid.Cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Field;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.FigureUI;
import com.uni.gruppenphaseandroid.playingfield.FigureUIimpl;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;
import com.uni.gruppenphaseandroid.playingfield.Typ;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class checkIfCardIsPlayableTest {
    PlayingField playingField;
    View view;
    Figure figure1;
    FigureUI figureUI1;
    Figure figure2;
    FigureUI figureUI2;
    ImageView imageView;

    @Before
    public void setUp(){
        view = mock(View.class);
        imageView = mock(ImageView.class);
        when(view.findViewWithTag(anyString())).thenReturn(imageView);

        playingField = new PlayingField(view);
        GameManager.getInstance().setPlayingField(playingField);
        Field startingAreaField = playingField.getRedStartingField().getPreviousStartingArea();
        startingAreaField.setCurrentFigure(new Figure());
        startingAreaField.getPreviousField().setCurrentFigure(new Figure());
        figureUI1 = mock(FigureUIimpl.class);
        figureUI2 = mock(FigureUIimpl.class);
        figure1 = new Figure(1, Color.RED, playingField.getRedStartingField(), Typ.JERK, figureUI1);
    }

    @After
    public void tearDown(){
        view = null;
        playingField = null;
        imageView = null;
    }

    /*
    TODO: Exceptiontest Figure is null
     */

    @Test
    public void checkMAGNET_True(){
        figure2 = new Figure(2, Color.BLUE, playingField.getBlueStartingField().getNextField(), Typ.KING, figureUI2);
        Card card = new Card(Cardtype.MAGNET);

        boolean res = card.checkIfCardIsPlayable(figure1, -1, null);

        Assert.assertTrue(res);
    }

    @Test
    public void checkMAGNET_NoOtherFigureOnPlayingfield(){
        Card card = new Card(Cardtype.MAGNET);

        boolean res = card.checkIfCardIsPlayable(figure1, -1, null);

        Assert.assertFalse(res);
    }

    @Test
    public void checkNumberCard_True(){
        Card card = new Card(Cardtype.TWO);

        boolean res = card.checkIfCardIsPlayable(figure1, -1, null);

        Assert.assertTrue(res);
    }

    /*
    TODO: checkNumberCard_triesToMoveOverGoal
     */

    /*
    TODO: Exceptiontest checkNonEffectCard
     */

    @Test
    public void checkFOUR_PLUSMINUS_effect1_True(){
        Card card = new Card(Cardtype.FOUR_PLUSMINUS);

        boolean res = card.checkIfCardIsPlayable(figure1, 1, null);

        Assert.assertTrue(res);
    }

    @Test
    public void checkFOUR_PLUSMINUS_effect2_True(){
        Card card = new Card(Cardtype.FOUR_PLUSMINUS);

        boolean res = card.checkIfCardIsPlayable(figure1, 2, null);

        Assert.assertTrue(res);
    }

    @Test
    public void checkONETOSEVEN_effect1_True(){
        Card card = new Card(Cardtype.ONETOSEVEN);

        boolean res = card.checkIfCardIsPlayable(figure1, 1, null);

        Assert.assertTrue(res);
    }

    @Test
    public void checkONETOSEVEN_effect2_True(){
        Card card = new Card(Cardtype.ONETOSEVEN);

        boolean res = card.checkIfCardIsPlayable(figure1, 2, null);

        Assert.assertTrue(res);
    }

    @Test
    public void checkONEORELEVEN_START_effect1_True(){
        figure1.setCurrentField(playingField.getRedStartingField().getPreviousStartingArea());
        Card card = new Card(Cardtype.ONEORELEVEN_START);

        boolean res = card.checkIfCardIsPlayable(figure1, 0, null);

        Assert.assertTrue(res);
    }

    @Test
    public void checkONEORELEVEN_START_effect1_False(){
        Card card = new Card(Cardtype.ONEORELEVEN_START);

        boolean res = card.checkIfCardIsPlayable(figure1, 0, null);

        Assert.assertFalse(res);
    }

    @Test
    public void checkONEORELEVEN_START_effect2_True(){
        Card card = new Card(Cardtype.ONEORELEVEN_START);

        boolean res = card.checkIfCardIsPlayable(figure1, 2, null);

        Assert.assertTrue(res);
    }

    @Test
    public void checkONEORELEVEN_START_effect3_True(){
        Card card = new Card(Cardtype.ONEORELEVEN_START);

        boolean res = card.checkIfCardIsPlayable(figure1, 11, null);

        Assert.assertTrue(res);
    }

    @Test
    public void checkTHIRTEEN_START_effect1_True(){
        figure1.setCurrentField(playingField.getRedStartingField().getPreviousStartingArea());
        Card card = new Card(Cardtype.THIRTEEN_START);

        boolean res = card.checkIfCardIsPlayable(figure1, 0, null);

        Assert.assertTrue(res);
    }

    @Test
    public void checkTHIRTEEN_START_effect2_True(){
        Card card = new Card(Cardtype.THIRTEEN_START);

        boolean res = card.checkIfCardIsPlayable(figure1, 0, null);

        Assert.assertTrue(res);
    }

    /*
    TODO: Exceptiontest checkEffectCard
     */

    @Test
    public void checkSWITCH_True(){
        figure2 = new Figure(2, Color.BLUE, playingField.getBlueStartingField().getNextField(), Typ.KING, figureUI2);
        Card card = new Card(Cardtype.SWITCH);

        boolean res = card.checkIfCardIsPlayable(figure1, -1, figure2);

        Assert.assertTrue(res);
    }

    @Test
    public void checkSWITCH_False_figure1InStartingAreaField(){
        figure1.setCurrentField(playingField.getRedStartingField().getPreviousStartingArea());
        figure2 = new Figure(2, Color.BLUE, playingField.getBlueStartingField().getNextField(), Typ.KING, figureUI2);
        Card card = new Card(Cardtype.SWITCH);

        boolean res = card.checkIfCardIsPlayable(figure1, -1, figure2);

        Assert.assertFalse(res);
    }

    @Test
    public void checkSWITCH_False_figure1InGoalField(){
        figure1.setCurrentField(playingField.getRedStartingField().getNextGoalField());
        figure2 = new Figure(2, Color.BLUE, playingField.getBlueStartingField().getNextField(), Typ.KING, figureUI2);
        Card card = new Card(Cardtype.SWITCH);

        boolean res = card.checkIfCardIsPlayable(figure1, -1, figure2);

        Assert.assertFalse(res);
    }

    @Test
    public void checkSWITCH_False_figure2InStartingField(){
        figure2 = new Figure(2, Color.BLUE, playingField.getBlueStartingField().getPreviousStartingArea(), Typ.KING, figureUI2);
        Card card = new Card(Cardtype.SWITCH);

        boolean res = card.checkIfCardIsPlayable(figure1, -1, figure2);

        Assert.assertFalse(res);
    }

    @Test
    public void checkSWITCH_False_figure2InGoalField(){
        figure2 = new Figure(2, Color.BLUE, playingField.getBlueStartingField().getNextGoalField(), Typ.KING, figureUI2);
        Card card = new Card(Cardtype.SWITCH);

        boolean res = card.checkIfCardIsPlayable(figure1, -1, figure2);

        Assert.assertFalse(res);
    }

    /*
    TODO: Exceptiontest checkSwitchCard
     */
}
