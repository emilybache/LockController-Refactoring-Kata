import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.MethodOrderer.Random
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode

@TestMethodOrder(Random::class)
@Execution(ExecutionMode.CONCURRENT)
class LockControllerShould {
    val dbConnection = object : IMassiveDbConnection {
        override var session: IMassiveSession
            get() = TODO("Not yet implemented")
            set(value) {}

        override fun getPathTo(mObject: String): MassiveLocation {
            TODO("Not yet implemented")
        }

        override fun fullObject(path: IMassiveLocation): IMassiveObject {
            TODO("Not yet implemented")
        }

        override fun wangle(fullMObject: IMassiveObject): MassiveLocation {
            TODO("Not yet implemented")
        }

        override fun callMassiveMethod(
            path: MassiveLocation,
            wangleLocation: MassiveLocation,
            inParameters: List<IMassiveVariant>,
            outParameters: MutableList<IMassiveVariant>,
            errors: MutableList<String>
        ): Int {
            TODO("Not yet implemented")
        }
    }

    @Test
    public fun `lock then unlock`() {
        // TODO: finish writing this test
        val m = MassiveObject("Id1");
        val controller = LockController(dbConnection);

        controller.lockObject(m.id);
        controller.unlockObject(m.id);
        controller.dispose();
        true shouldBe false
    }
}
