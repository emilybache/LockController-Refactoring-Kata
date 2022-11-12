export interface IMassiveObject {
  readonly Id: string;
}

export class MassiveObject implements IMassiveObject {
  constructor(readonly Id: string) {
  }
}
