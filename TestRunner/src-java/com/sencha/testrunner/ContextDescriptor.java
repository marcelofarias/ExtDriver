package com.sencha.testrunner;

public class ContextDescriptor {

	private String absolutePath;
	private String relativeUrl;

	public ContextDescriptor(String absolutePath, String relativeUrl) {
		super();
		this.absolutePath = absolutePath;
		this.relativeUrl = relativeUrl;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public String getRelativeUrl() {
		return relativeUrl;
	}

	@Override
	public int hashCode() {
		return getRelativeUrl().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ContextDescriptor) {
 			((ContextDescriptor) obj).getRelativeUrl().equals(this.getRelativeUrl());
		}
		return false;
	}

}
