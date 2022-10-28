using System.Xml;

namespace LockControllerLib;

public class LockController
{
    private readonly IMassiveDbConnection _connection;
    private readonly Subscription _subscription;
    private Dictionary<string, List<IMassiveVariant>> _lockedItems = new();

    public LockController(IMassiveDbConnection connection)
    {
        _connection = connection;
        
        // register a listener (Subscription) for changes in the Massive Database
        _subscription = new Subscription(_connection.Session);
        _subscription.PublishingEnabled = true;
        _subscription.PublishingInterval = 500;
        _subscription.RegisterCallback(SubscriptionDataChange);
        _subscription.Create();
    }

    /**
     * This is called by the Massive Database when data is changed,
     * because we registered it as a callback earlier.
     */
    private void SubscriptionDataChange(Subscription subscription, MassiveDataChangedEvent e)
    {
        var mObjectId = e.MId;

        if (e.Type == "Lock")
        {
            this.LockObject(mObjectId);
        }
        else if (e.Type == "Unlock")
        {
            this.UnlockObject(mObjectId);
        }
    }

    public bool LockObject(string mObjectId)
    {
        var path = _connection.getPathTo(mObjectId);
        var fullMObject = _connection.FullObject(path);
        List<IMassiveVariant> inParameters = new List<IMassiveVariant>();
        List<IMassiveVariant> outParameters = new List<IMassiveVariant>();
        List<string> errors = new List<String>();
        var statusCode = _connection.CallMassiveMethod(path, 
                _connection.Wangle(fullMObject),
                inParameters, outParameters, errors);

        if (errors.Count == 0 && statusCode == 0)
        {
            _lockedItems.Add(mObjectId, outParameters);
            return true;
        }
        else
        {
            // TODO: report errors
            return false;
        }
    }

    public bool UnlockObject(string mObjectId)
    {
        var path = _connection.getPathTo(mObjectId);
        var fullMObject = _connection.FullObject(path);
        List<IMassiveVariant> inParameters = new List<IMassiveVariant>();
        List<IMassiveVariant> outParameters = new List<IMassiveVariant>();
        List<string> errors = new List<String>();
        var statusCode = _connection.CallMassiveMethod(path, 
            _connection.Wangle(fullMObject),
            inParameters, outParameters, errors);
        
        if (errors.Count == 0 && statusCode == 0 && _lockedItems.ContainsKey(mObjectId))
        {
            _lockedItems.Remove(mObjectId);
            return true;
        }
        else
        {
            // TODO: report errors
            return false;
        }
    }

    /**
     * When we dispose this object we should ensure nothing is left locked
     * and that we remove the Subscription from the Massive Database
     */
    public void Dispose()
    {
        if (_lockedItems.Count > 0)
        {
            foreach (var item in _lockedItems)
            {
                UnlockObject(item.Key);
            }
        }

        _subscription?.Delete();
    }
}