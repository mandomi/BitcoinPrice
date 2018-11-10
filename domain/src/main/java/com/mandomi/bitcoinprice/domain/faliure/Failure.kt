package com.mandomi.bitcoinprice.domain.faliure

sealed class Failure(override val message: String) : Throwable(message) {
    class NetworkConnectionError(message: String, throwable: Throwable) : Failure("$message: $throwable")
    class ServerError(message: String, throwable: Throwable) : Failure("$message: $throwable")
    class HttpError(message: String) : Failure(message)
    class NotFoundError(message: String) : Failure(message)

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure(message: String) : Failure(message)
}