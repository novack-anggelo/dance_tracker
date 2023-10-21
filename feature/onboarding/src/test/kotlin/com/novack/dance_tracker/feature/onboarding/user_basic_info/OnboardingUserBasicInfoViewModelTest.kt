package com.novack.dance_tracker.feature.onboarding.user_basic_info

import com.novack.dance_tracker.core.model.data.Gender
import com.novack.dance_tracker.feature.onboarding.user_basic_info.domain.use_case.ValidateNameLastNameUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import org.junit.Assert.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.novack.dance_tracker.core.testing.util.MainDispatcherRule
import kotlinx.datetime.Clock

class OnboardingUserBasicInfoViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: OnboardingUserBasicInfoViewModel
    private val validateNameLastNameUseCaseMock: ValidateNameLastNameUseCase = mockk()

    @Before
    fun setUp() {
        viewModel = OnboardingUserBasicInfoViewModel(validateNameLastNameUseCaseMock)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `given valid name when UiEvent is nameChange then the fieldState name should be updated`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()){ viewModel.fieldsState.collect() }
        val name = "valid name"

        every { validateNameLastNameUseCaseMock(name) } returns listOf(ValidateNameLastNameUseCase.DtResult.Success)

        viewModel.onEvent(UiEvent.OnNameChange(name))

        val state = viewModel.fieldsState.value
        assertEquals(name, state.name)

        collectJob.cancel()
    }

    @Test
    fun `given invalid name when UiEvent is nameChange then the fieldState name should NOT be updated`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()){ viewModel.fieldsState.collect() }
        val name = "7&f name"

        every { validateNameLastNameUseCaseMock(name) } returns listOf(ValidateNameLastNameUseCase.DtResult.Error.ErrorCharacters)

        viewModel.onEvent(UiEvent.OnNameChange(name))

        val state = viewModel.fieldsState.value
        assertEquals(null, state.name)

        collectJob.cancel()
    }

    @Test
    fun `given valid last name when UiEvent is lastNameChange then the fieldState lastName should be updated`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()){ viewModel.fieldsState.collect() }
        val name = "valid name"

        every { validateNameLastNameUseCaseMock(name) } returns listOf(ValidateNameLastNameUseCase.DtResult.Success)

        viewModel.onEvent(UiEvent.OnLastNameChange(name))

        val state = viewModel.fieldsState.value
        assertEquals(name, state.lastName)

        collectJob.cancel()
    }

    @Test
    fun `given invalid last name when UiEvent is lastNameChange then the fieldState lastName should NOT be updated`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()){ viewModel.fieldsState.collect() }
        val name = "7&f name"

        every { validateNameLastNameUseCaseMock(name) } returns listOf(ValidateNameLastNameUseCase.DtResult.Error.ErrorCharacters)

        viewModel.onEvent(UiEvent.OnLastNameChange(name))

        val state = viewModel.fieldsState.value
        assertEquals(null, state.lastName)

        collectJob.cancel()
    }

    // TODO when added calendar validations this should change
    @Test
    fun `given valid dob when UiEvent is onDoBChange then the fieldState dob should be updated`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()){ viewModel.fieldsState.collect() }
        val dob = Clock.System.now().toEpochMilliseconds()

        viewModel.onEvent(UiEvent.OnDobChange(dob))

        val state = viewModel.fieldsState.value

        assertNotNull(state.dob)
        assertEquals(dob, state.dob?.toEpochMilliseconds())

        collectJob.cancel()
    }

    @Test
    fun `given invalid dob when UiEvent is onDoBChange then the fieldState dob should NOT be updated`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()){ viewModel.fieldsState.collect() }

        viewModel.onEvent(UiEvent.OnDobChange(null))

        val state = viewModel.fieldsState.value

        assertNull(state.dob)

        collectJob.cancel()
    }

    @Test
    fun `given valid gender when UiEvent is onGenderChange then the fieldState gender should be updated`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()){ viewModel.fieldsState.collect() }
        val gender = Gender.MALE

        viewModel.onEvent(UiEvent.OnGenderChange(gender))

        val state = viewModel.fieldsState.value

        assertEquals(Gender.MALE, state.gender)

        collectJob.cancel()
    }
}