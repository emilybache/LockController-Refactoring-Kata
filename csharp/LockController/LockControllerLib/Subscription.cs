namespace LockControllerLib;

public class Subscription
{
    private readonly IMassiveSession _connectionSession;

    public Subscription(IMassiveSession connectionSession)
    {
        _connectionSession = connectionSession;
    }

    public bool PublishingEnabled { get; set; }
    public int PublishingInterval { get; set; }

    public void Create()
    {
        throw new NotImplementedException("can't be called from a unit test");
    }

    public void RegisterCallback(Action<Subscription, MassiveDataChangedEvent> subscriptionDataChange)
    {
        throw new NotImplementedException("can't be called from a unit test");
    }

    public void Delete()
    {
        throw new NotImplementedException("can't be called from a unit test");
    }
}