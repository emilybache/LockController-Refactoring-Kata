interface IMassiveDbConnection {
    var session: IMassiveSession
    fun getPathTo(mObject: String): MassiveLocation
    fun fullObject(path: IMassiveLocation): IMassiveObject
    fun wangle(fullMObject: IMassiveObject): MassiveLocation

    fun callMassiveMethod(
        path: MassiveLocation,
        wangleLocation: MassiveLocation,
        inParameters: List<IMassiveVariant>,
        outParameters: MutableList<IMassiveVariant>,
        errors: MutableList<String>
    ): Int
}
