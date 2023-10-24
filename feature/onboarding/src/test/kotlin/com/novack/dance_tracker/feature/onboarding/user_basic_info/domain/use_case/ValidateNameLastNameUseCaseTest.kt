package com.novack.dance_tracker.feature.onboarding.user_basic_info.domain.use_case

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class ValidateNameLastNameUseCaseTest {

    private lateinit var useCase: ValidateNameLastNameUseCase

    @Before
    fun setUp() {
        useCase = ValidateNameLastNameUseCase()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `given an alphabetical name with less than 2 characters then should return ErrorLength`() {
        val result = useCase("a")

        assertEquals(listOf(ValidateNameLastNameUseCase.DtResult.Error.ErrorLength), result)
    }

    @Test
    fun `given an alphabetical name with more than  than 14 characters then should return ErrorLength`() {
        val result = useCase("aaaaaaaaaaaaaaa")

        assertEquals(listOf(ValidateNameLastNameUseCase.DtResult.Error.ErrorLength), result)
    }

    @Test
    fun `given a valid name then should return Success` () {
        val result = useCase("asdfdsf")

        assertEquals(listOf(ValidateNameLastNameUseCase.DtResult.Success), result)
    }
}