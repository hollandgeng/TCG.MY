package com.application.tcgmy.constants

import com.application.tcgmy.type.BanStatus

enum class BannedStatus(val status: BanStatus) {
    UNLIMITED(BanStatus.UNLIMITED),
    SEMI_LIMITED(BanStatus.SEMI_LIMITED),
    LIMITED(BanStatus.LIMITED),
    FORBIDDEN(BanStatus.FORBIDDEN);

    companion object {
        fun fromStatus(banStatus: BanStatus): BannedStatus {
            var result = UNLIMITED

            for (status in BannedStatus.values()) {
                if (banStatus == status.status)
                {
                    result = status
                }
            }

            return result
        }
    }
}