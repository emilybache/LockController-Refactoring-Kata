typealias Action = (Subscription, MassiveDataChangedEvent) -> Unit

class Subscription(private val connectionSession: IMassiveSession) {

    var publishingEnabled: Boolean = false
    var publishingInterval: Int = 0

    fun create() {
        TODO("can't be called from a unit test")
    }

    fun registerCallback(subscriptionDataChange: Action) {
        TODO("can't be called from a unit test")
    }

    fun delete() {
        TODO("can't be called from a unit test")
    }
}
