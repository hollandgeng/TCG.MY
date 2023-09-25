package com.application.tcgmy.data

import com.application.tcgmy.CardsQuery
import com.application.tcgmy.GamesQuery
import com.application.tcgmy.domain.Currency
import com.application.tcgmy.domain.SimpleCard

fun CardsQuery.Card.toSimpleCard(): SimpleCard {
    return SimpleCard (
        code = code,
        name = name,
        rarity = rarity,
        price = price,
        currency = Currency(
            code = currency.code,
            numericCode = currency.numericCode,
            fraction = currency.fraction,
            grapheme = currency.grapheme,
            template = currency.template,
            decimal = currency.decimal,
            thousand = currency.thousand
        ),
        image = image ?: "No Image",
        score = score ?: -1
    )
}

fun GamesQuery.Game.toGame(): Game {
    return Game(
//        id = GameTitle.fromGameCode(),
        title = title,
        imageUrl = image
    )
}