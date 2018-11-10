package com.mandomi.bitcoinprice.executer

import com.mandomi.bitcoinprice.domain.executer.UseCaseExecutor
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class ExecutorThread : UseCaseExecutor {
    override val scheduler: Scheduler
        get() = Schedulers.io()
}