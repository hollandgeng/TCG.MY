package com.application.tcgmy.data

import com.application.tcgmy.CardsQuery
import com.application.tcgmy.domain.SimpleCard

fun CardsQuery.Card.toSimpleCard(): SimpleCard {
    return SimpleCard (
        code = code,
        name = name,
        rarity = rarity,
        price = price,
        currency = currency,
        image = image ?: "No Image"
    )
}