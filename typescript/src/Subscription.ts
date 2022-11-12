import { IMassiveSession } from './IMassiveSession';
import { MassiveDataChangedEvent } from './MassiveDataChangedEvent';

type SubscriptionDataChange = (subscription: Subscription, event: MassiveDataChangedEvent) => void;

export class Subscription {
  publishingEnabled!: boolean;
  publishingInterval!: number;

  constructor(private readonly _connectionSession: IMassiveSession) {
  }

  create() {
    throw new Error("can't be called from a unit test");
  }

  registerCallback(_subscriptionDataChange: SubscriptionDataChange) {
    throw new Error("can't be called from a unit test");
  }

  delete() {
    throw new Error("can't be called from a unit test");
  }
}
