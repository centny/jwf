package org.cny.jwf.netw.bean;

public class Con {
	public String cid;
	public String r;
	public String s;
	public byte t;

	public void setCid(String cid) {
		this.cid = cid;
	}

	public void setR(String r) {
		this.r = r;
	}

	public void setS(String s) {
		this.s = s;
	}

	public void setT(byte t) {
		this.t = t;
	}

	public static class Res {
		public int code;
		public Con res = new Con();

		public void setCode(int code) {
			this.code = code;
		}

		public void setRes(Con res) {
			this.res = res;
		}

	}
}