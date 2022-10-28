using System.Data.Common;

namespace LockControllerLib;

public interface IMassiveObject
{
    string Id { get; }
}

public class MassiveObject : IMassiveObject
{
    public MassiveObject(string id)
    {
        Id = id;
    }

    public string Id { get; }
}