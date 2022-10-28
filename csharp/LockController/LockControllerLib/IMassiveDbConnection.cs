namespace LockControllerLib;

public interface IMassiveDbConnection
{
    IMassiveSession Session { get; set; }
    MassiveLocation getPathTo(string mObject);
    IMassiveObject FullObject(IMassiveLocation path);
    IMassiveLocation Wangle(IMassiveObject fullMObject);

    int CallMassiveMethod(
                IMassiveLocation path, 
                IMassiveLocation wangleLocation, 
                List<IMassiveVariant> inParameters,
                List<IMassiveVariant> outParameters, 
                List<string> errors);
}