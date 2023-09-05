package com.michael.stats;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

import org.stats.AnalyzerConfigurationException;
import org.stats.RatingAnalyzer;

import java.lang.reflect.Array;
import java.util.*;
import static java.util.Arrays.sort;

public class HighSpeedRatingAnalyzer implements RatingAnalyzer {
    // static methods (if any)
    // instance variables
    int[] ratings;

    // constructors
    public HighSpeedRatingAnalyzer(int[] ratings) throws IllegalArgumentException{
        setRatings(ratings);
    }

    // accessor methods
    public int[] getRatings() {
        return ratings;
    }

    public void setRatings(int[] ratings) {
        this.ratings = ratings;
    }

    // business methods

    @Override
    public double mean() {
        double result = 0.0;                // declare variable result starting at 0.0
        double sum = 0.0;                   // used to calculate the sum of numbers below and divide by length

        // get sum of all numbers in the array
        for (int rating : getRatings()) {        // for each rating in ratings
            sum += rating;                  // add each rating to the sum
        }
        // divide by the length of the array
        result = sum / (getRatings().length);    // divide the total sum by the length of the ratings array
        // return the result
        return result;
    }

    @Override
    public double median() {
        // find the length of the array
        double result = 0.0;
        double midDownOne;                      // used if array is even, assigned to upper lower number
        double midUpOne;                        // used if array is even, assigned to the upper middle number
        int length = getRatings().length;       // find the length of the array and assign it to 'length'

        // sort the array of ints
        Arrays.sort(getRatings());

        // find the middle of the array
        int middle = length / 2;                // assign 'middle' to half of the array length (if even, it rounds up)

        if (length % 2 == 0){                   // if length is divisible by 2/meaning if length is even:
            // if even, need to use the average of the 2 middle numbers
            midDownOne = (ratings[middle-1]);   // assign 'midDownOne' to half the array length(rounded up) minus 1
            midUpOne = (ratings[middle]);       // assign 'midUpOne' to half the array length (rounded up)

            result = (midDownOne + midUpOne) / 2.0; // add the two middle numbers and divide by 2
        }
        else{
            // if odd super simple, just use the middle number
            result = getRatings()[middle];      // result is assigned to the middle number
        }
        return result;
    }

    @Override
    public int[] mode() {
        // HashMap to store frequency of each number in ratings without duplicates
        // K=number from int[] ratings / V=number of appearances
        Map<Integer, Integer> occurrencesMap = new HashMap<>();

        // Calculate the frequency of each number in ratings and add to occurrencesMap
        for (int rating : ratings) {
            occurrencesMap.put(rating, occurrencesMap.getOrDefault(rating, 0) + 1);
        }

        // Create a stream of maxFrequency from occurrencesMap to return the max value or 0 if no value present
        int maxFrequency = occurrencesMap.values().stream()
                .mapToInt(Integer::intValue)          // convert occurrencesMap to an IntStream (int)
                .max()                                // returns the max freq. value
                .orElse(0);                     // if value is present, returns the value, otherwise returns 0

        // Find all elements with the maximum frequency (will be at least 1)
        List<Integer> modes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> value : occurrencesMap.entrySet()) {  // for each Map entry in frequenciesMap
            if (value.getValue() == maxFrequency) {     // get the Value field if it equals the maxFrequency
                modes.add(value.getKey());              // add the Key field to the modes ArrayList
            }
        }

        // Convert the list of modes to an array using a stream() :)
        int[] result = modes.stream()
                .mapToInt(Integer::intValue)
                .sorted()
                .toArray();

        return result;
    }
}