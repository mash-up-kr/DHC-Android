package com.dhc.home.model

enum class MissionDifficulty(val difficultyNo: Int, val displayName: String) {
    EASY(1, "Easy"),
    MEDIUM(2, "Medium"),
    HARD(3, "Hard");

    companion object {
        fun from(value: Int): String = when (value) {
            1 -> EASY.displayName
            2 -> MEDIUM.displayName
            3 -> HARD.displayName
            else -> EASY.displayName
        }
    }
}