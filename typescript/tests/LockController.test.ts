import { IMassiveDbConnection } from '../src/IMassiveDbConnection';
import { MassiveObject } from '../src/IMassiveObject';
import { LockController } from '../src/LockController';

describe("LockController", () => {
  it("locks and then unlocks", () => {
    // TODO: finish writing this test
    const m = new MassiveObject("Id1");
    const connection: IMassiveDbConnection = null as any; // TODO
    const controller = new LockController(connection);
    expect(controller.isSubscriptionActive()).toBeTruthy();
    controller.lockObject(m.Id);
    expect(controller.isLocked(m.Id)).toBeTruthy();
    controller.unlockObject(m.Id);
    expect(controller.isLocked(m.Id)).toBeFalsy();
    controller.dispose();
    expect(controller.isSubscriptionActive()).toBeFalsy();
  })
})
