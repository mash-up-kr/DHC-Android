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


object LineHeightToken {
    val H0 = 44.sp
    val H1 = 38.sp
    val H2 = 32.sp
    val H3 = 28.sp
    val H4 = 24.sp
    val H5 = 22.sp
    val H6 = 20.sp
    val H7 = 20.sp
    val H8 = 18.sp
    val Body0 = 40.sp
    val Body1 = 28.sp
    val Body2 = 26.sp
    val Body3 = 24.sp
    val Body4 = 22.sp
    val Body5 = 20.sp
    val Body6 = 20.sp
    val Body7 = 18.sp
}

object DhcTypoTokens {
    val typoFontFamily = FontFamily(
        Font(R.font.wantedsans_bold, FontWeight.W700),
        Font(R.font.wantedsans_semibold, FontWeight.W600),
        Font(R.font.wantedsans_medium, FontWeight.W500),
        Font(R.font.wantedsans_regular, FontWeight.W400),
    )

    val TitleH0 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = FontSizeTokens.H0,
        lineHeight = LineHeightToken.H0,
        letterSpacing = (-0.4).sp
    )

    val TitleH1 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = FontSizeTokens.H1,
        lineHeight = LineHeightToken.H1,
        letterSpacing = (-0.4).sp
    )

    val TitleH2 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = FontSizeTokens.H2,
        lineHeight = LineHeightToken.H2,
        letterSpacing = (-0.2).sp
    )

    val TitleH2_1 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = FontSizeTokens.H2,
        lineHeight = LineHeightToken.H2,
        letterSpacing = (-0.2).sp
    )

    val TitleH3 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = FontSizeTokens.H3,
        lineHeight = LineHeightToken.H3,
        letterSpacing = (-0.2).sp
    )

    val TitleH4 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = FontSizeTokens.H4,
        lineHeight = LineHeightToken.H4,
        letterSpacing = (-0.1).sp
    )

    val TitleH4_1 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = FontSizeTokens.H4,
        lineHeight = LineHeightToken.H4,
        letterSpacing = (-0.1).sp
    )

    val TitleH5 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = FontSizeTokens.H5,
        lineHeight = LineHeightToken.H5,
        letterSpacing = (-0.1).sp
    )

    val TitleH5_1 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = FontSizeTokens.H5,
        lineHeight = LineHeightToken.H5,
        letterSpacing = (-0.1).sp
    )

    val TitleH6 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = FontSizeTokens.H6,
        lineHeight = LineHeightToken.H6,
        letterSpacing = 0.sp
    )

    val TitleH7 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = FontSizeTokens.H7,
        lineHeight = LineHeightToken.H7,
        letterSpacing = 0.sp
    )

    val TitleH8 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = FontSizeTokens.H8,
        lineHeight = LineHeightToken.H8,
        letterSpacing = 0.sp
    )

    val Body0 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = FontSizeTokens.Body0,
        lineHeight = LineHeightToken.Body0,
        letterSpacing = (-0.2).sp
    )

    val Body1 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = FontSizeTokens.Body1,
        lineHeight = LineHeightToken.Body1,
        letterSpacing = (-0.2).sp
    )

    val Body2 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = FontSizeTokens.Body2,
        lineHeight = LineHeightToken.Body2,
        letterSpacing = (-0.2).sp
    )

    val Body3 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = FontSizeTokens.Body3,
        lineHeight = LineHeightToken.Body3,
        letterSpacing = (-0.1).sp
    )
    val Body4 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = FontSizeTokens.Body4,
        lineHeight = LineHeightToken.Body4,
        letterSpacing = 0.sp
    )

    val Body5 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = FontSizeTokens.Body5,
        lineHeight = LineHeightToken.Body5,
        letterSpacing = 0.sp
    )

    val Body6 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = FontSizeTokens.Body6,
        lineHeight = LineHeightToken.Body6,
        letterSpacing = 0.sp
    )

    val Body7 = TextStyle(
        fontFamily = typoFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = FontSizeTokens.Body7,
        lineHeight = LineHeightToken.Body7,
        letterSpacing = 0.sp
    )
}
