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
        IMassiveDbConnection connection = null; // TODO
        var controller = new LockController(connection);
        Assert.True(controller.IsSubscriptionActive());
        controller.LockObject(m.Id);
        Assert.True(controller.IsLocked(m.Id));
        controller.UnlockObject(m.Id);
        Assert.False(controller.IsLocked(m.Id));
        controller.Dispose();
        Assert.False(controller.IsSubscriptionActive());
    }
}