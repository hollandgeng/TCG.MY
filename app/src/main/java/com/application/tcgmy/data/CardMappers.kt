package com.application.tcgmy.data

import com.application.tcgmy.CardsQuery
import com.application.tcgmy.GamesQuery
import com.application.tcgmy.YgoCardDetailsQuery
import com.application.tcgmy.constants.BannedStatus
import com.application.tcgmy.constants.GameTitle
import com.application.tcgmy.constants.internal.Currency
import com.application.tcgmy.constants.internal.YgoDetailedCard
import com.application.tcgmy.constants.internal.Pendulum
import com.application.tcgmy.constants.internal.SimpleCard

fun CardsQuery.Card.toSimpleCard(): SimpleCard {
    return SimpleCard (
        code = code,
        name = jpName,
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
        image = image,
        score = score,
        game = GameTitle.fromGameCode(game.code)
    )
}

fun YgoCardDetailsQuery.Card.toYgoDetailedCard(): YgoDetailedCard {
    return YgoDetailedCard(
        code = code,
        jpName = jpName,
        engName = detail.engName,
        cardType = detail.cardType,
        property = detail.property,
        attribute = detail.attribute,
        types = detail.types,
        level = detail.level,
        linkArrows = detail.linkArrows,
        attack = detail.attack,
        defence = detail.defence,
        link = detail.link,
        effectTypes = detail.effectTypes,
        effect = detail.effect,
        pendulum = Pendulum(
            effectTypes = detail.pendulum.effectTypes,
            scale = detail.pendulum.scale,
            effect = detail.pendulum.effect
        ),
        status = BannedStatus.fromStatus(detail.status)
    )
}

fun GamesQuery.Game.toGame(): Game {
    return Game(
        code = GameTitle.fromGameCode(code),
        title = title,
        imageUrl = image
    )
}