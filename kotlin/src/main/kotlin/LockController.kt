class LockController(connection: IMassiveDbConnection) {
    private val _connection: IMassiveDbConnection
    private val _subscription: Subscription
    private val _lockedItems: MutableMap<String, MutableList<IMassiveVariant>> = mutableMapOf()

    init {
        _connection = connection

        // register a listener (Subscription) for changes in the Massive Database
        _subscription = Subscription(_connection.session)
        _subscription.publishingEnabled = true
        _subscription.publishingInterval = 500
        _subscription.registerCallback(::subscriptionDataChange)
        _subscription.create()
    }

    /**
     * This is called by the Massive Database when data is changed,
     * because we registered it as a callback earlier.
     */
    private fun subscriptionDataChange(subscription: Subscription, e: MassiveDataChangedEvent): Unit {
        val mObjectId = e.mId

        if (e.type == "Lock") {
            this.lockObject(mObjectId)
        } else if (e.type == "Unlock") {
            this.unlockObject(mObjectId)
        }
    }

    fun lockObject(mObjectId: String): Boolean {
        var path = _connection.getPathTo(mObjectId)
        var fullMObject = _connection.fullObject(path)
        var inParameters = listOf<IMassiveVariant>()
        var outParameters = mutableListOf<IMassiveVariant>()
        var errors = mutableListOf<String>()
        var statusCode = _connection.callMassiveMethod(
            path = path,
            wangleLocation = _connection.wangle(fullMObject),
            inParameters = inParameters,
            outParameters = outParameters,
            errors = errors
        )

        if (errors.size == 0 && statusCode == 0) {
            _lockedItems.put(mObjectId, outParameters)
            return true
        } else {
            // TODO: report errors
            return false
        }
    }

    fun unlockObject(mObjectId: String): Boolean {
        var path = _connection.getPathTo(mObjectId)
        var fullMObject = _connection.fullObject(path)
        var inParameters = listOf<IMassiveVariant>()
        var outParameters = mutableListOf<IMassiveVariant>()
        var errors = mutableListOf<String>()
        var statusCode = _connection.callMassiveMethod(
            path = path,
            wangleLocation = _connection.wangle(fullMObject),
            inParameters = inParameters,
            outParameters = outParameters,
            errors = errors
        )

        if (errors.size == 0 && statusCode == 0 && _lockedItems.containsKey(mObjectId)) {
            _lockedItems.remove(mObjectId)
            return true
        } else {
            // TODO: report errors
            return false
        }
    }

    /**
     * When we dispose this object we should ensure nothing is left locked
     * and that we remove the Subscription from the Massive Database
     */
    fun dispose() {
        if (_lockedItems.size > 0) {
            for (item in _lockedItems) {
                unlockObject(item.key)
            }
        }

        _subscription.delete()
    }
}
