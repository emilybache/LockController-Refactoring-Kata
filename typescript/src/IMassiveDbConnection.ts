import { IMassiveLocation, MassiveLocation } from './IMassiveLocation';
import { IMassiveObject } from './IMassiveObject';
import { IMassiveSession } from './IMassiveSession';
import { IMassiveVariant } from './IMassiveVariant';

export interface IMassiveDbConnection {
  session: IMassiveSession;
  getPathTo(mObject: string): MassiveLocation;
  fullObject(path: IMassiveLocation): IMassiveObject;
  wangle(fullMObject: IMassiveObject): MassiveLocation;

  callMassiveMethod(
    path: IMassiveLocation,
    wangleLocation: IMassiveLocation,
    inParameters: IMassiveVariant[],
    outParameters: IMassiveVariant[],
    errors: string[]
  ): number;
}
