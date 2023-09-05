package com.michael.stats;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.stats.AnalyzerConfigurationException;
import org.stats.RatingAnalyzer;
import com.michael.stats.HighSpeedRatingAnalyzer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HighSpeedRatingAnalyzerTest {
    // create an array of the test numbers to adjust all methods below collectively
    public final int[] ratings = {5, 3, 3, 1, 4, 3, 4, 5, 3, 4, 3, 2};      // Weekend ratings

    // create a class level analyzer to use the class level ratings array
    RatingAnalyzer analyzer = RatingAnalyzer.newInstance(ratings);

    @Before
    public void setUp() throws AnalyzerConfigurationException {

    }

    @Test
    public void modeTest_shouldShowCorrectly_whenUsingTheClassRatingsAndAnalyzer() {
        assertEquals(1, analyzer.mode().length);
        assertEquals(3, Array.get(analyzer.mode(), 0));
    }

    @Test
    public void modeTest_showOneResult_WhenOnlyOneNumberProvided() {
        int[] ratings2 = {3};
        RatingAnalyzer analyzer2 = RatingAnalyzer.newInstance(ratings2);
        assertEquals(1, analyzer.mode().length);
    }

    @Test
    public void modeTest_shouldReturnThreeModes_whenThreeNumbersEnteredOnce() {
        int[] ratings2 = {44, 55, 8};
        RatingAnalyzer analyzer2 = RatingAnalyzer.newInstance(ratings2);
        assertArrayEquals(new int[] {8, 44, 55}, analyzer2.mode());
        assertEquals(3, analyzer2.mode().length);
//        assertEquals(11, Array.get(analyzer2.mode(),0));
//        assertEquals(30, Array.get(analyzer2.mode(),2));
    }

    @Test
    public void modeTest_shouldReturnTwoModes_whenThereAreTwoEqualMaxFrequency() {
        int[] ratings2 = {2, 5, 2, 3, 1, 5, 5, 2};
        RatingAnalyzer analyzer2 = RatingAnalyzer.newInstance(ratings2);
        assertEquals(2, analyzer2.mode().length);
        assertEquals(2, Array.get(analyzer2.mode(),0));
        assertEquals(5,Array.get(analyzer2.mode(),1));
    }

    @Test
    public void modeTest_shouldReturnOneMode_whenOnlyOneEqualsMaxFrequency() {
        int[] ratings2 = {2, 5, 2, 3, 1, 4, 5, 2};
        RatingAnalyzer analyzer2 = RatingAnalyzer.newInstance(ratings2);
        assertEquals(1, analyzer2.mode().length);
        assertEquals(2,Array.get(analyzer2.mode(),0));
    }

    @Test
    public void medianTest_shouldReturnAccurateMedian_whenIntsProvidedInRating() {
        assertEquals(3,analyzer.median(), .01);
    }

    @Test
    public void medianTest_shouldReturnAverageOfMiddleNumbers_whenTwoDifferentMiddleNumbers() {
        int[] ratings2 = {1, 5, 6, 9, 23, 30};
        RatingAnalyzer analyzer2 = RatingAnalyzer.newInstance(ratings2);
        assertEquals(7.5, analyzer2.median(),.01);
    }

    @Test
    public void medianTest_shouldReturnMiddleValue_whenProvidedMultipleNumbers() {
        int[] ratings2 = {7, 5, 8, 6, 1};
        RatingAnalyzer analyzer2 = RatingAnalyzer.newInstance(ratings2);
        assertEquals(6, analyzer2.median(), .01);
    }

    @Test
    public void medianTest_shouldReturnSameNumberEntered_whenOnlyOneNumberUsed() {
        int[] ratings2 = {4};
        RatingAnalyzer analyzer2 = RatingAnalyzer.newInstance(ratings2);
        assertEquals(4, analyzer2.median(), .01);
    }

    @Test
    public void meanTest_shouldReturnCorrect_whenUsingClassRatingsAndAnalyzer() {
        assertEquals(3.33, analyzer.mean(),.01);
    }

    @Test
    public void meanTest_shouldReturnSame_whenOnlyOneIntProvided() {
        int[] ratings = {5};
        RatingAnalyzer analyzer = RatingAnalyzer.newInstance(ratings);
        assertEquals(5, analyzer.mean(),.01);
    }

    @Test
    public void meanTest_shouldReturnAccurateMean_whenIntsProvidedInRating() {
        int[] ratings = { 1, 2, 3, 5, 7, 9 };
        RatingAnalyzer analyzer = RatingAnalyzer.newInstance(ratings);
        assertEquals(4.5, analyzer.mean(), .01);
    }


}