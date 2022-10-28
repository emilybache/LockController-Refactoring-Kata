using LockControllerLib;
using Xunit;

namespace LockControllerTest;

public class LockControllerTest
{
    [Fact]
    public void LockThenUnlock()
    {
        // TODO: finish writing this test
        var m = new MassiveObject("Id1");
        var controller = new LockController(null);
        controller.LockObject(m.Id);
        controller.UnlockObject(m.Id);
        controller.Dispose();
    }
}