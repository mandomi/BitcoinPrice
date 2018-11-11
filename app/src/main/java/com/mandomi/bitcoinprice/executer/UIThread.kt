package com.mandomi.bitcoinprice.executer

import com.mandomi.bitcoinprice.domain.executer.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread : PostExecutionThread {
    override val scheduler: Scheduler by lazy { AndroidSchedulers.mainThread() }
}