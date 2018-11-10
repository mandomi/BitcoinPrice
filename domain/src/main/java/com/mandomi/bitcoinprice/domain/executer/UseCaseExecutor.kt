package com.mandomi.bitcoinprice.domain.executer

import io.reactivex.Scheduler

interface UseCaseExecutor {
    val scheduler: Scheduler
}