interface IMassiveObject {
    val id: String
}

class MassiveObject(override val id: String) : IMassiveObject
