package com.application.tcgmy.constants

import com.application.tcgmy.type.GameCode

enum class GameTitle(val code: GameCode) {
    Default(GameCode.YGO),
    YGO(GameCode.YGO),
    POC(GameCode.POC),
    VG(GameCode.VG),
    OPC(GameCode.OPC),
    WS(GameCode.WS);

    fun fromGameCode(gameCode: GameCode): GameTitle {
        var result = Default

        for (title in GameTitle.values()) {
            if (gameCode == title.code)
            {
                result = title
            }
        }

        return result
    }
}
