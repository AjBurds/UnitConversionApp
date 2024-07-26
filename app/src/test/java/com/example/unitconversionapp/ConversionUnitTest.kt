package com.example.unitconversionapp

import org.junit.Assert.assertEquals
import org.junit.Test

class ConversionUnitTest {

    private val tolerance = 0.001

    @Test
    fun testTemperatureConversion() {
        val input = 0.0
        val expectedOutput = 32.0
        val output = convertTemperature(input)
        assertEquals(expectedOutput, output, tolerance)
    }

    @Test
    fun testLengthConversion() {
        val input = 1.0
        val expectedOutput = 3.28084
        val output = convertLength(input)
        assertEquals(expectedOutput, output, tolerance)
    }

    @Test
    fun testWeightConversion() {
        val input = 1.0
        val expectedOutput = 2.20462
        val output = convertWeight(input)
        assertEquals(expectedOutput, output, tolerance)
    }

    private fun convertTemperature(value: Double): Double {
        return value * 9 / 5 + 32
    }

    private fun convertLength(value: Double): Double {
        return value * 3.28084
    }

    private fun convertWeight(value: Double): Double {
        return value * 2.20462
    }
}
