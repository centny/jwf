package org.cny.jwf.netw.r;

import org.cny.jwf.netw.r.Netw.ModException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NetwRunner implements NetwRunnable {
	private static Logger L = LoggerFactory.getLogger(NetwRunner.class);
	//
	protected CmdListener msgl;
	protected EvnListener evnl;
	protected boolean running;

	public NetwRunner(CmdListener msg, EvnListener evn) {
		this.msgl = msg;
		this.evnl = evn;
	}

	@Override
	public void run() {
		L.debug("starting running Netw");
		try {
			this.run_c();
		} catch (Throwable e) {
			L.error("running err:", e);
		}
		this.running = false;
		L.debug("Netw stopped");
	}

	public void stop() {
		this.running = false;
	}

	private void run_c() throws Exception {
		Netw nw = this.createNetw();
		this.evnl.onCon(this, nw);
		this.running = true;
		while (this.running) {
			try {
				this.msgl.onCmd(this, nw.readM());
			} catch (ModException e) {
				L.error(e.getMessage());
				continue;
			} catch (Throwable e) {
				this.evnl.onErr(this, e);
			}
		}
	}

	public void wcon() throws InterruptedException {
		while (true) {
			if (this.running) {
				break;
			} else {
				Thread.sleep(200);
			}
		}
	}

	protected abstract Netw createNetw() throws ModException;
}
