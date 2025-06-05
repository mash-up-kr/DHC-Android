package com.dhc.designsystem

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object FontSizeTokens {
    val H0 = 32.sp
    val H1 = 28.sp
    val H2 = 24.sp
    val H3 = 20.sp
    val H4 = 18.sp
    val H5 = 16.sp
    val H6 = 15.sp
    val H7 = 14.sp
    val H8 = 13.sp
    val Body0 = 28.sp
    val Body1 = 20.sp
    val Body2 = 18.sp
    val Body3 = 16.sp
    val Body4 = 15.sp
    val Body5 = 14.sp
    val Body6 = 13.sp
    val Body7 = 12.sp
}

/**
 * 정수로 받으면 추가 예정
 */
object LineHeightToken {

}

object LetterSpacingToken {
    val TitleLetterSpacing = (-1.0).sp
    val BodyLetterSpacing = (-0.5).sp

}

object DhcTypoTokens {
    private val typoFontFamily = FontFamily(
        Font(R.font.wantedsans_bold, FontWeight.W700),
        Font(R.font.wantedsans_semibold, FontWeight.W600),
        Font(R.font.wantedsans_medium, FontWeight.W500),
        Font(R.font.wantedsans_regular, FontWeight.W400),
    )

    val TitleH0 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = FontSizeTokens.H0,
    )

    val TitleH1 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = FontSizeTokens.H1,
    )

    val TitleH2 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = FontSizeTokens.H2,
    )

    val TitleH3 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = FontSizeTokens.H3,
    )

    val TitleH4 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = FontSizeTokens.H4,
    )

    val TitleH5 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = FontSizeTokens.H5,
    )

    val TitleH6 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = FontSizeTokens.H6,
    )

    val TitleH7 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = FontSizeTokens.H7,
    )

    val TitleH8 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = FontSizeTokens.H8,
    )

    val Body0 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = FontSizeTokens.Body0,
    )

    val Body1 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = FontSizeTokens.Body1,
    )

    val Body2 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = FontSizeTokens.Body2,
    )

    val Body3 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = FontSizeTokens.Body3,
    )

    val Body4 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = FontSizeTokens.Body4,
    )

    val Body5 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = FontSizeTokens.Body5,
    )

    val Body6 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = FontSizeTokens.Body6,
    )

    val Body7 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = FontSizeTokens.Body7,
    )
}
