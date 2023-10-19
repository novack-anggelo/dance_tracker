package com.novack.dance_tracker.feature.onboarding.user_basic_info.domain.use_case

import javax.inject.Inject


class ValidateNameLastNameUseCase @Inject constructor() {
    operator fun invoke(nameLastName: String): List<DtResult> {
        val result = mutableListOf<DtResult>()

        if (nameLastName.length !in 2..14) result.add(DtResult.Error.ErrorLength)
        if (!containsOnlyAlphabeticalAndSpaces(nameLastName)) result.add(DtResult.Error.ErrorCharacters)
        if (!result.any { it is DtResult.Error }) result.add(DtResult.Success)

        return result
    }

    // TODO this might be reused in other places, refactor if necessary
    private fun containsOnlyAlphabeticalAndSpaces(input: String): Boolean {
        val regex = Regex("^(?=.*[a-zA-Z])[a-zA-Z ]+$")
        return regex.matches(input) && !input.contains("  ")
    }

    sealed interface DtResult {
        data object Success : DtResult

        sealed interface Error : DtResult {
            data object ErrorLength : Error
            data object ErrorCharacters : Error
        }
    }
}